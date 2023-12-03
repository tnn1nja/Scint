package net.tnn1nja.scint;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javazoom.jl.player.Player;

import static net.tnn1nja.scint.Init.ioHand;

public class SoundPlayer {

    //Consts + Vars
    Random rand = new Random();
    Window window;
    boolean muted = false;
    int coeff = 1000;//*60;
    long minMins;
    long maxMins;


    //Start
    public void startSounds(int min, int max, Window win){
        minMins = min;
        maxMins = max;
        window = win;
        newSoundDelay();
    }


    // Generates New Pause Thread
    private void newSoundDelay(){
        Thread timer = new Thread(() -> {
            try{
                long delay;
                if(maxMins == minMins) {
                    delay = minMins*coeff;
                }else {
                    delay = rand.nextLong((maxMins - minMins) * coeff) + minMins * coeff;
                }
                window.setDelayLabel((String.valueOf(delay/coeff)));
                System.out.println("Next delay: " + delay/1000 + " minutes. (" + minMins + "-" + maxMins + ")");
                Thread.sleep(delay);
                if(!muted) {
                    playSound();
                }
                newSoundDelay();

            } catch (Exception e){
                System.out.println("Failed to Start Delay.");
                newSoundDelay();
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
                window.setLastLabel(chosen);
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
