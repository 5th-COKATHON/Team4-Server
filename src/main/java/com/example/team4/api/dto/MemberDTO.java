package com.example.team4.api.dto;

import com.example.team4.api.dto.request.SignUpRequest;
import com.example.team4.domain.entity.Member;

public record MemberDTO(
		Long id,
		String nickname,
		String email
) {
	public static MemberDTO toMemberDTO(Member member) {
		return new MemberDTO(
				member.getId(),
				member.getNickname(),
				member.getEmail()
		);
	}

	public static MemberDTO toMemberDTO(SignUpRequest signUpRequest) {
		return new MemberDTO(
				null,
				signUpRequest.nickname(),
				signUpRequest.email()
		);
	}
}
