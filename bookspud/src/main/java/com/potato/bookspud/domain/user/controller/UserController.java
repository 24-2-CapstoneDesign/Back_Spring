package com.potato.bookspud.domain.user.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.common.SuccessCode;
import com.potato.bookspud.domain.user.domain.User;
import com.potato.bookspud.domain.user.dto.request.NicknameRegisterRequest;
import com.potato.bookspud.domain.user.dto.request.PointUpdateRequest;
import com.potato.bookspud.domain.user.dto.request.ProfileRegisterRequest;
import com.potato.bookspud.domain.user.dto.response.GetPointResponse;
import com.potato.bookspud.domain.user.dto.response.ProfileRegisterResponse;
import com.potato.bookspud.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "User Controller", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "닉네임 등록", description = "로그인 후 닉네임 등록 API")
    @PostMapping("/nickname")
    public BaseResponse registerNickname(@RequestBody NicknameRegisterRequest request,
                                         @AccessTokenUser User user) {
        userService.registerNickname(request, user);
        return BaseResponse.success(SuccessCode.REGISTER_NICKNAME_SUCCESS);
    }

    @Operation(summary = "프로필 사진 등록", description = "사용자 프로필 사진 등록 API")
    @PostMapping("/profile")
    public BaseResponse<ProfileRegisterResponse> registerProfile(@ModelAttribute ProfileRegisterRequest profileRegisterRequest,
                                                                 @AccessTokenUser User user) throws IOException {
        String profileImageUrl = userService.registerProfileImage(profileRegisterRequest, user);
        ProfileRegisterResponse result = new ProfileRegisterResponse(profileImageUrl);
        return BaseResponse.success(SuccessCode.REGISTER_PROFILE_SUCCESS, result);
    }

    @Operation(summary = "포인트 조회", description = "사용자 포인트 조회 API")
    @GetMapping("/point")
    public BaseResponse<GetPointResponse> getPoint(@AccessTokenUser User user) {
        Integer point = userService.getPoint(user);
        GetPointResponse result = new GetPointResponse(user.getId(), point);
        return BaseResponse.success(SuccessCode.GET_POINT_SUCCESS, result);
    }

    @Operation(summary = "포인트 업데이트", description = "사용자 포인트 업데이트 API")
    @PatchMapping("/point")
    public BaseResponse updatePoint(@RequestBody PointUpdateRequest request,
                                    @AccessTokenUser User user) {
        userService.updatePoint(request.delta(), user);
        return BaseResponse.success(SuccessCode.UPDATE_POINT_SUCCESS);
    }
}
