package model;

import java.io.Serializable;
import java.util.Objects;

public class Computer implements Serializable {
    private int computerId;
    private int labId;
    private String computerIp;
    private String computerLoc;
    private int computerState;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return computerId == computer.computerId &&
                labId == computer.labId &&
                computerState == computer.computerState &&
                Objects.equals(computerIp, computer.computerIp) &&
                Objects.equals(computerLoc, computer.computerLoc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(computerId, labId, computerIp, computerLoc, computerState);
    }
}
