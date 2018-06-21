package model;

import java.sql.Timestamp;

public class Record {
    private int recordId;
    private int userId;
    private int computerId;
    private int labId;
    private Timestamp recordStartTime;
    private Timestamp recordEndTime;
    private User userByUserId;
    private Computer computer;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public Timestamp getRecordStartTime() {
        return recordStartTime;
    }

    public void setRecordStartTime(Timestamp recordStartTime) {
        this.recordStartTime = recordStartTime;
    }

    public Timestamp getRecordEndTime() {
        return recordEndTime;
    }

    public void setRecordEndTime(Timestamp recordEndTime) {
        this.recordEndTime = recordEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (recordId != record.recordId) return false;
        if (userId != record.userId) return false;
        if (computerId != record.computerId) return false;
        if (labId != record.labId) return false;
        if (recordStartTime != null ? !recordStartTime.equals(record.recordStartTime) : record.recordStartTime != null)
            return false;
        if (recordEndTime != null ? !recordEndTime.equals(record.recordEndTime) : record.recordEndTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordId;
        result = 31 * result + userId;
        result = 31 * result + computerId;
        result = 31 * result + labId;
        result = 31 * result + (recordStartTime != null ? recordStartTime.hashCode() : 0);
        result = 31 * result + (recordEndTime != null ? recordEndTime.hashCode() : 0);
        return result;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
