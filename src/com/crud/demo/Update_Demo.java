package com.crud.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Update_Demo extends Base_Demo {

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

//		insert_Query("insert into testchampionship(position,team,qualified,nrr,points)"
//				+ "values(5,'South Africa','NQ','-2.3',25)");
//		
		update_Query("update testchampionship set points=39 where team='New Zealand'");

		delete_Query("delete from testchampionship where team = 'South Africa' ");

		close_Connection();
	}

}
