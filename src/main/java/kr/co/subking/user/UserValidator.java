package kr.co.subking.user;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {
	public Map<String, String> validate(User user, String userPwConfirm) {
		Map<String, String> error = new HashMap<>();
		
		if (user == null) {
			error.put("입력에러", "다시 입력해주세요");
			return error;
		} 
		
		if(user.getUser_id() == null) {
			error.put("cnt", "아이디가 존재하지 않습니다");
		} else {
			error.put("cnt", "아이디가 존재합니다");
		}
		
		if (isEmptyOrNull(user.getUser_id()) || ! onLength(user.getUser_id(), 1, 20)) {
			error.put("아이디", "아이디는 1 ~ 20자로 입력해주세요");
		} 

		
		if (isEmptyOrNull(user.getUser_pw()) || ! onLength(user.getUser_pw(), 1, 20)) {
			error.put("비밀번호", "비밀번호는 1 ~ 20자로 입력해주세요");
		} else if (! user.getUser_pw().equals(userPwConfirm)) {
			error.put("비밀번호", "비밀번호가 일치하지 않습니다");
		}
		
		if (isEmptyOrNull(user.getUser_name()) || ! onLength(user.getUser_name(), 1, 20)) {
			error.put("이름", "이름은 1 ~ 20자로 입력해주세요");
		}
		if (isEmptyOrNull(user.getUser_birth()) || ! onLength(user.getUser_birth(), 6, 8)) {
			error.put("생년월일", "생년월일 8자를 입력해주세요");
		}
		if (isEmptyOrNull(user.getUser_phone()) || ! onLength(user.getUser_phone(),13,13)) {
			error.put("핸드폰번호", "핸드폰 번호는 (-)포함 13자리를 입력해주세요");
		} 
		if (isEmptyOrNull(user.getUser_email()) || ! onLength(user.getUser_email(), 1, 30)) {
			error.put("e-mail", "이메일 주소는 1 ~ 30자로 입력해주세요");
			}
		return error;
	}
	
	private boolean onLength(String text, int min, int max) {
		if (text.length() < min || text.length() > max) {
			return false;
		}
		return true;
	}
	
	private boolean isEmptyOrNull(String text) {
		if (text == null || text.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}
