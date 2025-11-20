package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UsuarioResponseDto {

    private Long userId;
    private String nome;
    private String email;
    private String senha;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;


    public static UsuarioResponseDto from(UsuarioEntity usuario) {
        return UsuarioResponseDto
                .builder()
                .userId(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .dataCriacao(usuario.getDataCriacao())
                .build();
    }
}
