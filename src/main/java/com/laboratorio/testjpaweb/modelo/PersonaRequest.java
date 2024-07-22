package com.laboratorio.testjpaweb.modelo;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class PersonaRequest {
    private int codigo;
    private String nombres;
    private String apellidos;
    private String fechaNac;
    private String experiencia;

    public PersonaRequest() {
        this.codigo = 0;
        this.nombres = "";
        this.apellidos = "";
        this.fechaNac = null;
        this.experiencia = "0";
    }

    public PersonaRequest(int codigo, String nombres, String apellidos, String fechaNac, String experiencia) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.experiencia = experiencia;
    }
    
    public PersonaRequest(Persona persona) {
        this.codigo = persona.getCodigo();
        this.nombres = persona.getNombres();
        this.apellidos = persona.getApellidos();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fechaNac = persona.getFechaNac().format(dtf);
        this.experiencia = String.valueOf(persona.getExperiencia());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
