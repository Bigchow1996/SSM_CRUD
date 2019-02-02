package com.bigchow.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigchow.ssm.bean.Employee;
import com.bigchow.ssm.bean.EmployeeExample;
import com.bigchow.ssm.bean.EmployeeExample.Criteria;
import com.bigchow.ssm.dao.EmployeeMapper;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 *查询所有员工（分页）
	 */
	@Override
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	/**
	 * 员工保存
	 * @param employee
	 */
	@Override
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}
	/**
	 * 检验用户名是否可用
	 * @param empName
	 * @return  true：代表当前姓名可用   fasle：不可用
	 */
	@Override
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		//创建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}
	/**
	 * 按照员工id查询员工
	 * @param id
	 * @return
	 */
	@Override
	public Employee getEmp(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	/**
	 * 员工更新
	 * @param employee
	 */
	@Override
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	//批量删除
	@Override
	public void deleteBatch(List<Integer> del_ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(del_ids);
		employeeMapper.deleteByExample(example);
	}
	//单个删除
	@Override
	public void deleteEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
}
