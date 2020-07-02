package com.example.route;

import java.util.List;

public class Lignes {

    public static float Ligne(float ligne, int newPosition){

        if ( ligne >= 4000 ){

            ligne = -800;
        }
        else if ( ligne <= -800){

            ligne = 4000;
        }

        ligne += newPosition;


        return ligne;
    }
}
