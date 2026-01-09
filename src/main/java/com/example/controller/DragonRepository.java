package com.example.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.conection.Sesion;
import com.example.model.Dragon;

public class DragonRepository {
    /**
     * Añade un nuevo dragon a la base de datos.
     * @param dragon objeto Dragon a persistir
     */
    public void addDragon(Dragon dragon){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.merge(dragon);
        tx.commit();
    }

    /**
     * Elimina un dragon de la base de datos.
     * @param dragon objeto Dragon a eliminar
     */
    public void deleteDragon(Dragon dragon){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.remove(dragon);    
        tx.commit();
    }

    /**
     * Añade un nuevo dragon a la base de datos.
     * @param dragon objeto Dragon a persistir
     */
    public void getDragon(Dragon dragon){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Dragon doragon = session.find(Dragon.class,dragon.getId());
        System.out.println(doragon);
        tx.commit();
    }

    /**
     * Añade un nuevo dragon a la base de datos.
     * @param dragon objeto Dragon a persistir
     */
    public void updateDragon(Dragon dragon){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Dragon doragon = session.find(Dragon.class,dragon.getId());
        doragon.setNombre(dragon.getNombre());
        doragon.setResistencia(dragon.getResistencia());
        doragon.setIntensidadFuego(dragon.getIntensidadFuego());
        tx.commit();
    }
}
