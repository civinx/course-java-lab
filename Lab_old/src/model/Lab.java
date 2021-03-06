package model;

import java.util.Objects;

public class Lab {
    private int labId;
    private String labName;
    private int labState;
    private int labGate;

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public int getLabState() {
        return labState;
    }

    public void setLabState(int labState) {
        this.labState = labState;
    }

    public int getLabGate() {
        return labGate;
    }

    public void setLabGate(int labGate) {
        this.labGate = labGate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lab lab = (Lab) o;
        return labId == lab.labId &&
                labState == lab.labState &&
                labGate == lab.labGate &&
                Objects.equals(labName, lab.labName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(labId, labName, labState, labGate);
    }
}
