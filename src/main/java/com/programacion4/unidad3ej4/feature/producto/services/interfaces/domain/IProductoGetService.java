package com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import java.util.List;

public interface IProductoGetService {
    List<ProductoResponseDto> obtenerProductos();
    ProductoResponseDto obtenerProductoPorId(Long id);
}
