package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.CheckinManualRequestDto;
import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import br.com.fiap.SoulBalance.entity.CheckinManualEntity;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.exception.NotFoundException;
import br.com.fiap.SoulBalance.repository.CheckinManualRepository;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckinManualService {

    @Autowired
    private CheckinManualRepository checkinManualRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Salva o humor, energia e foco do usuário logado e dispara a análise da IA.
     */
    @Transactional
    public CheckinManualResponseDto saveChekin(CheckinManualRequestDto filter, Long userId) {
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(NotFoundException.forUser(userId));

        CheckinManualEntity checkin = CheckinManualEntity.builder()
                .humor(filter.getHumor())
                .energia(filter.getEnergia())
                .foco(filter.getFoco())
                .time(LocalDateTime.now())
                .usuario(usuario)
                .build();

        CheckinManualEntity savedCheckin = checkinManualRepository.save(checkin);

        return CheckinManualResponseDto.from(savedCheckin);
    }

    /**
     * Retorna todos os check-ins de um dia específico para um usuário.
     */
    public List<CheckinManualResponseDto> getAllByUsuario(Long userId) {

        List<CheckinManualEntity> historico = checkinManualRepository.findByUsuario(userId);

        return historico.stream()
                .map(CheckinManualResponseDto::from)
                .toList();
    }

    public List<CheckinManualResponseDto> getAll() {
        return checkinManualRepository.findAll()
                .stream()
                .map(CheckinManualResponseDto::from)
                .toList();
    }

//    /**
//     * CRUCIAL: Após salvar o check-in, este método é chamado para notificar
//     * o AnaliseDiariaIAService para que ele comece a processar os dados do dia.
//     * @param userId ID do usuário.
//     * @param checkin Entidade Check-in recém-salva.
//     */
//    public void dispararAnaliseIA(Long userId, CheckinManualEntity checkin) {
//
//        // Chama o método no AnaliseDiariaIAService para iniciar o fluxo da IA
//        analiseDiariaIAService.processarNovoCheckin(userId, checkin);
//
//        System.out.println("Análise IA disparada para o usuário: " + userId + " após Check-in.");
//    }
}