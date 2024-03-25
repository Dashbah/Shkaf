package com.shkaf.controller;

import com.shkaf.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // @Secured("authentication.principal.name == #userName or hasAnyAuthority('ADMIN', 'MANAGER')")

    @Secured("#username == authentication.principal.username")
    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@RequestParam String username) {
        // Реализация получения списка заказов пользователя
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<String> createOrder(
            @RequestBody OrderDTO orderDTO) {
        // Реализация создания нового заказа
        // add order to authentication.principal.username
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PreAuthorize("authentication.principal.username == #userName or hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(
            @PathVariable Long orderId,
            @RequestParam String userName) {
        // Реализация получения информации о конкретном заказе
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PreAuthorize("authentication.principal.username == #userName or hasAnyAuthority('ADMIN', 'MANAGER')")
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(
            @PathVariable Long orderId,
            @RequestParam String userName) {
        // Реализация отмены заказа
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
