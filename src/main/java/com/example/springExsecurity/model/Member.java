package com.example.springExsecurity.model;

import lombok.Data;

@Data
public class Member {
	private String id;
    private String password;
    private String name;
    private RoleType role;
}