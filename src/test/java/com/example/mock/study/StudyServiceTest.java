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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;
    @Mock
    StudyRepository studyRepository;

    @Test
    @DisplayName("BDD 스타일 구현")
    void createStudyService() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("maxosa@email.com");

        Study study = new Study(10, "테스트");

        given(memberService.findById(1L))
                .willReturn(Optional.of(member));

        given(studyRepository.save(study))
                .willReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // Then
        assertEquals(member, study.getOwner());
        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoInteractions();
    }

}