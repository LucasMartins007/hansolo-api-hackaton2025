package com.example.conversaoarquivo.controller;

import com.example.conversaoarquivo.repository.SoloRepository;
import com.example.conversaoarquivo.service.SoloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

        if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                && !file.getContentType().equals("application/vnd.ms-excel")) {
            return ResponseEntity.badRequest().body("Tipo de Arquivo inválid. Envie um novo arquivo");
        }
        try {
            String fileName = file.getOriginalFilename();
            byte[] bytes = fileName.getBytes();
            soloService.lerArquivoExcel(file);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok("Arquivo enviado com sucesso.");



//
//            return ResponseEntity.ok("Arquivo Excel gerado com sucesso!");
//        }catch (Exception e){
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
    }
}
