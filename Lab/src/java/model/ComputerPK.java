package model;

import java.io.Serializable;
import java.util.Objects;

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
        return computerId == that.computerId &&
                labId == that.labId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(computerId, labId);
    }
}
