package com.aop.forumspring.dao;


import com.aop.forumspring.dto.CommDTO;
import com.aop.forumspring.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    public void addComment(CommDTO commDTO) {
        String sqlInsert = "" +
                "INSERT INTO comentarii (post_id, user_id, comm_text, comm_date) VALUES (:post_id, :user_id, :comm_text, :comm_date)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("post_id", commDTO.getPostId());
        namedParameters.addValue("user_id", commDTO.getUser().getUserId());
        namedParameters.addValue("comm_text", commDTO.getCommText());
        namedParameters.addValue("comm_date", new Date(commDTO.getCommDate().getTime()));

        jdbcTemplate.update(sqlInsert, namedParameters);
    }

    public List<CommDTO> getCommentsByPostId(int postId) {
        List<CommDTO> commDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                " * " +
                "FROM comentarii " +
                "WHERE post_id = :post_id ";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("post_id", postId);

        return namedJdbcTemplate.execute(sqlSelect, namedParameters, preparedStatement ->{
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                CommDTO commDTO = new CommDTO();
                commDTO.setCommId(rs.getInt("comm_id"));
                commDTO.setPostId(rs.getInt("post_id"));
                commDTO.setUser(new UserDTO(rs.getInt("user_id")));
                commDTO.setCommText(rs.getString("comm_text"));
                commDTO.setCommDate(rs.getDate("comm_date"));
                commDTOList.add(commDTO);
            }
            return commDTOList;

        });
    }
}
