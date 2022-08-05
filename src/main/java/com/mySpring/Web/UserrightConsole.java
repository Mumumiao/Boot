package com.mySpring.Web;

import com.mySpring.boot.Userright;
import com.mySpring.service.UserrightSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userright")
@Transactional(rollbackFor =Exception.class)
public class UserrightConsole {
    @Autowired
    private UserrightSeverce userrightSeverce;
    @GetMapping("/list")
    public List<Userright> seleAll(){

        return userrightSeverce.getAll();

    }

}
