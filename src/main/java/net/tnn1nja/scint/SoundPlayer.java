package net.tnn1nja.scint;

import java.io.FileInputStream;
import java.util.ArrayList;
import javazoom.jl.player.Player;

import static net.tnn1nja.scint.Main.ioHand;
import static net.tnn1nja.scint.Main.rand;

public class SoundPlayer {

    // Plays a Sound
    public void playSound() {
        ArrayList<String> sounds = ioHand.getSounds();
        String chosen = sounds.get(rand.nextInt(sounds.size()));
        FileInputStream soundPath = ioHand.getSound(chosen);

        if(soundPath != null) {
            try {
                Player clip = new Player(soundPath);
            } catch (Exception e) {
                System.out.println("Sound File Failed to Play, Skipping...");
            }

        }else{
            System.out.println("Sound File Not Found, Skipping...");
        }
    }
}
