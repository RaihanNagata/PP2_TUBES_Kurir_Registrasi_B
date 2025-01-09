package mapper;

import model.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("SELECT * FROM users WHERE (username = #{userInput} OR email = #{userInput}) AND password = #{password}")
    User authenticate(@Param("userInput") String userInput, @Param("password") String password);
    
    @Select("SELECT COUNT(*) FROM users WHERE username = #{username} OR email = #{email}")
    int checkDuplicate(@Param("username") String username, @Param("email") String email);
    
    @Insert("INSERT INTO users (username, password, nama, email, jenisKelamin, alamat) " +
            "VALUES (#{username}, #{password}, #{nama}, #{email}, #{jenisKelamin}, #{alamat})")
    int save(User user);
}
