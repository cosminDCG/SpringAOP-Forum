package com.aop.forumspring.dao;

import com.aop.forumspring.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class UserDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    public UserDTO getUserByEmail(String username) {
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM utilizatori " +
                "WHERE email = :given_email";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("given_email", username);

        return namedJdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement ->{
            ResultSet rs = preparedStatement.executeQuery();
            UserDTO userDTO = null;
            if(rs.next()) {
                userDTO = new UserDTO();
                userDTO.setUserId(rs.getInt("user_id"));
                userDTO.setFirstName(rs.getString("first_name"));
                userDTO.setLastName(rs.getString("last_name"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setPassword(rs.getString("password"));
            }
            return userDTO;

        });
    }

    public UserDTO getUserById(int id) {

        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM utilizatori " +
                "WHERE user_id = :user_id";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", id);

        return namedJdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement ->{
            ResultSet rs = preparedStatement.executeQuery();
            UserDTO userDTO = null;
            if(rs.next()) {
                userDTO = new UserDTO();
                userDTO.setUserId(rs.getInt("user_id"));
                userDTO.setFirstName(rs.getString("first_name"));
                userDTO.setLastName(rs.getString("last_name"));
                userDTO.setEmail(rs.getString("email"));
                userDTO.setPassword(rs.getString("password"));
            }
            return userDTO;

        });
    }

    public void addUser(UserDTO userDTO) {

        String sqlInsert = "" +
                "INSERT INTO utilizatori (first_name, last_name, email, password) VALUES (:first_name, :last_name, :email, :password)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("first_name", userDTO.getFirstName());
        namedParameters.addValue("last_name", userDTO.getLastName());
        namedParameters.addValue("email", userDTO.getEmail());
        namedParameters.addValue("password", userDTO.getPassword());

        jdbcTemplate.update(sqlInsert, namedParameters);
    }

    public void deleteUser(String email) {

        String sqlDelete = "" +
                "DELETE " +
                "FROM utilizatori " +
                "WHERE email = :email";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", email);

        jdbcTemplate.update(sqlDelete, namedParameters);
    }
}
