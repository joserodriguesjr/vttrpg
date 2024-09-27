//package com.vttrpg.RPG.domain.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.util.Date;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull(message = "Name may not be null")
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @JsonIgnore private String password;
//
//    @CreatedDate
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private Date createdAt;
//}
