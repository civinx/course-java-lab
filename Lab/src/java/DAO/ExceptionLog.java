package DAO;

import IDAO.IExceptionLog;

public class ExceptionLog implements IExceptionLog {
    @Override
    public void insert(String msg) throws Exception {
        System.out.println(msg);
        throw new Exception(msg);
    }
}
