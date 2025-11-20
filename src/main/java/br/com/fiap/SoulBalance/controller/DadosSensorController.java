package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.DadosSensorRequestDto;
import br.com.fiap.SoulBalance.dto.DadosSensorResponseDto;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import br.com.fiap.SoulBalance.service.DadosSensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dados-sensor")
public class DadosSensorController {

    @Autowired
    private DadosSensorService dadosSensorService;

    @PostMapping
    public ResponseEntity<DadosSensorResponseDto> saveDado(@RequestBody @Valid DadosSensorRequestDto filter) {

        DadosSensorResponseDto dadosSensorResponseDto = dadosSensorService.saveDado(filter);

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosSensorResponseDto);
    }

    @GetMapping()
    public ResponseEntity<List<DadosSensorResponseDto>> getAllByUsuario() {

        List<DadosSensorResponseDto> dados = dadosSensorService.getAll();

        return ResponseEntity.ok(dados);
    }

//    @GetMapping("/agregados")
//    public ResponseEntity<Map<TipoDadoSensor, Double>> agregarDadosDiarios(
//            @AuthenticationPrincipal UsuarioEntity usuarioLogado,
//            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
//
//        Map<TipoDadoSensor, Double> agregados = dadosSensorService.agregarDadosDiarios(
//                usuarioLogado.getId(),
//                data
//        );
//
//        return ResponseEntity.ok(agregados);
//    }

    @DeleteMapping("/{idDadoSensor}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idDadoSensor) {
        dadosSensorService.delete(idDadoSensor);
    }
    @GetMapping("/paginacao")
    public ResponseEntity<Page<DadosSensorResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<DadosSensorResponseDto> pageAbrigo = dadosSensorService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }

}