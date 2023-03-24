package com.crud.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Retrieve_All_Data extends Base_Demo {

	public static Connection connection;

	public static Statement statement;

	static {

		try {
			connection = create_Connection(
					"jdbc:sqlite:C:\\Users\\P3INW24\\eclipse-workspace\\Jdbc_Demo\\src\\com\\db\\WCC.db");
			statement = createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws SQLException {

		create_Table_IF_Exists("create table if not exists testchampionship" + "(position Integer,team text,"
				+ "qualified text ,nrr text,points Integer)");

		ResultSet resultSet = results_sets("select * from testchampionship");

		while (resultSet.next()) {

			System.out.println(resultSet.getInt("position") + resultSet.getString("team")
					+ resultSet.getString("qualified") + resultSet.getString("nrr") + resultSet.getInt("points"));

		}

		close_Connection();
	}

}
