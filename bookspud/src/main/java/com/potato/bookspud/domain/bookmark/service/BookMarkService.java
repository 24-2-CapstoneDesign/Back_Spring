package com.potato.bookspud.domain.bookmark.service;

import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.book.exception.InvalidMyBookException;
import com.potato.bookspud.domain.book.repository.MyBookRepository;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookmark.dto.request.BookMarkCreateRequest;
import com.potato.bookspud.domain.bookmark.dto.response.BookMarkDetailResponse;
import com.potato.bookspud.domain.bookmark.dto.response.BookMarkOthersResponse;
import com.potato.bookspud.domain.bookmark.dto.response.RandomBookMarkResponse;
import com.potato.bookspud.domain.bookmark.exception.InvalidBookMarkException;
import com.potato.bookspud.domain.bookmark.repository.BookMarkRepository;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.potato.bookspud.domain.common.ErrorCode.NOT_FOUND_BOOKMARK_EXCEPTION;
import static com.potato.bookspud.domain.common.ErrorCode.NOT_FOUND_MYBOOK_EXCEPTION;


@Service
@RequiredArgsConstructor
public class BookMarkService {


    private final BookMarkRepository bookMarkRepository;
    private final MyBookRepository myBookRepository;

    // 북마크 등록
    @Transactional
    public BookMark createBookMark(BookMarkCreateRequest request, User user) {

        MyBook myBook = myBookRepository.findById(request.myBookId())
                .orElseThrow(() -> new InvalidMyBookException(NOT_FOUND_MYBOOK_EXCEPTION));

        BookMark bookMark = BookMark.builder()
                .phase(request.phase())
                .page(request.page())
                .memo(request.memo())
                .emotion(request.emotion())
                .user(user)
                .myBook(myBook)
                .build();

        bookMarkRepository.save(bookMark);
        return bookMark;
    }

    // 북마크 삭제
    @Transactional
    public void deleteBookMark(Long id) {
        BookMark bookMark = bookMarkRepository.findById(id).orElseThrow(() -> new InvalidBookMarkException(NOT_FOUND_BOOKMARK_EXCEPTION));
        bookMarkRepository.delete(bookMark);
    }

    // 북마크 개별 조회
    public BookMarkDetailResponse getBookMark(Long id) {
        BookMark bookMark = bookMarkRepository.findById(id).orElseThrow(()-> new InvalidBookMarkException(NOT_FOUND_BOOKMARK_EXCEPTION));
        return BookMarkDetailResponse.of(bookMark);
    }

    // myBook 책별 북마크 목록 조회
    public List<BookMarkDetailResponse> getBookmarksByMyBookId(Long myBookId) {
        List<BookMark> bookmarks = bookMarkRepository.findByMyBookId(myBookId);
        return bookmarks.stream()
                .map(BookMarkDetailResponse::of)
                .collect(Collectors.toList());
    }

    // 특정 책의 특정 감정을 가진 북마크 목록 조회 - 추천책 하나 선택 시 사용
    public List<BookMarkOthersResponse> getBookMarksByBookIdAndMostUsedEmotion(Long bookId, User user) {
        // 특정 책에 대한 북마크 목록 조회
        List<BookMark> bookMarks = bookMarkRepository.findByMyBook_Book_Id(bookId);
        Map<Emotion, Long> emotionCounts = new HashMap<>();

        for (BookMark bookMark : bookMarks) {
            Emotion emotion = bookMark.getEmotion();
            emotionCounts.put(emotion, emotionCounts.getOrDefault(emotion, 0L) + 1);
        }
        // 가장 많이 사용된 감정
        Emotion mostUsedEmotion = Collections.max(emotionCounts.entrySet(), Map.Entry.comparingByValue()).getKey();

        // 전체 북마크 중 가장 많이 사용된 감정을 가진 북마크(본인꺼 제외)만 추출
        List<BookMarkOthersResponse> bookMarksResponses = new ArrayList<>();
        for (BookMark bookMark : bookMarks) {
            if (bookMark.getEmotion() == mostUsedEmotion && bookMark.getUser().getId() != user.getId()) {
                bookMarksResponses.add(new BookMarkOthersResponse(bookMark.getUser().getNickname(), bookMark.getUser().getProfileImageUrl(), bookMark.getEmotion(), bookMark.getPhase(), bookMark.getMemo()));
            }
        }
        return bookMarksResponses;
    }

    // 감정별 북마크 개수 조회
    public Map<Emotion, Long> getBookMarkCountByEmotion(User user) {
        Map<Emotion, Long> bookMarkCount =  new HashMap<>();
        for (Emotion emotion : Emotion.values()) {
            long count = bookMarkRepository.countByEmotionAndUser(emotion, user);
            bookMarkCount.put(emotion, count);
        }
        return bookMarkCount;
    }

    // 북마크 랜덤 조회
    public RandomBookMarkResponse getRandomBookMark(Emotion emotion, User user) {
        List<BookMark> randomBookMarks = bookMarkRepository.findRandomByEmotionAndUser(emotion, user, PageRequest.of(0, 1));
        if (!randomBookMarks.isEmpty()) {
            BookMark randomBookMark = randomBookMarks.get(0);
            Book book = randomBookMark.getMyBook().getBook();

            return new RandomBookMarkResponse(
                    randomBookMark.getEmotion(),
                    randomBookMark.getPhase(),
                    book.getTitle(),
                    book.getCover()
            );
        } else {
            throw new InvalidBookMarkException(NOT_FOUND_BOOKMARK_EXCEPTION);
        }
    }

    public List<BookMark> getBookMarksByUserandBook(MyBook myBook, User user){
        return bookMarkRepository.findByMyBookAndUser(myBook, user);
    }
}