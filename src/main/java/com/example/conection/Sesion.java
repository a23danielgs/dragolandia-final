package com.example.conection;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Clase singleton que gestiona la sesión de Hibernate para la aplicación.
 * Garantiza que exista una única instancia de SessionFactory en todo el proyecto.
 */
public class Sesion {

    /**
     * Instancia única de SessionFactory
     */
    private static SessionFactory instance;

    /**
     * Constructor privado para evitar instanciación externa
     */
    private Sesion(){}

    /**
     * Devuelve la instancia única de SessionFactory.
     * Si no existe, la crea de manera segura (thread-safe)
     * usando doble comprobación (double-checked locking).
     * @return instancia única de SessionFactory
     */
    public static SessionFactory getInstance(){
        if (instance == null) {
            synchronized (Sesion.class){
                if (instance == null) {
                    // Crea la configuración de Hibernate usando hibernate.cfg.xml
                    instance = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return instance;
    }
}
