package com.example.controller;

import java.util.Random;

import com.example.model.Bosque;
import com.example.model.Dragon;
import com.example.model.Hechizo;
import com.example.model.Mago;
import com.example.model.Monstruo;
import com.example.view.ConsolaView;

/**
 * Clase controlador que maneja la lógica de batalla entre un mago y un monstruo.
 * Utiliza la vista de consola para mostrar los turnos y el DatabaseController
 * para actualizar los hechizos en la base de datos.
 */
public class BattleController {

    /**
     * Vista de consola para mostrar mensajes de la batalla
     */
    ConsolaView CV = new ConsolaView();

    /**
     * Controlador de base de datos para actualizar hechizos
     */
    MagoRepository MR = new MagoRepository();

    Boolean batalla = true;
    /**
     * Simula una batalla entre un mago y un monstruo.
     * Alterna turnos de ataque entre el mago y el monstruo hasta que uno de los dos muere.
     * @param mago mago que participará en la batalla
     * @param monstruo monstruo que participará en la batalla
     */
    public void simularBatalla(Mago mago1,Mago mago2,Dragon dragon,Bosque bosque){
        /**
         * Bucle que representa los turnos de la batalla.
         * Se repite mientras ambos tengan vida.
         */
        do {
            // Turno del primer mago
            if (mago1.getConjuros().size()>0) {
                Random aleatorio = new Random();
                int hechizoMago1 = aleatorio.nextInt(3)+1;
                switch (hechizoMago1) {
                    case 1:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Bola de fuego",mago1));
                        break;
                    case 2:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Rayo",mago1));
                        break;
                    case 3:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Bola de nieve",mago1));
                        break;
                    case 4:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Cura",mago1));
                        break;
                }
                bosque.getMonstruos().forEach(
                 monstruo -> {
                    CV.imprimirTurnoMago(mago1, monstruo);
                 } 
                );
                
            }else{
                Random aleatorio = new Random();
                int monstruoAleatorio = aleatorio.nextInt(bosque.getMonstruos().size());
                Monstruo monstruo = bosque.getMonstruos().get(monstruoAleatorio);
                mago1.lanzarHechizo(monstruo);
                CV.imprimirTurnoMago(mago1, monstruo);
            }
            comprobarJefe(bosque);

            // Turno del segundo mago
            if (mago1.getConjuros().size()>0) {
                Random aleatorio = new Random();
                int hechizoMago1 = aleatorio.nextInt(3)+1;
                switch (hechizoMago1) {
                    case 1:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Bola de fuego",mago1));
                        break;
                    case 2:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Rayo",mago1));
                        break;
                    case 3:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Bola de nieve",mago1));
                        break;
                    case 4:
                        mago1.lanzarHechizo(bosque.getMonstruos(),new Hechizo("Cura",mago1));
                        break;
                }
                bosque.getMonstruos().forEach(
                 monstruo -> {
                    CV.imprimirTurnoMago(mago1, monstruo);
                 } 
                );
                
            }else{
                Random aleatorio = new Random();
                int monstruoAleatorio = aleatorio.nextInt(bosque.getMonstruos().size());
                Monstruo monstruo = bosque.getMonstruos().get(monstruoAleatorio);
                mago1.lanzarHechizo(monstruo);
                CV.imprimirTurnoMago(mago1, monstruo);
            }
            comprobarJefe(bosque);

            // Turno de los monstruo si sigue con vida
            bosque.getMonstruos().forEach(
                monstruo -> {
                    if (monstruo.getVida() > 0) {
                        if (monstruo.isCongelado()) {
                            // Si está congelado, pierde el turno pero se descongela
                            monstruo.setCongelado(false);
                            System.out.println("El monstruo está congelado");
                        } else {
                            // Si no está congelado, ataca a un mago
                            Random aleatorio = new Random();
                            int magoAleatorio = aleatorio.nextInt(2)+1;
                            switch (magoAleatorio) {
                                case 1:
                                    monstruo.atacar(mago1);
                                    CV.imprimirTurnoMonstruo(monstruo, mago1);
                                    break;
                                case 2:
                                    monstruo.atacar(mago2);
                                    CV.imprimirTurnoMonstruo(monstruo, mago2);
                                    break;
                            }
                        }
                    }
                }
            );


            // Turno del dragon
            dragon.exhalar(bosque.getMonstruoJefe());
            CV.imprimirTurnoDragon(dragon, bosque.getMonstruoJefe());

            comprobarJefe(bosque);

            if (mago1.getVida()>0||mago2.getVida()>0) {
            }else{
                batalla = false;
            }
            if (comprobarMonstruos(bosque)) {
            }else{
                batalla = false;
            }
        } while (batalla==true);

        /**
         * Determina el ganador de la batalla según quién tenga vida restante
         */
        String ganador = "";

        if (mago1.getVida() > 0 || mago2.getVida() > 0) {
            ganador = "Los magos :D";
        } else {
            ganador = "Los monstruos D:";
        }

        // Mostrar el ganador en consola
        CV.imprimirGanador(ganador);
    }

    public void comprobarJefe(Bosque bosque){
    //Comprobacion del monstruo jefe
        if (bosque.getMonstruoJefe().getVida()<=0) {
            bosque.getMonstruos().forEach(
                monstruo -> {
                    if (monstruo.getVida()>0) {
                        bosque.setMonstruoJefe(monstruo);
                        return;
                    }
                } 
            );
        }
    }

    public boolean comprobarMonstruos(Bosque bosque) {
    //Comprobacion de monstruos con vida
    if (bosque.getMonstruoJefe().getVida() <= 0) {
        for (Monstruo monstruo : bosque.getMonstruos()) {
            if (monstruo.getVida() > 0) {
                bosque.setMonstruoJefe(monstruo);
                return true;
            }
        }
    }
    return false;
}
}
