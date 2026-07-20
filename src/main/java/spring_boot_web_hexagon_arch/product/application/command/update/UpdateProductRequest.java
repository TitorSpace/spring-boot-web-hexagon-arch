package spring_boot_web_hexagon_arch.product.application.command.update;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import spring_boot_web_hexagon_arch.common.mediator.Request;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;

}
