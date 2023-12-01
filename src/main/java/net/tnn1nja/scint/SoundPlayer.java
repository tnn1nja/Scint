package net.tnn1nja.scint;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.ArrayList;

import static net.tnn1nja.scint.Main.ioHand;
import static net.tnn1nja.scint.Main.rand;

public class SoundPlayer implements LineListener {

    boolean playbackCompleted;

    @Override
    public void update(LineEvent e){
        if(LineEvent.Type.START == e.getType()){
            System.out.println("Sound Playing.");
        }else if(LineEvent.Type.STOP == e.getType()){
            playbackCompleted = true;
            System.out.print("Sound Finished.");
        }
    }

    // Plays a Sound
    public void playSound() {
        ArrayList<String> sounds = ioHand.getSounds();
        String chosen = sounds.get(rand.nextInt(sounds.size()));
        InputStream is = ioHand.getSound(chosen);
    }
}
