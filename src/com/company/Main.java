package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.text.JTextComponent;
import java.util.Random;


public class Main {

    public static String hours;
    public static String minutes;
    public static String alarmTime;
    public static JLabel alarmSet = new JLabel("");
    public static boolean alarmOn = false;



    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {



        JFrame mainWin = new JFrame("SnoozerLoser");
        mainWin.setSize(700, 400);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPan = new JPanel(null);
        JLabel mainText = new JLabel("SnoozerLoser");
        mainText.setBounds(25, 10, 400, 50);
        mainText.setFont(new Font("Verdana", Font.PLAIN, 35));



        String[] choices = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" };

        final JComboBox<String> hr = new JComboBox<String>(choices);
        hr.setLocation(50, 100);
        hr.setSize(50, 50);
        hr.setVisible(true);


        String[] choices2 = { ":00", ":01",":02",":03",":04",":05",":06",":07",":08",":09",":10",":11",":12",":13",":14",":15",":16",":17",":18",":19",":20",":21",":22",":23",":24",":25",":26",":27",":28",":29",":30",":31",":32",":33",":34",":35",":36",":37",":38",":39",":40",":41",":42",":43",":44",":45",":46",":47",":48",":49",":50",":51",":52",":53",":54",":55",":56",":57",":58",":59" };

        final JComboBox<String> min = new JComboBox<String>(choices2);
        min.setLocation(100, 100);
        min.setSize(50, 50);
        min.setVisible(true);

        JButton button = new JButton("Set Alarm");

        button.setBounds(200, 100, 300, 100);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                hours = (String) hr.getSelectedItem();
                minutes = (String) min.getSelectedItem();
                alarmTime = (hours + minutes + ":" + "00");

                alarmSet.setBackground(Color.RED);
                alarmSet.setText("Alarm set for " + hours + minutes);
                alarmSet.setVisible(true);

                button.setText("Alarm Set!");



            }
        });

        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

        String randomWord = "";

        for (int i = 0; i < 25; i++)
        {
            Random myRandom = new Random();
            randomWord = randomWord + alphabet[myRandom.nextInt(25) + 1];
        }



        JTextField typeArea = new JTextField(80);
        typeArea.setBounds(100, 300, 150, 200);
        typeArea.setSize(500, 50);
        typeArea.setVisible(false);



        alarmSet.setBounds(425, 10, 400, 50);
        alarmSet.setFont(new Font("Verdana", Font.PLAIN, 25));
        alarmSet.setVisible(false);

        JLabel typeInstruct = new JLabel("Type blank to disable.");
        typeInstruct.setBounds(100, 175, 500, 200);
        typeInstruct.setFont(new Font("Verdana", Font.PLAIN, 15));
        typeInstruct.setVisible(false);



        mainPan.add(typeInstruct);
        button.setVisible(true);
        mainPan.add(button);
        mainPan.add(min);
        mainPan.add(hr);
        mainPan.add(mainText);
        mainPan.add(alarmSet);
        mainPan.add(typeArea);
        mainWin.add(mainPan);
        mainWin.setVisible(true);



        while (true) {
            Calendar c = Calendar.getInstance();
            String currTime = new SimpleDateFormat("kk:mm:ss").format(c.getTime());
            System.out.println(currTime);


            if (currTime.equals(alarmTime))
            {

                alarmOn = true;
            }

            while(alarmOn)
            {
                File buzz = new File("src/com/company/Alarm-Clock Sound!!!.wav");
                PlaySound(buzz);
                typeArea.setVisible(true);
                typeInstruct.setText("Type " + "'" + randomWord + "'" + " to disable.");
                typeInstruct.setVisible(true);
                alarmSet.setText("Wake Up!");
                String userTypedValue = typeArea.getText();

                if (userTypedValue.equals(randomWord))
                {
                    alarmOn = false;
                    PlaySound(buzz);
                    alarmSet.setText("Good Morning.");
                }




            }


        }
    }

    static void PlaySound(File Sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(Sound));
        if (alarmOn)
        {
            clip.start();
        }
        else
        {
            clip.stop();
        }


    }






}
