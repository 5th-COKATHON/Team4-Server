package com.example.team4.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.team4.api.dto.MemberDTO;
import com.example.team4.api.dto.request.EmailRequest;
import com.example.team4.api.dto.request.NicknameRequest;
import com.example.team4.api.dto.request.SignUpRequest;
import com.example.team4.api.dto.response.CheckEmailResponse;
import com.example.team4.api.dto.response.CheckNicknameResponse;
import com.example.team4.api.dto.response.MemberIdResponse;
import com.example.team4.domain.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("members")
@Tag(name = "Member", description = "멤버 API")
public class MemberController {

	private final MemberService memberService;

	@Operation(summary = "이메일 중복 확인", description = "이메일 중복을 확인합니다.")
	@PostMapping("/email")
	public ResponseEntity<CheckEmailResponse> checkEmail(final @RequestBody @Valid EmailRequest emailRequest) {
		return ResponseEntity.ok(CheckEmailResponse.toCheckEmailResponse(memberService.checkEmail(emailRequest.email())));
	}

	@Operation(summary = "닉네임 중복 확인", description = "닉네임 중복을 확인합니다.")
	@PostMapping("/nickname")
	public ResponseEntity<CheckNicknameResponse> checkNickname(final @RequestBody @Valid NicknameRequest nicknameRequest) {
		return ResponseEntity.ok(CheckNicknameResponse.toCheckNicknameResponse(memberService.checkNickname(nicknameRequest.nickname())));
	}

	@Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
	@PostMapping("/join")
	public ResponseEntity<Void> signUp(final @RequestBody @Valid SignUpRequest signUpRequest) {
		memberService.signUp(MemberDTO.toMemberDTO(signUpRequest));
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "이메일에 해당하는 Member id 조회", description = "이메일에 해당하는 Member id를 조회합니다.")
	@GetMapping("email")
	public ResponseEntity<MemberIdResponse> findIdByEmail(final @RequestParam String email) {
		return ResponseEntity.ok(MemberIdResponse.toMemberIdResponse(memberService.findIdByEmail(email)));
	}

}
