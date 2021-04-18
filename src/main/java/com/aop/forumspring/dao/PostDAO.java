package com.aop.forumspring.dao;



import com.aop.forumspring.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    public void addPost(PostDTO postDTO) {
        String sqlInsert = "" +
                "INSERT INTO postari (user_id, post_text, post_date) VALUES (:user_id, :post_text, :post_date)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("user_id", postDTO.getUserId());
        namedParameters.addValue("post_text", postDTO.getPostText());
        namedParameters.addValue("post_date", new Date(postDTO.getPostDate().getTime()));

        jdbcTemplate.update(sqlInsert, namedParameters);
    }

    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM postari";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        return namedJdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement ->{
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostId(rs.getInt("post_id"));
                postDTO.setUserId(rs.getInt("user_id"));
                postDTO.setPostText(rs.getString("post_text"));
                postDTO.setPostDate(rs.getDate("post_date"));
                postDTOList.add(postDTO);
            }
            return postDTOList;

        });
    }

    public List<PostDTO> getPostsByUserId(int userId) {
        List<PostDTO> postDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM postari " +
                "WHERE user_id = :user_id";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("user_id", userId);

        return namedJdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement ->{
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostId(rs.getInt("post_id"));
                postDTO.setUserId(rs.getInt("user_id"));
                postDTO.setPostText(rs.getString("post_text"));
                postDTO.setPostDate(rs.getDate("post_date"));
                postDTOList.add(postDTO);
            }
            return postDTOList;

        });
    }
}
