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
//HttpHeaders header=new HttpHeaders()
 // return ResponseEntity.status(HttpStatus.OK).headers(header).body(list);
 return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
 }
			 
			 
@PutMapping("/update")
 public ResponseEntity<String> update(@PathVariable(value = "id") Integer id,  @RequestBody Employee e) throws RecordNotFoundException 
	{
	Employee emp =emprepo .findById(id).orElseThrow(()  -> new RecordNotFoundException("No record found  on this id "+ id));
  // emp.setId(e.getId());
	emp.setForename(e.getForename());
	emp.setSurname(e.getSurname());
	emp.setAge(e.getAge());
	emp.setCompany(e.getCompany());
	emp.setPostcode(e.getPostcode());
	emprepo.save(emp);
	return new ResponseEntity<String>("Recored updated successfully", HttpStatus.OK);
				 	
	 }
			 
			 
 @DeleteMapping("/{id}")
 	 public String deleteEmployeeById(@PathVariable("id") int id) throws RecordNotFoundException {
	 empservice.deleteEmployeeById(id);
     return "Deleted Successfully";
   }

}



