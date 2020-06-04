package com.dwkj.service.service;

import com.dwkj.base.fun.bean.test.TestBean;
import com.dwkj.service.dao.ITestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private ITestDao iTestDao;

    public List<TestBean> getTestList(int age){
        return iTestDao.getTestList(age);
    }
}
