package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;
import com.programacion4.unidad3ej4.config.exceptions.NotFoundException;
import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoPatchRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoPatchService;
import jakarta.transaction.Transactional;
import com.programacion4.unidad3ej4.feature.producto.services.impl.domain.ProductoPatchService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ProductoPatchService implements IProductoPatchService {
    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public ProductoResponseDto actualizarParcialmente(Long id, ProductoPatchRequestDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        Categoria categoria = null;

        if (dto.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        }

        ProductoMapper.patchEntityFromDto(dto, producto, categoria);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDto(guardado);
    }
}