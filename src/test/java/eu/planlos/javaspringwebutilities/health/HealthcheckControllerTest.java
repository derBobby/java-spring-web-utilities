package eu.planlos.javaspringwebutilities.health;

import eu.planlos.javaspringwebutilities.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = HealthcheckController.class)
@ContextConfiguration(classes={Application.class})
@ActiveProfiles("dev-java-spring-web-utilities")
class HealthcheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void healthEndpoint_returnsOK() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/health"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string("OK"));
    }
}

