package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.PerfilEvolutivoResponseDto;
import br.com.fiap.SoulBalance.dto.UsuarioRequestDto;
import br.com.fiap.SoulBalance.dto.UsuarioResponseDto;
import br.com.fiap.SoulBalance.entity.PerfilEvolutivoEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.PerfilEvolutivoRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PerfilEvolutivoRepository perfilEvolutivoRepository;


    public List<UsuarioResponseDto> getlAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDto::from)
                .toList();
    }

    public UsuarioResponseDto getById(Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException.forUser(idUsuario));

        return UsuarioResponseDto.from(usuario);
    }

    public void delete(@Valid Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException.forUser(idUsuario));

        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDto save(UsuarioRequestDto filter){
        UsuarioEntity usuario = UsuarioEntity
                .builder()
                .nome(filter.getName())
                .email(filter.getEmail())
                .senha(passwordEncoder.encode(filter.getSenha()))
                .dataCriacao(LocalDateTime.now())
                .build();

        savePerfil(usuario);

        return UsuarioResponseDto.from(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDto update(UsuarioResponseDto filter, Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException.forUser(idUsuario));

        usuario.setNome(filter.getNome());
        usuario.setEmail(filter.getEmail());
        usuario.setSenha(passwordEncoder.encode(filter.getSenha()));
        usuario.setDataCriacao(LocalDateTime.now());

        return UsuarioResponseDto.from(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDto findByEmail(String email) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(NotFoundException.forLogin(email));

        return UsuarioResponseDto.from(usuario);
    }

    public PerfilEvolutivoResponseDto savePerfil(UsuarioEntity usuario) {
        PerfilEvolutivoEntity perfilEvolutivo = PerfilEvolutivoEntity
                .builder()
                .ptoAutocuidado(1.0)
                .ptoResiliencia(1.0)
                .dataLastUpdate(LocalDateTime.now())
                .statusCurto("Novo Usuário")
                .jsonCompetencias("{ \"competencias\": [] }")
                .usuario(usuario)
                .build();

        return PerfilEvolutivoResponseDto.from(perfilEvolutivoRepository.save(perfilEvolutivo));
    }



}
