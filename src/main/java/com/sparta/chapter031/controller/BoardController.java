package com.sparta.chapter031.controller;

import com.sparta.chapter031.domain.Board;
import com.sparta.chapter031.domain.BoardRepository;
import com.sparta.chapter031.domain.BoardRequestDto;
import com.sparta.chapter031.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;


    //생성 부분
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    // 목록 불러오기 (조회 기능)
    @GetMapping("/api/boards")
    public List<Board> readBoard(){
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 시간 맞추기
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }


//    //디테일페이지
//    @GetMapping("/boardDetail/{id}")
//    public ModelAndView view(@PathVariable Long id, Model model){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/boardDetail");
//        mv.addObject("id", id);
////        model.addAttribute("id",id);
//        return mv;
//    }


    //업데이트
    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        boardService.update(id, requestDto);
        return id;
    }

    // 삭제
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
        return id;
    }




}
