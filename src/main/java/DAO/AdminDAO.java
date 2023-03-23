package DAO;

import model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AdminDAO implements BasicDAO<Admin, Integer>{

    private SessionFactory sessionFactory;

    public AdminDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Admin admin) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.merge(admin);
        transaction.commit();
        session.close();
    }

    @Override
    public Admin read(Integer id) {
        Session session=sessionFactory.openSession();
        Admin admin =session.createQuery("select s from Admin s where s.id= "+id, Admin.class).getSingleResult();
        session.close();
        return admin;
    }

    @Override
    public void update(Admin admin) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.persist(admin);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Admin admin) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.delete(admin);
        transaction.commit();
        session.close();

    }

    @Override
    public List<Admin> readAll() {
       Session session=sessionFactory.openSession();
         List<Admin> admins =session.createQuery("select s from Admin s", Admin.class).getResultList();

        session.close();
        return admins;
    }
}
