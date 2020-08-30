package io.github.byference.mapper.test;

import io.github.byference.mapper.TinyMapperApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TinyMapperApplication.class)
@AutoConfigureMockMvc
public class TinyMapperApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private final Random random = new Random();

    @Test
    public void insert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/tiny_mapper/insert")
                .param("username", "Tom" + random.nextInt(100))
                .param("password", "$qwe/" + LocalDateTime.now()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tiny_mapper/findById").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
