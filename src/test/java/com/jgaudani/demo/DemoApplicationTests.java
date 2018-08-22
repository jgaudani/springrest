package com.jgaudani.demo;

import com.google.gson.Gson;
import com.jgaudani.demo.services.UserAPIService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserAPIService userAPIService;

    private MockMvc mockMvc;

    List<User> mockUsers = new ArrayList<User>();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        User user1 = new User(1,1,"user1", "user1");
        User user2 = new User(2,2,"user2", "user2");
        User user3 = new User(2,3,"user3", "user3");
        mockUsers.add(0,user1);
        mockUsers.add(1,user2);
        mockUsers.add(2,user3);
    }

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void feedContentAndUserAPIServiceMatches() throws Exception{

        User anyUser = mockUsers.get(0);
        //mocking the service calls
        Mockito.when(userAPIService.findAll()).thenReturn(mockUsers);
        Mockito.when(userAPIService.findAtIndex(Mockito.anyInt())).thenReturn(anyUser);

        //verifying if updating the first element from overall json matches with final
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/total").accept(MediaType.APPLICATION_JSON)).andReturn();
        Map<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("total", "3" );
        expectedMap.put("unique", "2");
        Assert.assertEquals(new Gson().toJson(expectedMap), result.getResponse().getContentAsString());


        //verifying if updating the first element from overall json matches with final
        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.get("/users/update/0").accept(MediaType.APPLICATION_JSON)).andReturn();
        User user1 = mockUsers.get(0);
        user1.setTitle("1800Flowers");
        user1.setBody("1800Flowers");
        //mockUsers.set(0, user1);

        String expectedOutput = new Gson().toJson(mockUsers);
        Assert.assertEquals(expectedOutput, result2.getResponse().getContentAsString());
    }


}
