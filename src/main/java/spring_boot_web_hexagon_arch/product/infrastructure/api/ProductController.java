package spring_boot_web_hexagon_arch.product.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_web_hexagon_arch.common.mediator.Mediator;
import spring_boot_web_hexagon_arch.product.application.command.create.CreateProductRequest;
import spring_boot_web_hexagon_arch.product.application.command.delete.DeleteProductRequest;
import spring_boot_web_hexagon_arch.product.application.command.update.UpdateProductRequest;
import spring_boot_web_hexagon_arch.product.application.query.getAll.GetAllProductRequest;
import spring_boot_web_hexagon_arch.product.application.query.getAll.GetAllProductResponse;
import spring_boot_web_hexagon_arch.product.application.query.getById.GetProductByIdRequest;
import spring_boot_web_hexagon_arch.product.application.query.getById.GetProductByIdResponse;
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


        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());

        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProduct).toList();

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProduct(response.getProduct());

        return ResponseEntity.ok(productDto);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductDto productDto) {

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductDto productDto) {

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);

        mediator.dispatch(request);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        products.removeIf(product -> product.getId().equals(id));

        mediator.dispatch(new DeleteProductRequest(id));

        return ResponseEntity.noContent().build();
    }

    //PATH method is to update certains parts of a product, not the whole thing like UPDATE
}
