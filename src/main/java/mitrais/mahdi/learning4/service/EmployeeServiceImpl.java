package mitrais.mahdi.learning4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitrais.mahdi.learning4.model.Employee;
import mitrais.mahdi.learning4.repository.EmployeeRepository;

@Service("unitService")
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository unitRepository;
    
    @Override
    public void save(Employee unit){
        unitRepository.save(unit);
    }
	//Read
    @Override
	public Employee getById(int unitId){
        return unitRepository.findbyUnitId(unitId);
    }
	//Update
    @Override
	public void update(Employee unit){
        unitRepository.save(unit);
    }
	//Delete
    @Override
	public void delete(Employee unit){
        unitRepository.delete(unit);
    }
    //Get All
    @Override
	public List<Employee> getAll(){
        return unitRepository.findAll();
    }
}