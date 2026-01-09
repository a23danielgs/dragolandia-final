package com.example.model;

import java.util.List;
import jakarta.persistence.*;

/**
 * Clase modelo que representa un Hechizo dentro del juego.
 * Un hechizo tiene un tipo, un poder y pertenece a un mago
 * que es su portador.
 */
@Entity
@Table(name="Hechizo")
public class Hechizo {

    /**
     * Identificador único del hechizo en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    /**
     * Tipo de hechizo (bola de fuego, rayo, bola de nieve, cura, etc.)
     */
    private String tipo;

    /**
     * Poder del hechizo, normalmente relacionado con
     * el nivel de magia del mago
     */
    private int poder;

    /**
     * Mago portador del hechizo.
     * Relación muchos a uno, ya que un mago puede tener
     * varios hechizos asociados.
     */
    @ManyToOne
    @JoinColumn(name = "mago_portador", nullable = true)
    private Mago mago;

    /**
     * Constructor vacío
     */
    public Hechizo(){}

    /**
     * Constructor que crea un hechizo en función de su tipo
     * y del mago que lo posee.
     * @param tipo tipo del hechizo
     * @param mago mago portador del hechizo
     */
    public Hechizo(String tipo, Mago mago){
        this.mago = mago;

        /**
         * Se pasa el tipo a minúsculas para evitar errores
         * en la comparación del switch
         */
        tipo = tipo.toLowerCase();

        /**
         * Switch que asigna el tipo y el poder del hechizo.
         * El poder depende del nivel de magia del mago.
         */
        switch (tipo) {
            case "bola de fuego","rayo","bola de nieve","cura":
                this.tipo = tipo;
                this.poder = mago.getNivelMagia();
                break;
            default:
                this.tipo = "hechizo imperfecto";
                this.poder = mago.getNivelMagia();
                break;
        }
    }

    /**
     * Devuelve el identificador del hechizo
     * @return id del hechizo
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el tipo del hechizo
     * @return tipo del hechizo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo del hechizo
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el poder del hechizo
     * @return poder del hechizo
     */
    public int getPoder() {
        return poder;
    }

    /**
     * Modifica el poder del hechizo
     * @param poder nuevo poder
     */
    public void setPoder(int poder) {
        this.poder = poder;
    }

    /**
     * Aplica el efecto del hechizo sobre un único monstruo
     * @param monstruo monstruo afectado por el hechizo
     */
    public void efecto(Monstruo monstruo){
        switch (tipo) {
            case "bola de fuego":
                monstruo.setVida(monstruo.getVida() - (this.poder * 2));
                break;
            case "bola de nieve":
                monstruo.setCongelado(true);
                break;
            case "cura":
                mago.setVida(mago.getVida() + this.poder);
                break;
            case "hechizo imperfecto":
                monstruo.setVida(monstruo.getVida() - 1);
                break;
            case "rayo":
                monstruo.setVida(monstruo.getVida() - this.poder);
                break;
            default:
                break;
        }
    }

    /**
     * Aplica el efecto del hechizo sobre una lista de monstruos
     * @param monstruos lista de monstruos afectados
     */
    public void efectoArea(List<Monstruo> monstruos){
        switch (tipo) {
            case "bola de fuego":
                for (Monstruo monstruo : monstruos) {
                    monstruo.setVida(monstruo.getVida() - this.poder);
                }
                break;
            case "bola de nieve":
                for (Monstruo monstruo : monstruos) {
                    monstruo.setCongelado(true);
                }
                break;
            case "rayo":
                for (Monstruo monstruo : monstruos) {
                    monstruo.setVida(monstruo.getVida() - this.poder);
                }
                break;
            case "cura":
                mago.setVida(mago.getVida() + this.poder);
                break;
            case "hechizo imperfecto":
                for (Monstruo monstruo : monstruos) {
                    monstruo.setVida(monstruo.getVida() - 1);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Devuelve una representación en texto del hechizo
     * @return tipo del hechizo
     */
    @Override
    public String toString() {
        return tipo;
    }
}
