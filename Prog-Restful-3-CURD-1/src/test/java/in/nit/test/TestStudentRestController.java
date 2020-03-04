package in.nit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")

public class TestStudentRestController {
	@Autowired
	private MockMvc mockMvc;
	
	
	public void testSave() throws Exception {
		MockHttpServletRequestBuilder request=MockMvcRequestBuilders
				.post("rest/student/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"sname\":\"A\",\"sfee\":3.2}");
		MvcResult result=mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse resp=result.getResponse();
		
		assertEquals(200, resp.getStatus());
		if(!resp.getContentAsString().contains("saved")) {
			fail("Data not Saved");
		}
	}

}
