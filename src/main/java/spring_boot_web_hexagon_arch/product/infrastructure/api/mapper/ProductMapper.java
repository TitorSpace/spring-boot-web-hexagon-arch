package spring_boot_web_hexagon_arch.product.infrastructure.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import spring_boot_web_hexagon_arch.product.application.CreateProductRequest;
import spring_boot_web_hexagon_arch.product.infrastructure.api.dto.ProductDto;

//Mapper has diff attrss. ComponentModel now everytime that starts is going the implement in runtime the class of this interface
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(ProductDto productDto);

}
