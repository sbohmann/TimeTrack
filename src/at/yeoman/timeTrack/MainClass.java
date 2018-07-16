package at.yeoman.timeTrack;

import java.io.IOException;

import javax.swing.*;

import at.yeoman.timeTrack.storage.Storage;

class MainClass {
    public static void main(String[] args)
            throws IOException {
        setLookAndFeel();
        new MainWindow(new Storage()).show();
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
