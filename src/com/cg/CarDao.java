package com.cg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarDao {

	private DataSource ds = null;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {

		this.ds = ds;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
	}

	public void store(UserDTO userDto) throws SQLException {

		System.out.println(ds + " in setDs");
		Connection con = ds.getConnection();
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(userDto);
		// String insertQuery = "Insert into Persons values(?,?,?)";
		String insertQuery = "Insert into Persons values(:username,:password,:profession)";

		try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {

			namedParameterJdbcTemplate.update(insertQuery, parameterSource);

		}

	}

}
