package com.dwkj.service.dao;

import com.dwkj.base.fun.bean.test.TestBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITestDao {
    List<TestBean> getTestList(int age);
}
