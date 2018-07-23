package com.example.pl.demopl.controller;

import com.example.pl.demopl.Utils.JsonConverter;
import com.example.pl.demopl.domain.Uttag;
import com.example.pl.demopl.domain.UttagBuilder;
import com.example.pl.demopl.service.UttagService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UttagPlController.class, secure = false)
public class UttagPlControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UttagService uttagService;

    @Test
    public void getUttag() throws Exception {
        Integer id = 100;
        Uttag uttag = UttagBuilder.example().withId(id).build();
        String URI = "/api/uttag";
        Mockito.when(uttagService.getUttagBy(id)).thenReturn(uttag);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", id.toString());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Json Converter.
        Uttag convertedUttag = JsonConverter.convert(result.getResponse().getContentAsString(), Uttag.class);

        assertThat(convertedUttag, equalTo(uttag));

        Mockito.verify(uttagService, Mockito.times(1)).getUttagBy(id);
        Mockito.verifyNoMoreInteractions(uttagService);

        logger.info("Expected Uttag: {}", uttag);
        logger.info("Actualy  Uttag: {}", convertedUttag);

    }

    @Test
    public void getAllUttag() {

    }

    @Test
    public void removeUttag() {
    }

    @Test
    public void removeALL() {
    }
}