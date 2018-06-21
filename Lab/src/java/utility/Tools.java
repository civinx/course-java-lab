package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BusinessException;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String EncoderByMd5(String str) {
        String ret = "";
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            ret = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            System.out.println("EncoderByMd5");
            e.printStackTrace();
        } finally {
            return ret;
        }
    }

    public static void main(String[] args) {
        Tools tools = new Tools();
        String s = "czf";
        System.out.println(tools.EncoderByMd5(s));
    }
}
