package com.example.team4.api.dto.response;

public record CheckNicknameResponse(boolean isExist) {

	public static CheckNicknameResponse toCheckNicknameResponse(boolean isExist) {
		return new CheckNicknameResponse(isExist);
	}
}
