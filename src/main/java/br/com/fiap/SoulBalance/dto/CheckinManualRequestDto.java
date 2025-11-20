package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.enun.ValorEnun;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CheckinManualRequestDto {

    @NotNull
    private ValorEnun humor;

    @NotNull
    private ValorEnun energia;

    @NotNull
    private ValorEnun foco;

    @Email
    @NotBlank
    private String email;

}
