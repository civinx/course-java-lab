package IDAO;

import model.Record;

import java.util.List;

public interface IRecordDAO {
    void insert(Record record);
    Record query(int userId);
    List queryList(int userId);
    List queryList(String computerIp);
    List queryListByComputerId(int computerId);
    List queryListByLabId(int labId);
    void update(Record record);
}
