package spring_boot_web_hexagon_arch.product.infrastructure.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_web_hexagon_arch.common.mediator.Mediator;
import spring_boot_web_hexagon_arch.product.application.CreateProductRequest;
import spring_boot_web_hexagon_arch.product.infrastructure.api.dto.ProductDto;
import spring_boot_web_hexagon_arch.product.infrastructure.api.mapper.ProductMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    //With ResponseEntity objects we make sure that they last what the http request last, no more
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProduct(@RequestParam(required = false) String pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody ProductDto productDto) {

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDto productDto) {

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        products.removeIf(product -> product.getId().equals(id));

        return ResponseEntity.noContent().build();
    }

    //PATH method is to update certains parts of a product, not the whole thing like UPDATE
}
