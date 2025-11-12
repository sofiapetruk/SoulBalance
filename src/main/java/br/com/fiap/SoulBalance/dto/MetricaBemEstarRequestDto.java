package br.com.fiap.SoulBalance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MetricaBemEstarRequestDto {

    @NotNull
    private double fadigaScore;

    @NotNull
    private double estresseScore;

    @NotNull
    private double recuperacaoScore;

    @NotNull
    private LocalDateTime dataCriacao;

}
