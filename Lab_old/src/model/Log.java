package model;

import java.sql.Timestamp;
import java.util.Objects;

public class Log {
    private int logId;
    private int userId;
    private String logAction;
    private Timestamp logTime;
    private String logType;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return logId == log.logId &&
                userId == log.userId &&
                Objects.equals(logAction, log.logAction) &&
                Objects.equals(logTime, log.logTime) &&
                Objects.equals(logType, log.logType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(logId, userId, logAction, logTime, logType);
    }
}
