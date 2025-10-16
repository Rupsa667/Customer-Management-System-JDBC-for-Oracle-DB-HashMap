package com.example.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
}
