package net.tnn1nja.scint;

import java.io.File;
import java.util.ArrayList;

import static net.tnn1nja.scint.Init.main;

public class Reader {

    //Gen AppData


    //Update array
    public void updateSounds() {
        //Variables
        ArrayList<String> sounds = new ArrayList<String>();

        //Get Resources
        File dir;

        /*
        for (File f : dir.listFiles()) {
            if (f.getName().endsWith(".wav")) {
                sounds.add(f.getName().replace(".wav", ""));
            }
        }
         */

        main.sounds = (String[]) sounds.toArray();
    }
}
