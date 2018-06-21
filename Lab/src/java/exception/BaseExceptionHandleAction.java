package exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseExceptionHandleAction {

    @ExceptionHandler
    @ResponseBody
    public String handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        Map<String, Object> data = new HashMap<String, Object>();
        if(ex instanceof BusinessException) {
            BusinessException e = (BusinessException)ex;
            data.put("code", e.getErrorCode());
        }
        data.put("msg", ex.getMessage());
        data.put("success", false);
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
