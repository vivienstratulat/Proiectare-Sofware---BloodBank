package DAO;

import model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorDAO implements BasicDAO<Doctor, Integer> {
    private final SessionFactory sessionFactory;

    public DoctorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(doctor);
        transaction.commit();
        session.close();
    }

    @Override
    public Doctor read(Integer id) {
        Session session = sessionFactory.openSession();
        Doctor doctor = session.createQuery("select p from Doctor p where p.id= " + id, Doctor.class).getSingleResult();
        session.close();
        return doctor;
    }

    @Override
    public void update(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(doctor);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Doctor doctor) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(doctor);
        transaction.commit();
        session.close();

    }

    @Override
    public List<Doctor> readAll() {
        Session session = sessionFactory.openSession();
        List<Doctor> doctors = session.createQuery("select p from Doctor p", Doctor.class).getResultList();
        session.close();
        return doctors;
    }
}
