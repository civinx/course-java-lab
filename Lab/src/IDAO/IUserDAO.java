package IDAO;

import model.User;

import java.util.List;

public interface IUserDAO {
    void insert(User user) throws Exception;
    void delete(String userName) throws Exception;
    void delete(int userId) throws Exception;
    void update(User user) throws Exception;
    User getUser(String name) throws Exception;
    List getUserList(String userNick, int userType, int userState) throws Exception;

}
