package spring_boot_web_hexagon_arch.product.infrastructure.database.entity;

import lombok.Data;

@Data
public class ProductEntity {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

}
