package com.mySpring.Web;


import com.mySpring.boot.Menu;
import com.mySpring.service.MenuSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Transactional(rollbackFor =Exception.class)
public class MenuConsole {
    @Autowired
    private MenuSeverce menuSeverce;

    @GetMapping("/list")
    public List<Menu> seleall() {

        return menuSeverce.getAll();

    }
}
