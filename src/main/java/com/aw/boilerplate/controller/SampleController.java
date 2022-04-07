package com.aw.boilerplate.controller;

import com.aw.boilerplate.dto.SampleRequest;
import com.aw.boilerplate.enums.CustomExceptionCode;
import com.aw.boilerplate.exception.CustomException;
import com.aw.boilerplate.model.ApiResponse;
import com.aw.boilerplate.dto.SampleResponse;
import com.aw.boilerplate.entity.Sample;
import com.aw.boilerplate.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/samples")
@Tag(name = "Sample API")
@Slf4j
@RequiredArgsConstructor
public class SampleController {
    private final SampleService sampleService;

    @Operation(description = "샘플생성")
    @PostMapping
    public ApiResponse<SampleResponse> addSample (@RequestBody SampleRequest sampleRequest) {
        log.info("[addSample]" + sampleRequest.getEmail() + "," + sampleRequest.getName());

        Sample sample = Sample.builder()
                .name(sampleRequest.getName())
                .email(sampleRequest.getEmail())
                .build();
        Sample sampleEntity = sampleService.createSample(sample);
        SampleResponse sampleRes = SampleResponse.builder()
                .id(sampleEntity.getId())
                .name(sampleEntity.getName())
                .email(sampleEntity.getEmail())
                .build();

        return ApiResponse.success(sampleRes);
    }

    @Operation(description = "샘플조회")
    @GetMapping(value ="/{id}")
    public ApiResponse<SampleResponse> getSample(@PathVariable String id) throws Exception {
        log.info("[getSample]" + id);

        Sample sample = sampleService.getSample(Long.valueOf(id))
                .orElseThrow(() -> new CustomException(CustomExceptionCode.SAMPLE_API_RESPONSE_EMPTY));

        SampleResponse sampleRes = SampleResponse.builder()
                .id(sample.getId())
                .name(sample.getName())
                .email(sample.getEmail()).build();

        return ApiResponse.success(sampleRes);
    }

    @Operation(description = "샘플복수조회")
    @GetMapping
    public ApiResponse<List<SampleResponse>> getSampleList(@RequestParam @Nullable String name) throws Exception {
        log.info("[getSampleList] param=>{}", name);
        List<Sample> sampleList;
        if(name != null) {
            sampleList = sampleService.getSampleListByName(name);
        } else {
            sampleList = sampleService.getSampleList();
        }

        List<SampleResponse> sampleResList = new ArrayList<>();
        for(Sample sampleEntity: sampleList) {
            sampleResList.add(
                    SampleResponse.builder()
                            .id(sampleEntity.getId())
                            .name(sampleEntity.getName())
                            .email(sampleEntity.getEmail())
                            .build()
            );
        }

        if(sampleResList.isEmpty()) {
            throw new CustomException(CustomExceptionCode.SAMPLE_API_RESPONSE_EMPTY);
        }
        return ApiResponse.success(sampleResList);

    }

    @Operation(description = "샘플이름수정")
    @PatchMapping("/{id}")
    public ApiResponse<Long> updateSampleName(@PathVariable Long id, @RequestBody SampleRequest sampleRequest) throws Exception {
        log.info("[updateSampleName] id=>{}, body=>{}", id, sampleRequest);

        if(sampleRequest.getName() == null) {
            throw new CustomException(CustomExceptionCode.SAMPLE_REQUIRED_FIELD_ERROR);
        }
        Sample sample = Sample.builder().name(sampleRequest.getName()).email(sampleRequest.getEmail()).build();

        Long updateCount = sampleService.updateSample(id, sample);
        log.info("updateSampleName] updateCount =>{}", updateCount);

        return ApiResponse.success(updateCount);
    }

}