package com.example.model;

import jakarta.persistence.*;

/**
 * Clase modelo que representa un Monstruo dentro del juego.
 * Un monstruo tiene un tipo, puntos de vida, fuerza de ataque
 * y puede pertenecer a un bosque.
 */
@Entity
@Table(name="Monstruo")
public class Monstruo {
    /**
     * Identificador único del monstruo en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Nombre del monstruo
     */
    private String nombre;

    /**
     * Puntos de vida actuales del monstruo
     */
    private int vida;

    /**
     * Tipo de monstruo (OGRO, TROLL o ESPECTRO)
     */
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    /**
     * Fuerza de ataque del monstruo
     */
    private int fuerza;

    /**
     * Bosque al que pertenece el monstruo.
     * Relación muchos a uno, ya que varios monstruos
     * pueden pertenecer al mismo bosque.
     */
    @ManyToOne
    @JoinColumn(name = "bosque_id",nullable = true)
    private Bosque bosque;

    /**
     * Indica si el monstruo se encuentra congelado
     */
    private boolean congelado = false;

    /**
     * Constructor vacío
     */
    public Monstruo(){}
    /**
     * Constructor que crea un monstruo asignándole
     * valores iniciales según su tipo
     * @param nombre nombre del monstruo
     * @param tipo tipo del monstruo
     */
    public Monstruo(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        /**
         * Switch que asigna la vida y la fuerza del monstruo
         * dependiendo de su tipo
         */
        switch (tipo) {
            case OGRO:
                this.vida = 400;
                this.fuerza = 90;
                break;
            case TROLL:
                this.vida = 450;
                this.fuerza = 75;
                break;
            case ESPECTRO:
                this.vida = 250;
                this.fuerza = 60;
                break;
            default:
                break;
        }
    }

    /**
     * Devuelve el identificador del monstruo
     * @return id del monstruo
     */
    public int getId() {
        return id;
    }
    /**
     * Modifica el identificador del monstruo
     * @param id nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del monstruo
     * @return nombre del monstruo
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Modifica el nombre del monstruo
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve los puntos de vida del monstruo
     * @return vida actual
     */
    public int getVida() {
        return vida;
    }
    /**
     * Modifica los puntos de vida del monstruo
     * @param vida nueva cantidad de vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Devuelve la fuerza del monstruo
     * @return fuerza de ataque
     */
    public int getFuerza() {
        return fuerza;
    }
    /**
     * Modifica la fuerza del monstruo
     * @param fuerza nueva fuerza
     */
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * Devuelve el tipo del monstruo
     * @return tipo del monstruo
     */
    public Tipo getTipo() {
        return tipo;
    }
    /**
     * Modifica el tipo del monstruo
     * @param tipo nuevo tipo
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el bosque al que pertenece el monstruo
     * @return bosque del monstruo
     */
    public Bosque getBosque() {
        return bosque;
    }
    /**
     * Asigna un bosque al monstruo
     * @param bosque bosque al que pertenece
     */
    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    /**
     * Indica si el monstruo está congelado
     * @return true si está congelado, false en caso contrario
     */
    public boolean isCongelado() {
        return congelado;
    }
    /**
     * Modifica el estado de congelación del monstruo
     * @param congelado nuevo estado
     */
    public void setCongelado(boolean congelado) {
        this.congelado = congelado;
    }

    /**
     * Método que permite al monstruo atacar a un mago
     * restándole vida en función de su fuerza
     * @param m mago que recibe el ataque
     */
    public void atacar(Mago m){
        m.setVida(m.getVida()-this.fuerza);
    }

    /**
     * Devuelve una representación en texto del monstruo
     */
    @Override
    public String toString() {
        return "Monstruo [nombre=" + nombre + ", vida=" + vida + ", tipo=" + tipo + ", fuerza=" + fuerza + "]";
    }
}
