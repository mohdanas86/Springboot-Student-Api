package com.anasalam.restapi.RestApis.Entity;

import com.anasalam.restapi.RestApis.entity.StudentEntity;
import com.anasalam.restapi.RestApis.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class EntityTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testCustomQueries() {
        Optional<StudentEntity> s1 = studentRepository.findByEmail("john@gmail.com");
        System.out.println(s1);
    }
}
