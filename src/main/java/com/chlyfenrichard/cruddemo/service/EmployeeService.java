package com.chlyfenrichard.cruddemo.service;

import com.chlyfenrichard.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int theID);

    Employee save(Employee theEmployee);

    void deleteById(int theId);


}
