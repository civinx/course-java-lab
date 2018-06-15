package model;

import java.util.Objects;

public class LabUser {
    private int labUserId;
    private int userId;
    private int labId;

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
        return labUserId == labUser.labUserId &&
                userId == labUser.userId &&
                labId == labUser.labId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(labUserId, userId, labId);
    }
}
