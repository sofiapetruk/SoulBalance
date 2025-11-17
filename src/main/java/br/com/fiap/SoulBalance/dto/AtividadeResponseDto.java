package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoAtividade;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeResponseDto {
    private Long atividadeId;

    private TipoAtividade tipoAtividade;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    private Long duracaoMinutosAtividade;

    private Long usuarioId;

    public static AtividadeResponseDto from(AtividadeEntity atividade) {
        return AtividadeResponseDto
                .builder()
                .atividadeId(atividade.getAtividadeId())
                .inicio(atividade.getInicio())
                .fim(atividade.getFim())
                .duracaoMinutosAtividade(atividade.getDuracaoMinutosAtividade())
                .usuarioId(atividade.getUsuario().getUserId())
                .build();
    }

}
