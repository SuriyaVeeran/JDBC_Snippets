package com.crud.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert_With_Base extends Base_Demo {

	public static Connection connection;

	public static Statement statement;

	public static void main(String[] args) throws SQLException {

		connection = create_Connection(
				"jdbc:sqlite:C:\\Users\\P3INW24\\eclipse-workspace\\Jdbc_Demo\\src\\com\\db\\WCC.db");

		statement = createStatement();

		create_Table_IF_Exists("create table if not exists testchampionship" + 
		"(position Integer,team text,"
				+ "qualified text ,nrr text,points Integer)");

//		insert_Query("insert into testchampionship (position,team,qualified,nrr,points)"
//				+ "values(1,'Australia','Qualified','+1.56',56)");

		insert_Query("insert into testchampionship (position,team,qualified,nrr,points)"
				+ "values(2,'India','Qualified','+1.1',50)");

		insert_Query("insert into testchampionship (position,team,qualified,nrr,points)"
				+ "values(3,'England','Not Qualified','-1.56',45)");

		insert_Query("insert into testchampionship (position,team,qualified,nrr,points)"
				+ "values(4,'New Zealand','Not Qualified','-.56',44)");

		
		close_Connection();

	}

}
