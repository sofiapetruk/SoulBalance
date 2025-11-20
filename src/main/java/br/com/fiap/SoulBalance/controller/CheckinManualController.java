package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.CheckinManualRequestDto;
import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import br.com.fiap.SoulBalance.dto.UsuarioResponseDto;
import br.com.fiap.SoulBalance.service.CheckinManualService;
import br.com.fiap.SoulBalance.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("checkin-manual")
public class CheckinManualController {

    @Autowired
    private CheckinManualService checkinManualService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<CheckinManualResponseDto> saveChekin(
            @RequestBody @Valid CheckinManualRequestDto filter) {

        CheckinManualResponseDto checkinManualResponseDto = checkinManualService.saveChekin(filter);

        return ResponseEntity.status(HttpStatus.CREATED).body(checkinManualResponseDto);
    }

    @GetMapping("/historico/{idUsuario}")
    public ResponseEntity<List<CheckinManualResponseDto>> getAllByUsuario(@PathVariable Long idUsuario) {

        UsuarioResponseDto usuario = usuarioService.getById(idUsuario);

        List<CheckinManualResponseDto> historico = checkinManualService.getAllByUsuario(usuario.getUserId());

        return ResponseEntity.ok(historico);
    }

    @GetMapping()
    public ResponseEntity<List<CheckinManualResponseDto>> getAll() {

        List<CheckinManualResponseDto> chekinList = checkinManualService.getAll();

        return ResponseEntity.ok(chekinList);
    }

    @DeleteMapping("/users/{userId}/{chekinId}/chekins")
    public ResponseEntity<Void> deleteChekins(@PathVariable Long userId, @PathVariable Long chekinId) {
        checkinManualService.deleteUserChekin(userId, chekinId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<CheckinManualResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<CheckinManualResponseDto> pageAbrigo = checkinManualService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }

}