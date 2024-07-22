package com.laboratorio.testjpaweb.persistencia;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


@ApplicationScoped
public class ConnectionPoolManager {
    private EntityManager manager;

    public ConnectionPoolManager() {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("LaboratorioDS");
            this.manager = factory.createEntityManager();
        } catch (Exception e)  {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Causa: " + e.getCause().getMessage());
            this.manager = null;
        }
    }

    public EntityManager getManager() {
        return manager;
    }
}