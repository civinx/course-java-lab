package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Record {
    private int recordId;
    private int userId;
    private int computerId;
    private int labId;
    private Timestamp recordStartTime;
    private Timestamp recordEndTime;

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
        return recordId == record.recordId &&
                userId == record.userId &&
                computerId == record.computerId &&
                labId == record.labId &&
                Objects.equals(recordStartTime, record.recordStartTime) &&
                Objects.equals(recordEndTime, record.recordEndTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(recordId, userId, computerId, labId, recordStartTime, recordEndTime);
    }
}
