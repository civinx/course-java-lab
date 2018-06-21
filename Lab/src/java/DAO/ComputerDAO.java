package DAO;

import IDAO.IComputerDAO;
import model.Computer;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        String hql = "from Computer where computerIp = :computerIp and computerState != :computerState";
        Query query = session.createQuery(hql);
        query.setParameter("computerIp", computerIp);
        query.setParameter("computerState", STATE_DELETE);
        Computer computer = (Computer) query.uniqueResult();
        session.close();
        return computer;
    }

    @Override
    public Computer query(int labId, int computerId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Computer where labId = :labId and computerId = :computerId and computerState != :computerState";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        query.setParameter("computerId", computerId);
        query.setParameter("computerState", STATE_DELETE);
        Computer computer = (Computer) query.uniqueResult();
        session.close();
        return computer;
    }

    @Override
    public void update(Computer computer) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(computer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List queryListInRecord(int userId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "select computer from Record record right join record.computer computer " +
                     "where record.userId = :userId";

        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        List<Computer> result = query.list();
        session.close();
        return result;
    }
}
