package com.aw.boilerplate.controller;
//
//import com.aw.boilerplate.service.SampleRedisTestService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//public class SampleRedisTestController {
//
//    @Autowired
//    private final SampleRedisTestService simpleRedisTestService;
//
//    @GetMapping("/redis/sampleData")
//    public List<String> getSimpleData() throws Exception{
//        List<String> result = simpleRedisTestService.getSimpleData();
//        return result;
//    }
//
//    @PatchMapping("/redis/sampleData/remove")
//    public void removeSimpleData() throws Exception{
//        simpleRedisTestService.simpleDataEvict();
//    }
//
//    @GetMapping("/redis/sampleRedisData")
//    public List<String> getSampleRedisData() throws Exception{
//        return simpleRedisTestService.getAll();
//    }
//}
