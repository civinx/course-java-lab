package IDAO;

import model.Computer;

import java.util.List;

public interface IComputerDAO {
    void add(Computer computer) throws Exception;
    void delete(Computer computer) throws Exception;
    void delete(int labId, int computerId) throws Exception;
    List queryList(int labId) throws Exception;
    Computer query(String computerIp) throws Exception;
    Computer query(int labId, int computerId) throws Exception;
}
