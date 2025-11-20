package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.UsuarioRequestDto;
import br.com.fiap.SoulBalance.dto.UsuarioResponseDto;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {

        List<UsuarioResponseDto> usuarioList = usuarioService.getlAll();

        return ResponseEntity.ok(usuarioList);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long idUsuario) {
        UsuarioResponseDto usuario = usuarioService.getById(idUsuario);

        return  ResponseEntity.ok(usuario);

    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> save(@RequestBody @Valid UsuarioRequestDto filter) throws Exception {
        UsuarioResponseDto novoUsuario = usuarioService.save(filter);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> update(
            @RequestBody @Valid UsuarioRequestDto filter,
            @PathVariable Long idUsuario) {

        UsuarioResponseDto usuarioAtualizado = usuarioService.update(filter, idUsuario);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Long idUsuario) {
        usuarioService.delete(idUsuario);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<UsuarioResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UsuarioResponseDto> pageAbrigo = usuarioService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }


}
