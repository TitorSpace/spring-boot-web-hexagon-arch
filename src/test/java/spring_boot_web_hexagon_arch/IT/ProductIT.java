package spring_boot_web_hexagon_arch.IT;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import spring_boot_web_hexagon_arch.product.domain.entity.Product;
import spring_boot_web_hexagon_arch.product.domain.port.ProductRepository;
import spring_boot_web_hexagon_arch.product.infrastructure.api.dto.ProductDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@AutoconfigureMockMvc
public class ProductIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.upsert(
                Product.builder().id(1L).name("Product 1").description("Description 1").price(100.0).build()
        );
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteById(1L);
    }

    @Test
    public void getProductByIdExists() {
        // Fixed .Class to .class
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(
                "/api/v1/products/1",
                ProductDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product 1", response.getBody().getName());
        assertEquals("Description 1", response.getBody().getDescription());
        assertEquals(100.0, response.getBody().getPrice(), 0.001); // Added delta for double comparison
    }

    @Test
    public void saveProduct() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "image".getBytes());
        mockMvc.perform(
                multipart(HttpMethod.POST, "/api/v1/products")
                        .file(file)
                        .param("id", "2")
                        .param("name", "Name 2")
                        .param("description", "Description 2")
                        .param("price", "150.0")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isCreated());
    }
}