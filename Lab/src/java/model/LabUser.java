package model;

public class LabUser {
    private int labUserId;
    private int userId;
    private int labId;
    private User userByUserId;
    private Lab userByLabId;

    public Lab getUserByLabId() {
        return userByLabId;
    }

    public void setUserByLabId(Lab userByLabId) {
        this.userByLabId = userByLabId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public int getLabUserId() {
        return labUserId;
    }

    public void setLabUserId(int labUserId) {
        this.labUserId = labUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabUser labUser = (LabUser) o;

        if (labUserId != labUser.labUserId) return false;
        if (userId != labUser.userId) return false;
        if (labId != labUser.labId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = labUserId;
        result = 31 * result + userId;
        result = 31 * result + labId;
        return result;
    }
}
