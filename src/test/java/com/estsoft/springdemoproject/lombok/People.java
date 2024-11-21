package com.estsoft.springdemoproject.lombok;

import lombok.*;

import java.util.List;

/**
 * 롬복 적용해볼 People 클래스
 * -> 생성자, Getter, Setter 적용
 */
// POJO(Plain Object Java Object)
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class People {
    private final Long id;
    private final String name;
    private int age;
    private List<String> hobbies;
}
