package shu.scie.sbcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shu.scie.sbcp.domain.Admin;
import shu.scie.sbcp.domain.User;
import shu.scie.sbcp.service.AdminService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thorn on 2016/6/23.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/getAdminInfo",method = RequestMethod.POST)
    @ResponseBody
    public Admin getAdminInfo(HttpSession session){
        int id=(Integer) session.getAttribute("id");
        Admin admin=adminService.getAdminInfo(id);
        return admin;
    }

    @RequestMapping(value = "/getUserList" , method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUserList(HttpSession session){
        String userType=(String)session.getAttribute("type");
        List<User> list=new ArrayList<User>();
        if(!userType.equals("admin")){
            return list;
        }else{
            list=adminService.getUserList();
            return list;
        }
    }
}
