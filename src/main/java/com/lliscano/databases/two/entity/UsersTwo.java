package com.lliscano.databases.two.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
public class UsersTwo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_seq", schema = "public", allocationSize = 1)
    private Long id;

    @Column(name = "fullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "gender", nullable = false, length = 100)
    private String gender;

}
