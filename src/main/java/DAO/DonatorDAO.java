package DAO;

import model.Doctor;
import model.Donator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DonatorDAO implements BasicDAO<Donator, Integer>{
    private final SessionFactory sessionFactory;

    public DonatorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Donator donator) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(donator);
        transaction.commit();
        session.close();
    }

    @Override
    public Donator read(Integer id) {
        Session session = sessionFactory.openSession();
        Donator donator = session.createQuery("select p from Donator p where p.id= " + id, Donator.class).getSingleResult();
        session.close();
        return donator;
    }

    @Override
    public void update(Donator donator) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(donator);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Donator donator) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(donator);
        transaction.commit();
        session.close();

    }

    @Override
    public List<Donator> readAll() {
        Session session = sessionFactory.openSession();
        List<Donator> donators = session.createQuery("select p from Donator p", Donator.class).getResultList();
        session.close();
        return donators;
    }
}
