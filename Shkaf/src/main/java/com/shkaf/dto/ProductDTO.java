package com.shkaf.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal price;
}
