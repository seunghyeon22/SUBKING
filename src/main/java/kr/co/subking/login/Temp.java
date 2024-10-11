package kr.co.subking.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Temp {
	private String user_name;
	private String user_phone;
	private String user_id;
	private String user_pw;
}
