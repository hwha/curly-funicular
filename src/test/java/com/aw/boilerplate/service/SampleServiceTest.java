package com.aw.boilerplate.service;

import com.aw.boilerplate.entity.Sample;
import com.aw.boilerplate.repository.SampleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class SampleServiceTest {
    Logger log = LoggerFactory.getLogger(SampleServiceTest.class);

    @InjectMocks
    private SampleService sampleService;

    @Mock
    private SampleRepository sampleRepository;

    @DisplayName("샘플 리스트 조회 서비스 테스트")
    @Test
    public void getSampleListTest() {
        // given
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(Sample.builder().name("aaa").email("aaa@gmail.com").build());
        sampleList.add(Sample.builder().name("bbb").email("bbb@gmail.com").build());
        sampleList.add(Sample.builder().name("ccc").email("ccc@gmail.com").build());
        doReturn(sampleList).when(sampleRepository).findAll();

        // when
        List<Sample> sampleListResult = sampleService.getSampleList();
        log.info("[sampleListResult size] " + sampleListResult.size());

        // then
        assertTrue(sampleListResult.size() > 0);
    }
}