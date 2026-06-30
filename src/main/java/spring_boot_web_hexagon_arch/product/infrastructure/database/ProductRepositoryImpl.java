package spring_boot_web_hexagon_arch.product.infrastructure.database;

import org.springframework.stereotype.Repository;
import spring_boot_web_hexagon_arch.product.domain.Product;
import spring_boot_web_hexagon_arch.product.domain.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products;

    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();

    }

    @Override
    public void upsert(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
