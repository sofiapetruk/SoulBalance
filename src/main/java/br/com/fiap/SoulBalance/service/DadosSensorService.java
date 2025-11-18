package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import br.com.fiap.SoulBalance.dto.DadosSensorRequestDto;
import br.com.fiap.SoulBalance.dto.DadosSensorResponseDto;
import br.com.fiap.SoulBalance.entity.DadosSensorEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.DadosSensorRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DadosSensorService {

    @Autowired
    private DadosSensorRepository dadosSensorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    /**
     * Salva dados de sono, BPM, passos, etc., enviados pelo cliente.
     * O 'time' é gerado no servidor.
     */
    @Transactional
    public DadosSensorResponseDto saveDado(DadosSensorRequestDto filter, Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(NotFoundException.forUser(userId));

        DadosSensorEntity dado = DadosSensorEntity.builder()
                .tipoDado(filter.getTipoDadoSensor())
                .valor(filter.getValor())
                .time(LocalDateTime.now())
                .usuario(usuario)
                .build();

        DadosSensorEntity savedDado = dadosSensorRepository.save(dado);

        return DadosSensorResponseDto.from(savedDado);
    }

    /**
     * Combina múltiplos registros de sensor (ex: várias leituras de BPM ao longo do dia)
     * para gerar um único valor diário (média/total) para uso na análise da IA.
     * Retorna um mapa de TipoDadoSensor para o valor agregado (ex: Média de BPM ou Total de Passos).
     */
    public Map<TipoDadoSensor, Double> agregarDadosDiarios(Long userId, LocalDate data) {

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.plusDays(1).atStartOfDay().minusNanos(1);

        List<DadosSensorEntity> dadosDoDia = dadosSensorRepository
                .findByUsuarioIdAndTimeBetween(userId, inicioDoDia, fimDoDia);

        if (dadosDoDia.isEmpty()) {
            return Map.of();
        }

        return dadosDoDia.stream()
                .collect(Collectors.groupingBy(
                        DadosSensorEntity::getTipoDado,
                        Collectors.averagingInt(DadosSensorEntity::getValor)
                ));
    }

    /**
     * Retorna todos os registros de dados de sensor para um usuário específico,
     * ordenados por data e hora (do mais recente para o mais antigo).
     */
    public List<DadosSensorResponseDto> getAll(Long userId) {
        List<DadosSensorEntity> dadosDoUsuario = dadosSensorRepository
                .findByUsuarioIdOrderByTimeDesc(userId);

        return dadosDoUsuario.stream()
                .map(DadosSensorResponseDto::from)
                .toList();
    }

    public Page<DadosSensorResponseDto> findAllPage(PageRequest request) {
        return dadosSensorRepository.findAll(request)
                .map(DadosSensorResponseDto::from);
    }

    public void delete(Long dadoSensorId) {
        DadosSensorEntity dadosSensor = dadosSensorRepository.findById(dadoSensorId)
                .orElseThrow(NotFoundException.forDadoSensor(dadoSensorId));

        dadosSensorRepository.delete(dadosSensor);
    }
}