package com.programacion4.unidad3ej4.feature.producto.controllers.post;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/productos")
@AllArgsConstructor

public class ProductoGetController {
    private final IProductoGetService productoGetService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> obtenerProductos(){
        return ResponseEntity.ok(productoGetService.obtenerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto>obtenerProductoPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(productoGetService.obtenerProductoPorId(id));
                       
    }
}
