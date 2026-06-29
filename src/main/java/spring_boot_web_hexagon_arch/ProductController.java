package spring_boot_web_hexagon_arch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    public List<Product> products;

    public ProductController() {
        this.products = new ArrayList<>();

        products.add(Product.builder().id(1L).name("Product 1").description("Description 1").price(100.0).image("image 1").build());
        products.add(Product.builder().id(2L).name("Product 2").description("Description 2").price(200.0).image("image 2").build());
        //products.add(Product.builder().id(3L).name("Product 3").description("Description 3").price(300.0).image("image 3").build());

    }

    //With ResponseEntity objects we make sure that they last what the http request last, no more
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) String pageSize) {
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productOptional.get());
    }

    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(product.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product productSelected = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productSelected.setName(product.getName());
        productSelected.setDescription(product.getDescription());
        productSelected.setPrice(product.getPrice());
        productSelected.setImage(product.getImage());

        return ResponseEntity.ok(productSelected);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        products.removeIf(product -> product.getId().equals(id));

        return ResponseEntity.noContent().build();
    }

    //PATH method is to update certains parts of a product, not the whole thing like UPDATE
}
