package mitrais.mahdi.learning4;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import mitrais.mahdi.learning4.model.*;
import mitrais.mahdi.learning4.service.*;

@RestController
@RequestMapping("/api")
public class EmployeeController{
    
    @Autowired
    EmployeeService employeeService;

    //@GetMapping("/units")
    @RequestMapping(value="/employee", method = RequestMethod.GET)
    public List<Employee> getAllUnits() {
        return employeeService.getAll();
    }

    @PostMapping("/units")
    public void createUnit (@Valid @RequestBody Employee unit)
    {
        employeeService.save(unit);
    }

    @GetMapping("/units/{id}")
    public Employee getUnitbyId (@PathVariable(value = "unitId") int unitId){
        return employeeService.getById(unitId);
    }

    @PostMapping("/units/{id}")
    public void updateUnit (@PathVariable(value = "unitId") int unitId, @Valid @RequestBody Employee unitNewDetails){
        Employee unit = employeeService.getById(unitId);
        unit.setName(unitNewDetails.getName());
        unit.setSalary(unitNewDetails.getSalary());
        employeeService.save(unit);
    }

    @DeleteMapping("/units/{id}")
    public void deleteUnit(@PathVariable(value = "unitId") int unitId){
        Employee unit = employeeService.getById(unitId);
       employeeService.delete(unit);
    }



}