package view;

import DAO.DoctorDAO;
import DAO.DonatorDAO;
import model.Donator;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonatorView extends JFrame {
    SessionFactory sessionFactory;
    DonatorDAO donatorDAO;
    Donator donator;
    DoctorDAO doctorDAO;

    public DonatorView(SessionFactory sessionFactory, Donator donator, DonatorDAO donatorDAO, DoctorDAO doctorDAO) {
        this.sessionFactory = sessionFactory;
        this.donator = donator;
        this.donatorDAO = donatorDAO;
        this.doctorDAO = doctorDAO;


        JPanel contentPane;
        contentPane = new JPanel();
        setTitle("Donator");


        contentPane.setBounds(500, 500, 2000, 1500);
        this.setBounds(0, 0, 1200, 400);
        setContentPane(contentPane);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JButton delete= new JButton("Delete Account");
        delete.setBounds(10, 10, 200, 20);
        contentPane.add(delete);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                donatorDAO.delete(donator);
                DonatorView.this.dispose();
            }
        });

        JLabel idDel= new JLabel("Edit account");
        idDel.setFont(new java.awt.Font("Tahoma", 1, 20));
        idDel.setBounds(300, 40, 300, 20);
        contentPane.add(idDel);


        JComboBox cols= new JComboBox(new String [] {"name","email","password","location"});
        cols.setBounds(400, 80, 100, 20);
        contentPane.add(cols);

        JLabel colUp= new JLabel("Column to update");
        colUp.setBounds(280, 80, 100, 20);
        contentPane.add(colUp);

        JTextField colUpdateField = new JTextField();
        colUpdateField.setBounds(400, 110, 100, 30);
        contentPane.add(colUpdateField);

        JLabel valUp= new JLabel("New value");
        valUp.setBounds(180, 110, 100, 20);
        contentPane.add(valUp);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(300, 170, 200, 30);
        contentPane.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String col = (String) cols.getSelectedItem();
                String val = colUpdateField.getText();
                if(col.equals("name"))
                    donator.setName(val);
                else if(col.equals("email"))
                    donator.setEmail(val);
                else if(col.equals("password"))
                    donator.setPassword(val);
                else if(col.equals("location"))
                    donator.setLocation(val);
                colUpdateField.setText("");
                donatorDAO.update(donator);
                JOptionPane.showMessageDialog(null, "Updated");
            }
        });

        JButton locationButton = new JButton("View available locations");
        locationButton.setBounds(300, 210, 200, 30);
        contentPane.add(locationButton);

        locationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LocationView(sessionFactory, doctorDAO);
            }
        });



        contentPane.setLayout(null);



    }


}
