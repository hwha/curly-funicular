package com.aw.boilerplate.controller;

import com.aw.boilerplate.dto.SampleRequest;
import com.aw.boilerplate.entity.Sample;
import com.aw.boilerplate.service.SampleService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SampleControllerTest{

    @InjectMocks
    private SampleController sampleController;

    @Mock
    private SampleService sampleService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
    }

    @DisplayName("샘플 생성 컨트롤러 테스트")
    @Test
    void createSampleTest() throws Exception {
        //given
        Sample sample = Sample.builder().name("max").email("max@gmail.com").build();
        doReturn(sample).when(sampleService).createSample(sample);

        //when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/samples")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new Gson().toJson(
                                        new SampleRequest()
                                                .setEmail(sample.getEmail())
                                                .setName(sample.getName())
                                )
                        )
        );

        //then
        resultActions.andExpect(status().isOk());
    }
}
