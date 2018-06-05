package IDAO;

import model.Lab;
import model.User;

import java.util.List;

public interface IUserDAO {
    void add(User user) throws Exception;
    void delete(String userName) throws Exception;
    void delete(int userId) throws Exception;
    void update(User user) throws Exception;
    User query(String name) throws Exception;
    User query(int userId) throws Exception;
    List queryList(String userNick, int userType, int userState) throws Exception;

}
