package pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.service;

import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto.ProductoRequestDTO;
import pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> listar(String categoria);
    ProductoResponseDTO obtenerPorId(Long id);
    ProductoResponseDTO crear(ProductoRequestDTO productoRequestDTO);
    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO productoRequestDTO);
    void eliminar(Long id);
}