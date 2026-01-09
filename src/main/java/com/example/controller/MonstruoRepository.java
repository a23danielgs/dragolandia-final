package com.example.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.conection.Sesion;
import com.example.model.Monstruo;

public class MonstruoRepository {
    /**
     * Añade o actualiza un monstruo en la base de datos.
     * @param monstruo objeto Monstruo a guardar
     */
    public void addMonstruo(Monstruo monstruo){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.merge(monstruo);
        tx.commit();
    }

    /**
     * Elimina un monstruo de la base de datos.
     * @param monstruo objeto Monstruo a eliminar
     */
    public void deleteMonstruo(Monstruo monstruo){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.remove(monstruo);    
        tx.commit();
    }

    /**
     * Añade un nuevo monstruo a la base de datos.
     * @param monstruo objeto Monstruo a persistir
     */
    public void getMonstruo(Monstruo monstruo){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Monstruo monster = session.find(Monstruo.class,monstruo.getId());
        System.out.println(monster);
        tx.commit();
    }

    /**
     * Añade un nuevo monstruo a la base de datos.
     * @param monstruo objeto Monstruo a persistir
     */
    public void updateMonstruo(Monstruo monstruo){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Monstruo monster = session.find(Monstruo.class,monstruo.getId());
        monster.setNombre(monstruo.getNombre());
        monster.setVida(monstruo.getVida());
        monster.setFuerza(monstruo.getFuerza());
        monster.setBosque(monstruo.getBosque());
        tx.commit();
    }
}
