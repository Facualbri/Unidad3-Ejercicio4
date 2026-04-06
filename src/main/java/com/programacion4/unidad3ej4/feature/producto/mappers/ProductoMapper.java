package com.programacion4.unidad3ej4.feature.producto.mappers;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoPatchRequestDto;

public class ProductoMapper {
    
    public static Producto toEntity(ProductoCreateRequestDto dto, Categoria categoria) {
        return Producto.builder()
                .nombre(dto.getNombre())
                .codigo(dto.getCodigo())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .categoria(categoria)
                .build();
    }

    public static ProductoResponseDto toResponseDto(Producto producto) {
        return ProductoResponseDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .codigo(producto.getCodigo())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(CategoriaMapper.toDto(producto.getCategoria()))
                .build();
    }

   //PUT
    public static void updateEntityFromDto(ProductoUpdateRequestDto dto, Producto producto, Categoria categoria) {
        producto.setNombre(dto.getNombre());
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);
    }

    //PATH
    public static void patchEntityFromDto(ProductoPatchRequestDto dto, Producto producto, Categoria categoria) {
        if (dto.getPrecio() != null) {
            producto.setPrecio(dto.getPrecio());
        }
        if (dto.getStock() != null) {
            producto.setStock(dto.getStock());
        }
        if (dto.getCategoriaId() != null) {
            producto.setCategoria(categoria);
        }
    }
}

