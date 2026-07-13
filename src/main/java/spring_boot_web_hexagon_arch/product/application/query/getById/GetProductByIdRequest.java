package spring_boot_web_hexagon_arch.product.application.query.getById;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring_boot_web_hexagon_arch.common.mediator.Request;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {

    private Long id;

}
