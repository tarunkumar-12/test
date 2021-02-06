package employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
	
	@MockBean
	private EmployeeRepository repo;
	@Autowired
	private EmployeeService empservice;

	@Test
	public void testListAll() {
		List<Employee> studentList = new ArrayList<>();
	    studentList.add(new Employee("smith", "steve",27,"xyz",87790,1));
	    studentList.add(new Employee("maxwell", "glen",32,"abc",97609,2));

	    when(repo.findAll()).thenReturn(studentList);

		assertThat(studentList).size().isEqualTo(2);	
	}
    
    @Test
	public void testSave() throws Exception
	{
		Employee dummyemp=new Employee();
		dummyemp.setForename("virat");
		dummyemp.setSurname("kohli");
		dummyemp.setAge(31);
		dummyemp.setCompany("RCB");
		dummyemp.setPostcode(9755);
		Mockito.when(repo.save(dummyemp)).thenReturn(dummyemp);
		assertThat(empservice.saveOrUpdate(dummyemp)).isEqualTo(dummyemp);
		Mockito.when(repo.getOne(1)).thenReturn(dummyemp);
		Employee emp=(Employee) repo.getOne(1);
		Mockito.verify(repo).getOne(1);
		assertThat(emp).isNotNull();	
	}
    
    @Test
    public void testUpdateEmployee() throws Exception
    {
    	
    	Employee dummyemp=new Employee();
    	dummyemp.setForename("smith");
    	dummyemp.setSurname("steve");
    	dummyemp.setAge(31);
    	dummyemp.setCompany("RCB");
    	dummyemp.setPostcode(9755);
	    Mockito.when(empservice.getEmployeeById(1)).thenReturn(dummyemp);
		assertThat(repo.findById(1)).isEqualTo(dummyemp);
		Mockito.when(empservice.getEmployeeById(Mockito.anyInt())).thenReturn(dummyemp);
		
		String URI = "/emp/1";
		MockMvcRequestBuilders.put(URI);
    }

}
