package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.AtividadeRequestDto;
import br.com.fiap.SoulBalance.dto.AtividadeResponseDto;
import br.com.fiap.SoulBalance.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;


    @PostMapping()
    public ResponseEntity<AtividadeResponseDto> saveAtividade(@RequestBody @Valid AtividadeRequestDto atividadeRequestDto) {

        AtividadeResponseDto response = atividadeService.saveAtividade(atividadeRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/{atividadeId}/historico")
    public ResponseEntity<AtividadeResponseDto> buscarHistoricoPorPeriodo(
            @PathVariable Long userId, @PathVariable Long atividadeId) {

        AtividadeResponseDto historico = atividadeService.buscarHistoricoPorPeriodo(userId, atividadeId);
        return ResponseEntity.ok(historico);
    }

    @GetMapping()
    public ResponseEntity<List<AtividadeResponseDto>> getAll() {
        List<AtividadeResponseDto> atividadeList = atividadeService.getAll();

        return ResponseEntity.ok(atividadeList);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<AtividadeResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<AtividadeResponseDto> pageAbrigo = atividadeService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }

}