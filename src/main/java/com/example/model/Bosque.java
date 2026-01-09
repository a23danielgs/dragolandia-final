package com.example.model;

import java.util.List;

import com.example.controller.BosqueRepository;

import jakarta.persistence.*;

/**
 * Clase modelo que representa un Bosque dentro del juego.
 * Un bosque tiene un nombre, un nivel de peligro, un monstruo jefe
 * y una lista de monstruos que habitan en él.
 */
@Entity
@Table(name="Bosque")
public class Bosque {

    /**
     * Identificador único del bosque en la base de datos
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Nombre del bosque
     */
    private String nombre;

    /**
     * Nivel de peligro del bosque, utilizado para evaluar riesgos
     */
    private int nivelPeligro;
    
    /**
     * Monstruo jefe del bosque
     * Relación uno a uno, ya que cada bosque tiene un único jefe
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Monstruo monstruoJefe;

    /**
     * Lista de monstruos que habitan el bosque
     * Relación uno a muchos, un bosque puede tener varios monstruos
     */
    @OneToMany(mappedBy = "bosque", cascade = CascadeType.ALL)
    private List<Monstruo> monstruos;

    // @OneToOne(cascade = CascadeType.ALL)
    // private Dragon dragon;

    /**
     * Constructor vacío
     */
    public Bosque(){}

    /**
     * Constructor que crea un bosque con todos sus atributos
     * @param nombre nombre del bosque
     * @param nivelPeligro nivel de peligro del bosque
     * @param monstruoJefe monstruo jefe del bosque
     * @param monstruos lista de monstruos que habitan el bosque
     */
    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe, List<Monstruo> monstruos) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
        this.monstruos = monstruos;
        monstruos.add(monstruoJefe);
        System.out.println("BOSQUE===>"+monstruos);
    }

    /**
     * Devuelve el identificador del bosque
     * @return id del bosque
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el identificador del bosque
     * @param id nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del bosque
     * @return nombre del bosque
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del bosque
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nivel de peligro del bosque
     * @return nivel de peligro
     */
    public int getNivelPeligro() {
        return nivelPeligro;
    }

    /**
     * Modifica el nivel de peligro del bosque
     * @param nivelPeligro nuevo nivel de peligro
     */
    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    /**
     * Devuelve el monstruo jefe del bosque
     * @return monstruo jefe
     */
    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    /**
     * Modifica el monstruo jefe del bosque
     * @param monstruoJefe nuevo jefe
     */
    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    /**
     * Devuelve la lista de monstruos que habitan el bosque
     * @return lista de monstruos
     */
    public List<Monstruo> getMonstruos() {
        return monstruos;
    }

    /**
     * Asigna una lista de monstruos al bosque
     * @param monstruos nueva lista de monstruos
     */
    public void setMonstruos(List<Monstruo> monstruos) {
        this.monstruos = monstruos;
    }

    /**
     * Muestra por consola el monstruo jefe del bosque
     */
    public void mostrarJefe(){
        System.out.println(getMonstruoJefe());
    }

    /**
     * Cambia el monstruo jefe del bosque
     * @param nuevoJefe nuevo monstruo que será jefe
     */
    public void cambiarJefe(Monstruo nuevoJefe){
        setMonstruoJefe(nuevoJefe);
    }

    /**
     * Añade un monstruo al bosque y establece la relación
     * bidireccional entre el monstruo y el bosque.
     * También actualiza la base de datos mediante DatabaseController.
     * @param monstruo monstruo a añadir al bosque
     */
    public void añadirMonstruo(Monstruo monstruo){
        getMonstruos().add(monstruo);
        monstruo.setBosque(this);

        BosqueRepository BR = new BosqueRepository();
        BR.addBosque(this);
    }
}
