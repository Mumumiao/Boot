package com.mySpring.service.imple;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mySpring.boot.*;
import com.mySpring.mapper.MrcMapper;
import com.mySpring.mapper.RoleMapper;
import com.mySpring.mapper.UrcMapper;
import com.mySpring.service.RoleSeverce;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Service
@Slf4j
public class RoleServiceEn implements RoleSeverce {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UrcMapper urcMapper;
    @Autowired
    private MrcMapper mrcMapper;

    @Override
    public void add(Role role, ArrayList<Integer> right_id, ArrayList<Integer> menu_id) {
        roleMapper.add(role);
        int id = role.getId();
        log.info("角色的id是" + id);
        Userright userright = new Userright();
        Menu menu = new Menu();
        Urc urc = new Urc();
        urc.setRole(role);
        Mrc mrc = new Mrc();
        mrc.setRole(role);
        right_id.forEach(i -> {
            userright.setId(i);
            urc.setUserright(userright);
            urcMapper.add(urc);
        });
        menu_id.forEach(i -> {
            menu.setId(i);
            mrc.setMenu(menu);
            mrcMapper.add(mrc);

        });
    }

    @Override
    public List<Role> getByCon(Role role) {
        return roleMapper.seleByCon(role);

    }

    @Override
    public void dele(int i) {
        roleMapper.dele(i);
    }

    @Override
    public PageBean<Role> getByPage(Role role, int page, int pagesize) {
        PageBean<Role> pageBean = new PageBean<>();
        Page<Object> p = PageHelper.startPage(page, pagesize);
        List<Role> roles = roleMapper.seleByCon(role);
        pageBean.setData(roles);
        pageBean.setCurrPage(page);
        pageBean.setPageSize(roles.size());
        pageBean.setTotalPages(p.getPages());
        pageBean.setTotalNums((int) p.getTotal());
        return pageBean;

    }

    @Override
    public void update(Role role, ArrayList<Integer> menu_id) {
        mrcMapper.deleByRole(role.getId());
        Menu menu = new Menu();
        Mrc mrc = new Mrc();
        mrc.setRole(role);
        menu_id.forEach(e -> {
            menu.setId(e);
            mrc.setMenu(menu);
            mrcMapper.add(mrc);
        });

        roleMapper.update(role);
    }

    @Override
    public List<Menu> getMenu(int id) {
        Role role = roleMapper.getMenuById(id);
        return role.getMenus();
    }

    @Override
    public List<Userright> getRight(int id) {
        Role role = roleMapper.getRightById(id);
        return role.getUserrights();
    }
}
