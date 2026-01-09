package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.conection.Sesion;
import com.example.model.Bosque;
import com.example.model.Monstruo;
public class BosqueRepository {

    /**
     * Añade o actualiza un bosque en la base de datos.
     * @param bosque objeto Bosque a guardar
     */
    public void addBosque(Bosque bosque){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.merge(bosque);
        tx.commit();
    }

    /**
     * Elimina un bosque de la base de datos.
     * @param bosque objeto Bosque a eliminar
     */
    public void deleteBosque(Bosque bosque){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.remove(bosque);    
        tx.commit();
    }

    /**
     * Añade un nuevo bosque a la base de datos.
     * @param bosque objeto Bosque a persistir
     */
    public void getBosque(Bosque bosque){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Bosque forest = session.find(Bosque.class,bosque.getId());
        System.out.println(forest);
        tx.commit();
    }

    /**
     * Añade un nuevo bosque a la base de datos.
     * @param bosque objeto Bosque a persistir
     */
    public void updateBosque(Bosque bosque){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Bosque forest = session.find(Bosque.class,bosque.getId());
        forest.setNombre(bosque.getNombre());
        forest.setNivelPeligro(bosque.getNivelPeligro());
        Monstruo jefe = session.find(
            Monstruo.class,
            bosque.getMonstruoJefe().getId()
        );
        forest.setMonstruoJefe(jefe);

        List<Monstruo> monstruosManaged = new ArrayList<Monstruo>();

        for (Monstruo m : bosque.getMonstruos()) {
            Monstruo managed = session.find(Monstruo.class, m.getId());
            monstruosManaged.add(managed);
        }

        forest.setMonstruos(monstruosManaged);
        tx.commit();
    }
}
