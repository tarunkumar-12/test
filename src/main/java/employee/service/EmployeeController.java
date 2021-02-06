package employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@ComponentScan
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	
	@GetMapping("/")
	public String homePage( Model model) {
	    List<Employee> list = empService.listAll();
	    model.addAttribute("employee1", list);  
	    return "index";
	}
	
	@GetMapping("/add")
	public String getEmployee(  Model model){
		Employee employee=new Employee();
		model.addAttribute("employee2",employee);
		return "addEmployee";
	}
	
	@PostMapping("/save")
	public String addEmployee(@ModelAttribute("employee") Employee employee)
	{
		empService.save(employee);
		return "redirect:/";	
	}
	
	@GetMapping("/get/{id}")
	public String getEmployeeById(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException {
        
		if (id.isPresent()) {
			Employee emp = empService.getEmployeeById(id.get());
			model.addAttribute("employee", emp);
		} 
		return "index";
	}
}
	
		

