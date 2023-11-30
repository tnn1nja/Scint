package net.tnn1nja.scint;

import java.util.Random;

public class Main {

    //Constants
    Random rand = new Random();
    int coeff = 1000;//*60;

    //Variables
    int minMins;
    int maxMins;
    int wait;


    //Runs at Start Up
    public void onStart(){
        minMins = 5;
        maxMins = 10;
        newPause();
    }

    //Generates New Pause Thread
    public void newPause(){
        wait = (rand.nextInt(maxMins*coeff) + minMins*coeff);

        Thread timer = new Thread(() -> {
            try{
                Thread.sleep(wait);
                playSound();
                newPause();

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        timer.start();
    }

    //Plays a Sound
    public void playSound(){
        System.out.println("Noise");
    }
}
