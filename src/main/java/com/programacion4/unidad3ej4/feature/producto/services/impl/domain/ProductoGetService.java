package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import java.util.List;
import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.config.exceptions.NotFoundException;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ProductoGetService implements IProductoGetService{
    private final IProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> obtenerProductos() {
        List<Producto> productos = (List<Producto>) productoRepository.findByEstaEliminadoFalse();
        return productos.stream()
                .map(ProductoMapper::toResponseDto)
                .toList();
    }

    @Override
    public ProductoResponseDto obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findByIdAndEstaEliminadoFalse(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        return ProductoMapper.toResponseDto(producto);
    }
}