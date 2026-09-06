package pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(Long id) {
        super("Producto con id " + id + " no fue encontrado");
    }
}
