package com.syh.example.rbacoopdemo;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author shen.yuhang
 * created on 2020/11/12
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class,
	FlywayTestExecutionListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@FlywayTest
public abstract class BaseSpringBootTest {
}
