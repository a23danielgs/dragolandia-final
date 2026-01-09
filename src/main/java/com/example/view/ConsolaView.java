package com.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.controller.BosqueRepository;
import com.example.controller.DragonRepository;
import com.example.model.Bosque;
import com.example.model.Dragon;
import com.example.model.Hechizo;
import com.example.model.Mago;
import com.example.model.Monstruo;
import com.example.model.Tipo;

import com.example.controller.MagoRepository;
import com.example.controller.MonstruoRepository;;

public class ConsolaView {
    /**
     * Creamos el scanner y la clase DatabaseController
     */
    Scanner sc = new Scanner(System.in);
    MagoRepository MR = new MagoRepository();
    MonstruoRepository MoR = new MonstruoRepository();
    BosqueRepository BR = new BosqueRepository();
    DragonRepository DR = new DragonRepository();

    /**
     * Funcion que pide por pantalla los datos y con ellos crea un mago
     * @return devuelve el mago creado
     */
    public Mago crearMago(){
        System.out.println("Cual es el nombre del mago?");
        String nombre = sc.nextLine();
        System.out.println("Cuantos puntos de salud tendrá "+nombre+"?");
        Integer vida = sc.nextInt();
        
        System.out.println("Cual será el nivel de magia de "+nombre+"?");
        Integer nivelMagia = sc.nextInt();
        


        Mago mago = new Mago(nombre,vida,nivelMagia);

        List<Hechizo> conjuros = new ArrayList<Hechizo>();

        boolean salir = false;
        do {
            System.out.println("Que hechizo quieres añadirle al mago?");
            System.out.println("[1.(Bola de fuego)\n2.(Rayo),\n3.(Bola de nieve),\n4.(Cura)]");
            Integer opcInteger = sc.nextInt();
            sc.nextLine();
            switch (opcInteger) {
                case 1:
                    conjuros.add(new Hechizo("Bola de fuego",mago));
                    break;
                case 2:
                    conjuros.add(new Hechizo("Rayo",mago));
                    break;
                case 3:
                    conjuros.add(new Hechizo("Bola de Nieve",mago));
                    break;
                case 4:
                    conjuros.add(new Hechizo("Cura",mago));
                    break;
                default:
                    break;
            }
            if (conjuros.size()>=2) {
                System.out.println("Quieres añadir otro hechizo?");
                System.out.println(" S / N ");
                String respuesta = sc.nextLine();
                if (respuesta.equals("N")) {
                    salir = true;
                }
            }
        }
        while(salir!=true);
        mago.setConjuros(conjuros);

        /**
         * Se añade el mago a su respectiva tabla en la base de datos
         */
        MR.addMago(mago);

        return mago;
    }

    /**
     * Funcion que pide por pantalla los datos y con ellos crea un monstruo
     * @return devuelve el monstruo creado
     */
    public Monstruo crearMonstruo(){
        System.out.println("Cual es el nombre del monstro?");
        String nombre = sc.nextLine();
        
        System.out.println("Que tipo de monstro es "+nombre+"? (OGRO/TROLL/ESPECTRO)");
        String tipo = sc.nextLine();
        Tipo tipoMonstro = null;
        
        /**
         * Switch para asignar el valor del enumerado dependiendo del valor introducido por pantalla
         */
        switch (tipo) {
            case "OGRO","Ogro","ogro":
                tipoMonstro = Tipo.OGRO;
                break;
            case "TROLL","Troll","troll":
                tipoMonstro = Tipo.TROLL;
                break;
            case "ESPECTRO","Espectro","espectro":
                tipoMonstro = Tipo.ESPECTRO;
                break;
            default:
                break;
        }

        Monstruo monstruo = new Monstruo (nombre,tipoMonstro);
        /**
         * Se añade el monstruo a su respectiva tabla en la base de datos
         */
        MoR.addMonstruo(monstruo);
        return monstruo;
    }

    /**
     * Funcion que pide por pantalla los datos y con ellos crea un dragon
     * @return devuelve el dragon creado
     */
    public Dragon crearDragon(Bosque bosque){
        System.out.println("Cual es el nombre del dragon?");
        String nombre = sc.nextLine();
        
        System.out.println("Cual es la intensidad del fuego de "+nombre);
        Integer intensidadFuego = sc.nextInt();

        System.out.println("Cual es la resistencia de "+nombre);
        Integer resistencia = sc.nextInt();
        sc.nextLine();

        Dragon dragon = new Dragon(nombre,intensidadFuego,resistencia,bosque);
        /**
         * Se añade el Dragon() a su respectiva tabla en la base de datos
         */
        DR.addDragon(dragon);
        return dragon;
    }

