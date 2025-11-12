package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DadosSensorRequestDto {

    @NotNull
    private TipoDadoSensor tipoDadoSensor;

    @NotNull
    private int valor;
}
