package view;

import DAO.DoctorDAO;
import model.Doctor;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminView extends JFrame {
    SessionFactory sessionFactory;
    DoctorDAO doctorDAO;


    public AdminView(SessionFactory sessionFactory, DoctorDAO doctorDAO) {
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
    this.setBounds(0, 0, 800, 400);
        setContentPane(contentPane);
        contentPane.add(scrollPane);

        JLabel idDel= new JLabel("Id to delete");
        idDel.setBounds(10, 10, 100, 20);
        contentPane.add(idDel);

        //JTextField idDeleteField = new JTextField();
        //idDeleteField.setBounds(300, 100, 430, 290);
       // contentPane.add(idDeleteField);

        //JButton deleteButton = new JButton("Delete");
        //deleteButton.setBounds(30, 50, 200, 20);
        JTextField idDeleteField = new JTextField();
        idDeleteField.setBounds(90, 5, 35, 30);
        contentPane.add(idDeleteField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(30, 40, 200, 20);
        contentPane.add(deleteButton);
        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(idDeleteField.getText());
            for(Doctor doctor : doctors)
                if(doctor.getId() == id)
                    doctorDAO.delete(doctor);

            JOptionPane.showMessageDialog(null, "Deleted");
        });

        JLabel title;
        JLabel name;
        JTextField tname;
        JLabel mno;
        JTextField tmno;
        JLabel pass;
        JTextField passt;
        JLabel loc;
        JTextField loct;
        JLabel blood;
        JComboBox bloodt;


        // constructor, to initialize the components
        // with default values.


        JLabel title2 = new JLabel("Add Doctor");
        title2.setFont(new Font("Arial", Font.PLAIN, 20));
        title2.setSize(300, 30);
        title2.setLocation(160, 70);
        contentPane.add(title2);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 30);
        name.setLocation(50, 100);
        contentPane.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 30);
        tname.setLocation(120, 100);
        contentPane.add(tname);

        mno = new JLabel("Email");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setSize(100, 20);
        mno.setLocation(50, 150);
        contentPane.add(mno);

        tmno = new JTextField();
        tmno.setFont(new Font("Arial", Font.PLAIN, 15));
        tmno.setSize(190, 30);
        tmno.setLocation(120, 150);
        contentPane.add(tmno);

        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(100, 20);
        pass.setLocation(20, 200);
        contentPane.add(pass);

        passt = new JPasswordField();
        passt.setFont(new Font("Arial", Font.PLAIN, 15));
        passt.setSize(190, 30);
        passt.setLocation(120, 200);
        contentPane.add(passt);

        loc = new JLabel("Location");
        loc.setFont(new Font("Arial", Font.PLAIN, 20));
        loc.setSize(100, 30);
        loc.setLocation(40, 250);
        contentPane.add(loc);

        loct = new JTextField();
        loct.setFont(new Font("Arial", Font.PLAIN, 15));
        loct.setSize(190, 30);
        loct.setLocation(120, 250);
        contentPane.add(loct);

        JButton sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 30);
        sub.setLocation(150, 300);
        contentPane.add(sub);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loc = loct.getText();
                String name = tname.getText();
                String email = tmno.getText();
                String password = passt.getText();
                doctorDAO.create(new Doctor(name, email, password,loc));
                loct.setText("");
                tname.setText("");
                tmno.setText("");
                passt.setText("");
                JOptionPane.showMessageDialog(null, "Added");

            }
        });
        setTitle("Doctor CRUD");



        JLabel idUp= new JLabel("Id to update");
        idUp.setBounds(380, 50, 100, 20);
        contentPane.add(idUp);

        JTextField idUpdateField = new JTextField();
        idUpdateField.setBounds(500, 45, 35, 30);
        contentPane.add(idUpdateField);

        JComboBox cols= new JComboBox(new String [] {"name","email","password","location"});
        cols.setBounds(500, 80, 100, 20);
        contentPane.add(cols);

        JLabel colUp= new JLabel("Column to update");
        colUp.setBounds(380, 80, 100, 20);
        contentPane.add(colUp);

        JTextField colUpdateField = new JTextField();
        colUpdateField.setBounds(500, 110, 100, 30);
        contentPane.add(colUpdateField);

        JLabel valUp= new JLabel("New value");
        valUp.setBounds(380, 110, 100, 20);
        contentPane.add(valUp);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(400, 170, 200, 30);
        contentPane.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idUpdateField.getText());
                String col = (String) cols.getSelectedItem();
                String val = colUpdateField.getText();
                for(Doctor doctor : doctors){
                    if(doctor.getId() == id) {
                        if(col.equals("name"))
                            doctor.setName(val);
                        else if(col.equals("email"))
                            doctor.setEmail(val);
                        else if(col.equals("password"))
                            doctor.setPassword(val);
                        else if(col.equals("location"))
                            doctor.setLocation(val);
                    }
                    doctorDAO.update(doctor);
                }
                JOptionPane.showMessageDialog(null, "Updated");
            }
        });

    JLabel updateDoctor = new JLabel("Update Doctor");
    updateDoctor.setFont(new Font("Arial", Font.PLAIN, 20));
    updateDoctor.setSize(300, 30);
    updateDoctor.setLocation(450, 15);
    contentPane.add(updateDoctor);




        contentPane.setLayout(null);
       // contentPane.add(deleteButton);
        }



    }








