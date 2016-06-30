package shu.scie.sbcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shu.scie.sbcp.service.GlobalService;

import javax.servlet.http.HttpSession;


/**
 * Created by Thorn on 2016/6/22.
 */
@Controller
public class CommonController {
    @Autowired
    private GlobalService globalService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session, @RequestParam Integer userId,
                 @RequestParam String userPw, @RequestParam String userType) {
        String success="success";
        String fail="fail";
        if(globalService.checkAccount(userType,userId,userPw)){
            session.setAttribute("id",userId);
            session.setAttribute("type",userType);
            return success;
        }else{
            return fail;
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public @ResponseBody String logout(HttpSession session){
        session.removeAttribute("id");
        session.removeAttribute("type");
        return "success";
    }
}
