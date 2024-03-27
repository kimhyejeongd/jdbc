package com.jdbc.model.dto;
import java.time.LocalDate;

public record MemberDTO2(
		String memberId, String memberPwd, String memberName,
		char gender,int age, String email,String phone,String address, String[] hobby, LocalDate enrollDate){}

