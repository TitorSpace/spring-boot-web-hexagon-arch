package spring_boot_web_hexagon_arch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

}
