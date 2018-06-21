package model;

import java.util.Collection;
import java.util.Set;

public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userNick;
    private int userType;
    private int userState;
    private Collection<Computer> computersByUserId;
    private Collection<Record> recordsByUserId;
    private Collection<LabUser> LabUserByUserId;

    public Collection<LabUser> getLabUserByUserId() {
        return LabUserByUserId;
    }

    public void setLabUserByUserId(Collection<LabUser> labUserByUserId) {
        LabUserByUserId = labUserByUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userType != user.userType) return false;
        if (userState != user.userState) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(user.userPassword) : user.userPassword != null) return false;
        if (userNick != null ? !userNick.equals(user.userNick) : user.userNick != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userNick != null ? userNick.hashCode() : 0);
        result = 31 * result + userType;
        result = 31 * result + userState;
        return result;
    }

    public Collection<Computer> getComputersByUserId() {
        return computersByUserId;
    }

    public void setComputersByUserId(Collection<Computer> computersByUserId) {
        this.computersByUserId = computersByUserId;
    }

    public Collection<Record> getRecordsByUserId() {
        return recordsByUserId;
    }

    public void setRecordsByUserId(Collection<Record> recordsByUserId) {
        this.recordsByUserId = recordsByUserId;
    }
}
