package net.tnn1nja.scint;

import static net.tnn1nja.scint.Init.ioHand;

public class Main {

    // Const/Var
    SoundPlayer player = new SoundPlayer();
    Window window = new Window();


    // Runs at Start Up
    public void onStart() {
        if(!ioHand.isAppData()){
            ioHand.genAppData();
        }

        player.startSounds(5,10, window);
        window.launch(this);
    }

    //Runs at Close
    public void onClose(){
        System.out.println("Program Closing...");

        System.exit(0);
    }

}