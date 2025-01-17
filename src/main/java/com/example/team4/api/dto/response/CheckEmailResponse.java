package com.example.team4.api.dto.response;

public record CheckEmailResponse(boolean isExist) {

	public static CheckEmailResponse toCheckEmailResponse(boolean isExist) {
		return new CheckEmailResponse(isExist);
	}
}
