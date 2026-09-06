package pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.mapper;

import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto.ProductoRequestDTO;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto.ProductoResponseDTO;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.entity.Producto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

    public void actualizarEntidadDesdeDTO(Producto producto, ProductoRequestDTO dto) {
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
    }

    public ProductoResponseDTO toResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }

    public List<ProductoResponseDTO> toResponseDTOList(List<Producto> productos) {
        return productos.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}