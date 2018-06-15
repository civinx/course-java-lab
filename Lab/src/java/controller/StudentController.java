package controller;

import IDAO.IComputerDAO;
import IDAO.ILabDAO;
import IDAO.IUserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BaseExceptionHandleAction;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utility.Constants;
import utility.Tools;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class StudentController extends BaseExceptionHandleAction implements Constants {
    @Resource(name = "labDAO")
    private ILabDAO labDAO;

    @Resource (name = "userDAO")
    private IUserDAO userDAO;

    @Resource(name = "computerDAO")
    private IComputerDAO computerDAO;

    @RequestMapping("/home/student/lab")
    private String student_lab(Model model) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SESSION_USER);
        model.addAttribute(ATTRIBUTE_LAB_LIST, labDAO.queryList(user.getUserId()));
        return "/student_lab.jsp";
    }

    @RequestMapping("/home/student/computer")
    private String student_computer(Model model,
                                    @RequestParam(value = "labId") int labId) throws Exception {
        model.addAttribute(ATTRIBUTE_COMPUTER_LIST, computerDAO.queryList(labId));
        model.addAttribute(ATTRIBUTE_LAB, labDAO.query(labId));
        return "/student_computer.jsp";
    }

    @RequestMapping("/home/student/computer/start")
    @ResponseBody
    private String studetn_start(@RequestBody String requestJson, HttpServletRequest request) throws Exception {
        System.out.println(requestJson);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(requestJson, Map.class);
        int computerId = Integer.valueOf((String) map.get("computerId"));
        System.out.println(computerId);
        return Tools.creteJasonString(CODE_SUCCESS);
    }

}
