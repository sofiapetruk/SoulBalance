package br.com.fiap.SoulBalance.entity;

import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_DADOS_SENSOR")
@SequenceGenerator(name = "dados", sequenceName = "SQ_TB_SOULBALANCE_DADOS_SENSOR", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DadosSensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dado_id")
    private Long dadoId;

    @Column(name = "tipo_dado")
    @Enumerated(EnumType.STRING)
    private TipoDadoSensor tipoDado;

    @Column(name = "valor")
    private int valor;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario")
    private UsuarioEntity usuario;
}
