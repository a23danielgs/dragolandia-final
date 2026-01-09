package com.example;

import com.example.controller.BattleController;
import com.example.model.Bosque;
import com.example.model.Dragon;
import com.example.model.Mago;
import com.example.model.Monstruo;
import com.example.view.ConsolaView;

public class Main {
    public static void main(String[] args) {

        ConsolaView view = new ConsolaView();
        BattleController controller = new BattleController();

        // Leer datos
        
        Monstruo mons1 = view.crearMonstruo();
        Monstruo mons2 = view.crearMonstruo();
        Monstruo mons3 = view.crearMonstruo();

        Bosque bosque = view.crearBosque(mons1);
        bosque.añadirMonstruo(mons2);
        bosque.añadirMonstruo(mons3);

        Dragon dragon = view.crearDragon(bosque);
        
        Mago ma1 = view.crearMago();
        Mago ma2 = view.crearMago();

        controller.simularBatalla(ma1, ma2, dragon, bosque);
    }
}