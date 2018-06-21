package model;

import java.io.Serializable;
import java.util.Collection;

public class Computer implements Serializable {
    private int computerId;
    private int labId;
    private String computerIp;
    private String computerLoc;
    private int computerState;
    private Integer userId;
    private User userByUserId;
    private Collection<Record> records;

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

    public String getComputerIp() {
        return computerIp;
    }

    public void setComputerIp(String computerIp) {
        this.computerIp = computerIp;
    }

    public String getComputerLoc() {
        return computerLoc;
    }

    public void setComputerLoc(String computerLoc) {
        this.computerLoc = computerLoc;
    }

    public int getComputerState() {
        return computerState;
    }

    public void setComputerState(int computerState) {
        this.computerState = computerState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (computerId != computer.computerId) return false;
        if (labId != computer.labId) return false;
        if (computerState != computer.computerState) return false;
        if (computerIp != null ? !computerIp.equals(computer.computerIp) : computer.computerIp != null) return false;
        if (computerLoc != null ? !computerLoc.equals(computer.computerLoc) : computer.computerLoc != null)
            return false;
        if (userId != null ? !userId.equals(computer.userId) : computer.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = computerId;
        result = 31 * result + labId;
        result = 31 * result + (computerIp != null ? computerIp.hashCode() : 0);
        result = 31 * result + (computerLoc != null ? computerLoc.hashCode() : 0);
        result = 31 * result + computerState;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void setRecords(Collection<Record> records) {
        this.records = records;
    }
}
