import DAO.AdminDAO;
import DAO.DoctorDAO;
import DAO.DonatorDAO;
import db.DataBaseConfig;
import model.Admin;
import model.Doctor;
import model.Donator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import view.*;

import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;

    public static void main(String[] args) {
        Main m = new Main();
        m.setVisible(true);


        //doctorDAO.readAll().forEach(System.out::println);
        // doctorDAO.delete(p);
        // System.out.println(professorDAO.read("Popescu"));

    }

    public Main() {
        SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
       // Doctor p = new Doctor("nume", "email", "parola", "cluj");
        DoctorDAO doctorDAO = new DoctorDAO(sessionFactory);
        DonatorDAO donatorDAO = new DonatorDAO(sessionFactory);
        AdminDAO adminDAO = new AdminDAO(sessionFactory);
        //doctorDAO.create(p);
        Admin a= new Admin("admin","admin");
        adminDAO.create(a);


////////////////////////am popullat baza de date momentan de aici, apoi din interfata//////////////////////////////////
        /////////////CONT PT ADMIN: EMAIL:admin, PAROLA: admin///////////////////////////



       // Doctor p2 = new Doctor("nume2", "email2", "parola2", "cluj2");
       // doctorDAO.create(p2);
       // Donator d = new Donator("donator", "mail", "123", "turda", "B+");
       // donatorDAO.create(d);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 300, 1500);
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, 2000, 2000);
      //  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
       // contentPane.setLayout(null);
        this.setBounds(0, 0, 1000, 1000);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel.setBounds(450, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField.setBounds(420, 175, 160, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(420, 220, 160, 30);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(340, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(340, 200, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(420, 300, 100, 30);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {

                                           public void actionPerformed(ActionEvent e) {
                                               int flag = 0;
                                               String userName = textField.getText();
                                               String password = passwordField.getText();
                                               List<Doctor> doctors = doctorDAO.readAll();
                                               List<Donator> donators= donatorDAO.readAll();
                                               List<Admin> admins = adminDAO.readAll();
                                               for (Doctor doctor : doctors) {
                                                   if (doctor.getEmail().equals(userName) && doctor.getPassword().equals(password)) {
                                                       JOptionPane.showMessageDialog(Main.this, "Welcome Dr. " + userName + "!");
                                                     //  Main.this.dispose();
                                                       System.out.println("success");
                                                         DoctorFrame doctorFrame = new DoctorFrame();
                                                       break;
                                                   } else {
                                                       flag=1;
                                                   }
                                               }

                                               for(Donator donator : donators){
                                                   if(donator.getEmail().equals(userName) && donator.getPassword().equals(password)){
                                                       JOptionPane.showMessageDialog(Main.this, "Welcome " + userName + "!");
                                                       //Main.this.dispose();
                                                       System.out.println("success");
                                                       DonatorView donatorView = new DonatorView(sessionFactory,donator,donatorDAO,doctorDAO);
                                                       flag=0;
                                                       break;
                                                   }else{
                                                       flag=1;
                                                   }
                                               }
                                               for(Admin admin : admins){
                                                   if(admin.getEmail().equals(userName) && admin.getPassword().equals(password)){
                                                       JOptionPane.showMessageDialog(Main.this, "Welcome " + userName + "!");
                                                     //  Main.this.dispose();
                                                       System.out.println("success");
                                                       AdminView adminView = new AdminView(sessionFactory,doctorDAO);
                                                       DoctorTable doctorTable = new DoctorTable(sessionFactory,doctorDAO);

                                                       flag=0;
                                                       break;
                                                   }else{
                                                       flag=1;
                                                   }
                                               }
                                              // if(flag==1)
                                               //    JOptionPane.showMessageDialog(Main.this, "Sorry, Username or Password Error","Login Error!", JOptionPane.ERROR_MESSAGE);


                                               label = new JLabel("");
                                               label.setBounds(0, 0, 1008, 562);
                                               contentPane.add(label);
                                           }
                                       }
        );
         Container c;
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
            setTitle("Registration Form");
            setBounds(200, 20, 600, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);

            c = getContentPane();
            c.setLayout(null);

            title = new JLabel("Registration Form");
            title.setFont(new Font("Arial", Font.PLAIN, 30));
            title.setSize(300, 30);
            title.setLocation(80, 30);
            c.add(title);

            name = new JLabel("Name");
            name.setFont(new Font("Arial", Font.PLAIN, 20));
            name.setSize(100, 30);
            name.setLocation(50, 100);
            c.add(name);

            tname = new JTextField();
            tname.setFont(new Font("Arial", Font.PLAIN, 15));
            tname.setSize(190, 30);
            tname.setLocation(120, 100);
            c.add(tname);

            mno = new JLabel("Email");
            mno.setFont(new Font("Arial", Font.PLAIN, 20));
            mno.setSize(100, 20);
            mno.setLocation(50, 150);
            c.add(mno);

            tmno = new JTextField();
            tmno.setFont(new Font("Arial", Font.PLAIN, 15));
            tmno.setSize(190, 30);
            tmno.setLocation(120, 150);
            c.add(tmno);

            pass = new JLabel("Password");
            pass.setFont(new Font("Arial", Font.PLAIN, 20));
            pass.setSize(100, 20);
            pass.setLocation(20, 200);
            c.add(pass);

            passt = new JPasswordField();
            passt.setFont(new Font("Arial", Font.PLAIN, 15));
            passt.setSize(190, 30);
            passt.setLocation(120, 200);
            c.add(passt);

            loc = new JLabel("Location");
            loc.setFont(new Font("Arial", Font.PLAIN, 20));
            loc.setSize(100, 30);
            loc.setLocation(40, 250);
            c.add(loc);

            loct = new JTextField();
            loct.setFont(new Font("Arial", Font.PLAIN, 15));
            loct.setSize(190, 30);
            loct.setLocation(120, 250);
            c.add(loct);

            blood = new JLabel("BloodType");
            blood.setFont(new Font("Arial", Font.PLAIN, 20));
            blood.setSize(100, 20);
            blood.setLocation(20, 300);
            c.add(blood);

            bloodt = new JComboBox(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
            bloodt.setFont(new Font("Arial", Font.PLAIN, 15));
            bloodt.setSize(190, 30);
            bloodt.setLocation(120, 300);
            c.add(bloodt);

            JButton btn = new JButton("Submit");
            btn.setBounds(120, 400, 100, 30);
            c.add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = tname.getText();
                    String email = tmno.getText();
                    String password = passt.getText();
                    String location = loct.getText();
                    String bloodType = bloodt.getSelectedItem().toString();
                   // donator = new Donator(name, email, password, location, bloodType);
                    donatorDAO.create(new Donator(name, email, password, location, bloodType));


                    setVisible(true);
                }

                // method actionPerformed()
                // to get the action performed
                // by the user and act accordingly
            });
        }}



