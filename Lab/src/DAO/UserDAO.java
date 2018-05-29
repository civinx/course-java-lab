package DAO;

import IDAO.IUserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

    /*
    userNick = ""
    userType = -1
    userState = -1
    上述情况不查
     */
    @Override
    public List getUserList(String userNick, int userType, int userState) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from User ";
//        if (!userNick.equals("")) {
//            hql += " and userNick = :userNick";
//        }
//        if (userType != -1) {
//            hql += " and userType = :userType";
//        }
//        if (userState != -1) {
//            hql += " and userState = :userState";
//        }
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

//    public static void main(String[] args) throws Exception{
//        List<User> test = userDAO.getUserList("", -1, -1);
//        for (User user : test) {
//            System.out.println(user.getUserName());
//        }
//    }
}
