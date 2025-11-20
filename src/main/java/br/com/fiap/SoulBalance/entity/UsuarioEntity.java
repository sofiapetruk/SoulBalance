package br.com.fiap.SoulBalance.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SOULBALANCE_USUARIO")
@SequenceGenerator(name = "usuario", sequenceName = "SQ_TB_SOULBALANCE_USUARIO", allocationSize = 1)

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

}
