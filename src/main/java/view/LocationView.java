package view;

import DAO.DoctorDAO;
import model.Doctor;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.util.List;

public class LocationView extends JFrame {
    SessionFactory sessionFactory;
    DoctorDAO doctorDAO;

    public LocationView(SessionFactory sessionFactory, DoctorDAO doctorDAO) {
        this.sessionFactory = sessionFactory;
        this.doctorDAO = doctorDAO;



        List<Doctor> doctors = doctorDAO.readAll();
        Object[][] data = new Object[doctors.size()][2];
        for(int i = 0; i < doctors.size(); i++)
        {

            data[i][0] = doctors.get(i).getLocation();
            data[i][1] = doctors.get(i).getName();


        }

        String[] columnNames = {
                "Location",
                "Doctor Name",
                };


        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        table.setFillsViewportHeight(true);
        setVisible(true);
        JPanel contentPane;
        contentPane = new JPanel();


        contentPane.setBounds(500, 500, 2000, 1500);
        this.setBounds(0, 0, 600, 400);
        setContentPane(contentPane);
        contentPane.add(scrollPane);
    }


}
