package com.example.mock.member;

import com.example.mock.domain.Member;
import com.example.mock.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
