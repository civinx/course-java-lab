package IDAO;

import model.User;

import java.util.List;

public interface IUserDAO {
    void insert(User user);
    void delete(String userName);
    void delete(int userId);
    void update(User user);
    User getUser(String name);
    List getUserList(String userNick, String userType, String userState);

}
