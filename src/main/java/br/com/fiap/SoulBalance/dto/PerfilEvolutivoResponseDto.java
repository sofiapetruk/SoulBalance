package br.com.fiap.SoulBalance.dto;

import br.com.fiap.SoulBalance.entity.PerfilEvolutivoEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PerfilEvolutivoResponseDto {

    private Long perfilId;
    private double ptoAutocuidado;
    private double ptoResiliencia;
    private LocalDateTime dataLastUpdate;
    private Long usuario;
    private String statusCurto;
    private String jsonCompetencias;

    public static PerfilEvolutivoResponseDto from(PerfilEvolutivoEntity perfilEvolutivo) {
        return PerfilEvolutivoResponseDto
                .builder()
                .perfilId(perfilEvolutivo.getPerfilId())
                .ptoAutocuidado(perfilEvolutivo.getPtoAutocuidado())
                .ptoResiliencia(perfilEvolutivo.getPtoResiliencia())
                .dataLastUpdate(perfilEvolutivo.getDataLastUpdate())
                .usuario(perfilEvolutivo.getUsuario().getUserId())
                .statusCurto(perfilEvolutivo.getStatusCurto())
                .jsonCompetencias(perfilEvolutivo.getJsonCompetencias())
                .build();
    }

}
