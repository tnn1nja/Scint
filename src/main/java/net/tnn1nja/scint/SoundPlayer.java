package net.tnn1nja.scint;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javazoom.jl.player.Player;

import static net.tnn1nja.scint.Init.ioHand;

public class SoundPlayer {

    //Consts + Vars
    Random rand = new Random();
    boolean muted = false;
    int coeff = 1000;//*60;
    long minMins;
    long maxMins;


    //Start
    public void startSounds(int min, int max){
        minMins = min;
        maxMins = max;
        newSoundDelay();
    }


    // Generates New Pause Thread
    private void newSoundDelay(){
        Thread timer = new Thread(() -> {
            try{
                long delay = rand.nextLong((maxMins-minMins)*coeff) + minMins *coeff;
                System.out.println("Next delay: " + delay/1000 + " minutes.");
                Thread.sleep(delay);
                if(!muted) {
                    playSound();
                }
                newSoundDelay();

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        timer.start();
    }

    // Plays a Sound
    private void playSound() {
        ArrayList<String> sounds = ioHand.getSounds();
        String chosen = sounds.get(rand.nextInt(sounds.size()));
        FileInputStream soundPath = ioHand.getSound(chosen);

        if(soundPath != null) {
            try {
                Player clip = new Player(soundPath);
                System.out.println("Playing: " + chosen);
                clip.play();
                clip.close();
            } catch (Exception e) {
                System.out.println("Sound File Failed to Play, Skipping...");
            }

        }else{
            System.out.println("Sound File Not Found, Skipping...");
        }
    }


    //Toggle Mute
    public void toggleMute(){
        muted = !muted;
        System.out.println("Mute Toggled: " + muted);
    }

}
