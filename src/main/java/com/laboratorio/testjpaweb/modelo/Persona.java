package com.laboratorio.testjpaweb.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personas")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "nombres", length = 25, nullable = false)
    private String nombres;
    
    @Column(name = "apellidos", length = 25, nullable = false)
    private String apellidos;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;
    
    @Column(name = "experiencia", nullable = false)
    private int experiencia;

    public Persona(String nombres, String apellidos, LocalDate fechaNac, int experiencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNac=" + fechaNac + ", experiencia=" + experiencia + '}';
    }
}