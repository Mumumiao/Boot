package com.mySpring.Web;

import com.mySpring.boot.Role;
import com.mySpring.service.MenuSeverce;
import com.mySpring.service.RoleSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Role")
@Transactional(rollbackFor =Exception.class)
public class RoleCotrol {
    @Autowired
    private RoleSeverce roleSeverce;
    @Autowired
    private MenuSeverce menuSeverce;
    @PostMapping("/sele")
    public List<Role> getByCon(Role role){
        System.out.println(role.getName());
        role.setName("干物杰");
     return roleSeverce.getByCon(role);
    }


}
