package mitrais.mahdi.learning4.service;

import java.util.List;

import mitrais.mahdi.learning4.model.*;

public interface EmployeeService{
    public void save(Employee unit);
	//Read
	public Employee getById(int unitId);
	//Update
	public void update(Employee unit);
	//Delete
	public void delete(Employee unit);
	//Get All
	public List<Employee> getAll();
}