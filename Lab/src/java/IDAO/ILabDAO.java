package IDAO;

import model.Lab;

import java.util.List;

public interface ILabDAO {
    void add(Lab lab) throws Exception;
    void delete(int labId) throws Exception;
    void update(int labId) throws Exception;
    Lab query(int labId) throws Exception;
    Lab query(String labName) throws Exception;
    List queryList(String labName, int labState, int labGate) throws Exception;
    List queryList(int userId) throws Exception;
    List queryMembers(int labId) throws Exception;
    List queryMembersOption(int labId) throws Exception;
    void addMember(int labId, int userId) throws Exception;
    void deleteMember(int labId, int userId) throws Exception;
}
