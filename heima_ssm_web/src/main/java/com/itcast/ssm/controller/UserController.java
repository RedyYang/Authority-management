package com.itcast.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import com.itcast.ssm.service.IRoleService;
import com.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
@Secured({"ROLE_king","ROLE_ADMIN"})
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/deleteRoleById.do")
    public String deleteRoleById(String roleId, String userId) throws Exception{

        userService.deleteRoleById(roleId,userId);
        return "redirect:findById.do?id="+userId;
    }

    @Secured("ROLE_King")
    @RequestMapping("/relatedRole.do")
    public String relatedRole(String id,String roleId) throws Exception{
        userService.addRoleToUser(id,roleId);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findOtherRoleByUserId(id);
        mv.addObject("roleList",roleList);
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-role");
        return mv;
    }
    @Secured("ROLE_King")
    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception{
        userService.deleteById(id);
        return "redirect:findAll.do";
    }

    //查询指定id的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //用户添加
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    //查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll(page,size);
        PageInfo userList = new PageInfo(userInfoList);
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
}
