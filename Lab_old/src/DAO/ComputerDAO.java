package DAO;

import IDAO.IComputerDAO;
import model.Computer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;
import utility.Constants;

import javax.annotation.Resource;
import java.util.List;

public class ComputerDAO implements IComputerDAO, Constants {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Computer computer) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(computer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Computer computer) throws Exception {

    }

    @Override
    public void delete(int labId, int computerId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Computer computer = query(labId, computerId);
        computer.setComputerState(STATE_DELETE);
        session.update(computer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List queryList(int labId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Computer where labId = :labId and computerState != :computerState";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        query.setParameter("computerState", STATE_DELETE);
        List result = query.list();
        session.close();
        return result;
    }

    @Override
    public Computer query(String computerIp) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Computer where computerIp = :computerIp";
        Query query = session.createQuery(hql);
        query.setParameter("computerIp", computerIp);
        Computer computer = (Computer) query.uniqueResult();
        session.close();
        return computer;
    }

    @Override
    public Computer query(int labId, int computerId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Computer where labId = :labId and computerId = :computerId";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        query.setParameter("computerId", computerId);
        Computer computer = (Computer) query.uniqueResult();
        session.close();
        return computer;
    }
}
