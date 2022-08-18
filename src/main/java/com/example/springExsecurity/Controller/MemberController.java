package com.example.springExsecurity.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springExsecurity.Repository.MemberMapper;
import com.example.springExsecurity.config.UserInfo;
import com.example.springExsecurity.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
@Slf4j
public class MemberController {
	private final MemberMapper memberMapper;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/joinForm")
	public String joinForm() {
		log.info("joinForm");
		return "/member/joinForm";
	}
	@PostMapping("/join")
	public String join(@ModelAttribute Member member) {
		log.info("member:{}",member);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberMapper.saveMember(member);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		log.info("loginForm");
		return "/member/login";
	}
	@PostMapping("/login")
	public String login() {
		log.info("logIn");
		return "redirect:/";
	}
	@GetMapping("/login-success")
	public String login_Success(@AuthenticationPrincipal UserInfo userInfo,Model model) {
		log.info("login-success");
		log.info("userinfo: {}",userInfo);
	
		return "redirect:/";
	}
	@GetMapping("/login-fail")
	public String login_fail() {
		log.info("login-fail");
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request,response,SecurityContextHolder.getContext().getAuthentication());
	return "redirect:/";
	}
}
