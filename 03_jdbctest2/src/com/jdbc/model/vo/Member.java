package com.jdbc.model.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//lombok라이브러리를 이용해서 getter,setter,생성자,toString,hasCode,equlas를 자동생성해줄 수 있음
// 자동생성해주는 어노테이션을 선언
//@Data//기본생성자, 전체필드 getter,setter,toString,hashCode,equals
//@NoArgsConstructor // 기본 생성자
//@AllArgsConstructor// 매개변수 있는 생성자
//@Getter //필드 getter생성
//@Setter // 필드 setter생성
//@ToString(exclude="memberId")//memberid는 빼고 tostring 여러개면 배열받식으로 (exclude={"",""})
//@EqualsAndHashCode(exclude= {"memberId","gender"})
//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private LocalDate enrollDate;
}
