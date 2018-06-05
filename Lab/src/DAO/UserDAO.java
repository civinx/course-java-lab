package DAO;

import IDAO.IUserDAO;
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
    }

    @Override
    public void update(User user) {

    }

    @Override
    public synchronized User query(String userName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from User where userName = ?";
        User user = (User) session.createQuery(hql).setParameter(0, userName).uniqueResult();
        System.out.println(user);
        return user;
    }

    /*
    userNick = ""
    userType = -1
    userState = -1
    上述情况不查
     */
    @Override
    public List queryList(String userNick, int userType, int userState) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from User ";
        if (!userNick.equals("") || userState != -1 || userType != -1) {
            hql += "where ";
        }
        if (!userNick.equals("")) {
            hql += " and userNick = :userNick";
        }
        if (userType != -1) {
            hql += " and userType = :userType";
        }
        if (userState != -1) {
            hql += " and userState = :userState";
        }
        Query query = session.createQuery(hql);
        if (!userNick.equals("")) {
            query.setParameter("userNick", userNick);
        }
        if (userType != -1) {
            query.setParameter("userType", userType);
        }
        if (userState != -1) {
            query.setParameter("userState", userState);
        }
        List<User> result = query.list();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public User query(int userId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from User where userId = ?";
        User user = (User) session.createQuery(hql).setParameter(0, userId).uniqueResult();
        System.out.println(user);
        return user;
    }

    //    public static void main(String[] args) throws Exception{
//        List<User> test = userDAO.queryList("", -1, -1);
//        for (User user : test) {
//            System.out.println(user.getUserName());
//        }
//    }
}
