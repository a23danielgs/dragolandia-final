package com.example.conection;



import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



public class EMFactory {


    private static EMFactory instance;
    private static EntityManagerFactory emf;

    private EMFactory() {}


    public static EMFactory getInstance() {
        if (instance == null) {
            synchronized (EMFactory.class) {
                if (instance == null) {
                    instance = new EMFactory();
                    emf = Persistence.createEntityManagerFactory("");
                }
            }
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