    /**
     * Funcion que pide por pantalla los datos y con ellos crea un bosque
     * @param jefe se le pasa un monstruo para asignarlo como jefe del bosque
     * @return devuelve el bosque creado
     */
    public Bosque crearBosque(Monstruo jefe){
        System.out.println("Cual es el nombre del bosque?");
        String nombre = sc.nextLine();
        System.out.println("Cuanto es el nivel de peligro?");
        Integer nivelPeligro = sc.nextInt();
        sc.nextLine();

        Bosque bosque = new Bosque(nombre,nivelPeligro,jefe,new ArrayList<Monstruo>());

        bosque.getMonstruos().add(jefe);
        jefe.setBosque(bosque);

        BR.addBosque(bosque);
        return bosque;
    }


    /**
     * Función que imprime por pantalla el turno en el que el mago ataca 
     * y calcula el daño recibido por el monstruo e imprime su vida restante 
     * @param mago El mago que esta atacando
     * @param monstruo El monstruo que esta recibiendo daño
     */
    public void imprimirTurnoMago(Mago mago,Monstruo monstruo,String hechizo){
        /**
         * if para controlar que el monstruo no tenga vida negativa
         */
        if (monstruo.getVida()<0) {
            monstruo.setVida(0);
        }
        if (!hechizo.equals("cura")) {
            System.out.println("El mago "+mago.getNombre()+" ataca a "+monstruo.getNombre()+" con su poder de "+mago.getNivelMagia()+" ("+hechizo+")");
            System.out.println("*EXPLOSIÓN*");
            System.out.println("A "+monstruo.getNombre()+" le quedan "+monstruo.getVida()+" puntos de vida");
        }else{
            System.out.println("El mago "+mago.getNombre()+" usa ("+hechizo+")");
            System.out.println("El mago se cura y ahora tiene "+mago.getVida()+" puntos de vida");
        }

    }

    public void imprimirTurnoMagoFallo(Mago mago){
        System.out.println("Ostras que malo! El mago ha fallado se hace 1 punto de daño!!");
    }

    public void imprimirTurnoMagoBasico(Mago mago,Monstruo monstruo){
        System.out.println("El mago "+mago.getNombre()+" ataca a "+monstruo.getNombre()+" con su poder de "+mago.getNivelMagia());
        System.out.println("*EXPLOSIÓN*");
        /**
         * if para controlar que el monstruo no tenga vida negativa
         */
        if (monstruo.getVida()<0) {
            monstruo.setVida(0);
        }
        System.out.println("A "+monstruo.getNombre()+" le quedan "+monstruo.getVida()+" puntos de vida");
    }

    /**
     * Función que imprime por pantalla el turno en el que el monstruo ataca 
     * y calcula el daño recibido por el mago e imprime su vida restante 
     * @param monstruo El monstruo que esta atacando
     * @param mago El mago que esta recibiendo daño
     */
    public void imprimirTurnoMonstruo(Monstruo monstruo,Mago mago){
        System.out.println("El monstruo "+monstruo.getNombre()+" ataca a "+mago.getNombre()+" con su fuerza de "+monstruo.getFuerza());
        System.out.println("PATA PLAM!");
        if (mago.getVida()<0) {
            mago.setVida(0);
        }
        System.out.println("A "+mago.getNombre()+" le quedan "+mago.getVida()+" puntos de vida");
    }

    public void imprimirTurnoDragon(Dragon dragon,Monstruo monstruo){
        System.out.println("El dragon "+dragon.getNombre()+" ataca a "+monstruo.getNombre()+" con su fuego de "+dragon.getIntensidadFuego()+" de intensidad");
        System.out.println("FUUSHH");
        if (monstruo.getVida()<0) {
            monstruo.setVida(0);
        }
        System.out.println("A "+monstruo.getNombre()+" le quedan "+monstruo.getVida()+" puntos de vida");
    }

    /**
     * Función que imprime el ganador de la batalla
     * @param ganador el es nombre del mago o monstruo que ganó la batalla
     */
    public void imprimirGanador(String ganador) {
        System.out.println("El ganador son "+ganador+"!!!");
    }
}
