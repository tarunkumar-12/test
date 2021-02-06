package employee.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit4.SpringRunner;


import com.jayway.jsonpath.internal.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
	@MockBean
	private EmployeeRepository repo;
	@MockBean
	private EmployeeService empservice;
	@Autowired
	private MockMvc mockMvc;
	private Employee emp;

		
		@InjectMocks
		private EmployeeController studentController;
		
		@Before
		public void setUp() throws Exception{
			mockMvc =MockMvcBuilders.standaloneSetup(studentController)
					.build();
		}

	@Test
	void testViewHomePage() throws Exception {
		 List<Employee> empList = new ArrayList<>();
		 empList.add(new Employee("smith", "steve",27,"RCB",87790,1));
		 empList.add(new Employee("maxwell", "glen",32,"CSK",97609,2));
		    //studentRepository.saveAll(studentList);
	    MvcResult mvcResult= mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();
	    byte[] bytes=mvcResult.getResponse().getContentAsByteArray();
	    Path path=(Path) Paths.get("index.xlsx");
	   //Files.write(path, bytes);
}
	@Test
	public void testPost() throws Exception {
		Integer Id=1;
		Mockito.when(empservice.getEmployeeById(Id)).thenReturn(emp);
		String url="/new";
		 MvcResult mvcResult=(MvcResult) mockMvc.perform(put(url)
				.param("id", "1")
				.param("Forename", "smith")
				.param("Surname", "steve")
				.param("age", "30")
				.param("Company", "RCB")
				.param("Postcode","87790"))
				.andExpect(status().isOk()).andReturn();
		 byte[] bytes=mvcResult.getResponse().getContentAsByteArray();
		    Path path=(Path) Paths.get("form.xlsx");
		  //Files.write(path, bytes);
		         	
}

}
