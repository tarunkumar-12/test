package employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceTest {
		
		@Autowired
		private EmployeeService empservice;
		@MockBean
		private EmployeeRepository repo;
		
		@Test
		public void listAll(){
		    List<Employee> empList = new ArrayList<>();
		    empList.add(new Employee("smith", "steve",27,"RCB",87790,1));
		    empList.add(new Employee("maxwell", "glen",32,"CSK",97609,2));
		    when(repo.findAll()).thenReturn(empList);
		    List<Employee> expectedList = empservice.listAll();
		    assertEquals(2, expectedList.size());
		 }
		
		@Test
	    public void testDelete() throws Exception
	    {
	   
			Employee dummyemp=new Employee();
			dummyemp.setForename("smith");
			dummyemp.setSurname("steve");
			dummyemp.setAge(30);
			dummyemp.setCompany("RCB");
			dummyemp.setPostcode(9755);
			Mockito.when(repo.save(dummyemp)).thenReturn(dummyemp);
			//Mockito.when(repo.findById(1)).thenReturn(dummyemp);
			assertThat(repo.findById(1)).isEqualTo(empservice.getEmployeeById(1));
		    Mockito.when(repo.existsById((int) dummyemp.getId())).thenReturn(false);
	        assertFalse(repo.existsById((int) dummyemp.getId()));
	    }

}
