package DAO;

import IDAO.IUserDAO;
import model.Record;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utility.Constants;

import javax.annotation.Resource;
import java.util.List;

public class UserDAO implements IUserDAO, Constants {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(String userName) {

    }

    @Override
    public void delete(int userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, userId);
        user.setUserState(STATE_DELETE);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public synchronized User query(String userName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from User where userName = :userName and userState != :userState";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("userState", STATE_DELETE);
        User user = (User) query.uniqueResult();
        System.out.println(user);
        session.close();
        return user;
    }


    @Override
    public List queryList() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from User where userState != :userState";
        Query query = session.createQuery(hql);
        query.setParameter("userState", STATE_DELETE);
        List<User> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List queryListInRecord(int labId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "select user from Record record right join record.userByUserId user " +
                     "where record.labId = :labId and user.userState != :userState";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        query.setParameter("userState", STATE_DELETE);
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public User query(int userId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from User where userId = :userId and userState != :userState";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("userState", STATE_DELETE);
        User user = (User) query.uniqueResult();
        System.out.println(user);
        session.close();
        return user;
    }


}
