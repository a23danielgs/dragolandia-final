package com.example.model;

import java.util.List;

import jakarta.persistence.*;

/**
 * Clase modelo que representa un Mago dentro del juego.
 * Un mago tiene puntos de vida, un nivel de magia y una lista
 * de hechizos que puede lanzar contra los monstruos.
 */
@Entity
@Table(name="Mago")
public class Mago {

    /**
     * Identificador único del mago en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del mago
     */
    private String nombre;

    /**
     * Puntos de vida actuales del mago
     */
    private int vida;

    /**
     * Nivel de magia del mago, usado para calcular el daño
     */
    private int nivelMagia;

    /**
     * Lista de hechizos del mago.
     * Relación uno a muchos, ya que un mago puede tener
     * varios hechizos asociados.
     */
    @OneToMany(mappedBy = "mago", cascade = CascadeType.ALL)
    private List<Hechizo> conjuros;

    /**
     * Constructor vacío necesario para JPA
     */
    public Mago(){}

    /**
     * Constructor que crea un mago con sus valores iniciales
     * @param nombre nombre del mago
     * @param vida puntos de vida iniciales
     * @param nivelMagia nivel de magia inicial
     */
    public Mago(String nombre, int vida, int nivelMagia) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
    }

    /**
     * Devuelve el identificador del mago
     * @return id del mago
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del mago
     * @param id nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del mago
     * @return nombre del mago
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del mago
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve los puntos de vida del mago
     * @return vida actual
     */
    public int getVida() {
        return vida;
    }

    /**
     * Modifica los puntos de vida del mago
     * @param vida nueva cantidad de vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Devuelve el nivel de magia del mago
     * @return nivel de magia
     */
    public int getNivelMagia() {
        return nivelMagia;
    }

    /**
     * Modifica el nivel de magia del mago
     * @param nivelMagia nuevo nivel de magia
     */
    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    /**
     * Devuelve la lista de hechizos del mago
     * @return lista de conjuros
     */
    public List<Hechizo> getConjuros() {
        return conjuros;
    }

    /**
     * Devuelve un hechizo concreto de la lista según su posición
     * @param posicion posición del hechizo en la lista
     * @return hechizo seleccionado
     */
    public Hechizo getConjuro(int posicion){
        return conjuros.get(posicion);
    }

    /**
     * Asigna una lista de hechizos al mago
     * @param conjuros lista de hechizos
     */
    public void setConjuros(List<Hechizo> conjuros) {
        this.conjuros = conjuros;
    }

    /**
     * Lanza un hechizo básico contra un monstruo,
     * reduciendo su vida en función del nivel de magia
     * @param m monstruo que recibe el ataque
     */
    public void lanzarHechizo(Monstruo m){
        m.setVida(m.getVida() - this.nivelMagia);
    }

    /**
     * Lanza un hechizo específico contra un monstruo.
     * Si el mago no posee el hechizo, pierde un punto de vida.
     * @param m monstruo objetivo
     * @param hechizo hechizo a lanzar
     */
    public boolean lanzarHechizo(Monstruo m, String hechizo){
        for (Hechizo conjuro : conjuros) {
            if (conjuro.getTipo() == hechizo) {
                conjuro.efecto(m);
                conjuros.remove(conjuro);
                return false; 
            }
        }
        setVida(getVida() - 1);
        return true;
    }

    /**
     * Lanza un hechizo de área contra una lista de monstruos.
     * Si el mago no posee el hechizo, pierde un punto de vida.
     * @param m lista de monstruos afectados
     * @param hechizo hechizo de área
     */
    public boolean lanzarHechizo(List<Monstruo> m, String hechizo){
        for (Hechizo conjuro : conjuros) {
            if (conjuro.getTipo().equals(hechizo)) {
                conjuro.efectoArea(m);
                conjuros.remove(conjuro);
                return false; 
            }
        }
        setVida(getVida() - 1);
        return true;
    }
    /**
     * Devuelve una representación en texto de un mago
     */
    @Override
    public String toString() {
        return "Mago [nombre=" + nombre + ", vida=" + vida + ", nivelMagia=" + nivelMagia + ", conjuros=" + conjuros
                + "]";
    }

    
}
