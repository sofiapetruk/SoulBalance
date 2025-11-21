package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.AtividadeRequestDto;
import br.com.fiap.SoulBalance.dto.AtividadeResponseDto;
import br.com.fiap.SoulBalance.entity.AtividadeEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.AtividadeRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Salva uma nova atividade (trabalho, descanso, lazer) para o usuário logado.
     */
    @Transactional
    @CacheEvict(value = "historicoAtividades")
    public AtividadeResponseDto saveAtividade(AtividadeRequestDto filter) {
        if (filter.getInicio().isAfter(filter.getFim())) {
            throw new IllegalArgumentException("O horário de início deve ser anterior ao horário de fim da atividade.");
        }

        UsuarioEntity usuario = validarUsuario(filter.getEmail());

        AtividadeEntity atividade = AtividadeEntity.builder()
                .tipoAtividade(filter.getTipoAtividade())
                .descricao(filter.getDescricao())
                .duracaoMinutosAtividade(calcularDuracaoMinutos(filter.getInicio(), filter.getFim()))
                .usuario(usuario)
                .build();

        AtividadeEntity savedAtividade = atividadeRepository.save(atividade);
        return AtividadeResponseDto.from(savedAtividade);
    }

    /**
     * Retorna o histórico de atividades (como DTO) dentro de um período.
     * Essencial para construir o dashboard e relatórios.
     */
    @Cacheable(value = "historicoAtividades", key = "{#userId, #atividadeId}")
    public AtividadeResponseDto buscarHistoricoPorPeriodo(Long userId, Long atividadeId) {

        AtividadeEntity atividade = atividadeRepository
                .findByUsuarioIdAndAtividadeId(userId, atividadeId)
                .orElseThrow(() -> new NotFoundException("Atividade não encontrada para o usuário e ID especificados."));

        return AtividadeResponseDto.from(atividade);
    }

    public List<AtividadeResponseDto> getAll() {
        return atividadeRepository.findAll()
                .stream()
                .map(AtividadeResponseDto::from)
                .toList();
    }

    public Page<AtividadeResponseDto> findAllPage(PageRequest request) {
        return atividadeRepository.findAll(request)
                .map(AtividadeResponseDto::from);
    }

    public long calcularDuracaoMinutos(LocalDateTime inicio, LocalDateTime fim) {
        Duration duracao = Duration.between(inicio, fim);

        return duracao.toMinutes();
    }

    private UsuarioEntity validarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(NotFoundException.forEmail(email));
    }

}