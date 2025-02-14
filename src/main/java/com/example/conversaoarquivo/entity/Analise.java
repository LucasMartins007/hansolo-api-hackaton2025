package com.example.conversaoarquivo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data;

    @OneToMany(mappedBy = "analise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Solo> solos;

    public Analise(){
        this.data = LocalDate.now();
    }

}