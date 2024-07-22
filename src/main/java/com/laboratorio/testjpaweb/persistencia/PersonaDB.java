package com.laboratorio.testjpaweb.persistencia;

import com.laboratorio.testjpaweb.modelo.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PersonaDB {
    private final ConnectionPoolManager connectionManager;

    public PersonaDB(ConnectionPoolManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    public boolean check() {
        EntityManager manager = (EntityManager) this.connectionManager.getManager();
        return manager != null;
    }
    
    public List<Persona> getPersonas() throws Exception {
        EntityManager manager = (EntityManager) this.connectionManager.getManager();
        if (manager == null) {
            throw new Exception("No ha sido posible conectar con la base de datos");
        }
        
        List<Persona> personas = new ArrayList<>();
        
        try {
            manager.getTransaction().begin();
            Query query = manager.createQuery("SELECT p FROM Persona p ORDER BY codigo", Persona.class);
            personas = query.getResultList();
            manager.getTransaction().commit();
            manager.clear();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw  e;
        }
        
        return personas;
    }
    
    public Persona buscar(int codigo) throws Exception {
        EntityManager manager = (EntityManager) this.connectionManager.getManager();
        if (manager == null) {
            throw new Exception("No ha sido posible conectar con la base de datos");
        }
        
        Persona persona = null;
        
        try {
            manager.getTransaction().begin();
            persona = manager.find(Persona.class, codigo);
            manager.getTransaction().commit();
            manager.clear();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw  e;
        }
        
        return persona;
    }
    
    public boolean insertar(String nombres, String apellidos, String fechaNac, String experiencia) throws Exception {
        EntityManager manager = (EntityManager) this.connectionManager.getManager();
        if (manager == null) {
            throw new Exception("No ha sido posible conectar con la base de datos");
        }
        
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Persona persona = new Persona(nombres, apellidos, LocalDate.parse(fechaNac, dtf), Integer.parseInt(experiencia));

            manager.getTransaction().begin();
            manager.persist(persona);
            manager.getTransaction().commit();
            manager.clear();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        
        return true;
    }
    
    public boolean editar(int codigo, String nombres, String apellidos, String fechaNac, String experiencia) throws Exception {
        EntityManager manager = (EntityManager) this.connectionManager.getManager();
        if (manager == null) {
            throw new Exception("No ha sido posible conectar con la base de datos");
        }
        
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Persona persona = new Persona(codigo, nombres, apellidos, LocalDate.parse(fechaNac, dtf), Integer.parseInt(experiencia));
            
            manager.getTransaction().begin();
            manager.merge(persona);
            manager.getTransaction().commit();
            manager.clear();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        
        return true;
    }
    
    public boolean eliminar(int codigo) throws Exception {
        EntityManager manager = (EntityManager) this.connectionManager.getManager();
        if (manager == null) {
            throw new Exception("No ha sido posible conectar con la base de datos");
        }
        
        try {
            manager.getTransaction().begin();
            Persona persona = manager.find(Persona.class, codigo);
            if (persona == null) {
                manager.getTransaction().rollback();
                return false;
            }
            manager.remove(persona);
            manager.getTransaction().commit();
            manager.clear();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        
        return true;
    }
    
    public String validar(String nombres, String apellidos, String fechaNac, String experiencia) {
        StringBuilder resultado = new StringBuilder("");
        
        if (nombres.isEmpty()) {
            resultado.append("<p>Los nombres no pueden estar vacíos.</p>");
        } else  {
            if (nombres.length() < 2) {
                resultado.append("<p>Los nombres deben tener al menos 2 caracteres.</p>");
            }
        }
        
        if (apellidos.isEmpty()) {
            resultado.append("<p>Los apellidos no pueden estar vacíos.</p>");
        } else  {
            if (nombres.length() < 2) {
                resultado.append("<p>Los apellidos deben tener al menos 2 caracteres.</p>");
            }
        }
        
        if (fechaNac.isEmpty()) {
            resultado.append("<p>La fecha de nacimiento no puede estar vacía.</p>");
        } else  {
            if (!fechaNac.matches("^(19|20)(((([02468][048])|([13579][26]))-02-29)|(\\d{2})-((02-((0[1-9])|1\\d|2[0-8]))|((((0[13456789])|1[012]))-((0[1-9])|((1|2)\\d)|30))|(((0[13578])|(1[02]))-31)))$")) {
                resultado.append("<p>La fecha tiene un formato incorrecto.</p>");
            }
        }
        
        if (experiencia.isEmpty()) {
            resultado.append("<p>La experiencia no puede estar vacía.</p>");
        } else  {
            if (!experiencia.matches("^[0-9]+$")) {
                resultado.append("<p>La experiencia debe ser un número.</p>");
            }
        }
        
        return resultado.toString();
    }
}