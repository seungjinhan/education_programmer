package com.radiuslab;

import com.radiuslab.study.StudyApplication;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StudyApplication.class)
@ActiveProfiles("test")
public abstract class SpringTestBase {
    
}
