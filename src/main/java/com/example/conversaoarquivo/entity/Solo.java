package com.example.conversaoarquivo.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.security.sasl.SaslException;
import java.util.List;

@Entity
@Data
public class Solo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome_Ponto;

    private String X;

    private String Y;

    private String Materia_Organica;

    private String Potassio;

    private String Fosforo;

    private String Nitrogenio;

    private String Magnesio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_analise", referencedColumnName = "id")
    private Analise analise;

}
