package spring_boot_web_hexagon_arch.product.infrastructure.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import spring_boot_web_hexagon_arch.product.infrastructure.api.dto.CreateProductDto;
import spring_boot_web_hexagon_arch.product.infrastructure.api.dto.ProductDto;
import spring_boot_web_hexagon_arch.product.infrastructure.api.dto.UpdateProductDto;
import spring_boot_web_hexagon_arch.product.infrastructure.api.mapper.ProductMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    //With ResponseEntity objects we make sure that they last what the http request last, no more
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProduct(@RequestParam(required = false) String pageSize) {

        log.info("Getting all products");

        GetAllProductResponse response = mediator.dispatch(new GetAllProductRequest());

        List<ProductDto> productDtos = response.getProducts().stream().map(productMapper::mapToProduct).toList();

        log.info("Found {} products", productDtos.size());

        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        log.info("Getting product with id {}", id);

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProduct(response.getProduct());

        log.info("Found product with id {}", id);

        return ResponseEntity.ok(productDto);
    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto) {

        log.info("Saving product with id {}", productDto.getId());

        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Saved product with id {}", productDto.getId());

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto productDto) {

        log.info("Updating product with id {}", productDto.getId());

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Updated product with id {}", productDto.getId());

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        log.info("Deleting product with id {}", id);

        mediator.dispatchAsync(new DeleteProductRequest(id));

        log.info("Deleted product with id {}", id);

        return ResponseEntity.noContent().build();
    }

    //PATH method is to update certains parts of a product, not the whole thing like UPDATE
}
