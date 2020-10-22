package com.example.mock.study;

import com.example.mock.domain.Member;
import com.example.mock.domain.Study;
import com.example.mock.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock MemberService memberService;
    @Mock StudyRepository studyRepository;

    @Test
    void createStudyService() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);


        Member member = new Member();
        member.setId(1L);
        member.setEmail("maxosa@email.com");

        Study study = new Study(10, "테스트");

        when(memberService.findById(1L))
                .thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);


        studyService.createNewStudy(1L, study);

        assertEquals(member, study.getOwner());

        verify(memberService, times(1)).notify(study);
        verifyNoMoreInteractions(memberService);
    }

}