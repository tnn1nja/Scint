package net.tnn1nja.scint;

import java.io.*;
import java.util.ArrayList;

import static net.tnn1nja.scint.Init.main;

public class Reader {

    //Consts
    File sndFldr = new File(System.getenv("APPDATA") + "\\" + ".scint" + "\\" + "snd");



    //Gen AppData
    public void genAppData() {
        if(!sndFldr.exists()){
            sndFldr.mkdirs();
        }

        InputStream is = getClass().getClassLoader().getResourceAsStream("sndList.txt");
        InputStreamReader isr = new InputStreamReader(is);
        LineNumberReader lnr = new LineNumberReader(isr);

        try {
            boolean reading = true;
            while (reading) {
                String filename = lnr.readLine();
                if (filename == null) {
                    reading = false;
                } else {
                    filename = filename.trim();
                    InputStream is2 = getClass().getClassLoader().getResourceAsStream( "snd" + "/" + filename);
                    FileOutputStream fos2 = new FileOutputStream(sndFldr + "/" + filename);
                    is2.transferTo(fos2);
                    is2.close();
                    fos2.close();
                    System.out.println(filename + " copied.");
                }
            }
            lnr.close();
        }catch(IOException e) {
            e.printStackTrace();
        }

    }



    //Update Sounds Array
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
