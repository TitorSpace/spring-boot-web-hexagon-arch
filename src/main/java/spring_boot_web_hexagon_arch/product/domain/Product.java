package spring_boot_web_hexagon_arch.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

}
