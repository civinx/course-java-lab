package controller;

import DAO.LabDAO;
import IDAO.IComputerDAO;
import IDAO.ILabDAO;
import exception.BaseExceptionHandleAction;
import exception.BusinessException;
import model.Computer;
import model.Lab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utility.Constants;
import utility.Tools;

import javax.annotation.Resource;

@Controller
public class ComputerController extends BaseExceptionHandleAction implements Constants {
    @Resource(name = "computerDAO")
    private IComputerDAO computerDAO;

    @Resource(name = "labDAO")
    private ILabDAO labDAO;


    @RequestMapping("/home/lab/computer")
    private String computer_list(Model model, @RequestParam(value = "labId") int labId) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_COMPUTER_LIST, computerDAO.queryList(labId));
            model.addAttribute(ATTRIBUTE_LAB, labDAO.query(labId));
            return "/lab_computer.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/computer/add")
    private String computer_add(Model model,
                                 @RequestParam(value = "labId") int labId) throws Exception {
        try {
            model.addAttribute(ATTRIBUTE_LAB, labDAO.query(labId));
            return "/lab_computer_add.jsp";
        } catch (Exception e) {
            return "/error.jsp";
        }
    }

    @RequestMapping("/home/lab/computer/delete")
    private String computer_delete(Model model,
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
    private String computer_add_action(
            @RequestParam(value = "labId") int labId,
//            @RequestParam(value = "computerId") int computerId,
            @RequestParam(value = "computerIpLast") int computerIpLast,
            @RequestParam(value = "computerLoc") String computerLoc) throws Exception {

        if (computerIpLast < 0 || computerIpLast > 255) {
//                return ALERT_COMPUTER_IP_OUT_OF_RANGE;
            throw new BusinessException(ALERT_COMPUTER_IP_OUT_OF_RANGE, CODE_ERROR);
        }
        Lab lab = labDAO.query(labId);
        String computerIp = Tools.createIP(lab.getLabGate(), computerIpLast);
        if (computerDAO.query(computerIp) != null) {
            throw new BusinessException(ALERT_COMPUTER_IP_USED);
        }
//        if (computerDAO.query(labId, computerId) != null) {
//            throw new BusinessException(ALERT_COMPUTER_ID_USED);
//        }
        Computer computer = new Computer();
        computer.setComputerIp(computerIp);
        computer.setComputerLoc(computerLoc);
        computer.setComputerState(STATE_AVAILABLE);
        computer.setLabId(labId);
//        computer.setComputerId(computerId);
        computer.setUserId(0);
        computerDAO.add(computer);
        return Tools.creteJasonString(CODE_SUCCESS);
    }
}
