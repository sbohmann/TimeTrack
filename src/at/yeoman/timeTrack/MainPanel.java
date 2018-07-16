package at.yeoman.timeTrack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MainPanel {
    final JPanel view;

    private JButton button;
    private boolean running;

    private long runningTimeSeconds;
    private Runnable startHandler;
    private Runnable stopHandler;

    MainPanel() {
        view = new JPanel();
        setupView();
        createButton();
        setRunning(false);
    }

    public void setRunningTimeSeconds(long runningTimeSeconds) {
        this.runningTimeSeconds = runningTimeSeconds;
    }

    public void setStartHandler(Runnable startHandler) {
        this.startHandler = startHandler;
    }

    public void setStopHandler(Runnable stopHandler) {
        this.stopHandler = stopHandler;
    }

    private void setupView() {
        view.setPreferredSize(new Dimension(800, 600));
        view.setLayout(new BorderLayout());
    }

    private void createButton() {
        button = new JButton();
        button.setForeground(Color.white);
        view.add(button, BorderLayout.CENTER);
        button.addActionListener(this::buttonClicked);
    }

    private void buttonClicked(ActionEvent actionEvent) {
        setRunning(!running);
    }

    private void setRunning(boolean running) {
        this.running = running;
        if (running) {
            button.setBackground(Color.red);
            button.setText("Stop");
        } else {
            button.setBackground(Color.green);
            button.setText("Start");
        }
    }
}
