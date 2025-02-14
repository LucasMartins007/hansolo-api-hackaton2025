package com.example.conversaoarquivo.controller;

import com.example.conversaoarquivo.entity.Analise;
import com.example.conversaoarquivo.entity.Solo;
import com.example.conversaoarquivo.service.SoloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    private SoloService soloService;
    public ExcelController(SoloService soloService) {
        this.soloService = soloService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo não enviado.");
        }
        try {

            soloService.lerArquivoExcel(file);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok("Arquivo enviado com sucesso.");
    }

    @GetMapping
    public List<Analise> ListarAnalise(){
        return soloService.ListarAnalise();
    }
}
