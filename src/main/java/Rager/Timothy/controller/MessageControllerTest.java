package Rager.Timothy.controller;

import Rager.Timothy.model.Message;
import Rager.Timothy.service.MessageService;
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
@WebMvcTest(value = MessageController.class, secure = false)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Before
    public void preGame() {
        ArrayList<Message> messages = new ArrayList<>();

    }

    Message mockMessage = new Message(-100, -100, -100, "TimeyWimey", "Some Test Content", "EditTime", "TimNeversoft", true);
    Message mockMessage2 = new Message(-101, -101, -101, "TimeyWimey", "Some Test Content Again", "EditTime", "TimNeversoft", true);
    ArrayList<Message> mockMessages = new ArrayList<>(Arrays.asList(mockMessage, mockMessage2));

    String exampleMessageJson = "{\"messageId\": -100, \"userId\": -100, \"threadId\": -100, \"timestamp\": \"TimeyWimey\", \"content\": \"Some Test Content\", \"editTime\":\"EditTime\", \"userName\": \"TimNeversoft\", \"display\": true}";

    @Test
    public void testGetMessageById() throws Exception {

        Mockito.when(
                messageService.getMessage(Mockito.anyLong())).thenReturn(mockMessage);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages/-100").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"messageId\": -100, \"userId\": -100, \"threadId\": -100, \"timestamp\": \"TimeyWimey\", \"content\": \"Some Test Content\", \"display\": true}";


        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testGetAllMessages() throws Exception {

        Mockito.when(
                messageService.getAllMessages()).thenReturn(mockMessages);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/messages").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"messageId\":-100,\"userId\":-100,\"threadId\":-100,\"timestamp\":\"TimeyWimey\",\"content\":\"Some Test Content\", \"display\": true},{\"messageId\":-101,\"userId\":-101,\"threadId\":-101,\"timestamp\":\"TimeyWimey\",\"content\":\"Some Test Content Again\", \"display\": true}]";


        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testAddMessage() throws Exception {

        Mockito.when(
                messageService.addMessage(Mockito.any(Message.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/messages")
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        Assert.assertEquals("http://localhost/messages/-100",
                response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    public void testDeleteMessageExisting() throws Exception {

        Mockito.when(
                messageService.deleteMessage(Mockito.anyLong())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/messages/1");/*
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJson)
                .contentType(MediaType.APPLICATION_JSON);*/

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.valueOf(204).value(), response.getStatus());
    }

    @Test
    public void testDeleteMessageNotExisting() throws Exception {

        Mockito.when(
                messageService.deleteMessage(Mockito.anyLong())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/messages/999");/*
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJson)
                .contentType(MediaType.APPLICATION_JSON);*/

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void testUpdateMessageExisting() throws Exception {
        Mockito.when(
                messageService.updateMessage(Mockito.anyLong(), Mockito.any(Message.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/messages/-100")
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.valueOf(204).value(), response.getStatus());
    }

    @Test
    public void testUpdateMessageNotExisting() throws Exception {
        Mockito.when(
                messageService.updateMessage(Mockito.anyLong(),Mockito.any(Message.class))).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/messages/999")
                .accept(MediaType.APPLICATION_JSON).content(exampleMessageJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}
