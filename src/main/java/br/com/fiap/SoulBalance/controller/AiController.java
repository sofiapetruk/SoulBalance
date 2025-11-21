package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.AiRequest;
import br.com.fiap.SoulBalance.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ask")
    public String ask(@RequestBody AiRequest request) {
        return aiService.ask(request.getMessage());
    }
}
