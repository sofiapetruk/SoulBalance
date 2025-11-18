package br.com.fiap.SoulBalance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_RECOMENDACAO")
@SequenceGenerator(name = "recomendacoes", sequenceName = "SQ_TB_SOULBALANCE_RECOMENDACAO", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RecomendacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recomendacao_id")
    private Long recomendacaoId;

    @Column(name = "sugestao")
    private String sugestao; //isso vou tem que fazer com o chagpt a descrição

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;

}
