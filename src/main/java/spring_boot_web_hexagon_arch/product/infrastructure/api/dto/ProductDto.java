package spring_boot_web_hexagon_arch.product.infrastructure.api.dto;

import lombok.Data;


@Data
//This is the information that arrive to our input of APIRest
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

}
