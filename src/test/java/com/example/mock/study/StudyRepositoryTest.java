package com.example.mock.study;

import com.example.mock.domain.Study;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyRepositoryTest {

    @Autowired
    StudyRepository repository;

    @Test
    void save() {
        Study study = new Study(10, "Java");
        repository.save(study);
        List<Study> all = repository.findAll();
        assertEquals(1, all.size());
    }

}