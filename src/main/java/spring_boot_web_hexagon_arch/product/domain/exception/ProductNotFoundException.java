package spring_boot_web_hexagon_arch.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("The product with id " + id + "was not found");
    }
}
