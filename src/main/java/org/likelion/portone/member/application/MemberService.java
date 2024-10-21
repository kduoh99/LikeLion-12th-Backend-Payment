package org.likelion.portone.member.application;

import lombok.RequiredArgsConstructor;
import org.likelion.portone.member.domain.Member;
import org.likelion.portone.member.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 자동 회원가입
    @Transactional
    public Member signUp() {
        Member member = Member.builder()
                .name(UUID.randomUUID().toString())
                .email("payment@example.com")
                .address("서울특별시 구로구")
                .build();

        return memberRepository.save(member);
    }
}
