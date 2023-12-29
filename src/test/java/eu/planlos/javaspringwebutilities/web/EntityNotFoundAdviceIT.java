package eu.planlos.javaspringwebutilities.web;

import eu.planlos.javaspringwebutilities.Application;
import eu.planlos.javaspringwebutilities.common.ThingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes={Application.class})
@ComponentScan(basePackages = "eu.planlos.javaspringwebutilities")
class EntityNotFoundAdviceIT {

    @MockBean
    private ThingRepository mockRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void entityNotExisting_adviceTriggered() throws Exception {

        doThrow(new EntityNotFoundException("Nope"))
                .when(mockRepository)
                .deleteById(any());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/thing/{id}", 99999))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.content().json("{\"error\":\"Nope\"}"));
    }
}