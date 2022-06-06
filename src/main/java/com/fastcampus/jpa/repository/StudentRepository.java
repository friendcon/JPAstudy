package com.fastcampus.jpa.repository;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.fastcampus.jpa.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    // 이메일로 학생정보 조회
    Student findByEmail(String email);
    Student getByEmail(String email);
    Student readByEmail(String email);
    Student queryByEmail(String email);
    Student searchByEmail(String email);
    Student streamByEmail(String email);
    Student findStudentByEmail(String email);

    // 가장 먼저 조회된 데이터 리턴
    List<Student> findFirst1ByName(String name);
    List<Student> findTop1ByName(String name);

    // 조건 여러개로 조회
    List<Student> findByEmailAndName(String email, String name);
    List<Student> findByEmailOrName(String email, String name);

    // 비교 조건보다 큰 데이터 조회
    List<Student> findByIdAfter(Long id);
    /*List<Student> findByCreatedAtAfter(LocalDateTime yesterday);
    List<Student> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<Student> findByCreatedAtGraterThanEqual(LocalDateTime yesterday);*/
    // between은 해당 지점의 값을 포함
    /*List<Student> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);*/
    List<Student> findByIdBetween(Long id1, Long id2);

    // null 포함 확인
    List<Student> findByIdIsNotNull();
    /* Student 엔티티에 존재하는 리스트가 null 이 아닌 학생정보를 조회
    List<Student> findBy리스트명IsNotEmpty();
    */

    // 문자 포함 확인
    List<Student> findByNameIn(List<String> names);
    List<Student> findByNameStartingWith(String name);
    List<Student> findByNameEndingWith(String name);
    List<Student> findByNameContains(String name);
    // like를 통해 %를 사용하면 코드 가독성을 해침
    List<Student> findByNameLike(String name);

    // is 키워드
    List<Student> findStudentByNameIs(String name);
    List<Student> findStudentByName(String name);
    List<Student> findStudentByNameEquals(String name);

    // List<Student> findTop1ByName(String name);
    List<Student> findTop1ByNameOrderByIdDesc(String name);
    List<Student> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<Student> findFirstByName(String name, Sort sort);

    Page<Student> findByName(String name, Pageable pageable);
}

