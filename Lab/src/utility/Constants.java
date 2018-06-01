package utility;

import java.util.HashMap;
import java.util.Map;

public interface Constants {
    // USER_TYPE
    int USER_TYPE_VISTOR = 0;
    int USER_TYPE_STUDENT = 1;
    int USER_STATE_ADMIN = 2;
    String MAP_USER_TYPE[] = {"游客", "学生", "管理员"};

    // USER_STATE
    int USER_STATE_BAN = 0;
    int USER_STATE_AVAILABLE = 1;
    int USER_STATE_DELETE = 2;
    String MAP_USER_STATE[] = {"封禁", "正常", "删除"};

    // SESSION
    String SESSION_USER = "SESSION_USER";
    String SESSION_ERROR = "SESSION_ERROR";

    // ATTRIBUTE
    String ATTRIBUTE_USER_LIST = "ATTRIBUTE_USER_LIST";
}
