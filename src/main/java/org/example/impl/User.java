package org.example.impl;
import org.example.util.hibernate.UtilHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
public class User {
    private final SessionFactory sessionFactory = UtilHibernate.createsessionFactory();
        public User() {
    }
    public void createUsersTable() {
        Session session = UtilHibernate.createsessionFactory().openSession();
        session.beginTransaction();
        session.createNativeQuery("create table users(" +
                "id serial primary key, " +
                "lastname varchar(50)," +
                ");");
        session.getTransaction().commit();
        session.close();
        System.out.println("create table");
    }
    public void dropUsersTable() {
        Session session  = UtilHibernate.createsessionFactory().openSession();
        session.beginTransaction();

        session.createNativeQuery("drop table  userss").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("drop table_no error");
    }
    public void saveUser(Long id, String lastName) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(lastName, id));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("error saveUsers!!!");
        }
    }
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
        session.close();
    }
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List userss= session.createQuery("select u from User u").getResultList();
        session.getTransaction().commit();
        session.close();
        return userss;
    }
    public void cleanUsersTable() {
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery("truncate table userss").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}

