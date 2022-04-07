package com.aw.boilerplate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sample")
public class Sample extends BaseEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)// 자동 생성되는 키 값
    private Long id;

    private String name;

    private String email;

    @Builder
    public Sample(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
