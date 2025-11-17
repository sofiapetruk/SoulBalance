package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.CheckinManualRequestDto;
import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.service.CheckinManualService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("checkin-manual")
public class CheckinManualController {

    @Autowired
    private CheckinManualService checkinManualService;

    @PostMapping
    public ResponseEntity<CheckinManualResponseDto> saveChekin(
            @RequestBody @Valid CheckinManualRequestDto filter,
            @AuthenticationPrincipal UsuarioEntity usuarioLogado) {

        CheckinManualResponseDto checkinManualResponseDto = checkinManualService.saveChekin(filter, usuarioLogado.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(checkinManualResponseDto);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<CheckinManualResponseDto>> getAllByUsuario(@AuthenticationPrincipal UsuarioEntity usuarioLogado) {

        List<CheckinManualResponseDto> historico = checkinManualService.getAllByUsuario(usuarioLogado.getUserId());

        return ResponseEntity.ok(historico);
    }

    @GetMapping()
    public ResponseEntity<List<CheckinManualResponseDto>> getAll() {

        List<CheckinManualResponseDto> chekinList = checkinManualService.getAll();

        return ResponseEntity.ok(chekinList);
    }
}