package com.viifly.wba.admin.dao;

import com.viifly.wba.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcUserDao implements UserDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User getUserByLoginId(String loginId) {
        String sql = "select login_id,nick_name,email_address,status,created_date,status_changed_date," +
                "modified_date from wba_user where login_id = :login_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("login_id", loginId);

        User resultUser = this.namedParameterJdbcTemplate.queryForObject(sql,
                namedParameters,
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setLoginId(rs.getString("login_id"));
                        user.setNickName(rs.getString("nick_name"));
                        user.setEmailAddress(rs.getString("email_address"));
                        user.setStatus(rs.getString("status"));
                        user.setCreatedDate(rs.getTimestamp("created_date"));
                        user.setStatusChangedDate(rs.getTimestamp("status_changed_date"));
                        user.setModifiedDate(rs.getTimestamp("modified_date"));
                        return user;
                    }
                });
        return resultUser;
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into wba_user(login_id,nick_name,email_address,status,created_date,status_changed_date,modified_date) " +
                "VALUES(:login_id,:nick_name,:email_address,:status,:created_date,:status_changed_date,:modified_date)";

        SqlParameterSource namedParameters = new MapSqlParameterSource("login_id", user.getLoginId())
                .addValue("nick_name", user.getNickName())
                .addValue("email_address", user.getEmailAddress())
                .addValue("status", user.getStatus())
                .addValue("created_date", user.getCreatedDate())
                .addValue("status_changed_date", user.getStatusChangedDate())
                .addValue("modified_date", user.getModifiedDate());

        return this.namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public int getUserCount(String loginId) {
        String sql = "select count(*) from wba_user where login_id = :login_id";

        SqlParameterSource namedParameters = new MapSqlParameterSource("login_id", loginId);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public List<User> findUsers() {
        String sql = "select login_id,nick_name,email_address,status,created_date,status_changed_date,modified_date from wba_user";

        return this.namedParameterJdbcTemplate.query(sql, Collections.<String, Object>emptyMap(),
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setLoginId(rs.getString("login_id"));
                        user.setNickName(rs.getString("nick_name"));
                        user.setEmailAddress(rs.getString("email_address"));
                        user.setStatus(rs.getString("status"));
                        user.setCreatedDate(rs.getTimestamp("created_date"));
                        user.setStatusChangedDate(rs.getTimestamp("status_changed_date"));
                        user.setModifiedDate(rs.getTimestamp("modified_date"));
                        return user;
                    }
                });
    }
}
