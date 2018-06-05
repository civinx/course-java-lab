package aspect;

import IDAO.ILogDAO;
import model.Log;
import model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utility.Constants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class MyAspect implements Constants {

    @Resource(name = "logDAO")
    private ILogDAO logDAO;

    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        int userId = 0;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
//            userId = (int) session.getAttribute("userId");
            User user = (User) session.getAttribute(SESSION_USER);
            userId = user.getUserId();
        } catch (NullPointerException ex) {
            System.out.println("null pointer at around");
        }
        String clazz = proceedingJoinPoint.getTarget().getClass().getName();
        String method = proceedingJoinPoint.getSignature().getName();
        Object[] method_args = proceedingJoinPoint.getArgs();
        String action = clazz + "." + method + "(";
        for (Object o : method_args) {
            action += o.toString() + ",";
        }
        action += ")";
        Log log = new Log();
        log.setUserId(userId);
        log.setLogAction(action);
        log.setLogTime(new Timestamp(System.currentTimeMillis()));
        if (clazz.endsWith("ExceptionLog")) {
            log.setLogType("Exception");
        } else {
            log.setLogType("normal");
        }

//        logDAO.add(log);
        Object obj = proceedingJoinPoint.proceed();
        return obj;
    }
}
