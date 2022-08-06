package com.mySpring.Web;


import com.mySpring.boot.*;
import com.mySpring.service.MenuSeverce;
import com.mySpring.service.RoleSeverce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@Transactional(rollbackFor =Exception.class)
@Validated
public class Roleconsole {
    @Autowired
    private RoleSeverce roleSeverce;
    @Autowired
    private MenuSeverce menuSeverce;
    private int roleid;
    private int tempage;


 /*   @RequestMapping("/rolei")
    public String role(String name, Model model, HttpSession session) {
        if ("角色新增".equals(name)) {
            return "role_add";
        } else if ("角色列表".equals(name)) {
            User user = (User) session.getAttribute("user");
            Role role = new Role();
            PageBean<Role> pagebean = roleSeverce.getByPage(role, 1, 3);
            System.out.println("总页数是" + pagebean.getTotalPages());
            List<Role> roles = pagebean.getData();
            roles.forEach(e -> {
                System.out.println(e.getName());
            });
            model.addAttribute("page", pagebean);
            model.addAttribute("role", roles);
            return "role_list";
        }
        return "index";
    }*/


    @RequestMapping("/role/add")
    @ResponseBody
    public ResponseEntility<String> roleadd(@Validated(AddGroup.class) Role role, @NotEmpty(message = "菜单不能为空") int[] mid, @NotEmpty(message = "权利不能为空") int[] uid) {
        ArrayList<Integer> midarr = new ArrayList<>();
        ArrayList<Integer> uidarr = new ArrayList<>();
        role.setStatus("可见");
        if (mid != null) {
            for (int i : mid) {
                System.out.println("m" + i);
                midarr.add(i);
            }
        }
        if (uid != null) {
            for (int i : uid) {
                System.out.println("u" + i);
                uidarr.add(i);
            }
        }
        roleSeverce.add(role, uidarr, midarr);

        return ResponseFactory.getSuResponseEntility("添加的角色的id为"+role.getId());
    }

 /*   @RequestMapping("/role/dele")
    public String roledele(int id, Model model, int page) {
        roleSeverce.dele(id);
        Role role = new Role();
        PageBean<Role> pagebean = roleSeverce.getByPage(role, page, 3);
        List<Role> roles = pagebean.getData();
        model.addAttribute("page", pagebean);
        model.addAttribute("role", roles);
        return "role_list";
    }*/

    @RequestMapping("/rolepage")
    @ResponseBody
    public ResponseEntility<PageBean<Role>> rolepage(@Min(value = 1,message = "页码不能为空") int page) {
        Role role = new Role();
        PageBean<Role> pagebean = roleSeverce.getByPage(role, page, 3);
        System.out.println("总页数是" + pagebean.getTotalPages());
        List<Role> roles = pagebean.getData();
        return ResponseFactory.getSuResponseEntility(pagebean);
    }
    @RequestMapping("/role/selecon")
    @ResponseBody
    public ResponseEntility<List<Role>> getByCon(@RequestBody  Role role){
        return   ResponseFactory.getSuResponseEntility(roleSeverce.getByCon(role));
    }
    /*@RequestMapping("/toroleup/{id}/{page}")
    public String toroleUp(@PathVariable("id") int id, Model model, @PathVariable("page") int page) {
        log.info("输出要更新的id" + id);
        Role role = new Role();
        PageBean<Role> pagebean = roleSeverce.getByPage(role, page, 3);
        List<Role> roles = pagebean.getData();
        model.addAttribute("page", pagebean);
        model.addAttribute("role", roles);
        return "role_up";
    }*/
/*
    @RequestMapping("/role/toup/{id}/{page}")
    public String toroleUp(@PathVariable("id") int id, Model model, @PathVariable("page") int page) {
        roleid = id;
        tempage = page;
        return "role_up";
    }

    @RequestMapping("/roleup/getmenu")
    @ResponseBody
    public List<Menu> roleUpGetMenu() {
        List<Menu> menus = roleSeverce.getMenu(roleid);
        return menus;
    }
    @RequestMapping("/roleup/getall")
    @ResponseBody
    public List<Menu> roleUpGetall() {
        return menuSeverce.getAll();
    }
*/

    @RequestMapping("/role/up")
    @ResponseBody
    public ResponseEntility<String> roleup(@Valid Role role, Model model,int[] mid,int[] uid) {
        ArrayList<Integer> midarr = new ArrayList<>();
        ArrayList<Integer> uidarr = new ArrayList<>();
        if (mid != null) {
            for (int i : mid) {
                System.out.println("m" + i);
                midarr.add(i);
            }
        }
        if (uid != null) {
            for (int i : uid) {
                System.out.println("u" + i);
                uidarr.add(i);
            }
        }
        roleSeverce.update(role,midarr,uidarr);
        return ResponseFactory.getSuResponseEntility("修改的角色名字为"+role.getName());
    }
 /*   @RequestMapping(value = "/rolelist")
    public void rolelist(HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("Application/json;Charset=utf-8");
        User user = (User) session.getAttribute("user");
        Role role = new Role();
        List<Role> roles = roleSeverce.getByCon(role);
        String r = JSON.toJSONString(roles);
        PrintWriter writer = response.getWriter();
        writer.write(r);
        writer.close();
    }*/
   /* @RequestMapping(value = "/roleli")
    public String rolelist(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Role role = new Role();
        List<Role> roles=roleSeverce.getByCon(role);
        model.addAttribute(roles);
        return "/";

    }*/

}
