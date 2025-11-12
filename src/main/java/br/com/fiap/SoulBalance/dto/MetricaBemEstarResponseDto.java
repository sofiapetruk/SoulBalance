package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.MetricaBemEstarEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MetricaBemEstarResponseDto {

    private Long metricaId;
    private double fadigaScore;
    private double estresseScore;
    private double recuperacaoScore;
    private LocalDateTime dataCriacao;
    private Long usuario;

    public static MetricaBemEstarResponseDto from(MetricaBemEstarEntity metricaBemEstar) {
        return MetricaBemEstarResponseDto
                .builder()
                .metricaId(metricaBemEstar.getMetricaId())
                .fadigaScore(metricaBemEstar.getFadigaScore())
                .estresseScore(metricaBemEstar.getEstresseScore())
                .recuperacaoScore(metricaBemEstar.getRecuperacaoScore())
                .dataCriacao(metricaBemEstar.getDataCriacao())
                .usuario(metricaBemEstar.getUsuario().getUserId())
                .build();
    }
}
