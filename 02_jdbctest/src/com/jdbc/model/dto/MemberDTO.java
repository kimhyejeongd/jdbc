package com.jdbc.model.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class MemberDTO {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private int age;
	private String emial;
	private String phone;
	private String address;
	private String[] hobby;
	private LocalDate enrollDate;
	
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String memberId, String memberPwd, String memberName, char gender, int age, String emial,
			String phone, String address, String[] hobby, LocalDate enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.emial = emial;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmial() {
		return emial;
	}

	public void setEmial(String emial) {
		this.emial = emial;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public LocalDate getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(LocalDate enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", gender=" + gender + ", age=" + age + ", emial=" + emial + ", phone=" + phone + ", address="
				+ address + ", hobby=" + Arrays.toString(hobby) + ", enrollDate=" + enrollDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hobby);
		result = prime * result
				+ Objects.hash(address, age, emial, enrollDate, gender, memberId, memberName, memberPwd, phone);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		return Objects.equals(address, other.address) && age == other.age && Objects.equals(emial, other.emial)
				&& Objects.equals(enrollDate, other.enrollDate) && gender == other.gender
				&& Arrays.equals(hobby, other.hobby) && Objects.equals(memberId, other.memberId)
				&& Objects.equals(memberName, other.memberName) && Objects.equals(memberPwd, other.memberPwd)
				&& Objects.equals(phone, other.phone);
	}
	
	
}
