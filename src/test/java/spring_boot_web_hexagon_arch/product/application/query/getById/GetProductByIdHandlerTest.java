package spring_boot_web_hexagon_arch.product.application.query.getById;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring_boot_web_hexagon_arch.product.domain.entity.Product;
import spring_boot_web_hexagon_arch.product.domain.exception.ProductNotFoundException;
import spring_boot_web_hexagon_arch.product.domain.port.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
class GetProductByIdHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductByIdHandler handler;

    @Test
    void shouldReturnProductWhenFound() {
        long productId = 1l;

        Product mockProduct = Product.builder().id(productId).build();
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        GetProductByIdResponse response = handler.handle(request);

        assertNotNull(response);
        assertEquals(mockProduct, response.getProduct());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        long productId = 1L;
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> handler.handle(request));
    }

};