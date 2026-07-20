package spring_boot_web_hexagon_arch.product.application.query.getAll;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring_boot_web_hexagon_arch.product.domain.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAllProductResponse {

    private List<Product> products;

}
