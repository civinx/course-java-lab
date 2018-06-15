package utility;

import java.util.HashMap;
import java.util.Map;

public interface Constants {

    String IP_GATE = "10.66.";

    // USER_TYPE
    int USER_TYPE_VISTOR = 0;
    int USER_TYPE_STUDENT = 1;
    int USER_STATE_ADMIN = 2;
    String MAP_USER_TYPE[] = {"游客", "学生", "管理员"};

    // STATE
    int STATE_BAN = 0;
    int STATE_AVAILABLE = 1;
    int STATE_DELETE = 2;
    int STATE_USED = 3;
    String MAP_STATE[] = {"封禁", "正常", "删除", "被使用"};


    // SESSION
    String SESSION_USER = "SESSION_USER";
    String SESSION_ERROR = "SESSION_ERROR";

    // ATTRIBUTE
    String ATTRIBUTE_USER_LIST = "ATTRIBUTE_USER_LIST";
    String ATTRIBUTE_LAB_LIST = "ATTRIBUTE_LAB_LIST";
    String ATTRIBUTE_COMPUTER_LIST = "ATTRIBUTE_COMPUTER_LIST";
    String ATTRIBUTE_LAB = "ATTRIBUTE_LAB";
    String ATTRIBUTE_USER_OPTION = "ATTRIBUTE_USER_OPTION";

    // ALERT_INFO
    String ALERT_USER_NOT_EXIST = "ALERT_USER_NOT_EXIST";
    String ALERT_USER_WRONG_PASSWORD = "ALERT_USER_WRONG_PASSWORD";
    String ALERT_LAB_NAME_USED = "ALERT_LAB_NAME_USED";
    String ALERT_LAB_GATE_OUT_OF_RANGE = "ALERT_LAB_GATE_ERROR";
    String ALERT_LAB_GATE_USED = "ALERT_LAB_GATE_USED";
    String ALERT_COMPUTER_IP_USED = "LERT_COMPUTER_IP_USED";
    String ALERT_COMPUTER_IP_OUT_OF_RANGE = "ALERT_COMPUTER_IP_OUT_OF_RANGE";
    String ALERT_COMPUTER_ID_USED = "ALERT_COMPUTER_ID_USED";
    String ALERT_COMPUTER_STATE_NOT_AVAILABLE = "ALERT_COMPUTER_STATE_NOT_AVAILABLE";
    String ALERT_COMPUTER_USER_ALREADY_ONLINE = "ALERT_COMPUTER_USER_ALREADY_ONLINE";
    String CODE_ERROR = "ERROR";
    String CODE_SUCCESS = "SUCCESS";
}
