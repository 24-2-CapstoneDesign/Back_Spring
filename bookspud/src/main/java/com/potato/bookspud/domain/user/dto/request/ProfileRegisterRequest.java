package com.potato.bookspud.domain.user.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record ProfileRegisterRequest(
        MultipartFile image
) {
}
