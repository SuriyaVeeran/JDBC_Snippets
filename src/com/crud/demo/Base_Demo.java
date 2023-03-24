package com.crud.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base_Demo {

	public static Connection connection;

	public static Statement statement;

	public static Connection create_Connection(String path) throws SQLException {

		connection = DriverManager.getConnection(path);

		return connection;

	}

	public static Statement createStatement() throws SQLException {

		statement = connection.createStatement();

		return statement;

	}

	public static void create_Table(String query) throws SQLException {

		statement.execute(query);

	}

	public static void create_Table_IF_Exists(String query) throws SQLException {

		statement.execute(query);

	}

	public static void insert_Query(String query) throws SQLException {

		statement.execute(query);

	}

	public static void update_Query(String query) throws SQLException {

		statement.execute(query);

	}

	public static void delete_Query(String query) throws SQLException {

		statement.execute(query);

	}

	public static void close_Connection() throws SQLException {

		statement.close();
		connection.close();

	}

	public static void execute_Query(String query) throws SQLException {

		statement.execute(query);

	}

	public static ResultSet results_sets(String query) throws SQLException {

		ResultSet resultSet = statement.executeQuery(query);

		return resultSet;

	}

}
