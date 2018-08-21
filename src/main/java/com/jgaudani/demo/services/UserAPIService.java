package com.jgaudani.demo.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgaudani.demo.User;
import jdk.internal.jline.internal.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAPIService {

    @Value("${feed.url}")
    private String feedURL;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Returns feed of the URL(feedURL) set in properties file
     * @return
     * @throws Exception
     */
    private String getFeed() throws Exception {
        try {
            URI url = new URI(feedURL);
            ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
            return (String) response.getBody();
        } catch (Exception e) {
            Log.error("error while fetching feed from " + feedURL,e);
            return "";
        }

    }

    /**
     *
     * @return - List of all the User object - converted from the JSON objects from the feed
     */
    public List<User> findAll() {
        try {
            String feed = this.getFeed();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            List<User> userList = mapper.readValue(feed, new TypeReference<List<User>>(){});
            return userList;
        } catch (Exception e) {
            Log.error("error while reading all",e);
            //TODO: return here depends on logic in controller, currently returning empty list, so dont have to handle null in controller
            return new ArrayList<>();
        }
    }

    /**
     *
     * @return - Returns User object at certain index
     */
    public User findAtIndex(int elementIndex) {
        try {
            String feed = this.getFeed();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            List<User> userList = mapper.readValue(feed, new TypeReference<List<User>>(){});
            return userList.get(elementIndex);
        } catch (Exception e) {
            Log.error("error while reading one",e);
            return null;
        }
    }

}
