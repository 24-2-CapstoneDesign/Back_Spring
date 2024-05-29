package com.potato.bookspud.domain.user.domain;

import com.potato.bookspud.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String profileImageUrl;
    private String accountEmail;
    private Long socialId;

    @Builder
    public User(String nickname, String profileImageUrl, String accountEmail, Long socialId) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.accountEmail = accountEmail;
        this.socialId = socialId;
    }

    public static User of(String nickname, String profileImageUrl, String accountEmail, Long socialId) {
        return User.builder()
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .accountEmail(accountEmail)
                .socialId(socialId)
                .build();
    }
}