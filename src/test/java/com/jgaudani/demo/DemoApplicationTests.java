package com.jgaudani.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.jgaudani.demo.services.UserAPIService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserAPIService userAPIService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

	@Test
	public void contextLoads() throws Exception {
	}

	@Test
    public void feedReturnsCorrectHttpStatus() throws Exception {
/*      //trying to test if the feed URL comes back with 200, also for the static feed can assert the feed content
        URI feedUrl = new URI("http://jsonplaceholder.typicode.com/posts");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(feedUrl);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.print(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatus());*/
    }

    @Test
    public void feedContentAndUserAPIServiceMatches(){
        //feed is steady as of now, so can unit test the consistency of feed element
        User user = userAPIService.findAtIndex(3);
        User expectedUser = new User(1, 4, "eum et est occaecati", "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit");
        Assert.assertEquals(expectedUser.getTitle(), user.getTitle());
        Assert.assertEquals(expectedUser.getBody(), user.getBody());
        Assert.assertEquals(expectedUser.getId(), user.getId());
        Assert.assertEquals(expectedUser.getUserId(), user.getUserId());


        //verify total number of elements - should be 100
        List<User> users = userAPIService.findAll();
        Assert.assertEquals(100, users.size());
    }


}
