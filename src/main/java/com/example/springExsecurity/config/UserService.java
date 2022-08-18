package com.example.springExsecurity.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springExsecurity.Repository.MemberMapper;
import com.example.springExsecurity.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//로그인 요청이 오면 자동으로 UserDetailsService로 LoadUserbyUserName메소드로 보냄
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService{
	private final MemberMapper memberMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("userbane:{}", username);
		Member findMember = memberMapper.findMember(username);
		log.info("findMember: {}",findMember);
		if(findMember!=null) {
			return new UserInfo(findMember);
		}
		return null;
	}
}
