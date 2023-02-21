package com.noduco.demoproject.service;

import com.noduco.demoproject.entity.Employee;
import com.noduco.demoproject.entity.EmployeeCsvRecord;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    private List<Map<String, Object>> employeeMaps = new ArrayList<>();

    public void processEmployeeData(Employee employee) {
        employees.add(employee);
    }
    public void processEmployeeMap(Map<String, Object> employeeMap) {
        employeeMaps.add(employeeMap);
    }
    public Map<String, Object> getMap(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("employeeName", employee.getEmployeeName());
        map.put("employeeAge", employee.getEmployeeAge());
        map.put("employeeGender", employee.getEmployeeGender());
        map.put("employeeDepartment", employee.getEmployeeDepartment());
        map.put("employeeSalary", employee.getEmployeeSalary());
        return map;
    }

    public Employee convertAndTransform(EmployeeCsvRecord csvRecord) {
//        return Employee.builder()
//                .employeeName(csvRecord.getEmployeeName().trim().toUpperCase())
//                .employeeDepartment(csvRecord.getEmployeeDepartment().trim().toUpperCase())
//                .employeeAge(csvRecord.getEmployeeAge())
//                .employeeGender(csvRecord.getEmployeeGender().trim().toUpperCase())
//                .employeeSalary(csvRecord.getEmployeeSalary())
//                .build();
        return null;
    }

}
