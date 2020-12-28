package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.service.ComponentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ComponentControllerTest {

    @Autowired
    private ComponentController componentController;

    @MockBean
    private ComponentService componentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(componentController).build();
    }

    @Test
    void createComponent() throws Exception {
        mockMvc.perform(get("/component/new?recipeId=100"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("componentDTO"))
                .andExpect(view().name("component/componentform"));
    }

    @Test
    void updateComponent() throws Exception {
        when(componentService.getDtoById(anyLong())).thenReturn(new ComponentDTO());
        mockMvc.perform(get("/component/update/100"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("componentDTO"))
                .andExpect(view().name("component/componentform"));
    }

    @Test
    void deleteComponent() throws Exception {
        mockMvc.perform(get("/component/delete/100?recipeId=1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void saveComponent() throws Exception {
        when(componentService.save(any())).thenReturn(new Component());
        mockMvc.perform(post("/component"))
                .andExpect(status().is3xxRedirection());
        verify(componentService).save(any());
    }
}