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

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestParam String str){
        System.out.println(str);
        return "success";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session, @RequestParam String userId,
                 @RequestParam String userPw, @RequestParam String userType,
                        @RequestParam String source) {
        String success="success";
        String fail="fail";
        if(userType.equals("family")){
            System.out.println(userId);
            if(globalService.checkFamilyAccount(userId,userPw)){
                session.setAttribute("id",userId);
                session.setAttribute("type",userType);
                return success;
            } else {
                return fail;
            }
        } else {
            if(globalService.checkAccount(userType,Integer.parseInt(userId),userPw)){
                session.setAttribute("id",Integer.parseInt(userId));
                session.setAttribute("type",userType);
                if(source.equals("device")){
                    globalService.userLogin(Integer.parseInt(userId));
                }
                return success;
            }else{
                return fail;
            }
        }

    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public @ResponseBody String logout(HttpSession session,@RequestParam String source){
        if(source.equals("device")){
            int id=(Integer)session.getAttribute("id");
            globalService.userLogout(id);
        }
        session.removeAttribute("id");
        session.removeAttribute("type");
        return "success";
    }

    @RequestMapping(value = "/logoutFromApp",method = RequestMethod.POST)
    public @ResponseBody String logoutFromApp(@RequestParam int id){
        globalService.userLogout(id);
        return "success";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam String id,@RequestParam String name,
                           @RequestParam String pw,@RequestParam String phone){
        System.out.println("register");
        System.out.println("注册");
        if(globalService.checkFamilyID(id)){
            return "exist";
        }else {
            System.out.println("not exist");
            globalService.insertFamily(id,name,pw,phone);
            return "success";
        }
    }
}
