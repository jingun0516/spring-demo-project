package com.estsoft.springdemoproject.user.repository;

import com.estsoft.springdemoproject.user.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


// 실제 데이터 저장해서 검증
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 사용자 이메일로 조회
    @Test
    public void testFindByEmail() {
        // given : 사용자 정보 저장
        Users user = getUser();
        userRepository.save(user);

        //when
        Users returnUser = userRepository.findByEmail(user.getEmail()).orElseThrow();


        //then
        assertThat(returnUser.getEmail(), is(user.getEmail()));
        assertThat(returnUser.getPassword(), is(user.getPassword()));

    }


    // 사용자 저장
    @Test
    public void testSave() {
        // given
        Users user = getUser();

        // when
        Users savedUser = userRepository.save(user);


        // then
        assertThat(savedUser.getEmail(), is(user.getEmail()));
        assertThat(savedUser.getPassword(), is(user.getPassword()));

    }

    // 사용자 전체 조회
    @Test
    public void testFindAll() {
        // given
        userRepository.save(getUser());
        userRepository.save(getUser());
        userRepository.save(getUser());

        // when
        List<Users> userList = userRepository.findAll();

        // then
        assertThat(userList.size(), is(3));

    }



    private static Users getUser() {
        String email = "test@test.com";
        String password = "pw";
        Users user = new Users(email, password);
        return user;
    }

}