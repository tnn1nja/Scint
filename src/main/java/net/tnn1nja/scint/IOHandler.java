package net.tnn1nja.scint;

import java.io.*;
import java.util.ArrayList;

public class IOHandler {

    //Consts
    static File sndFldr = new File(System.getenv("APPDATA") + "\\" + ".scint" + "\\" + "media");


    //Check AppData
    public boolean isAppData(){
        return (sndFldr.exists() && sndFldr.listFiles() != null);
    }

    //Gen AppData
    public void genAppData() {
        sndFldr.mkdirs();

        InputStream is = getClass().getClassLoader().getResourceAsStream("media/meta.txt");
        InputStreamReader isr = new InputStreamReader(is);
        LineNumberReader lnr = new LineNumberReader(isr);

        try {
            InputStream icoIn = getClass().getClassLoader().getResourceAsStream("icons/icon.ico");
            FileOutputStream icoOut = new FileOutputStream(System.getenv("APPDATA") + "/.scint/icon.ico");
            icoIn.transferTo(icoOut);
            icoIn.close();
            icoOut.close();

            boolean reading = true;
            while (reading) {
                String filename = lnr.readLine();
                if (filename == null) {
                    reading = false;
                } else {
                    filename = filename.trim();
                    InputStream is2 = getClass().getClassLoader().getResourceAsStream( "media/data/" + filename);
                    FileOutputStream fos2 = new FileOutputStream(sndFldr + "/" + filename);
                    is2.transferTo(fos2);
                    is2.close();
                    fos2.close();
                }
            }
            lnr.close();
            System.out.println("AppData Folder Instantiated.");
        } catch(IOException e) {
            System.out.println("Invalid Resources.");
            e.printStackTrace();
        }
    }


    //Update Sounds Array
    public ArrayList<String> getSounds() {
        File[] sndFiles = sndFldr.listFiles();
        ArrayList<String> newSounds = new ArrayList<String>();

        for(File f: sndFiles){
            if(f.getName().endsWith(".mp3")){
                newSounds.add(f.getName());
            }
        }

        return newSounds;
    }

    //Get Sound Directory
    public FileInputStream getSound(String soundname){
        try{
            return new FileInputStream(sndFldr + "/" + soundname);
        }catch(IOException e){
            return null;
        }
    }

}

