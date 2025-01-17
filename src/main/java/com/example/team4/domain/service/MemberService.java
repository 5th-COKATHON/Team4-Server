package com.example.team4.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team4.api.dto.MemberDTO;
import com.example.team4.domain.entity.Member;
import com.example.team4.domain.repository.MemberRepository;
import com.example.team4.global.error.AppException;
import com.example.team4.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	// 이메일 중복 확인
	public boolean checkEmail(final String email) {

		return memberRepository.existsByEmail(email);
	}

	// 닉네임 중복 확인
	public boolean checkNickname(final String nickname) {

		return memberRepository.existsByNickname(nickname);
	}

	// 회원가입
	@Transactional
	public void signUp(final MemberDTO memberDTO) {
		memberRepository.save(Member.toEntity(memberDTO));
	}

	// 이메일에 해당하는 Member id 조회
	public Long findIdByEmail(final String email) {

		Member member =  memberRepository.findByEmail(email)
				.orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));

		return member.getId();
	}

}
