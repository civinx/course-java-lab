package model;

public class Lab {
    private int labId;
    private String labName;
    private Integer labState;
    private Integer labGate;

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

    public Integer getLabState() {
        return labState;
    }

    public void setLabState(Integer labState) {
        this.labState = labState;
    }

    public Integer getLabGate() {
        return labGate;
    }

    public void setLabGate(Integer labGate) {
        this.labGate = labGate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lab lab = (Lab) o;

        if (labId != lab.labId) return false;
        if (labName != null ? !labName.equals(lab.labName) : lab.labName != null) return false;
        if (labState != null ? !labState.equals(lab.labState) : lab.labState != null) return false;
        if (labGate != null ? !labGate.equals(lab.labGate) : lab.labGate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = labId;
        result = 31 * result + (labName != null ? labName.hashCode() : 0);
        result = 31 * result + (labState != null ? labState.hashCode() : 0);
        result = 31 * result + (labGate != null ? labGate.hashCode() : 0);
        return result;
    }
}
