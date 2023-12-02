package net.tnn1nja.scint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    //Create GUI
    public void launch(Main main) {

        //Base Settings
        JFrame frame = new JFrame("Scint - DND Noises.");
        frame.setSize(350, 150);
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



        //Mute Button
        JButton muteButton = new JButton("Mute");
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


        //MinMins Slider
        JSlider minSlider = new JSlider(JSlider.HORIZONTAL, 0, 30, 5);
        JTextField minText = new JTextField(String.valueOf(minSlider.getValue()));

        minSlider.setMajorTickSpacing(10);
        minSlider.setMinorTickSpacing(1);
        minSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                if (!s.getValueIsAdjusting()){
                    main.player.minMins = s.getValue();
                    minText.setText(String.valueOf(minSlider.getValue()));
                }
            }
        });

        minText.setEditable(true);
        minText.setPreferredSize(new Dimension(50, 20));
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


        //Create and Assign Panels
        JPanel sliderPanel = new JPanel(new GridLayout(2, 1));
        sliderPanel.add(minText);
        sliderPanel.add(minSlider);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(muteButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(sliderPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        //Enable
        frame.setVisible(true);
    }
}
