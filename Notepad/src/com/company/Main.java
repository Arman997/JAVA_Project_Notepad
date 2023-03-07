package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;


public class Main {
    public static void main(String[] args){

        JTabbedPane tabs = new JTabbedPane();
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("file");
        JFileChooser f = new JFileChooser();

        JMenuItem newFile = new JMenuItem("New File");
        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        menu.add(file);

        JFrame frame = new JFrame("notePade");
        frame.setSize(720,800);
        frame.setJMenuBar(menu);
        frame.add(tabs);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#FFA919"));
        frame.setVisible(true);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextArea area = new JTextArea();
                area.setLineWrap(true);
                 scroll scroll = new scroll(area);
                tabs.addTab("new File", scroll);
            }
        });
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              if(tabs.countComponents() != 0) {
                  scroll area = (scroll) tabs.getSelectedComponent();
                  String output = area.getText();
                  File file = f.getSelectedFile();
                  try {
                      FileOutputStream writer = new FileOutputStream(file);
                      writer.write(output.getBytes());
                  }    catch (IOException eq) {
                        eq.printStackTrace();
                    }
                }
              }
        });
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = f.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = f.getSelectedFile();
                    try {
                        String content = new String(Files.readAllBytes(file.toPath()));
                        JTextArea area = new JTextArea(content);
                        area.setLineWrap(true);
                        scroll scroll = new scroll(area);
                        tabs.addTab(file.getName(), scroll);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

}