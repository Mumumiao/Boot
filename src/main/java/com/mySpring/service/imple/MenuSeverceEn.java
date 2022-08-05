package com.mySpring.service.imple;

import com.mySpring.boot.Menu;
import com.mySpring.mapper.MenuMapper;
import com.mySpring.service.MenuSeverce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuSeverceEn implements MenuSeverce {
    @Autowired
    private MenuMapper menuMapper;
     @Override
    public List<Menu> getAll() {
        return menuMapper.selectall();
    }
}
