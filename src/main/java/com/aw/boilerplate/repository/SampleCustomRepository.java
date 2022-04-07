package com.aw.boilerplate.repository;

import com.aw.boilerplate.entity.Sample;

import java.util.List;

public interface SampleCustomRepository {
    List<Sample> findSampleByName(String name);
    Long updateSampleName(Long sampleId, String sampleName);
}
