package controller;

import IDAO.IExceptionLog;
import IDAO.IUserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BaseExceptionHandleAction;
import exception.BusinessException;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.Constants;
import utility.Tools;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController extends BaseExceptionHandleAction implements Constants {

    @Resource (name = "userDAO")
    private IUserDAO userDAO;

    @Resource (name = "exceptionLog")
    private IExceptionLog exceptionLog;

    @RequestMapping("/")
    private String index(Model model) {
        System.out.println("index");
        return "/login";
    }


    @ResponseBody
    @RequestMapping("/register_action")
    private String register_action(@RequestBody String requestJson, HttpServletRequest request) throws Exception {
        System.out.println("!!!");
        System.out.println(requestJson);
        User user = new User();
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(requestJson, Map.class);
        String userName = (String) map.get("userName");
        String userPassword = Tools.EncoderByMd5((String) map.get("userPassword"));
        String userNick  = (String) map.get("userNick");
        if (userDAO.query(userName) != null) {
            throw new BusinessException(ALERT_USER_NAME_ALREADY_EXIST);
        }
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserNick(userNick);
        user.setUserState(STATE_AVAILABLE);
        user.setUserType(USER_TYPE_STUDENT);
        userDAO.add(user);
        return Tools.creteJasonString(Constants.CODE_SUCCESS);
    }

    @RequestMapping("/register")
    private String register(Model model) throws Exception{
        return "/register.jsp";
    }




    @ResponseBody
    @RequestMapping("/login_action")
    private String login_action(
                                @RequestParam(value = "userName") String userName,
                                @RequestParam(value = "userPassword") String userPassword,
                                HttpSession session) throws Exception {
        User user = userDAO.query(userName);
        if (user == null) {
            throw new BusinessException(ALERT_USER_NOT_EXIST);
        }
        if (!(Tools.EncoderByMd5(userPassword)).equals(user.getUserPassword())) {
            throw new BusinessException(ALERT_USER_WRONG_PASSWORD);
        }
        session.setAttribute(SESSION_USER, user);
        System.out.println(user);
        return Tools.creteJasonString(CODE_SUCCESS);

    }

    @RequestMapping("/login")
    private String login(Model model) throws Exception{
        return "/login.jsp";
    }


    @RequestMapping("/home")
    private String home(Model model) throws Exception {
        return "index.jsp";
    }

    @RequestMapping("/home/user")
    private String list(Model model) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_USER_LIST, userDAO.queryList());
            return "/user.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/user/delete")
    private String delete(Model model,
                          @RequestParam(value = "userId") int userId) {
        try {
            userDAO.delete(userId);
            return "/home/user";
        } catch (Exception ex) {
            return "/error.jsp";
        }
    }


}
