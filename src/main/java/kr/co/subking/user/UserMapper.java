package kr.co.subking.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    
    @Insert(value = {
        "INSERT INTO user (user_id, user_pw, user_name, user_birth, user_phone, user_gender, user_email, user_address, user_role, user_signupDate, user_updateDate)",
        "VALUES (#{user.userId}, #{user.userPw}, #{user.userName}, #{user.userBirth}, #{user.userPhone}, #{user.userGender}, #{user.userEmail}, #{user.userAddress}, #{user.userRole}, #{user.userSignupDate}, #{user.userUpdateDate})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", resultType = int.class, before = false, keyProperty = "user.userId")
    int insert(@Param("user") User user);

    @Select("SELECT user_id, user_pw, user_name, user_birth, user_phone, user_gender, user_email, user_address, user_role, user_signupDate, user_updateDate FROM user WHERE user_id = #{userId}")
    @ResultMap(value = "userResults")
    User selectByPk(@Param("userId") int pk);

    @Update({
        "UPDATE user",
        "SET user_pw=#{userPw}, user_name=#{userName}, user_birth=#{userBirth}, user_phone=#{userPhone}, user_gender=#{userGender}, user_email=#{userEmail}, user_address=#{userAddress}, user_role=#{userRole}, user_signupDate=#{userSignupDate}, user_updateDate=#{userUpdateDate}",
        "WHERE user_id=#{userId}"
    })
    int update(User user);

    @Delete("DELETE FROM user WHERE user_id=#{userId}")
    int delete(@Param("userId") int userId);

	List<User> selectAll();
}













