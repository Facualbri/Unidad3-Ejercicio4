package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoCreateService;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.commons.IProductoExistByNameService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductoCreateService implements IProductoCreateService {


    private final IProductoExistByNameService productoExistByNameService;
    private final IProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    

    @Override
    public ProductoResponseDto create(ProductoCreateRequestDto dto) {

        // 1. Validar si ya existe el producto
        if (productoExistByNameService.existByName(dto.getNombre())) {
            throw new BadRequestException("El nombre del producto ya existe");
        }

        // capitalizo antes de guardar
        dto.setNombre(capitalizar(dto.getNombre()));
        dto.setDescripcion(capitalizar(dto.getDescripcion()));

        // 2. Mapear DTO → Entity
        Producto productoAGuardar = ProductoMapper.toEntity(dto);

        // 3. Guardar en BD
        Producto productoGuardado = productoRepository.save(productoAGuardar);

        // 4. Mapear Entity → Response DTO
        return productoMapper.toResponseDto(productoGuardado);
    }


    // funcion de capitalizacion para el nombre y descripcion del producto  
     private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        texto = texto.toLowerCase();
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}