package com.potato.bookspud.domain.auth.jwt.redis;

import com.amazonaws.services.kms.model.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

//    private final RedisTemplate<String, Object> redisTemplate;
    private final TokenRepository tokenRepository;

    @Transactional
    public void saveRefreshToken(
            final Long userId,
            final String refreshToken
    ) {
        tokenRepository.save(
                Token.of(
                        userId,
                        refreshToken
                )
        );
    }

    public Long findIdByRefreshToken(
            final String refreshToken
    ) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(
                        () -> new NotFoundException("해당 사용자의 리프레시 토큰이 존재하지 않습니다.")
                );
        return token.getId();
    }

    //삭제
    @Transactional
    public void deleteRefreshToken(
            final Long userId
    ) {
        Token token = tokenRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자의 리프레시 토큰이 존재하지 않습니다.")
        );
        tokenRepository.delete(token);
    }
}
