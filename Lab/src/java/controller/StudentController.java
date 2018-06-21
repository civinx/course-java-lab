package controller;

import IDAO.IComputerDAO;
import IDAO.ILabDAO;
import IDAO.IRecordDAO;
import IDAO.IUserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.BaseExceptionHandleAction;
import exception.BusinessException;
import model.Computer;
import model.Record;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController extends BaseExceptionHandleAction implements Constants {
    @Resource(name = "labDAO")
    private ILabDAO labDAO;

    @Resource (name = "userDAO")
    private IUserDAO userDAO;

    @Resource(name = "computerDAO")
    private IComputerDAO computerDAO;

    @Resource(name = "recordDAO")
    private IRecordDAO recordDAO;

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

    @RequestMapping("/home/student/record")
    private String student_my_record(Model model, HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute(SESSION_USER);
        int userId = user.getUserId();
        List recordList = (List) recordDAO.queryList(userId);
        List labList = (List) labDAO.queryListInRecord(userId);
        List computerList = (List) computerDAO.queryListInRecord(userId);
        model.addAttribute("test", 123);
        model.addAttribute(ATTRIBUTE_RECORD_LIST, recordList);
        model.addAttribute(ATTRIBUTE_LAB_LIST, labList);
        model.addAttribute(ATTRIBUTE_COMPUTER_LIST, computerList);
        return "/student_record.jsp";
    }

    @RequestMapping("/home/student/computer/start")
    @ResponseBody
    private String studetn_start(@RequestBody String requestJson, HttpServletRequest request) throws Exception {
        System.out.println(requestJson);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(requestJson, Map.class);
        String computerIp = (String) map.get("computerIp");

        Computer computer = computerDAO.query(computerIp);
        User user = (User)request.getSession().getAttribute(SESSION_USER);

        if (computer.getComputerState() != STATE_AVAILABLE) {
            throw new BusinessException(ALERT_COMPUTER_STATE_NOT_AVAILABLE);
        }
        if (recordDAO.query(user.getUserId()) != null) {
            throw new BusinessException(ALERT_COMPUTER_USER_ALREADY_ONLINE);
        }

        computer.setUserId(user.getUserId());
        computer.setComputerState(STATE_USED);
        computerDAO.update(computer);

        Record record = new Record();
        record.setComputerId(computer.getComputerId());
        record.setLabId(computer.getLabId());
        record.setUserId(user.getUserId());
        record.setRecordStartTime(new Timestamp(System.currentTimeMillis()));
        record.setRecordEndTime(null);
        recordDAO.insert(record);

        return Tools.creteJasonString(CODE_SUCCESS);
    }

    @RequestMapping("/home/student/computer/end")
    @ResponseBody
    private String studetn_end(@RequestBody String requestJson, HttpServletRequest request) throws Exception {
        System.out.println(requestJson);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(requestJson, Map.class);
        String computerIp = (String) map.get("computerIp");
        Computer computer = computerDAO.query(computerIp);

        computer.setUserId(0);
        computer.setComputerState(STATE_AVAILABLE);
        computerDAO.update(computer);

        User user = (User)request.getSession().getAttribute(SESSION_USER);
        Record record = recordDAO.query(user.getUserId());
        record.setRecordEndTime(new Timestamp(System.currentTimeMillis()));
        recordDAO.update(record);
        return Tools.creteJasonString(CODE_SUCCESS);
    }
}
