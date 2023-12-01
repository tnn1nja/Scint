package net.tnn1nja.scint;

import java.util.Random;

public class Main {

    // Const/Var
    static Random rand = new Random();
    static IOHandler ioHand = new IOHandler();
    static SoundPlayer player = new SoundPlayer();
    int coeff = 1000;//*60;
    int minMins = 5;
    int maxMins = 10;


    // Runs at Start Up
    public void onStart() {
        if(!ioHand.isAppData()){
            ioHand.genAppData();
        }
        //newSoundDelay();
        player.playSound();
    }

    // Generates New Pause Thread
    public void newSoundDelay(){
        Thread timer = new Thread(() -> {
            try{
                Thread.sleep(rand.nextInt(maxMins*coeff) + (long) minMins *coeff);
                //player.playSound();
                newSoundDelay();

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        timer.start();
    }

}