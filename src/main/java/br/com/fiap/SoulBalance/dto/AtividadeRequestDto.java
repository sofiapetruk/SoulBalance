package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.enun.TipoAtividade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AtividadeRequestDto {

    private TipoAtividade tipoAtividade;

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fim;

    @NotBlank
    private String descricao;

    @Email
    @NotBlank
    private String email;

}
