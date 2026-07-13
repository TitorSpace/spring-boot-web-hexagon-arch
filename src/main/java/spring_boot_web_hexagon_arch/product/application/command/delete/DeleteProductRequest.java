package spring_boot_web_hexagon_arch.product.application.command.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring_boot_web_hexagon_arch.common.mediator.Request;

@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {

    private Long id;

}
