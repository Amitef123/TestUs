package com.springbootquickstart.TestUs.test;

import org.springframework.stereotype.Service;

import com.springbootquickstart.TestUs.dto.TestCreationDto;

public interface TestService {
    void createTest(TestCreationDto test);
}