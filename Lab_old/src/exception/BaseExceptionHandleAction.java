package exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BaseExceptionHandleAction {
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        Map<String, Object> data = new HashMap<String, Object>();
        if(ex instanceof BusinessException) {
            BusinessException e = (BusinessException)ex;
            data.put("code", e.getErrorCode());
        }
        data.put("msg", ex.getMessage());
        data.put("success", false);
        data.put("data", null);
        return data;
    }
}
