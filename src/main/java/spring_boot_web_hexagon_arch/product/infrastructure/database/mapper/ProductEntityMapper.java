package spring_boot_web_hexagon_arch.product.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import spring_boot_web_hexagon_arch.product.domain.Product;
import spring_boot_web_hexagon_arch.product.infrastructure.database.entity.ProductEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    ProductEntity mapToProductEntity(Product product);

    Product mapToProduct(ProductEntity productEntity);

}
