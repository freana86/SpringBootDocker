package com.example.pl.demopl.service;

import com.example.pl.demopl.client.UttagClient;
import com.example.pl.demopl.domain.Uttag;
import com.example.pl.demopl.domain.UttagBuilder;
import com.example.pl.demopl.repository.UttagRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoUttagServiceImplTest {

    @Autowired
    private UttagService uttagService;

    @MockBean
    private UttagClient uttagClient;

    @MockBean
    private UttagRepository uttagRepository;


    @Test
    public void processUttag() {
    }

    @Test
    public void getAllUttag() {
    }

    @Test
    public void getUttagBy() {
    }

    @Test
    public void removeUttag() {
        Integer id = 100;
        Uttag expectedUttag = UttagBuilder.example().withId(id).build();
        Optional<Uttag> optionalUttag = Optional.of(expectedUttag); // JPA stuff..
        Mockito.when(uttagRepository.findById(id)).thenReturn(optionalUttag);

        Uttag acutallUttag = this.uttagService.getUttagBy(id);

        assertThat(acutallUttag, equalTo(expectedUttag));

        Mockito.verify(uttagRepository, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(uttagRepository);
    }

    @Test
    public void clearDB() {
        this.uttagService.clearDB();
        Mockito.verify(uttagRepository, Mockito.times(1)).deleteAll();
        Mockito.verifyNoMoreInteractions(uttagRepository);
    }

    @Test
    public void sync() {
    }
}