package pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.service.impl;

import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto.ProductoRequestDTO;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto.ProductoResponseDTO;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.entity.Producto;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.exception.ProductoNoEncontradoException;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.mapper.ProductoMapper;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.repository.ProductoRepository;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    // Inyección por constructor: permite marcar los campos como 'final',
    // hace explícitas las dependencias y facilita las pruebas unitarias
    // (no se necesita un contenedor de Spring para instanciar la clase en un test).
    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoResponseDTO> listar(String categoria) {
        List<Producto> productos = (categoria != null && !categoria.isBlank())
                ? productoRepository.findByCategoria(categoria)
                : productoRepository.findAll();
        return productoMapper.toResponseDTOList(productos);
    }

    @Override
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = buscarPorIdOLanzarExcepcion(id);
        return productoMapper.toResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO productoRequestDTO) {
        Producto producto = productoMapper.toEntity(productoRequestDTO);
        Producto guardado = productoRepository.save(producto);
        return productoMapper.toResponseDTO(guardado);
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO productoRequestDTO) {
        Producto producto = buscarPorIdOLanzarExcepcion(id);
        productoMapper.actualizarEntidadDesdeDTO(producto, productoRequestDTO);
        Producto actualizado = productoRepository.save(producto);
        return productoMapper.toResponseDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = buscarPorIdOLanzarExcepcion(id);
        productoRepository.delete(producto);
    }

    private Producto buscarPorIdOLanzarExcepcion(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }
}