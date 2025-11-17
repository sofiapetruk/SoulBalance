package br.com.fiap.SoulBalance.entity;

import br.com.fiap.SoulBalance.enun.TipoAtividade;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_ATIVIDADE")
@SequenceGenerator(name = "atividades", sequenceName = "SQ_TB_SOULBALANCE_ATIVIDADES", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atividade_id")
    private Long atividadeId;

    @Column(name = "tipo_atividade")
    private TipoAtividade tipoAtividade;

    @Column(name = "descricao")
    private String descricao;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    @Column(name = "duracao_minutos_atividade")   //criar um método nessa classe que faz a duração do inicio e fim da atividade
    private Long duracaoMinutosAtividade;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;
}
