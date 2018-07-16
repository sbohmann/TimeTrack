package at.yeoman.timeTrack;

import javax.swing.*;

import at.yeoman.timeTrack.storage.Storage;

class MainWindow {
    private Storage storage;
    private JFrame mainFrame;
    private MainPanel mainPanel;

    MainWindow(Storage storage) {
        this.storage = storage;
    }

    void show() {
        mainFrame = new JFrame("Time Track");
        mainPanel = new MainPanel();
    }
}
