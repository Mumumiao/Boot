package com.mySpring.boot;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.mySpring.boot.Config.Bean1;
import com.mySpring.boot.Config.Bean2;
import com.mySpring.boot.Config.SpringConfig;
import com.mySpring.mapper.DeptMapper;
import com.mySpring.service.DeptService;
import com.mySpring.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
class BootApplicationTests {
    @Autowired
    private DeptMapper oaDeptMapper;
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;

    @Test
    void contextLoads() {
        Dept dept = new Dept();
       /* oa_dept.setName("市场部");
        oaDeptMapper.insert(oa_dept);*/
        List<Dept> oaDepts = oaDeptMapper.selectList(null);
        oaDepts.forEach(i -> {
            System.out.println(i.getName());
        });
    }

    @Test
    void insert() {
        Dept dept = new Dept();
        dept.setName("销售部");
        deptService.save(dept);
    }

    @Test
    void insert2() {
        Emp emp = new Emp();
        emp.setName("李华");
        Dept dept = new Dept();
        dept.setId(1);
        emp.setDept(dept);
        emp.setGender("男");
        emp.setEntry_date("2012/9/8");
        emp.setSalary(new BigDecimal("123"));
        emp.setPhoto("/png");
        empService.save(emp);
    }

    @Test
    void tsett() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Bean1 bean1 = (Bean1) context.getBean("bean1");
        Bean2 bean2 = (Bean2) context.getBean("bean2");
        System.out.println("输出" + bean2.getName());
        System.out.println("输出" + bean1.getBean2().getName());

    }

    @Test
    public void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        Role role = new Role();
        user.setRole(role);
        user.setAccount("");
        Set<ConstraintViolation<User>> validate = validator.validate(user, AddGroup.class);
        validate.forEach(c -> {
            System.out.println(c.getMessage());
        });
    }

    @Test
    public void simpleRead() {
        String fileName = "C://Users/ASUS/Desktop/新项目/" + "test.xls";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Emp.class, new ReadListener<Emp>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 10;
            /**
             *临时存储
             */
            private List<Emp> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(Emp data, AnalysisContext context) {
                cachedDataList.add(data);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
                log.info("存储数据库成功！");
            }
        }).sheet().doRead();


    }


}
