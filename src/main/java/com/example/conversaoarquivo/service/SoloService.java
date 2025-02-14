package com.example.conversaoarquivo.service;

import com.example.conversaoarquivo.entity.Analise;
import com.example.conversaoarquivo.entity.Solo;
import com.example.conversaoarquivo.repository.AnaliseRespository;
import com.example.conversaoarquivo.repository.SoloRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoloService {

    @Autowired
    private SoloRepository soloRepository;

    @Autowired
    private AnaliseRespository analiseRespository;


    public void lerArquivoExcel(MultipartFile file) throws IOException {
        Analise analise = new Analise();
        analise.setData(LocalDate.now());
        analiseRespository.save(analise);


        List<Solo> listaSolo = new ArrayList<>();
        InputStream inputStream = file.getInputStream();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Integer count = 0;
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                count++;

                Cell cellX = row.getCell(0);
                Cell cellY = row.getCell(1);
                Cell cellMateria = row.getCell(2);
                Cell cellPotassio = row.getCell(3);
                Cell cellFosforo = row.getCell(4);
                Cell cellNitrogenio = row.getCell(5);
                Cell cellMagnesio = row.getCell(6);


                Solo solo = new Solo();
                solo.setNome_Ponto("." + count);
                solo.setX(String.valueOf(cellX));
                solo.setY(String.valueOf(cellY));
                solo.setMateria_Organica(String.valueOf(cellMateria));
                solo.setPotassio(String.valueOf(cellPotassio));
                solo.setFosforo(String.valueOf(cellFosforo));
                solo.setNitrogenio(String.valueOf(cellNitrogenio));
                solo.setMagnesio(String.valueOf(cellMagnesio));
                solo.setAnalise(analise);
                listaSolo.add(solo);
            }
            soloRepository.saveAll(listaSolo);
    }

    public List<Analise> ListarAnalise(){
        return analiseRespository.findAll();
    }
}
