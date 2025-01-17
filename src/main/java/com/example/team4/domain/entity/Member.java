package com.example.team4.domain.entity;

import com.example.team4.api.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Email
    @Column(name = "member_email")
    private String email;

    @Column(name = "member_nickname")
    private String nickname;

    public static Member toEntity(MemberDTO memberDTO) {
        return new Member(
                null,
                memberDTO.email(),
                memberDTO.nickname()
        );
    }
}
