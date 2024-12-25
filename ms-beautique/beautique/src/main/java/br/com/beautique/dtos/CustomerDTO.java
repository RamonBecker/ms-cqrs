package br.com.beautique.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {


    private String name;
    private String email;
    private String phone;
}
