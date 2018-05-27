package DAO;

import IDAO.ILogDAO;
import model.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

public class LogDAO implements ILogDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void insert(Log log) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(log);
        session.getTransaction().commit();
    }
}
