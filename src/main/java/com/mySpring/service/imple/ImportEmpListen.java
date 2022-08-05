package com.mySpring.service.imple;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.mySpring.boot.Dept;
import com.mySpring.boot.Emp;
import com.mySpring.service.EmpService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImportEmpListen implements ReadListener<Emp> {
    public static final int BATCH_COUNT = 10;
    private EmpService empService;
    /**
     * 临时存储
     */
    private List<Emp> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public ImportEmpListen(EmpService empService) {
        this.empService = empService;
    }
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
        cachedDataList.forEach(i->{
            Dept dept = new Dept();
            dept.setId(i.getDt());
            i.setDept(dept);
            empService.save(i);
            log.info("已经导入数据库"+i.getName());
        });
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("存储数据库成功！");
    }


        }
