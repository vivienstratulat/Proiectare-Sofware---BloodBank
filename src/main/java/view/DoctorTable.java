package view;

import DAO.DoctorDAO;
import model.Doctor;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.util.List;

//////////tabelul afiseaza tabelul cu doctori inainte de modificari asupra tabulului, pt a vedea modificarile trebuie sa te uiti in mysql workbench
//sau sa inchizi fereastra cu tabelul si sa te loghezi iar ca admin, si va aparea tabelul cu doctorii modificati
public class DoctorTable extends JFrame {
    SessionFactory sessionFactory;
    DoctorDAO doctorDAO;

    public DoctorTable(SessionFactory sessionFactory, DoctorDAO doctorDAO) {
        this.sessionFactory = sessionFactory;
        this.doctorDAO = doctorDAO;
        List<Doctor> doctors = doctorDAO.readAll();
        Object[][] data = new Object[doctors.size()][5];
        for(int i = 0; i < doctors.size(); i++)
        {
            data[i][0] = doctors.get(i).getId();
            data[i][1] = doctors.get(i).getLocation();
            data[i][2] = doctors.get(i).getEmail();
            data[i][3] = doctors.get(i).getName();
            data[i][4] = doctors.get(i).getPassword();

        }

        String[] columnNames = {"Id",
                "Location",
                "email",
                "name",
                "password"};


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
