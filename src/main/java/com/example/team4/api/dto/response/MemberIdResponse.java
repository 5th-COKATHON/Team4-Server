package com.example.team4.api.dto.response;

public record MemberIdResponse(Long memberId) {

	public static MemberIdResponse toMemberIdResponse(Long memberId) {
		return new MemberIdResponse(memberId);
	}
}
