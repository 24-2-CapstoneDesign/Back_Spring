package com.potato.bookspud.domain.user.domain;

import com.potato.bookspud.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String profileImageUrl;
    private String birthyear;
    private Integer point = 0; // 기본값 설정
    private Long socialId;

    public static User of(String birthyear, Long socialId) {
        return User.builder()
                .birthyear(birthyear)
                .socialId(socialId)
                .point(0)
                .build();
    }
}