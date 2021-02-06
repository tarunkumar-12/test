	package employee.service;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmployeeRestController {			
	
	@Autowired EmployeeService empservice;
	@Autowired EmployeeRepository emprepo;
			
 @GetMapping("/emp")
 public ResponseEntity<List<Employee>> getAllEmployees() {
 List<Employee> list = empservice.getAll();
 return new ResponseEntity<> (list, new HttpHeaders(), HttpStatus.OK);
 }
			 
			 
@PutMapping("/update")
 public ResponseEntity<String> update(@PathVariable(value = "id") Integer id,  @RequestBody Employee employee) throws RecordNotFoundException 
	{
	Employee emp =emprepo .findById(id).orElseThrow(()  -> new RecordNotFoundException("No record found  on this id "+ id));
 
	emp.setForename(employee.getForename());
	emp.setSurname(employee.getSurname());
	emp.setAge(employee.getAge());
	emp.setCompany(employee.getCompany());
	emp.setPostcode(employee.getPostcode());
	emprepo.save(emp);
	return new ResponseEntity<>("Recored updated successfully", HttpStatus.OK);
				 	
	 }
			 
			 
 @DeleteMapping("/{id}")
 	 public String deleteEmployeeById(@PathVariable("id") int id) throws RecordNotFoundException {
	 empservice.deleteEmployeeById(id);
     return "Deleted Successfully";
   }

}



