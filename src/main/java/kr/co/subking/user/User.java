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
	@JsonProperty(value = "id")
	private String user_id;
	@JsonProperty(value = "password")
	private String user_pw;
	@JsonProperty(value = "name")
	private String user_name;
	@JsonProperty(value = "birthdate")
	private String user_birth;
	@JsonProperty(value = "phone")
	private String user_phone;
	@JsonProperty(value = "gender")
	private int gender;
	@JsonProperty(value = "email")
	private String user_email;
	@JsonProperty(value = "address")
	private String user_address;
	@JsonProperty(value = "role")
	private String user_role;
	@JsonProperty(value = "signupdate")
	private Timestamp signupdate;
	@JsonProperty(value = "updatedate")
	private Timestamp updatedate;
	
}
