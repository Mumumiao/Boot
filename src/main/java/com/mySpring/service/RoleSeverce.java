package com.mySpring.service;


import com.mySpring.boot.Menu;
import com.mySpring.boot.PageBean;
import com.mySpring.boot.Role;
import com.mySpring.boot.Userright;

import java.util.ArrayList;
import java.util.List;

public interface RoleSeverce {
    public void add(Role role, ArrayList<Integer> right_id, ArrayList<Integer> menu_id);
    public List<Role> getByCon(Role role);
    public  void dele(int i);
    public PageBean<Role> getByPage(Role role, int page, int pagesize);
    public void update(Role role, ArrayList<Integer> menu_id,ArrayList<Integer> right_id);
    public List<Menu> getMenu(int id);
    public List<Userright> getRight(int id);
}
