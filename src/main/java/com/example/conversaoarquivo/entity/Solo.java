package com.example.conversaoarquivo.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
public class Solo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String X;

    public String Y;

    public String Materia_Organica;

    public String Potassio;

    public String Fosforo;

    public String Nitrogenio;

    public String Magnesio;
}
