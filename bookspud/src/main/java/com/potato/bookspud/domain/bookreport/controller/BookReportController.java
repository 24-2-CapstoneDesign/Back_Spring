package com.potato.bookspud.domain.bookreport.controller;

import com.potato.bookspud.domain.common.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class BookReportController {
    @GetMapping("/api/{id}/argument")
    public BaseResponse<ArgumentsResponse> getArguments(@PathVariable Long id){



    }
}
