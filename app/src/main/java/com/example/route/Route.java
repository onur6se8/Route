package com.example.route;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Route extends AppCompatActivity {

    MediaPlayer sonDemarrage;
    MediaPlayer sonPointMort;
    MediaPlayer sonAcceleration;
    MediaPlayer sonAccelerationTurbo;

    Timer timer = new Timer();

    private int newPosition = 0;
    private boolean voitureSon = true;

    @BindView(R.id.retour)
    public ImageButton retour;

    @BindView(R.id.ligne1)
    public View ligne1;
    @BindView(R.id.ligne2)
    public View ligne2;
    @BindView(R.id.ligne3)
    public View ligne3;
    @BindView(R.id.ligne4)
    public View ligne4;
    @BindView(R.id.ligne5)
    public View ligne5;
    @BindView(R.id.ligne6)
    public View ligne6;

    @BindView(R.id.boite_de_vitesse)
    public ImageView boiteDeVitesse;
    @BindView(R.id.turbo)
    public Button turbo;
    @BindView(R.id.superTurbo)
    public Button superTurbo;

    @BindView(R.id.voiture)
    public ImageButton voiture;
    @BindView(R.id.feu_turbo)
    public ImageView feuTurbo;

    @BindView(R.id.vitesse_sup)
    public  ImageButton vitesseSup;
    @BindView(R.id.vitesse_inf)
    public  ImageButton vitesseInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route);

        ButterKnife.bind(this);

        ligne1.setY(0);
        ligne2.setY(800);
        ligne3.setY(1600);
        ligne4.setY(2400);
        ligne5.setY(3200);
        ligne6.setY(4000);

        sonDemarrage = MediaPlayer.create(Route.this, R.raw.demarrage);
        sonPointMort = MediaPlayer.create(Route.this,R.raw.ferrari_point_mort);
        sonAcceleration = MediaPlayer.create(Route.this, R.raw.deppart);
        sonAccelerationTurbo = MediaPlayer.create(Route.this, R.raw.deppart2);

        sonDemarrage.start();
        sonPointMort.start();
        sonPointMort.setLooping(true);

        this.elementsGraphiques();
    }

    @Override
    protected void onResume() {

        timer.scheduleAtFixedRate( new TimerTask() {
            @Override
            public void run() {

                ligne1.setY(Lignes.Ligne(ligne1.getY(), newPosition));
                ligne2.setY(Lignes.Ligne(ligne2.getY(), newPosition));
                ligne3.setY(Lignes.Ligne(ligne3.getY(), newPosition));
                ligne4.setY(Lignes.Ligne(ligne4.getY(), newPosition));
                ligne5.setY(Lignes.Ligne(ligne5.getY(), newPosition));
                ligne6.setY(Lignes.Ligne(ligne6.getY(), newPosition));

            }
        }, 0, 1);

        super.onResume();
    }

    @Override
    protected void onPause() {

        timer.cancel();

        sonDemarrage.stop();
        sonPointMort.stop();
        sonAcceleration.stop();
        sonAccelerationTurbo.stop();

        super.onPause();
    }


                             //  ----------------------- Les éléments graphiques -------------------------------

    private void elementsGraphiques (){

        boiteDeVitesse.setOnClickListener(v -> {

            if ( vitesseSup.getVisibility() == View.VISIBLE){

                vitesseSup.setVisibility(View.INVISIBLE);
                vitesseInf.setVisibility(View.INVISIBLE);
            }
            else {

                vitesseSup.setVisibility(View.VISIBLE);
                vitesseInf.setVisibility(View.VISIBLE);
            }

        });

        vitesseSup.setOnClickListener(v -> {

            departVoiture(1);

            if( newPosition < 6){
                newPosition++;
            }
            boiteDeVitesseImage();
        });

        vitesseInf.setOnClickListener(v -> {

            departVoiture(1);

            if( newPosition > -2){
                newPosition--;
            }
            boiteDeVitesseImage();
        });


        vitesseSup.setOnLongClickListener(v -> {

            departVoiture(2);

            while (newPosition < 6){

                newPosition++;
            }

            sonPointMort.pause();

            boiteDeVitesseImage();
            return false;
        });

        vitesseInf.setOnLongClickListener(v -> {

            departVoiture(1);

            while (newPosition != 0){

                if( newPosition < 0 ){

                    newPosition++;
                }

                else{

                    newPosition--;
                }
            }

            boiteDeVitesseImage();

            return false;
        });

        turbo.setOnClickListener(v -> {

            departVoiture(2);

            newPosition = 8;
            Glide.with(boiteDeVitesse)
                    .load(R.drawable.vitesse_turbo)
                    .into(boiteDeVitesse);

        });

        superTurbo.setOnClickListener(v -> {

            departVoiture(2);

            newPosition = 10;
            Glide.with(boiteDeVitesse)
                    .load(R.drawable.vitesse_super_turbo)
                    .into(boiteDeVitesse);

        });

        voiture.setOnClickListener(v -> departVoiture(1) );

        retour.setOnClickListener(v -> finish());
    }

                        //  ----------------------- Image de la boite de vitesse -------------------------------

    private void boiteDeVitesseImage (){

        if ( newPosition > 6){
            newPosition = 6;
        }

        switch (newPosition)
        {
            case -1:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse_arriere)
                        .into(boiteDeVitesse);
                break;
            case 0:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse_nul)
                        .into(boiteDeVitesse);
                break;
            case 1:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse1)
                        .into(boiteDeVitesse);
                break;
            case 2:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse2)
                        .into(boiteDeVitesse);
                break;
            case 3:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse3)
                        .into(boiteDeVitesse);
                break;
            case 4:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse4)
                        .into(boiteDeVitesse);
                break;
            case 5:
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse5)
                        .into(boiteDeVitesse);
                break;
            case 6 :
                Glide.with(boiteDeVitesse)
                        .load(R.drawable.vitesse6)
                        .into(boiteDeVitesse);
                break;
        }

    }


    private void departVoiture (int numero){

        if ( voitureSon ){

            sonPointMort.pause();

            if( numero == 1){
                sonAcceleration.start();
                ObjectAnimator.ofFloat(voiture,"translationY", 0, -2000).setDuration(3000).start();
            }
            else if ( numero == 2){
                sonAccelerationTurbo.start();
                ObjectAnimator.ofFloat(voiture,"translationY", 0, 2000,-2000).setDuration(5000).start();
            }

            voitureSon = false;
        }
    }

}