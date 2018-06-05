package controller;

import IDAO.IExceptionLog;
import IDAO.IUserDAO;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.Constants;

import javax.annotation.Resource;
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


    @RequestMapping("/register_action")
    private String register_action(Model model,
                         @RequestParam(value = "userName") String userName,
                         @RequestParam(value = "userPassword") String userPassword,
                         @RequestParam(value = "userNick") String userNick,
                         HttpSession session) throws Exception{
        System.out.println(userNick);
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserNick(userNick);
        user.setUserState(STATE_AVAILABLE);
        user.setUserType(USER_TYPE_STUDENT);
        userDAO.add(user);
        return "/login";
    }

    @RequestMapping("/register")
    private String register(Model model) throws Exception{
        return "/register.jsp";
    }




    @ResponseBody
    @RequestMapping("/login_action")
    private String login_action(@RequestParam(value = "userName") String userName,
                         @RequestParam(value = "userPassword") String userPassword,
                         HttpSession session) throws Exception {
        System.out.println("a~~~`");
        try {
            User user = userDAO.query(userName);
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
            return "error";
        }
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
            model.addAttribute(ATTRIBUTE_USER_LIST, userDAO.queryList("", -1, -1));
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
