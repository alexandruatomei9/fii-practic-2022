package ro.info.iasi.fiipractic.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ro.info.iasi.fiipractic.config.AppConfig;
import ro.info.iasi.fiipractic.config.FrontControllerConfig;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FrontControllerConfig.class, AppConfig.class})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntegrationTest {

    public static final String JSON_CONTENT_TYPE = "application/json";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Order(1)
    @Test
    public void shouldFindPostControllerBeanInServletContext() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("userController"));
    }

    @Order(2)
    @DisplayName("get all users test")
    @Test
    public void getAllUsers() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))

                // 1st user returned
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstName").value("Laura"))
                .andExpect(jsonPath("$[0].lastName").value("Iordan"))
                .andExpect(jsonPath("$[0].email").value("email@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("password"))

                //2nd user returned
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].firstName").value("Laura"))
                .andExpect(jsonPath("$[1].lastName").value("Iordan"))
                .andExpect(jsonPath("$[1].email").value("email1@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("password"));
    }

    @Order(3)
    @DisplayName("register a new user test")
    @Test
    public void registerUser() throws Exception {
        String createUserPayload = "{\n" +
                "    \"firstName\": \"Laura\",\n" +
                "    \"lastName\": \"Iordan\",\n" +
                "    \"email\": \"email2@gmail.com\",\n" +
                "    \"password\": \"password\"\n" +
                "}";
        this.mockMvc.perform(post("/users")
                        .contentType(JSON_CONTENT_TYPE)
                        .content(createUserPayload))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Order(4)
    @DisplayName("delete a user by id test")
    @Test
    public void deleteUser() throws Exception {
        this.mockMvc.perform(delete("/users/{id}", 28))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
    }

}