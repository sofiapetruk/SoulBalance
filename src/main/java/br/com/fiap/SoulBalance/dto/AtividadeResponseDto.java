package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import br.com.fiap.SoulBalance.enun.TipoAtividade;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeResponseDto {
    private Long atividadeId;

    private TipoAtividade tipoAtividade;

    private String descricao;

    private Long duracaoMinutosAtividade;

    private Long usuarioId;

    public static AtividadeResponseDto from(AtividadeEntity atividade) {
        return AtividadeResponseDto
                .builder()
                .atividadeId(atividade.getAtividadeId())
                .tipoAtividade(atividade.getTipoAtividade())
                .descricao(atividade.getDescricao())
                .duracaoMinutosAtividade(atividade.getDuracaoMinutosAtividade())
                .usuarioId(atividade.getUsuario().getId())
                .build();
    }

}
