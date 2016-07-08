package shu.scie.sbcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shu.scie.sbcp.domain.MedicalHistory;
import shu.scie.sbcp.domain.Parameter;
import shu.scie.sbcp.domain.User;
import shu.scie.sbcp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Thorn on 2016/6/23.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public int getUserIdPara(HttpSession session,String data){
        int id=0;
        if(data.equals("user")){
            id=(Integer) session.getAttribute("id");
        }else{
            String type=(String)session.getAttribute("type");
            if(type.equals("admin")){
                id=Integer.parseInt(data);
            }
        }
        return id;
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public User getUserInfo(HttpSession session, @RequestParam String data){
        int id=getUserIdPara(session,data);
        User user=userService.getUserInfo(id);
        return user;
    }

    @RequestMapping(value = "/getMedicalHistoryList",method = RequestMethod.POST)
    @ResponseBody
    public List<MedicalHistory> getMedicalHistoryList(HttpSession session, @RequestParam String data){
        int id=getUserIdPara(session,data);
        List<MedicalHistory> list=userService.getMedicalHistoryList(id);
        return list;
    }

    @RequestMapping(value = "/getParameterList",method = RequestMethod.POST)
    @ResponseBody
    public List<Parameter> getParameterList(HttpSession session,@RequestParam String data){
        int id=getUserIdPara(session,data);
        List<Parameter> list=userService.getParameterList(id);
        return list;
    }

    @RequestMapping(value = "/userLeaveBed",method = RequestMethod.POST)
    @ResponseBody
    public String userLeaveBed(@RequestParam int userId){
        userService.userLeaveBed(userId);
        return "success";
    }
}
