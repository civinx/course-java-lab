package controller;

import IDAO.ILabDAO;
import IDAO.IUserDAO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Lab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.Constants;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LabController implements Constants {

    @Resource(name = "labDAO")
    private ILabDAO labDAO;

    @Resource (name = "userDAO")
    private IUserDAO userDAO;

    @ResponseBody
    @RequestMapping("/home/lab/add_action")
    private String lab_add_action(@RequestParam(value = "labName") String labName,
                                @RequestParam(value = "labGate") int labGate) throws Exception {
        try {
            Lab lab = labDAO.query(labName);
            if (lab != null) {
                return ALERT_LAB_NAME_USED;
            }
            if (labGate < 0 || labGate > 255) {
                return ALERT_LAB_GATE_OUT_OF_RANGE;
            }
            List temp = (List) labDAO.queryList("", -1, labGate);
            if (temp != null && temp.size() > 0) {
                return ALERT_LAB_GATE_USED;
            }
            lab = new Lab();
            lab.setLabName(labName);
            lab.setLabState(STATE_AVAILABLE);
            lab.setLabGate(labGate);
            labDAO.add(lab);
            return "success";
        } catch (Exception ex) {
            return "error";
        }
    }

    @RequestMapping("/home/lab/add")
    private String lab_add_action() throws Exception {
        try {
            return "/lab_add.jsp";
        } catch (Exception ex) {
            return "/error.jsp";
        }
    }


    @RequestMapping("/home/lab")
    private String lab_list(Model model) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_LAB_LIST, labDAO.queryList("", -1, -1));
            return "/lab.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/delete")
    private String lab_delete(Model model, @RequestParam(value = "labId") int labId) throws Exception {
        try {
            labDAO.delete(labId);
            return "/home/lab";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/member")
    private String member(Model model, @RequestParam(value = "labId") int labId) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_USER_LIST, labDAO.queryMembers(labId));
            model.addAttribute(ATTRIBUTE_LAB, labDAO.query(labId));
            return "/lab_member.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/member/add")
    private String member_add(Model model, @RequestParam(value = "labId") int labId) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_USER_LIST, labDAO.queryMembers(labId));
            model.addAttribute(ATTRIBUTE_USER_OPTION, labDAO.queryMembersOption(labId));
            return "/lab_member_add.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/member/add_action")
    private String member_add_action(Model model,
                                     @RequestParam(value = "labId") int labId,
                                     @RequestParam(value = "userId") int userId) throws Exception {
        try {
            System.out.println(labId);
            System.out.println(userId);
            labDAO.addMember(labId, userId);
            String result = "redirect: /home/lab/member?labId=" + String.valueOf(labId);
            return result;
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/member/delete")
    private String member_delete(Model model,
                                 @RequestParam(value = "labId") int labId,
                                 @RequestParam(value = "userId") int userId) throws Exception {
        try {
            labDAO.deleteMember(labId, userId);
            String result = "redirect: /home/lab/member?labId=" + String.valueOf(labId);
            return result;
        } catch (Exception e) {
            model.addAttribute(SESSION_ERROR, e.getMessage());
            return "/error.jsp";
        }
    }
}
