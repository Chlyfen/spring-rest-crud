package com.chlyfenrichard.cruddemo.rest;

import com.chlyfenrichard.cruddemo.entity.Employee;
import com.chlyfenrichard.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    //quick and dirty: inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){

        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of eemployees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee  = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - "+ employeeId);
        }

        return theEmployee;

    }

    //add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //pada case ini pakai request body karena akan di post mengunakkan JSON

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for PUT /employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee theEmployee  = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Delete employee id - "+ employeeId;
    }

}
