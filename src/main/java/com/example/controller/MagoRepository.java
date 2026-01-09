package com.example.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.conection.Sesion;
import com.example.model.Hechizo;
import com.example.model.Mago;

public class MagoRepository {
    /**
     * Añade un nuevo mago a la base de datos.
     * @param mago objeto Mago a persistir
     */
    public void addMago(Mago mago){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.persist(mago);
        tx.commit();
    }

    /**
     * Elimina un mago de la base de datos.
     * @param mago objeto Mago a eliminar
     */
    public void deleteMago(Mago mago){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        session.remove(mago);    
        tx.commit();
    }

    /**
     * Añade un nuevo mago a la base de datos.
     * @param mago objeto Mago a persistir
     */
    public void getMago(Mago mago){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Mago mage = session.find(Mago.class,mago.getId());
        System.out.println(mage);
        tx.commit();
    }

    /**
     * Añade un nuevo mago a la base de datos.
     * @param mago objeto Mago a persistir
     */
    public void updateMago(Mago mago){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession(); 
        Transaction tx = session.beginTransaction();
        Mago mage = session.find(Mago.class,mago.getId());
        mage.setNombre(mago.getNombre());
        mage.setVida(mago.getVida());
        mage.setNivelMagia(mago.getNivelMagia());
        mage.setConjuros(mago.getConjuros());
        tx.commit();
    }

    /**
     * Elimina un hechizo de la base de datos.
     * Se busca el hechizo por su ID y luego se elimina.
     * @param hechizo objeto Hechizo a eliminar
     */
    public void quitarHechizo(Hechizo hechizo){
        SessionFactory factory = Sesion.getInstance();
        Session session = factory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        // Buscamos el hechizo en la base de datos
        Hechizo a = session.find(Hechizo.class, hechizo.getId());
        session.remove(a);
        tx.commit();
    }
}
