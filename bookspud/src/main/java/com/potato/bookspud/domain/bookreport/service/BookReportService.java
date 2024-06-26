package com.potato.bookspud.domain.bookreport.service;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookreport.domain.BookReport;
import com.potato.bookspud.domain.bookreport.domain.BookReportContent;
import com.potato.bookspud.domain.bookreport.domain.Status;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.request.DraftCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.request.FinalCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.response.*;
import com.potato.bookspud.domain.bookreport.repository.BookReportContentRepository;
import com.potato.bookspud.domain.bookreport.repository.BookReportRepository;
import com.potato.bookspud.domain.chatgpt.controller.ChatGPTController;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentsRequest;
import com.potato.bookspud.domain.user.domain.User;
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
    private final BookReportContentRepository bookReportContentRepository;

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

        String bookInfo = "책 제목은 " + request.myBook().getBook().getTitle() + "이고, 저자는 " +  request.myBook().getBook().getAuthor() + " 이고, 책 소개는 " +  request.myBook().getBook().getContent();
        info.add(bookInfo);

        for (BookMark bookMark : request.bookMarks()){
            String bookMarkInfo = "이 책에 대해 내가 쓴 북마크 구절은 " + bookMark.getPhase() + "이고, 이에 대한 내 메모는 " + bookMark.getMemo();
            info.add(bookMarkInfo);
        }

        return info;
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

    private List<String> makeInfo(BookReport bookReport, DraftCreateRequest request){
        List<String> info = new ArrayList<>();

        String bookInfo = "책 제목은 " + bookReport.getMybook().getBook().getTitle()
                + "이고, 저자는 " + bookReport.getMybook().getBook().getAuthor();
        info.add(bookInfo);

        String argumentInfo = "독서록의 주제는 " + bookReport.getArgument();
        info.add(argumentInfo);

        String qaInfo = "서론과 관련된 질문은 " + request.introQuestion() + "이고, 그에 대한 답변은 " + request.introAnswer()
                + "이고, 본론과 관련된 질문은 " + request.bodyQuestion() + "이고, 그에 대한 답변은 " + request.bodyAnswer()
                + "이고, 결론과 관련된 질문은 " + request.conclusionQuestion() + "이고, 그에 대한 답변은 " + request.conclusionAnswer();
        info.add(qaInfo);

        return info;
    }

    private List<String> parseResult(String result){
        return Arrays.stream(result.split("\n"))
                .filter(line -> !line.trim().isEmpty())
                .toList();
    }

    public ArgumentCreateResponse createBookReport(MyBook myBook, ArgumentCreateRequest request){
        BookReport bookReport = BookReport.builder()
                .mybook(myBook)
                .user(myBook.getUser())
                .argument(request.argument())
                .build();
        bookReportRepository.save(bookReport);
        return ArgumentCreateResponse.of(bookReport);
    }


    public DraftResponse createDraft(Long id, DraftCreateRequest request){
        BookReport bookReport = bookReportRepository.getById(id);
        List<String> info = makeInfo(bookReport, request);
        String result = chatGPTController.makeDraft(info);
        List<String> draft = parseResult(result);

        //BookReportContent 생성
        BookReportContent bookReportContent = createBookReportContent(bookReport, draft);

        // BookReport status update
        bookReport.updateStatus(Status.DRAFT);

        return DraftResponse.of(bookReportContent);
    }

    private BookReportContent createBookReportContent(BookReport bookReport, List<String> draft){
        BookReportContent newBookReportContent = BookReportContent.builder()
                .bookReport(bookReport)
                .intro(draft.get(0))
                .body(draft.get(1))
                .conclusion(draft.get(2))
                .build();

        bookReportContentRepository.save(newBookReportContent);
        return newBookReportContent;
    }

    public void createFinal(Long id, FinalCreateRequest request){
        BookReport bookReport = bookReportRepository.getById(id);
        updateBookReportContent(bookReport, request);
        bookReport.updateStatus(Status.FINAL);
    }

    private void updateBookReportContent(BookReport bookReport, FinalCreateRequest request){
        BookReportContent bookReportContent = bookReportContentRepository.findByBookReport(bookReport);
        bookReportContent.updateFinal(request.introEmotion(), request.bodyEmotion(), request.conclusionEmotion());
        bookReportContentRepository.save(bookReportContent);
    }

    public BookReportsResponse getBookReports(User user){
        List<BookReportResponse> bookReports = bookReportRepository.findAllByUser(user)
                .stream()
                .map(BookReportResponse::of)
                .toList();
        return BookReportsResponse.of(bookReports);
    }

    public BookReportDetailResponse getBookReportDetail(Long id){
        BookReport bookReport = bookReportRepository.getById(id);
        BookReportContent bookReportContent = bookReportContentRepository.findByBookReport(bookReport);
        return BookReportDetailResponse.of(bookReportContent);
    }
}
