package com.company;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class scroll extends JScrollPane {


    private JTextArea area;

    public scroll(JTextArea area) {
        super(area);
        this.area = area;
    }

    public String getText() {

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("File.txt"));
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fc.getSelectedFile();
            String output = area.getText();

            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            try {
                FileOutputStream writer = new FileOutputStream(file);
                writer.write(output.getBytes());

            } catch (IOException eq) {
                eq.printStackTrace();
            }

        }
        return null;
    }
}