package com.bigchow.ssm.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bigchow.ssm.bean.Department;
import com.bigchow.ssm.bean.Employee;
import com.bigchow.ssm.dao.DepartmentMapper;
/**
 * 测试dao层的工作
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件,如果不使用Spring单元测试的话我们还得自己写ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
 * spring-test封装了junit，使得spring应用进行单元测试更加简单方便。
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */
import com.bigchow.ssm.dao.EmployeeMapper;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void test(){
		//1、插入几个部门
		departmentMapper.insertSelective(new Department(null, "开发部"));
		departmentMapper.insertSelective(new Department(null, "测试部"));
		//2、生成员工数据，测试员工插入	
		employeeMapper.insertSelective(new Employee(null, "bigchow", "M", "bigchow@qq.com", 1));
		//3、批量插入多个员工；批量，使用可以执行批量操作的sqlSession。
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0;i<1000;i++){
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null,uid, "M", uid+"@qq.com", 1));
		}
		System.out.println("批量完成");
	}
}
