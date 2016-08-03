package shu.scie.sbcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.Family;
import shu.scie.sbcp.domain.User;
import shu.scie.sbcp.service.FamilyService;
import shu.scie.sbcp.service.GlobalService;
import shu.scie.sbcp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thorn on 2016/8/1.
 */
@Controller
public class FamilyController {
    @Autowired
    private FamilyService familyService;
    private GlobalService globalService;

    @RequestMapping(value = "/getFamilyInfo",method = RequestMethod.POST)
    @ResponseBody
    public Family getFamilyInfo(HttpSession session){
        String id=(String) session.getAttribute("id");
        Family family=familyService.getFamilyInfo(id);
        return family;
    }

    @RequestMapping(value = "/getFamilyUserList",method = RequestMethod.POST)
    @ResponseBody
    public List<User> getFamilyUserList(HttpSession session){
        String userType=(String)session.getAttribute("type");
        String userId=(String)session.getAttribute("id");
        List<User> list=new ArrayList<User>();
        if(!userType.equals("family")){
            return list;
        }else{
            list=familyService.getFamilyUserList(userId);
            return list;
        }
    }

    @RequestMapping(value = "/addBind",method = RequestMethod.POST)
    @ResponseBody
    public String addBind(HttpSession session, @RequestParam int id,@RequestParam String pw){
        String type = (String)session.getAttribute("type");
        if(type.equals("family")){
            if(familyService.checkAccount("user",id,pw)){
                String family = (String) session.getAttribute("id");
                familyService.bind(id,family);
                return "success";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }
}
