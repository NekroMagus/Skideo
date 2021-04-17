package net.skideo.controller;

import net.skideo.annotations.ControllerTest;
import net.skideo.dto.UserAuthDto;
import net.skideo.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.skideo.model.enums.RolePeople;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest
@WebMvcTest(AuthRestController.class)
public class AuthRestControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository playerRepository;

  //  @MockBean
  //  private JwtTokenUtil jwtTokenUtil;



    @Test
    public void authenticateTest() throws Exception {
        mockMvc.perform(post("/api/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserAuthDto("egor","12341234", RolePeople.PROFESSIONAL))))
        .andExpect(status().is2xxSuccessful());
    }



}