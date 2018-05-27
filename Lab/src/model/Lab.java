package model;

import java.util.Objects;

public class Lab {
    private int labId;
    private String labName;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lab lab = (Lab) o;
        return labId == lab.labId &&
                Objects.equals(labName, lab.labName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(labId, labName);
    }
}
