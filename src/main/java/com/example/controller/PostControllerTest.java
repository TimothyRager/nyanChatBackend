package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class, secure = false)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Before
    public void preGame() {
        ArrayList<Post> posts = new ArrayList<>();

    }

    Post mockPost = new Post(-100, -100, -100, "TimeyWimey", "Some Test Content", "EditTime", "TimNeversoft", true);
    Post mockPost2 = new Post(-101, -101, -101, "TimeyWimey", "Some Test Content Again", "EditTime", "TimNeversoft", true);
    ArrayList<Post> mockPosts = new ArrayList<>(Arrays.asList(mockPost, mockPost2));

    String examplePostJson = "{\"postId\": -100, \"userId\": -100, \"threadId\": -100, \"timestamp\": \"TimeyWimey\", \"content\": \"Some Test Content\", \"editTime\":\"EditTime\", \"userName\": \"TimNeversoft\", \"display\": true}";

    @Test
    public void testGetPostById() throws Exception {

        Mockito.when(
                postService.getPost(Mockito.anyLong())).thenReturn(mockPost);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/posts/-100").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"postId\": -100, \"userId\": -100, \"threadId\": -100, \"timestamp\": \"TimeyWimey\", \"content\": \"Some Test Content\", \"display\": true}";


        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testGetAllPosts() throws Exception {

        Mockito.when(
                postService.getAllPosts()).thenReturn(mockPosts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/posts").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"postId\":-100,\"userId\":-100,\"threadId\":-100,\"timestamp\":\"TimeyWimey\",\"content\":\"Some Test Content\", \"display\": true},{\"postId\":-101,\"userId\":-101,\"threadId\":-101,\"timestamp\":\"TimeyWimey\",\"content\":\"Some Test Content Again\", \"display\": true}]";


        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testAddPost() throws Exception {

        Mockito.when(
                postService.addPost(Mockito.any(Post.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/posts")
                .accept(MediaType.APPLICATION_JSON).content(examplePostJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        Assert.assertEquals("http://localhost/posts/-100",
                response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    public void testDeletePostExisting() throws Exception {

        Mockito.when(
                postService.deletePost(Mockito.anyLong())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/posts/1");/*
                .accept(MediaType.APPLICATION_JSON).content(examplePostJson)
                .contentType(MediaType.APPLICATION_JSON);*/

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.valueOf(204).value(), response.getStatus());
    }

    @Test
    public void testDeletePostNotExisting() throws Exception {

        Mockito.when(
                postService.deletePost(Mockito.anyLong())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/posts/999");/*
                .accept(MediaType.APPLICATION_JSON).content(examplePostJson)
                .contentType(MediaType.APPLICATION_JSON);*/

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void testUpdatePostExisting() throws Exception {
        Mockito.when(
                postService.updatePost(Mockito.anyLong(), Mockito.any(Post.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/posts/-100")
                .accept(MediaType.APPLICATION_JSON).content(examplePostJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.valueOf(204).value(), response.getStatus());
    }

    @Test
    public void testUpdatePostNotExisting() throws Exception {
        Mockito.when(
                postService.updatePost(Mockito.anyLong(),Mockito.any(Post.class))).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/posts/999")
                .accept(MediaType.APPLICATION_JSON).content(examplePostJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}
