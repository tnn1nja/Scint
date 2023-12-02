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


    //Create GUI
    public void launch(Main main) {

        //Base Settings
        JFrame frame = new JFrame("Scint - DND Noises.");
        frame.setSize(410, 265);
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
        JLabel delayLabel = new JLabel("Current Delay: ...");
        delayLabel.setBounds(15, 200, 185, 20);
        JLabel lastLabel = new JLabel("Last Played: ...");
        lastLabel.setBounds(225, 200, 180, 20);
        JLabel minLabel = new JLabel("Minimum Delay (Mins)");
        minLabel.setBounds(10, 5, 135, 25);
        JLabel maxLabel = new JLabel("Maximum Delay (Mins)");
        maxLabel.setBounds(10, 75, 140, 30);


        //Buttons
        JToggleButton muteButton = new JToggleButton("Mute", false);
        muteButton.setBounds(275, 165, 100, 25);
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.player.toggleMute();
                if(muteButton.getLabel().equalsIgnoreCase("Muted")) {
                    muteButton.setLabel("Mute");
                }else{
                    muteButton.setLabel("Muted");
                }
            }
        });

        JButton mp3Button = new JButton("Open MP3 Folder");
        mp3Button.setBounds(15, 165, 140, 25);
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



        //Create Sliders
            //Minimum Slider
        JSlider minSlider = new JSlider(0, 30, (int) main.player.minMins);
        JTextField minText = new JTextField(String.valueOf(main.player.minMins), 5);
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
                main.player.minMins = s.getValue();
                minText.setText(String.valueOf(minSlider.getValue()));
                System.out.println("Minimum Minutes Updated: " + main.player.minMins);

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
        JSlider maxSlider = new JSlider(0, 30, (int) main.player.maxMins);
        JTextField maxText = new JTextField(String.valueOf(main.player.maxMins),5);
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
                main.player.maxMins = s.getValue();
                maxText.setText(String.valueOf(maxSlider.getValue()));
                System.out.println("Maximum Minutes Updated: " + main.player.maxMins);

            }
        });

        minText.addActionListener(new ActionListener() {
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

        //Enable
        frame.add(panel);
        frame.setVisible(true);
    }
}
