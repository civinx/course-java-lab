package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BusinessException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class Tools implements Constants {
    public static String createIP(int labGate, int computerIpLast) {
        String IP = IP_GATE;
        IP += String.valueOf(labGate);
        IP += "." + computerIpLast;
        return IP;
    }

    @ResponseBody
    public static String creteJasonString(String code) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("code", code);
        data.put("msg", "");
        data.put("success", true);
        data.put("data", null);
        String ret = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ret = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ret);
        return ret;
    }
}
