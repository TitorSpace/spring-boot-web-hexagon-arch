package spring_boot_web_hexagon_arch.product.application.query.getById;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring_boot_web_hexagon_arch.product.domain.entity.Product;

@Data
@AllArgsConstructor
public class GetProductByIdResponse {

    private Product product;

}
