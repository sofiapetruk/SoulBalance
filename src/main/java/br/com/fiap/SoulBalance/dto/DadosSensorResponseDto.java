package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.DadosSensorEntity;
import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DadosSensorResponseDto {

    private Long dadoId;
    private TipoDadoSensor tipoDado;
    private int valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime time;

    private Long usuarioId;

    public static DadosSensorResponseDto from(DadosSensorEntity dadosSensor) {
        if (dadosSensor == null) {
            return null;
        }

        return DadosSensorResponseDto
                .builder()
                .dadoId(dadosSensor.getDadoId())
                .tipoDado(dadosSensor.getTipoDado())
                .valor(dadosSensor.getValor())
                .time(dadosSensor.getTime())
                .usuarioId(dadosSensor.getUsuario().getId())
                .build();
    }


}
