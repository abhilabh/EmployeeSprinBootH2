package web.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @PostMapping("/addEmp")
    public String addEmp(@RequestBody Employee emp) {
        employeeRepository.save(emp);
        return "SUCCESS";
    }
    @GetMapping("/getAllEmp")
    public Iterable<Employee> getAllEmp(){
        return employeeRepository.findAll();
    }

    @GetMapping("/getEmp/{id}")
    public Optional<Employee> getEmpById(@PathVariable Integer id ){
        return employeeRepository.findById(id);
    }
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmpById(@PathVariable Integer id ){
        employeeRepository.deleteById(id);
        return "SUCCESS";
    }
}
