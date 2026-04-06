package com.programacion4.unidad3ej4.feature.producto.controllers.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoPatchRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoPatchService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/productos")
@AllArgsConstructor 

public class ProductoPatchController {
    private final IProductoPatchService productoPatchService;

     @PatchMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> actualizarParcialmente(@Valid @PathVariable Long id,
             @RequestBody ProductoPatchRequestDto dto) {
        return ResponseEntity.ok(productoPatchService.actualizarParcialmente(id, dto));
    }
}
