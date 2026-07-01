package spring_boot_web_hexagon_arch.product.infrastructure.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring_boot_web_hexagon_arch.product.domain.Product;
import spring_boot_web_hexagon_arch.product.domain.ProductRepository;
import spring_boot_web_hexagon_arch.product.infrastructure.database.entity.ProductEntity;
import spring_boot_web_hexagon_arch.product.infrastructure.database.mapper.ProductEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private List<ProductEntity> products = new ArrayList<>();

    private final ProductEntityMapper productEntityMapper;


    @Override
    public void upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.mapToProductEntity(product);
        products.add(productEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(productEntityMapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        return products.stream()
                .map(productEntityMapper::mapToProduct)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
