package view;

import javax.swing.*;

public class DoctorFrame extends JFrame {

    public DoctorFrame() {
        super("Doctor");
        JLabel title = new JLabel("Doctor");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
