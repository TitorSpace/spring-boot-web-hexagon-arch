package spring_boot_web_hexagon_arch.product.application.command.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_web_hexagon_arch.common.mediator.RequestHandler;
import spring_boot_web_hexagon_arch.common.util.FileUtils;
import spring_boot_web_hexagon_arch.product.domain.entity.Product;
import spring_boot_web_hexagon_arch.product.domain.port.ProductRepository;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    @Override
    public Void handle(CreateProductRequest request) {

        String uniqueFilename = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFilename)
                .build();

        productRepository.upsert(product);
        return null;
    }


    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
