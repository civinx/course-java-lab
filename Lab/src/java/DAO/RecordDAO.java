package DAO;

import IDAO.IRecordDAO;
import model.Record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class RecordDAO implements IRecordDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void insert(Record record) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(record);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List queryList(int userId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Record where userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        List<Record> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List queryList(String computerIp) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "select Record from Record join Computer where Computer.computerIp = :computerIp";
        Query query = session.createQuery(hql);
        query.setParameter("computerIp", computerIp);
        List<Record> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List queryListByComputerId(int computerId) {
        return null;
    }

    @Override
    public List queryListByLabId(int labId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Record where labId = :labId";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        List<Record> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Record query(int userId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Record where userId = :userId and recordEndTime is null ";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        Record result = (Record) query.uniqueResult();
        session.close();
        return result;
    }

    @Override
    public void update(Record record) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(record);
        session.getTransaction().commit();
        session.close();
    }
}
