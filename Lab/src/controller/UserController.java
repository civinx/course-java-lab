package controller;

import DAO.ExceptionLog;
import IDAO.IExceptionLog;
import IDAO.IUserDAO;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utility.Constants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController implements Constants {

    @Resource (name = "userDAO")
    private IUserDAO userDAO;

    @Resource (name = "exceptionLog")
    private IExceptionLog exceptionLog;

    @RequestMapping("/")
    private String index(Model model) {
        System.out.println("index");
        return "login.jsp";
    }


    @RequestMapping("/register")
    private String register(Model model,
                         @RequestParam(value = "userName") String userName,
                         @RequestParam(value = "userPassword") String userPassword,
                         @RequestParam(value = "userNick") String userNick,
                         HttpSession session) {
        System.out.println(userNick);
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserNick(userNick);
        user.setUserState(USER_STATE_AVAILABLE);
        user.setUserType(USER_TYPE_STUDENT);
        userDAO.insert(user);
        return "/login";
    }

    @RequestMapping("/login")
    private String login(Model model,
                         @RequestParam(value = "userName") String userName,
                         @RequestParam(value = "userPassword") String userPassword,
                         HttpSession session) {
        try {
            User user = userDAO.getUser(userName);
            if (user == null) {
                exceptionLog.insert("用户名不存在");
            }
            if (!userPassword.endsWith(user.getUserPassword())) {
                exceptionLog.insert("密码错误");
            }
            session.setAttribute(SESSION_USER, user);
            System.out.println(user);
            return "/home";
        } catch (Exception ex) {
            model.addAttribute(SESSION_ERROR, ex.getMessage());
            return "/login.jsp";
        }

    }

    @RequestMapping("/home")
    private String home(Model model) {
        return "index.jsp";
    }


}