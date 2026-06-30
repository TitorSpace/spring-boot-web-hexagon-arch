package spring_boot_web_hexagon_arch.product.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import spring_boot_web_hexagon_arch.common.mediator.Request;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

}
