package spring_boot_web_hexagon_arch.product.application.command.update;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_web_hexagon_arch.common.mediator.RequestHandler;
import spring_boot_web_hexagon_arch.product.domain.Product;
import spring_boot_web_hexagon_arch.product.domain.ProductRepository;

@Service
@RequiredArgsConstructor
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(UpdateProductRequest request) {

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();

        productRepository.upsert(product);
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
