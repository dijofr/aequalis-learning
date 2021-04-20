package com.learning.sphc.cotroller;

import com.learning.sphc.exception.ResourceNotFoundException;
import com.learning.sphc.model.Employee;
import com.learning.sphc.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.Exception;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;



    //get employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }

    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeeById(
            @PathVariable(value = "id") Long id) throws Exception {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no employee found for id ::" + id));
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    //save employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.save(employee);
    }

    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "id") Long employeeId,
            @RequestBody Employee employeeDetails) throws ResourceNotFoundException {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("no employee found for id ::" + employeeId));
        BeanUtils.copyProperties(employeeDetails,employee);
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    //delete employee
    @DeleteMapping("/employees/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("no employee found for id ::" + employeeId));
        this.employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
