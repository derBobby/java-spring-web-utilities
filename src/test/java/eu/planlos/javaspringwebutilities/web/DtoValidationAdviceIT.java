package eu.planlos.javaspringwebutilities.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.planlos.javaspringwebutilities.Application;
import eu.planlos.javaspringwebutilities.common.Thing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes={Application.class})
@ComponentScan(basePackages = "eu.planlos.javaspringwebutilities")
class DtoValidationAdviceIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void entityNotExisting_adviceTriggered() throws Exception {

        Thing thing = new Thing("INVALID THING");

        ObjectMapper mapper = new ObjectMapper();
        String thingJson = mapper.writeValueAsString(thing);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/thing")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(thingJson))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().string("{\"name\":\"Invalid thing\"}"));
    }
}