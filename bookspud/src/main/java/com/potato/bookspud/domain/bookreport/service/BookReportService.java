package com.potato.bookspud.domain.bookreport.service;

import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookreport.domain.BookReport;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentCreateResponse;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentsResponse;
import com.potato.bookspud.domain.bookreport.repository.BookReportRepository;
import com.potato.bookspud.domain.chatgpt.controller.ChatGPTController;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentsRequest;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class BookReportService {
    private final ChatGPTController chatGPTController;
    private final BookReportRepository bookReportRepository;

    public ArgumentsResponse getArguments(ArgumentsRequest request){
        List<String> info = makeInfo(request);
        String result = chatGPTController.makeArguments(info);
        List<String> arguments = parseResult(result);
        return ArgumentsResponse.of(arguments);
    }

    public QuestionsResponse getQuestions(Long id){
        BookReport bookReport = bookReportRepository.getById(id);
        List<String> info = makeInfo(bookReport);
        String result = chatGPTController.makeQuestions(info);
        List<String> questions = parseResult(result);
        return QuestionsResponse.of(questions);
    }

    private List<String> makeInfo(ArgumentsRequest request){
        List<String> info = new ArrayList<>();

        String bookInfo = "책 제목은 " + request.book().getTitle() + "이고, 저자는 " + request.book().getAuthor() + " 이고, 책 소개는 " + request.book().getContent();
        info.add(bookInfo);

        for (BookMark bookMark : request.bookMarks()){
            String bookMarkInfo = "이 책에 대해 내가 쓴 북마크 구절은 " + bookMark.getPhase() + "이고, 이에 대한 내 메모는 " + bookMark.getMemo();
            info.add(bookMarkInfo);
        }

        return info;
    }

    private List<String> parseResult(String result){
        return Arrays.stream(result.split("\n"))
                .filter(line -> !line.trim().isEmpty())
                .toList();
    }

    public ArgumentCreateResponse createBookReport(ArgumentCreateRequest request){
        BookReport bookReport = BookReport.builder()
                .argument(request.argument())
                .build();
        bookReportRepository.save(bookReport);
        return ArgumentCreateResponse.of(bookReport.getId());
    }



    private List<String> makeInfo(BookReport bookReport){
        List<String> info = new ArrayList<>();

        String bookInfo = "책 제목은 " + bookReport.getMybook().getBook().getTitle()
                + "이고, 저자는 " + bookReport.getMybook().getBook().getAuthor()
                + "이고, 책 소개는 " + bookReport.getMybook().getBook().getContent();
        info.add(bookInfo);

        String argumentInfo = "이 독서록의 논점은 " + bookReport.getArgument();
        info.add(argumentInfo);

        return info;
    }
}
