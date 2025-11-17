package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.AtividadeRequestDto;
import br.com.fiap.SoulBalance.dto.AtividadeResponseDto;
import br.com.fiap.SoulBalance.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;


    @PostMapping("/users/{userId}/atividades")
    public ResponseEntity<AtividadeResponseDto> saveAtividade(
            @PathVariable Long userId,
            @RequestBody @Valid AtividadeRequestDto atividadeRequestDto) {

        AtividadeResponseDto response = atividadeService.saveAtividade(atividadeRequestDto, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/atividades/historico")
    public ResponseEntity<List<AtividadeResponseDto>> buscarHistoricoPorPeriodo(
            @PathVariable Long userId,
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {

        List<AtividadeResponseDto> historico = atividadeService.buscarHistoricoPorPeriodo(userId, inicio, fim);
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/atividades")
    public ResponseEntity<List<AtividadeResponseDto>> getAll() {
        List<AtividadeResponseDto> atividadeList = atividadeService.getAll();

        return ResponseEntity.ok(atividadeList);
    }
}