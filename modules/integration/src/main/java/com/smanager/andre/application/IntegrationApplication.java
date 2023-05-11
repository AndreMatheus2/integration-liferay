package com.smanager.andre.application;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Set;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smanager.andre.application.dto.FollowerDto;
import com.smanager.andre.application.dto.PostDto;
import com.smanager.andre.application.dto.UserDto;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

/**
 * @author andre-arao
 */
@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/integration",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=Integration.Rest",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
@RestController
public class IntegrationApplication extends Application {

    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static HttpGet requestGet = new HttpGet();
    private static HttpPost requestPost = new HttpPost();
    private static HttpPut requestPut = new HttpPut();
    private static String url = "http://localhost:8084/users";


    // INICIO DOS RECURSOS USERS
    @GET
    @Path("/users")
    @Produces("application/json")
    public String listUsers() {


        String result = restTemplate.getForObject(url, String.class);

        System.out.println(result);
        return result;
    }

    @POST
    @Path("/createuser")
    @Produces("application/json")
    @Consumes("application/json")
    public String postUser(UserDto userDto) {

        try {
            requestPost.setURI(new URI(url));
            requestPost.setHeader("Content-type", "application/json");
            requestPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(userDto)));
            HttpEntity httpEntity = httpClient.execute(requestPost).getEntity();
            String response = EntityUtils.toString(httpEntity);
            System.out.println(response);
            return response;
        } catch (URISyntaxException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    @DELETE
    @Path("/deleteuser/{id}")
    public void deletetUser(@PathParam(value = "id") Long id) {

        String urlId = url + "/" + id;
        restTemplate.delete(urlId);
    }

    @PUT
    @Path("/updateuser/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public String updateUser(@PathParam(value = "id") Long id,
                             UserDto userDto) {

        try {
            requestPut.setURI(new URI(url + "/" + id));
            requestPut.setHeader("Content-type", "application/json");
            requestPut.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(userDto)));
            HttpEntity httpEntity = httpClient.execute(requestPut).getEntity();
            String response = EntityUtils.toString(httpEntity);
            System.out.println(response);
            return response;
        } catch (URISyntaxException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    // FIM DOS RECURSOS USERS

    // INICIO DOS RECURSOS POSTS
    @GET
    @Path("/users/{userId}/posts")
    @Produces("application/json")
    public String listPosts(@PathParam(value = "userId") Long userId,
                            @HeaderParam(value = "followerId") Long followerId) {

        try {
            requestGet.setURI(new URI(url + "/" + userId + "/posts"));
            requestGet.setHeader("followerId", String.valueOf(followerId));
            HttpEntity httpEntity = httpClient.execute(requestGet).getEntity();
            String response = EntityUtils.toString(httpEntity);
            return response;
        } catch (URISyntaxException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    @POST
    @Path("/users/{userId}/posts")
    @Produces("application/json")
    @Consumes("application/json")
    public String savePost(@PathParam(value = "userId") Long userId, PostDto postDto) {

        try {
            requestPost.setURI(new URI(url + "/" + userId + "/posts"));
            requestPost.setHeader("Content-type", "application/json");
            requestPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(postDto)));
            HttpEntity httpEntity = httpClient.execute(requestPost).getEntity();
            String response = EntityUtils.toString(httpEntity);
            System.out.println(response);
            return response;
        } catch (URISyntaxException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    // FIM DOS RECURSOS POSTS

    // INICIO DOS RECURSOS FOLLOWERS

    @GET
    @Path("/users/{userId}/followers")
    @Produces("application/json")
    public String listFollowers(@PathParam(value = "userId") Long userId) {

        try {
            requestGet.setURI(new URI(url + "/" + userId + "/followers"));
            HttpEntity httpEntity = httpClient.execute(requestGet).getEntity();
            String response = EntityUtils.toString(httpEntity);
            System.out.println(response);
            return response;
        } catch (URISyntaxException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    @PUT
    @Path("/users/{userId}/followers")
    @Produces("application/json")
    @Consumes("application/json")
    public String followUser(@PathParam(value = "userId") Long userId,
                             FollowerDto followerDto) {

        try {
            requestPut.setURI(new URI(url + "/" + userId + "/followers"));
            requestPut.setHeader("Content-type", "application/json");
            requestPut.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(followerDto)));
            HttpEntity httpEntity = httpClient.execute(requestPut).getEntity();
            String response = EntityUtils.toString(httpEntity);
            System.out.println(response);
            return response;
        } catch (URISyntaxException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }

    @DELETE
    @Path("/users/{userId}/followers")
    public void unfollowUser(@PathParam("userId") Long userId,
                             @QueryParam("followerId") Long followerId) {

        String urlId = url + "/" + userId + "/followers" + "?followerId=" + followerId;
        restTemplate.delete(urlId);
    }

    // FIM DOS RECURSOS FOLLOWERS

    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
}