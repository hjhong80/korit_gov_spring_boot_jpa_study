package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.AddPostReqDto;
import com.korit.jpa_study.dto.EditPostReqDto;
import com.korit.jpa_study.entity.Post;
import com.korit.jpa_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*

1. 기술적 정의 (중급 개발자용)
**JPA(Java Persistence API)**는 자바 진영의 **ORM(Object-Relational Mapping) 기술 표준 명세(Interface)**입니다.
객체 지향 프로그래밍(Java)과 관계형 데이터베이스(RDBMS) 간의 패러다임 불일치(Paradigm Mismatch) 문제를 해결하기 위해 등장했습니다.
개발자가 직접 SQL을 작성하는 대신, JPA가 제공하는 API를 사용하면 **JPA 구현체(예: Hibernate)**가 적절한 SQL을 자동 생성하여 DB에 실행합니다.
이를 통해 데이터 중심이 아닌 객체 중심의 개발이 가능해지며, 유지보수성과 생산성이 비약적으로 향상됩니다.

2. 쉬운 비유 설명: "말이 통하지 않는 두 나라 사이의 유능한 통역사"
JPA를 **'한국인(Java 객체)과 미국인(데이터베이스) 사이의 유능한 통역사'**라고 상상해 보세요.

1) 서로 다른 언어와 문화 (패러다임의 불일치)
자바(한국인): "객체", "상속", "참조"라는 문법을 사용합니다. 데이터를 아주 유연하고 복잡한 구조로 다룹니다.
데이터베이스(미국인): "테이블", "행(Row)", "열(Column)"이라는 문법만 사용합니다. 모든 것을 엑셀 표처럼 납작하게 저장해야만 알아듣습니다.

2) 통역사가 없을 때 (JPA가 없을 때 - JDBC/SQL Mapper)
개발자인 당신이 직접 통역해야 합니다.
자바 객체를 DB에 넣을 때: "이 객체를 분해해서... 이름은 여기 넣고, 나이는 저기 넣고..." 하며
일일이 SQL(영어)로 번역해서 DB에 전달해야 합니다. (너무 힘들고 오타가 나기 쉽습니다.)
DB에서 꺼낼 때: DB가 준 표 데이터를 하나하나 읽어서 다시 자바 객체로 조립해야 합니다.

3) 통역사(JPA)가 있을 때
당신은 그저 자바 언어로 명령만 내리면 됩니다.
당신: "이 객체 저장해 줘(persist)."
JPA(통역사): (알아서 객체를 분석하고 최적화된 SQL을 생성하여) "DB야, INSERT INTO... 해라."
당신: "1번 회원 데려와(find)."
JPA(통역사): (DB에서 SELECT 해서 가져온 뒤, 자바 객체로 딱 조립해서) "여기 있습니다."
즉, JPA는 개발자가 복잡한 SQL 번역 업무에서 벗어나 비즈니스 로직(사업 구상)에만 집중할 수 있도록 도와주는 최고의 파트너입니다.

3. 솔루션 Java Code (JPA Entity & EntityManager)이 코드는 JPA의 동작 원리를 가장 명확하게 보여주는 기본 구현입니다.
알고리즘: Dirty Checking(변경 감지) 및 1차 캐시 전략 사용.
복잡도:시간 복잡도: ID 조회 시 DB 인덱스를 타므로 $O(1)$ (네트워크 비용 제외).
공간 복잡도: 영속성 컨텍스트(Persistence Context) 내에 객체를 보관하므로 $O(N)$의 메모리 사용.
전문가 검토: Lombok 라이브러리 사용을 가정하여 코드를 간결화했으며, 트랜잭션 관리 원칙을 준수했습니다.


JPA - Java Persistence API
객체지향 언어의 객체와 관계형 데이터베이스의 테이블간 매핑을 자동으로 처리
-> SQL 문법을 직접 구현하지 않고 자바를 중심으로 DB를 다루는 API

장점 : 별도의 SQL 구현 없이 CRUD 구현 가능. 코드량 감소 - 편리함
단점 : 복잡한 쿼리는 어렵고 디버깅이 힘들다 - 자유도가 부족함.

*/

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody AddPostReqDto addPostReqDto) {
        System.out.println(addPostReqDto.toString());
        return ResponseEntity.ok(postService.addPost(addPostReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editPost(@RequestBody EditPostReqDto editPostReqDto) {
        return ResponseEntity.ok(postService.editPost(editPostReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removePost(@RequestParam Integer postId) {
        return ResponseEntity.ok(postService.removePost(postId));
    }

}
