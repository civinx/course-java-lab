package model;

import java.io.Serializable;

public class ComputerPK implements Serializable {
    private int computerId;
    private int labId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerPK that = (ComputerPK) o;

        if (computerId != that.computerId) return false;
        if (labId != that.labId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = computerId;
        result = 31 * result + labId;
        return result;
    }
}
