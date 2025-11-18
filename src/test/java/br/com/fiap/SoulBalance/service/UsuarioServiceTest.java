package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.UsuarioRequestDto;
import br.com.fiap.SoulBalance.dto.UsuarioResponseDto;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UsuarioRequestDto requestDto;
    private final String RAW_PASSWORD = "senha123";
    private final String ENCODED_PASSWORD = "$2a$10$encodedPasswordHash";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDto = UsuarioRequestDto.builder()
                .name("Sofia Petruk")
                .email("soso@email.com")
                .senha(RAW_PASSWORD)
                .build();
    }

    @Test
    @DisplayName("Deve salvar o usuário com sucesso, criptografando a senha")
    void save_shouldSaveUserWithEncodedPasswordAndDefaultRole() {

        when(passwordEncoder.encode(RAW_PASSWORD)).thenReturn(ENCODED_PASSWORD);

        UsuarioEntity usuarioSaved = UsuarioEntity.builder()
                .userId(1L)
                .nome("Sofia Petruk")
                .email("soso@email.com")
                .senha(ENCODED_PASSWORD)
                .build();

        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioSaved);

        UsuarioResponseDto result = usuarioService.save(requestDto);

        ArgumentCaptor<UsuarioEntity> usuarioCaptor = ArgumentCaptor.forClass(UsuarioEntity.class);
        verify(usuarioRepository, times(1)).save(usuarioCaptor.capture());

        verify(passwordEncoder, times(1)).encode(RAW_PASSWORD);

        UsuarioEntity entitySaved = usuarioCaptor.getValue();
        assertEquals(ENCODED_PASSWORD, entitySaved.getSenha(), "A senha salva deve ser a criptografada.");
        assertEquals(requestDto.getEmail(), entitySaved.getEmail(), "O email deve ser o mesmo do Request DTO.");

        assertNotNull(result, "O resultado não deve ser nulo.");
        assertEquals(usuarioSaved.getUserId(), result.getUserId(), "O ID do usuário salvo deve ser retornado.");
    }
}