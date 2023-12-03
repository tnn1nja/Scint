package net.tnn1nja.scint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Window {

    public JLabel delayLabel = new JLabel();
    public JLabel lastLabel = new JLabel();
    public JLabel soundsLoaded = new JLabel();

    //Setters
    public void setDelayLabel(String label){
        delayLabel.setText("Current Delay: " + label + " minutes.");
    }
    public void setLastLabel(String label){
        lastLabel.setText("Last Played: " + label);
    }
    public void setSoundsLoaded(int value){
        soundsLoaded.setText("MP3s Loaded: " + String.valueOf(value));
    }


    //Create GUI
    public void launch(Main main) {

        //Base Settings
        JFrame frame = new JFrame("Scint - DND Noises.");
        frame.setSize(410, 254);
        frame.setResizable(false);

        //On Close Operation
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                main.onClose();
            }

        });

        //Labels
        Font smallFont = new Font("Dialog", Font.ITALIC, 10);
        setDelayLabel("-");
        delayLabel.setBounds(15, 191, 185, 20);
        delayLabel.setFont(smallFont);
        setLastLabel("...");
        lastLabel.setBounds(145, 191, 145, 20);
        lastLabel.setFont(smallFont);
        setSoundsLoaded(0);
        soundsLoaded.setBounds(300, 191, 145, 20);
        soundsLoaded.setFont(smallFont);

        JLabel minLabel = new JLabel("Minimum Delay (Mins)");
        minLabel.setBounds(10, 5, 135, 25);
        JLabel maxLabel = new JLabel("Maximum Delay (Mins)");
        maxLabel.setBounds(10, 75, 140, 30);


        //Buttons
        JToggleButton muteButton = new JToggleButton("\uD83D\uDD0A", false);
        muteButton.setBounds (330, 165, 50, 25);
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.player.toggleMute();
                if(muteButton.getLabel().equalsIgnoreCase("\uD83D\uDD07")) {
                    muteButton.setLabel("\uD83D\uDD0A");
                }else{
                    muteButton.setLabel("\uD83D\uDD07");
                }
            }
        });

        JButton mp3Button = new JButton("Open MP3 Folder");
        mp3Button.setBounds (15, 165, 130, 25);
        mp3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(IOHandler.sndFldr);
                    System.out.println("Opening AppData Folder.");
                } catch (IOException ex) {
                    System.out.println("AppData Folder Not Found.");
                }
            }
        });

        JButton regenButton = new JButton("⟳");
        regenButton.setBounds (150, 165, 50, 25);
        regenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Init.ioHand.genAppData();
            }
        });

        JButton timerButton = new JButton("⊘");
        timerButton.setBounds (275, 165, 50, 25);
        timerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.player.interruptTimer(main.window);
            }
        });



        //Create Sliders
        JSlider minSlider = new JSlider(0, 30, (int) main.player.minMins);
        JTextField minText = new JTextField(String.valueOf(main.player.minMins), 5);
        JSlider maxSlider = new JSlider(0, 30, (int) main.player.maxMins);
        JTextField maxText = new JTextField(String.valueOf(main.player.maxMins),5);

            //Minimum Slider
        minSlider.setBounds(65, 25, 320, 60);
        minText.setBounds(15, 40, 45, 25);

        minSlider.setOrientation(JSlider.HORIZONTAL);
        minSlider.setMinorTickSpacing(1);
        minSlider.setMajorTickSpacing(5);
        minSlider.setPaintTicks(true);
        minSlider.setPaintLabels(false);
        minSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                minText.setText(String.valueOf(minSlider.getValue()));
                if(maxSlider.getValue() < s.getValue()){
                    maxSlider.setValue(s.getValue());
                }
                if(!s.getValueIsAdjusting()){
                    main.player.minMins = s.getValue();
                    System.out.println("Minimum Minutes Updated: " + main.player.minMins);
                }
            }
        });

        minText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int value = Integer.parseInt(minText.getText());
                    if (value >= minSlider.getMinimum() && value <= minSlider.getMaximum()){
                        minSlider.setValue(value);
                    }else{
                        minText.setText(String.valueOf(minSlider.getValue()));
                    }
                }catch(NumberFormatException ex){
                    minText.setText(String.valueOf(minSlider.getValue()));
                }
            }
        });


            //Maximum Slider
        maxSlider.setBounds(65, 100, 320, 60);
        maxText.setBounds(15, 110, 45, 25);

        maxSlider.setOrientation(JSlider.HORIZONTAL);
        maxSlider.setMinorTickSpacing(1);
        maxSlider.setMajorTickSpacing(5);
        maxSlider.setPaintTicks(true);
        maxSlider.setPaintLabels(false);
        maxSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                maxText.setText(String.valueOf(s.getValue()));
                if(minSlider.getValue() > s.getValue()){
                    minSlider.setValue(s.getValue());
                }
                if(!s.getValueIsAdjusting()) {
                    main.player.maxMins = s.getValue();
                    System.out.println("Maximum Minutes Updated: " + main.player.maxMins);
                }

            }
        });

        maxText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int value = Integer.parseInt(maxText.getText());
                    if (value >= maxSlider.getMinimum() && value <= maxSlider.getMaximum()){
                        maxSlider.setValue(value);
                    }else{
                        maxText.setText(String.valueOf(maxSlider.getValue()));
                    }
                }catch(NumberFormatException ex){
                    maxText.setText(String.valueOf(maxSlider.getValue()));
                }
            }
        });



        //Create Panel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(385, 223));
        panel.setLayout(null);
        panel.add(delayLabel);
        panel.add(lastLabel);
        panel.add(minSlider);
        panel.add(minText);
        panel.add(maxSlider);
        panel.add(maxText);
        panel.add(muteButton);
        panel.add(maxLabel);
        panel.add(minLabel);
        panel.add(mp3Button);
        panel.add(regenButton);
        panel.add(soundsLoaded);
        panel.add(timerButton);

        //Enable
        frame.add(panel);
        frame.setVisible(true);
    }
}
