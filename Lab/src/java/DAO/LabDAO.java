package DAO;

import IDAO.ILabDAO;
import IDAO.IUserDAO;
import model.Lab;
import model.LabUser;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utility.Constants;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class LabDAO implements ILabDAO, Constants {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource (name = "userDAO")
    private IUserDAO userDAO;

    @Override
    public void add(Lab lab) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lab);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int labId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "update Lab set labState = :labState where labId = :labId";
        Query query = session.createQuery(hql);
        query.setParameter("labState", STATE_DELETE);
        query.setParameter("labId", labId);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void update(int labId) throws Exception {

    }

    @Override
    public Lab query(int labId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Lab where labId = :labId and labState != :labState";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        query.setParameter("labState", STATE_DELETE);
        Lab lab = (Lab) query.uniqueResult();
        session.close();
        return lab;
    }

    @Override
    public Lab query(String labName) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Lab where labName = :labName and labState != :labState";
        Query query = session.createQuery(hql);
        query.setParameter("labName", labName);
        query.setParameter("labState", STATE_DELETE);
        Lab lab = (Lab) query.uniqueResult();
        session.close();
        return lab;
    }

    @Override
    public List queryList(String labName, int labState, int labGate) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Lab where labState != " + STATE_DELETE;
        if (!labName.equals("")) {
            hql += " and labName = :labName";
        }
        if (labState != -1) {
            hql += " and labState = :labState";
        }
        if (labGate != -1) {
            hql += " and labGate = :labGate";
        }

        Query query = session.createQuery(hql);

        if (!labName.equals("")) {
            query.setParameter("labName", labName);
        }
        if (labState != -1) {
            query.setParameter("labState", labState);
        }
        if (labGate != -1) {
            query.setParameter("labGate", labGate);
        }

        List<Lab> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List queryList(int userId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Lab where labState != :labState and labId in (select labId from LabUser where userId = :userId)";
        Query query = session.createQuery(hql);
        query.setParameter("labState", STATE_DELETE);
        query.setParameter("userId", userId);
        List<Lab> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List queryMembers(int labId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//        String hql = "from LabUser where labId = :labId";
        String hql = "select user from LabUser labUser right join labUser.userByUserId user " +
                     "where user.userState != :userState and labUser.labId = :labId";
        Query query = session.createQuery(hql);
        query.setParameter("userState", STATE_DELETE);
        query.setParameter("labId", labId);
        List userList = query.list();
//        List<LabUser> labUserList = query.list();
//        if (labUserList == null) {
//            return null;
//        }
//        List<User> userList = new ArrayList<>();
//        for (LabUser labUser: labUserList) {
//            User user = userDAO.query(labUser.getUserId());
//            userList.add(user);
//        }
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public List queryMembersOption(int labId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from User where userState != :userState and userId not in(" +
                    "select userId from LabUser where labId = :labId)";
        Query query = session.createQuery(hql);
        query.setParameter("userState", STATE_DELETE);
        query.setParameter("labId", labId);
        List<User> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void addMember(int labId, int userId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        LabUser labUser = new LabUser();
        labUser.setLabId(labId);
        labUser.setUserId(userId);
        session.save(labUser);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteMember(int labId, int userId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql =  "delete from LabUser where labId = :labId and userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        query.setParameter("userId", userId);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List queryListInRecord(int userId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "select lab from Record record right join record.labByLabId lab " +
                     "where record.userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        List<Lab> result = query.list();
        session.close();
        return result;
    }
}
