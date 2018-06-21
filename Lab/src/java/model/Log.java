package model;

import java.sql.Timestamp;

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

        if (logId != log.logId) return false;
        if (userId != log.userId) return false;
        if (logAction != null ? !logAction.equals(log.logAction) : log.logAction != null) return false;
        if (logTime != null ? !logTime.equals(log.logTime) : log.logTime != null) return false;
        if (logType != null ? !logType.equals(log.logType) : log.logType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + userId;
        result = 31 * result + (logAction != null ? logAction.hashCode() : 0);
        result = 31 * result + (logTime != null ? logTime.hashCode() : 0);
        result = 31 * result + (logType != null ? logType.hashCode() : 0);
        return result;
    }
}
