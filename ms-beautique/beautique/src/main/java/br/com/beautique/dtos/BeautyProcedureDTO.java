package br.com.beautique.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeautyProcedureDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}
