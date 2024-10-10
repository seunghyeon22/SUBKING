package kr.co.subking.user;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_birth;
	private String user_phone;
	private int user_gender; // 0이 여자고 1이 남자.
	private String user_email;
	private String user_address; // not null 적용 안된 상태
	private String user_role;

	private Timestamp user_signupdate;
	private Timestamp user_updatedate;
	
}
