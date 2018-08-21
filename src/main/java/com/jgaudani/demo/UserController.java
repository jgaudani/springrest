package com.jgaudani.demo;

import com.google.gson.Gson;
import com.jgaudani.demo.services.UserAPIService;
import jdk.internal.jline.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    //consuming the external feed is converted to service for isolation of functionality
    //it is a good practise for convertability of service layer
    @Autowired
    private UserAPIService userAPIService;


    @RequestMapping(value = "/total", method =  RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String users() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //fetches all the user objects
            List<User> userList = userAPIService.findAll();
            //will iterate through all users and store userids in map for quicker search than other data structure
            Map<Integer, Integer> uniqueUserIdMap = new HashMap<Integer, Integer>();
            for (User u:userList) {
                if (uniqueUserIdMap.get(u.getUserId()) == null) {
                    uniqueUserIdMap.put(u.getUserId(), 1);
                }
            }

            //size of map is the number of unique userids
            map.put("total", "" + userList.size());
            map.put("unique", "" + uniqueUserIdMap.size());
            return new Gson().toJson(map);
        } catch (Exception e) {
            //catch exception if type casting fails here
            return "error";
        }
    }

    @RequestMapping(value = "/update/{elementIndex}", method =  RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String users(@PathVariable int elementIndex) throws Exception {
        try {
            //fetches all the user objects
            List<User> userList = userAPIService.findAll();
            //get element at passed index
            User selectedUser = userList.get(elementIndex);
            if (selectedUser != null) {
                //if element found at index, change title and body
                //because this is reference element, we don't need to assign it back
                selectedUser.setTitle("1800Flowers");
                selectedUser.setBody("1800Flowers");
            }
            return new Gson().toJson(userList);
        } catch (Exception e) {
            Log.error(e);
            return "";
        }
    }


}
