package com.itcast.ssm.controller;

import com.itcast.ssm.dao.IRoleDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IPermissionService;
import com.itcast.ssm.service.IProductService;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/role")
@Secured({"ROLE_king","ROLE_ADMIN"})
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/deletePermissionById.do")
    public String deletePermissionById(String permissionId, String roleId) throws Exception{
        roleService.deletePermissionById(permissionId,roleId);
        return "redirect:findById.do?id="+roleId;
    }

    @RequestMapping("/addPermission.do")
    public String addPermission(String id,String permissionId) throws Exception{
        roleService.addPermissionToRole(id,permissionId);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception{
        roleService.deleteById(id);
        return "redirect:findAll.do";
    }

    //根据角色查询对应的资源权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findOtherPermissionByRoleId(id);
        mv.addObject("permission",permissionList);
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-addpermission");

        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }
}
