package com.fastcampus.jpa.repository;

import com.fastcampus.jpa.domain.Student;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StudentRepositoryTests {
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void 초기화() {
        Student student = Student.builder()
                        .name("sayho")
                        .email("sayho@test.com").build();
        Student student2 = Student.builder()
                        .name("sayho")
                        .email("sayho@gmail.com").build();

        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        studentList.add(student2);

        studentRepository.saveAll(studentList);
    }

    @Test
    @DisplayName("학생정보생성")
    void 학생정보생성() {
        Student student = Student.builder()
                .name("hozumi")
                .email("hozumi@test.com").build();
        Student response = studentRepository.save(student);
        Assertions.assertThat(student.getName()).isEqualTo(response.getName());
    }

    @Test
    @DisplayName("이름으로 학생정보 조회")
    void 학생정보_이름으로조회() {
        String name = "sayho";
        List<Student> response = studentRepository.findByName(name);
        response.forEach(
            it -> {
                Assertions.assertThat(it.getName()).isEqualTo(name);
            }
        );
    }

    @Test
    @DisplayName("다양한 방법으로 학생 정보 조회")
    void 학생조회() {
        String email = "sayho@test.com";
        String name = "sayho";
        System.out.println("findByEmail : " + studentRepository.findByEmail(email));
        System.out.println("getByEmail : " + studentRepository.getByEmail(email));
        System.out.println("readByEmail : " + studentRepository.readByEmail(email));
        System.out.println("queryByEmail : " + studentRepository.queryByEmail(email));
        System.out.println("searchByEmail : " + studentRepository.searchByEmail(email));
        System.out.println("streamByEmail : " + studentRepository.streamByEmail(email));
        System.out.println("findStudentByEmail : " + studentRepository.findStudentByEmail(email));
        System.out.println("findTop1ByName : " + studentRepository.findTop1ByName(name));
        System.out.println("findFirst1ByName : " + studentRepository.findFirst1ByName(name));
        System.out.println("findByEmailOrName : " + studentRepository.findByEmailOrName(email, name));
        System.out.println("findByEmailAndName : " + studentRepository.findByEmailAndName(email, name));
        /*System.out.println("findByCreatedatBetween : " + studentRepository
                .findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));*/

    }

    @Test
    @DisplayName("메서드 이름을 이용한 학생정보조회")
    void 학생정보조회_쿼리2탄() {
        System.out.println("findByIdIsNotNull : " + studentRepository.findByIdIsNotNull());
        // System.out.println("findByIdIsNotEmpty : " + studentRepository.findByIdIsNotEmpty());
        System.out.println("findByNameIn : " + studentRepository.findByNameIn(Lists.newArrayList("hozumi", "sayho")));
        System.out.println("findByNameStartingWith : " + studentRepository.findByNameStartingWith("say"));
        System.out.println("findByNameEndingWith : " + studentRepository.findByNameEndingWith("ho"));
        System.out.println("findByNameContains : " + studentRepository.findByNameContains("ho"));
    }

    @Test
    @DisplayName("페이징 및 정렬")
    void 페이징과정렬() {
        System.out.println("findTop1ByNameOrderByIdDesc : " + studentRepository.findTop1ByNameOrderByIdDesc("hozumi"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + studentRepository.findFirstByNameOrderByIdDescEmailAsc("hozumi"));
        System.out.println("findFirstByName : " + studentRepository.findFirstByName("hozumi", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findByNameWithPaging : " + studentRepository.findByName("hozumi", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))));

    }

}
