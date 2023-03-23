package db;

import model.Doctor;
import model.Admin;
import model.Donator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBaseConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory=new Configuration()
                    .configure("hibernate.cfg.xml").addAnnotatedClass(Doctor.class).addAnnotatedClass(Admin.class).addAnnotatedClass(Donator.class).buildSessionFactory();
        }
        return sessionFactory;
    }
}
