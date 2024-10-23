package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.TestService;
import com.example.demo.model.service.BlogService; // 추가된 import

@Controller // 컨트롤러 어노테이션 명시
public class DemoController {

    @Autowired
    TestService testService; // DemoController 클래스 아래 객체 생성

    @Autowired
    BlogService blogService; // BlogService 주입 추가

    @GetMapping("/hello") // 전송 방식 GET
    public String hello(Model model) {
        model.addAttribute("data", " 방갑습니다."); // model 설정
        return "hello"; // hello.html 연결
    }

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        TestDB test = testService.findByName("홍길동");
        model.addAttribute("data4", test);
        System.out.println("데이터 출력 디버그 : " + test);
        return "testdb";
    }

    @GetMapping("/article_list")
    public String article_list() {
        return "article_list"; // .html 연결
    }

    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request); // blogService를 사용하여 업데이트
        return "redirect:/article_list"; // 글 수정 이후 .html 연결
    }
}
    

