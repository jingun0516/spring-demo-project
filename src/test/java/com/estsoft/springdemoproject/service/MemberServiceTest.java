package com.estsoft.springdemoproject.service;

import com.estsoft.springdemoproject.repository.Member;
import com.estsoft.springdemoproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;  // Repository를 Mock 처리

    @InjectMocks
    private MemberService memberService;  // 실제 서비스 객체에 Mock된 Repository 주입

    @Test
    void testGetAllMembers() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        List<Member> members = Arrays.asList(member1, member2);

        when(memberRepository.findAll()).thenReturn(members);  // findAll을 Mock 처리

        // when
        List<Member> result = memberService.getAllMembers();  // 서비스 메서드 호출

        // then
        assertNotNull(result);  // 결과가 null이 아님을 확인
        assertEquals(2, result.size());  // 리스트 크기 확인
        verify(memberRepository, times(1)).findAll();  // findAll 호출 횟수 검증
    }

    @Test
    void testSaveMember() {
        // given
        Member newMember = new Member();
        newMember.setName("ㅎㅇ");

        when(memberRepository.save(any(Member.class))).thenReturn(newMember);  // save 메서드를 Mock 처리

        // when
        Member result = memberService.saveMember(newMember);  // 서비스 메서드 호출

        // then
        assertNotNull(result);  // 결과가 null이 아님을 확인
        assertEquals("ㅎㅇ", result.getName());  // 저장된 멤버 이름 확인
        verify(memberRepository, times(1)).save(newMember);  // save 호출 횟수 검증
    }
}