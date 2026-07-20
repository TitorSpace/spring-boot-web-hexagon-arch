package spring_boot_web_hexagon_arch.product.application.command.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_web_hexagon_arch.common.mediator.RequestHandler;
import spring_boot_web_hexagon_arch.product.domain.port.ProductRepository;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        productRepository.deleteById(request.getId());
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
