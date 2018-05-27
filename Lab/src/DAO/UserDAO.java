package DAO;

import IDAO.IUserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.List;

public class UserDAO implements IUserDAO  {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void insert(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(String userName) {

    }

    @Override
    public void delete(int userId) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public synchronized User getUser(String userName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from User where userName = ?";
        User user = (User) session.createQuery(hql).setParameter(0, userName).uniqueResult();
        System.out.println(user);
        return user;
    }

    @Override
    public List getUserList(String userNick, String userType, String userState) {
        return null;
    }
}
