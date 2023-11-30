package net.tnn1nja.scint;

import java.util.Random;

public class Main {

    // Const/Var
    static Random rand = new Random();
    static Reader read = new Reader();
    static String[] sounds;
    int coeff = 1000;//*60;
    int minMins = 5;
    int maxMins = 10;



    // Runs at Start Up
    public void onStart(){
        //read.updateSounds();
        newPause();
    }

    // Generates New Pause Thread
    public void newPause(){
        Thread timer = new Thread(() -> {
            try{
                Thread.sleep(rand.nextInt(maxMins*coeff) + (long) minMins *coeff);
                playSound();
                newPause();

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        timer.start();
    }

    // Plays a Sound
    public void playSound() {
        System.out.println("Noise");
    }

}