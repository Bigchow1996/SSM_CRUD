package com.bigchow.ssm.service;

import java.util.List;
import com.bigchow.ssm.bean.Employee;

public interface EmployeeService {
	public List<Employee> getAll();

	public void saveEmp(Employee employee);

	public boolean checkUser(String empName);

	public Employee getEmp(Integer id);

	public void updateEmp(Employee employee);

	public void deleteBatch(List<Integer> del_ids);

	public void deleteEmp(Integer id);
}
