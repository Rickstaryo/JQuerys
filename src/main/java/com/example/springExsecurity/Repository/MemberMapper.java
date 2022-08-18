package com.example.springExsecurity.Repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.springExsecurity.model.Member;

@Mapper
public interface MemberMapper {
	public void saveMember(Member member);
	public Member findMember(String id);
}
