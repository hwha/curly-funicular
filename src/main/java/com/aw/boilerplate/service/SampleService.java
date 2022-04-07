package com.aw.boilerplate.service;

import com.aw.boilerplate.entity.Sample;
import com.aw.boilerplate.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    @Transactional
    public Sample createSample(Sample news) {
        return sampleRepository.save(news);
    }

    @Transactional(readOnly = true)
    public List<Sample> getSampleList() {
        return sampleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Sample> getSample(Long sampleId) {
        return sampleRepository.findById(sampleId);
    }

    @Transactional(readOnly = true)
    public List<Sample> getSampleListByName(String name) {
        return sampleRepository.findSampleByName(name);
    }

    @Transactional
    public Long updateSampleName(Long sampleId, String sampleName) {
        return sampleRepository.updateSampleName(sampleId, sampleName);
    }

    @Transactional
    public Long updateSample(Long sampleId, Sample sampleUpdate) {
        Sample sample = sampleRepository.getById(sampleId);
        if (sampleUpdate.getName() != null) {
            sample.updateName(sampleUpdate.getName());
        }
        if (sampleUpdate.getEmail() != null) {
            sample.updateEmail(sampleUpdate.getEmail());
        }
        return 1L;
    }
}