package employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private Employee emp;
	@MockBean
	private EmployeeService empservice;
	@MockBean
	private EmployeeRepository repo;
	
	@Test
	public void testPut() throws Exception {
		Integer Id=1;
		Mockito.when(empservice.getEmployeeById(Id)).thenReturn(emp);
		String url="/emp/1";
		mockMvc.perform(put(url)
				.param("id", "1")
				.param("Forename", "virst")
				.param("Surname", "kohli")
				.param("age", "31")
				.param("Company", "RCB")
				.param("Postcode","522321")
				);
	}
	
	@Test
	public void testGetById() throws Exception
	{
		Employee dummyEmployee=new Employee();
		dummyEmployee.setId(1);
		dummyEmployee.setForename("virat");
		dummyEmployee.setSurname("kohli");
		dummyEmployee.setAge(31);
		dummyEmployee.setCompany("RCB");
		dummyEmployee.setPostcode(522321);
		Mockito.when(repo.save(dummyEmployee)).thenReturn(dummyEmployee);

	Mockito.when(empservice.getEmployeeById(Mockito.anyInt())).thenReturn(dummyEmployee);
	
	String URI = "/std/1";
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
			URI).accept(
			MediaType.APPLICATION_JSON);

	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String expectedJson = this.mapToJson(dummyEmployee);
	String outputInJson = result.getResponse().getContentAsString();
    assertThat(outputInJson).isNotEqualTo(expectedJson);  
}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
