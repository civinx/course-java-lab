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
    }

    @Override
    public void delete(int labId) throws Exception {

    }

    @Override
    public void update(int labId) throws Exception {

    }

    @Override
    public Lab query(int labId) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Lab where labId = ?";
        Lab lab = (Lab) session.createQuery(hql).setParameter(0, labId).uniqueResult();
        System.out.println(lab);
        return lab;
    }

    @Override
    public Lab query(String labName) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Lab where labName = ?";
        Lab lab = (Lab) session.createQuery(hql).setParameter(0, labName).uniqueResult();
        System.out.println(lab);
        return lab;
    }

    @Override
    public List queryList(String labName, int labState) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from Lab ";
        if (!labName.equals("") || labState != -1) {
            hql += "where ";
        }
        if (!labName.equals("")) {
            hql += " and labName = :labName";
        }
        if (labState != -1) {
            hql += " and labState = :labState";
        }

        Query query = session.createQuery(hql);
        if (!labName.equals("")) {
            query.setParameter("labName", labName);
        }
        if (labState != -1) {
            query.setParameter("labState", labState);
        }

        List<Lab> result = query.list();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public List queryMembers(int labId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from LabUser where labId = :labId";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        List<LabUser> labUserList = query.list();
        if (labUserList == null) {
            return null;
        }
        List userList = new ArrayList();
        for (LabUser labUser: labUserList) {
            User user = userDAO.query(labUser.getUserId());
            if (user.getUserState() != STATE_DELETE) {
                userList.add(user);
            }
        }
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public List queryMembersOption(int labId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String hql = "from User where userId not in(" +
                    "select userId from LabUser where labId = :labId)";
        Query query = session.createQuery(hql);
        query.setParameter("labId", labId);
        List<User> result = query.list();
        session.getTransaction().commit();
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
    }

    @Override
    public void deleteMember(int labId, int userId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        LabUser labUser = new LabUser();
        labUser.setLabId(labId);
        labUser.setUserId(userId);
        session.delete(labUser);
        session.getTransaction().commit();
    }

//    Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//
//    Query query = session.createQuery(hql);
//        session.getTransaction().commit();
}
