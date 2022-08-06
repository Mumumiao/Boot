package com.mySpring.Web;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mySpring.boot.*;
import com.mySpring.service.EmpService;
import com.mySpring.service.imple.ImportEmpListen;
import com.mySpring.vo.Empvo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Slf4j
@RestController
@RequestMapping("/Emp")
@Transactional(rollbackFor = Exception.class)
@Validated
public class EmpControl {
    @Autowired
    private EmpService empService;
    @Value("${m.picture}")
    private String picture;

    @PostMapping("/add")
    public ResponseEntility add(@RequestBody @Valid Emp emp) {
        Dept dept = new Dept();
        dept.setId(emp.getDt());
        emp.setDept(dept);
        empService.save(emp);
        return ResponseFactory.getSuResponseEntility("成功插入");
    }
    @PostMapping("/up")
    public ResponseEntility up(@RequestBody @Validated(AddGroup.class)  Emp emp) {
        Dept dept = new Dept();
        dept.setId(emp.getDt());
        emp.setDept(dept);
        empService.up(emp);
        return ResponseFactory.getSuResponseEntility("成功更新"+emp.getId());
    }

    @PostMapping("/sele")
    public ResponseEntility sele(@RequestBody @Valid Empvo empvo) {
        Dept dept = new Dept();
        dept.setId(empvo.getDt());
        empvo.getEmp().setDept(dept);
        return ResponseFactory.getSuResponseEntility(empService.getBycon(empvo.getEmp(), empvo.getPage(), empvo.getSize()));
    }

    @PostMapping("/pic")
    public ResponseEntility part(MultipartFile file, @Min(value = 1,message = "id不能为空") int id) {
        String path = picture + file.getOriginalFilename();
        Emp emp = new Emp();
        emp.setId(id);
        emp.setPhoto(path);
        empService.up(emp);
        try {
            if (file != null) {
                file.transferTo(new File(path));
            } else {
                return ResponseFactory.getDeResponseEntility("失败上传");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseFactory.getSuResponseEntility("成功上传图片");
    }

    @PostMapping("page")
    public ResponseEntility part(@Min(value = 1, message = "不能小于1亲亲") int p) {
        System.out.println("输出页数" + p);
        PageBean<Emp> pageBean = new PageBean<>();
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        IPage<Emp> page = new Page<>(p, 4);
        IPage<Emp> fpage = empService.page(page, wrapper);
        pageBean.setData(fpage.getRecords());
        pageBean.setCurrPage((int) fpage.getCurrent());
        pageBean.setPageSize((int) fpage.getSize());
        pageBean.setTotalPages((int) fpage.getPages());
        pageBean.setTotalNums((int) fpage.getTotal());
        return ResponseFactory.getSuResponseEntility(pageBean);
    }

    @PostMapping("import")
    public ResponseEntility<String> importEmp(MultipartFile file) {
        System.out.println("文件名是" + file.getOriginalFilename());
        String path = picture + file.getOriginalFilename();
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        EasyExcel.read(path, Emp.class, new ImportEmpListen(empService)).sheet().doRead();
        return ResponseFactory.getSuResponseEntility("导入成功");
    }

    @PostMapping("export")
    public ResponseEntility<String> exportEmp(@RequestBody @Valid Empvo empvo) {
        Dept dept = new Dept();
        dept.setId(empvo.getDt());
        empvo.getEmp().setDept(dept);
       List<Emp> emps= empService.getBycon(empvo.getEmp(), empvo.getPage(), empvo.getSize()).getData();
       List<Emp> empf=new ArrayList<>();
       emps.forEach(i->{
           Emp emp=new Emp();
           emp.setName(i.getName());
           emp.setGender(i.getGender());
           emp.setPhoto(i.getPhoto());
           emp.setEntry_date(i.getEntry_date());
           emp.setSalary(i.getSalary());
           emp.setId(i.getId());
           emp.setDt(i.getDept().getId());
           empf.add(emp);
       });
        emps.forEach(i->{
            System.out.println(i.getName());
        });
        String filepath=picture + "emp.xlsx";
        File file = new File(filepath);
        System.out.println("文件路径为"+filepath);
        if (!file.exists()) {
            try {
                System.out.println("文件不存在重新创建");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("dept");
        excludeColumnFiledNames.add("num");
        EasyExcel.write(filepath, Emp.class).excludeColumnFieldNames(excludeColumnFiledNames).sheet("员工表").doWrite(empf);
        return ResponseFactory.getSuResponseEntility("导出成功");
    }
}
