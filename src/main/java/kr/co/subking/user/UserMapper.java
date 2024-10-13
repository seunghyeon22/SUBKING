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
import org.apache.ibatis.type.JdbcType;import lombok.experimental.PackagePrivate;

public interface UserMapper {
//    
//    @Insert(value = {
//        "INSERT INTO user (user_id, user_pw, user_name, user_birth, user_phone, user_gender, user_email, user_address, user_role, user_signupDate, user_updateDate)",
//        "VALUES (#{user.userId}, #{user.userPw}, #{user.userName}, #{user.userBirth}, #{user.userPhone}, #{user.userGender}, #{user.userEmail}, #{user.userAddress}, #{user.userRole}, #{user.userSignupDate}, #{user.userUpdateDate})"
//    })
//    @SelectKey(statement = "SELECT LAST_INSERT_ID()", resultType = int.class, before = false, keyProperty = "user.userId")
//    int insert(@Param("user") User user);


	@Select("SELECT user_id, user_pw, user_name, user_birth, user_phone, user_gender, user_email, user_address, user_role, user_signupDate, user_updateDate FROM user WHERE user_id = #{userId}")
    User selectByUserId(@Param("userId") String userId);


	@Select("SELECT user_id, user_pw, user_name, user_birth, user_phone, user_gender, user_email, user_address, user_role, user_signupDate, user_updateDate FROM user WHERE user_id = #{userId}")
    User selectByPk(@Param("userId") int pk);

    @Update({
        "UPDATE user",
        "SET user_pw=#{userPw}, user_name=#{userName}, user_birth=#{userBirth}, user_phone=#{userPhone}, user_gender=#{userGender}, user_email=#{userEmail}, user_address=#{userAddress}, user_role=#{userRole}, user_signupDate=#{userSignupDate}, user_updateDate=#{userUpdateDate}",
        "WHERE user_id=#{userId}"
    })
    int update(User user);

    @Delete("DELETE FROM user WHERE user_id=#{userId}")
    int delete(@Param("userId") String user_id);

    @Select("SELECT * FROM user WHERE user_id = #{user_id} AND user_pw=#{user_pw}")
    User login(@Param("user_id") String user_id, @Param("user_pw") String user_pw);
	
	List<User> selectAll();

	@Insert("insert into user (user_id, user_pw, user_name, user_birth,user_phone, user_gender, user_email, user_address, user_role) values(#{user.user_id}, #{user.user_pw}, #{user.user_name}, #{user.user_birth}, #{user.user_phone}, #{user.user_gender}, #{user.user_email}, #{user.user_address}, #{user.user_role})")
	int insertUser(@Param("user") User user);

	@Results(value = {
			@Result(column = "user_id", property = "user_id", jdbcType = JdbcType.VARCHAR, id = true)})
	@Select("SELECT user_id FROM user WHERE user_id = #{user_id}")
	String checkUserExists(@Param("user_id") String user_id);

	// 해쉬된 비밀번호 받아오기
	@Select("SELECT user_pw FROM user WHERE user_id = #{user_id}")
	String getHashedPassword(@Param("user_id") String user_id);


	
	@Select("SELECT user_id from user where user_name = #{user_name} AND user_phone=#{user_phone}")
	User findIdByNameAndPhone(@Param("user_name") String user_name, @Param("user_phone") String user_phone);

	@Select("SELECT user_id, user_name, user_phone from user where user_id = #{user_id} AND user_name=#{user_name} AND user_phone= #{user_phone}")
	User findIdByPw(@Param("user_id") String user_id, @Param("user_name") String user_name, @Param("user_phone") String user_phone);
	
    @Update({
        "UPDATE user",
        "SET user_pw=#{user_pw}",
        "WHERE user_id=#{user_id}"
    })
    int update2(@Param("user_id")String user_id, @Param("user_pw")String user_pw);


    @Select("SELECT * FROM user WHERE user_id = #{user_id}")
	User adminLogin(@Param("user_id") String user_id);


	
	
}














