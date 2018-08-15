package jbr.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jbr.springmvc.model.Answer;
import jbr.springmvc.model.Login;
import jbr.springmvc.model.Query;
import jbr.springmvc.model.QuesAns;
import jbr.springmvc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void register(User user) {
		String registerQuery = "insert into userdata values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(registerQuery, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
				user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone() });
	}
	
	public boolean validateRegister(User user) {
		String sql = "select * from userdata where username='" + user.getUsername() + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? false : true;
	}

	public void addQuery(Query user) {
		String addQuestions = "insert into questions values(?,?,?)";
		String tmpQuery = "select * from questions where username = '" + user.getUsername() + "'";
		List<Query> ques = jdbcTemplate.query(tmpQuery, new QuesMapper());
		String qid = user.getUsername() + ques.size() + 1;
		jdbcTemplate.update(addQuestions, new Object[] { user.getUsername(), user.getQuery(), qid });
	}

	public void addAnswer(Answer user) {
		String addAnswer = "insert into answers values(?,?,?)";
		jdbcTemplate.update(addAnswer, new Object[] { user.getUname(), user.getAnswer(), user.getQid() });

	}

	public QuesAns ViewQuery(String uname) {
		String sql = "select * from questions where username = '" + uname + "'";
		String viewAnswer = "select * from answers";
		List<Query> ques = jdbcTemplate.query(sql, new QuesMapper());
		List<Answer> ans = jdbcTemplate.query(viewAnswer, new AnswerMapper());

		QuesAns quesans = new QuesAns();
		quesans.setAns(ans);
		quesans.setQues(ques);
		quesans.setUname(uname);
		return quesans;
	}

	public QuesAns ViewAllQuery(String uname) {
		String viewQues = "select * from questions";
		String viewAnswer = "select * from answers";
		List<Query> ques = jdbcTemplate.query(viewQues, new QuesMapper());
		List<Answer> ans = jdbcTemplate.query(viewAnswer, new AnswerMapper());

		QuesAns quesans = new QuesAns();
		quesans.setAns(ans);
		quesans.setQues(ques);
		quesans.setUname(uname);
		return quesans;
	}

	public User validateUser(Login login) {
		String sql = "select * from userdata where username='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

}

class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setPhone(rs.getString("phone"));

		return user;
	}
}

class QuesMapper implements RowMapper<Query> {
	public Query mapRow(ResultSet rs, int arg1) throws SQLException {
		Query user = new Query();
		user.setUsername(rs.getString("username"));
		user.setQuery(rs.getString("question"));
		user.setQid(rs.getString("qid"));

		return user;
	}
}

class AnswerMapper implements RowMapper<Answer> {
	public Answer mapRow(ResultSet rs, int arg1) throws SQLException {
		Answer user = new Answer();
		user.setAnswer(rs.getString("answer"));
		user.setQid(rs.getString("qid"));
		user.setUname(rs.getString("username"));

		return user;
	}
}