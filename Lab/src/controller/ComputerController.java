package controller;

import DAO.LabDAO;
import IDAO.IComputerDAO;
import IDAO.ILabDAO;
import model.Computer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.Constants;
import utility.Tools;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ComputerController implements Constants {
    @Resource(name = "computerDAO")
    private IComputerDAO computerDAO;

    @Resource(name = "labDAO")
    private ILabDAO labDAO;



    @RequestMapping("/home/lab/computer")
    private String member_add(Model model, @RequestParam(value = "labId") int labId) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_COMPUTER_LIST, computerDAO.queryList(labId));
            model.addAttribute(ATTRIBUTE_LAB, labDAO.query(labId));
            return "/lab_computer.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/computer/delete")
    private String member_delete(Model model,
                                 @RequestParam(value = "labId") int labId,
                                 @RequestParam(value = "computerId") int computerId) throws Exception {
        try {
            computerDAO.delete(labId, computerId);
            return "redirect: /home/lab/computer?labId=" + String.valueOf(labId);
        } catch (Exception e) {
            return "/error.jsp";
        }
    }


    @ResponseBody
    @RequestMapping("/home/lab/computer/add_action")
    private String computerAddAction(
            @RequestParam(value = "labId") int labId,
            @RequestParam(value = "computerId") int computerId,
            @RequestParam(value = "computerIpLast") int computerIpLast,
            @RequestParam(value = "computerLoc") String computerLoc) throws Exception {
        try {
            if (computerIpLast < 0 || computerIpLast > 255) {
                return ALERT_COMPUTER_IP_OUT_OF_RANGE;
            }
            String computerIp = Tools.createIP(labId, computerIpLast);
            if (computerDAO.query(computerIp) != null) {
                return ALERT_COMPUTER_IP_USED;
            }
            if (computerDAO.query(labId, computerId) != null) {
                return ALERT_COMPUTER_ID_USED;
            }
            Computer computer = new Computer();
            computer.setComputerIp(computerIp);
            computer.setComputerLoc(computerLoc);
            computer.setComputerState(STATE_AVAILABLE);
            computer.setLabId(labId);
            computer.setComputerId(computerId);
            computerDAO.add(computer);
            return "success";
        } catch (Exception ex) {
            return "error";
        }
    }


}
