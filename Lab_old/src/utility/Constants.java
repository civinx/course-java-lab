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
    String MAP_STATE[] = {"封禁", "正常", "删除"};


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
    String ALERT_LAB_NAME_USED = "该实验室名称已被使用";
    String ALERT_LAB_GATE_OUT_OF_RANGE = "ALERT_LAB_GATE_ERROR";
    String ALERT_LAB_GATE_USED = "ALERT_LAB_GATE_USED";
    String ALERT_COMPUTER_IP_USED = "该IP已被使用";
    String ALERT_COMPUTER_IP_OUT_OF_RANGE = "ALERT_COMPUTER_IP_OUT_OF_RANGE";
    String ALERT_COMPUTER_ID_USED = "ALERT_COMPUTER_ID_USED";

    String CODE_ERROR = "ERROR";
    String CODE_SUCCESS = "SUCCESS";
}
