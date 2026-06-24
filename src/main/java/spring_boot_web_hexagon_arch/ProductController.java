package spring_boot_web_hexagon_arch;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("")
    public String getAllProduct(@RequestParam String pageSize) {
        return "Product controller ".concat(pageSize);
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Product controller ".concat(id.toString());
    }
}
