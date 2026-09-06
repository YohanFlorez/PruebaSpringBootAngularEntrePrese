package pruebatecnicaSpringBoot.pruebatecnicaSpringBoot.dto;

import java.math.BigDecimal;

public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private String categoria;

    public ProductoResponseDTO() {}

    public ProductoResponseDTO(Long id, String nombre, BigDecimal precio, Integer stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}