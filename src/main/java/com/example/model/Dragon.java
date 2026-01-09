package com.example.model;

import jakarta.persistence.*;

/**
 * Clase modelo que representa un Dragón dentro del juego.
 * Un dragón tiene un nombre, una intensidad de fuego,
 * una resistencia y está asociado a un bosque.
 */
@Entity
@Table(name="Dragon")
public class Dragon {

    /**
     * Identificador único del dragón en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del dragón
     */
    private String nombre;

    /**
     * Intensidad del fuego del dragón, utilizada para calcular el daño
     */
    private int intensidadFuego;

    /**
     * Nivel de resistencia del dragón
     */
    private int resistencia;

    /**
     * Bosque asociado al dragón.
     * Relación uno a uno, ya que un dragón protege o habita un único bosque.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Bosque bosque;

    /**
     * Constructor vacío necesario para JPA
     */
    public Dragon(){}

    /**
     * Constructor que crea un dragón con todos sus atributos
     * @param nombre nombre del dragón
     * @param intensidadFuego intensidad del fuego del dragón
     * @param resistencia nivel de resistencia del dragón
     * @param bosque bosque asociado al dragón
     */
    public Dragon(String nombre, int intensidadFuego, int resistencia, Bosque bosque) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
        this.bosque = bosque;
    }

    /**
     * Devuelve el identificador del dragón
     * @return id del dragón
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del dragón
     * @param id nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del dragón
     * @return nombre del dragón
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del dragón
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la intensidad del fuego del dragón
     * @return intensidad de fuego
     */
    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    /**
     * Modifica la intensidad del fuego del dragón
     * @param intensidadFuego nueva intensidad de fuego
     */
    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    /**
     * Devuelve la resistencia del dragón
     * @return resistencia del dragón
     */
    public int getResistencia() {
        return resistencia;
    }

    /**
     * Modifica la resistencia del dragón
     * @param resistencia nueva resistencia
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     * Permite al dragón atacar a un monstruo exhalando fuego,
     * reduciendo su vida en función de la intensidad del fuego.
     * @param m monstruo que recibe el ataque
     */
    public void exhalar(Monstruo m){
        m.setVida(m.getVida() - this.intensidadFuego);
    }
}
