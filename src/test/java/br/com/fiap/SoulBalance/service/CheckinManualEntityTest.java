package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.ValorEnun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CheckinManualEntityTest {

    private CheckinManualEntity checkin;
    private UsuarioEntity mockUsuario;
    private final LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        mockUsuario = UsuarioEntity.builder()
                .userId(1L)
                .email("teste@fiap.com.br")
                .nome("Test User")
                .senha("encodedPass")
                .build();
    }

    @Test
    @DisplayName("Teste de Construtor sem Argumentos (@NoArgsConstructor)")
    void testNoArgsConstructor() {
        CheckinManualEntity checkinNoArgs = new CheckinManualEntity();
        assertNotNull(checkinNoArgs, "O construtor sem argumentos não deve retornar nulo.");
        assertNull(checkinNoArgs.getChekinId(), "O ID deve ser nulo após a construção sem argumentos.");
    }

    @Test
    @DisplayName("Teste de Construtor com Todos os Argumentos (@AllArgsConstructor)")
    void testAllArgsConstructor() {
        checkin = new CheckinManualEntity(
                2L,
                ValorEnun.ALTO,
                ValorEnun.MEDIO,
                ValorEnun.BAIXO,
                now,
                mockUsuario
        );

        assertEquals(2L, checkin.getChekinId());
        assertEquals(ValorEnun.ALTO, checkin.getHumor());
        assertEquals(ValorEnun.MEDIO, checkin.getEnergia());
        assertEquals(ValorEnun.BAIXO, checkin.getFoco());
        assertEquals(now, checkin.getTime());
        assertEquals(mockUsuario, checkin.getUsuario());
    }

    @Test
    @DisplayName("Teste do Padrão Builder")
    void testBuilderPattern() {
        checkin = CheckinManualEntity.builder()
                .chekinId(3L)
                .humor(ValorEnun.MUITO_ALTO)
                .energia(ValorEnun.MUITO_BAIXO)
                .foco(ValorEnun.ALTO)
                .time(now)
                .usuario(mockUsuario)
                .build();

        assertNotNull(checkin);
        assertEquals(3L, checkin.getChekinId());
        assertEquals(ValorEnun.MUITO_ALTO, checkin.getHumor());
        assertEquals(ValorEnun.MUITO_BAIXO, checkin.getEnergia());
        assertEquals(ValorEnun.ALTO, checkin.getFoco());
        assertEquals(now, checkin.getTime());
        assertEquals(mockUsuario, checkin.getUsuario());
    }

    @Test
    @DisplayName("Teste de Getters e Setters (@Getter e @Setter)")
    void testGettersAndSetters() {
        checkin = new CheckinManualEntity();

        checkin.setChekinId(10L);
        checkin.setHumor(ValorEnun.BAIXO);
        checkin.setEnergia(ValorEnun.MUITO_BAIXO);
        checkin.setFoco(ValorEnun.MEDIO);
        checkin.setTime(now);
        checkin.setUsuario(mockUsuario);

        assertEquals(10L, checkin.getChekinId());
        assertEquals(ValorEnun.BAIXO, checkin.getHumor());
        assertEquals(ValorEnun.MUITO_BAIXO, checkin.getEnergia());
        assertEquals(ValorEnun.MEDIO, checkin.getFoco());
        assertEquals(now, checkin.getTime());
        assertEquals(mockUsuario, checkin.getUsuario());
    }
}