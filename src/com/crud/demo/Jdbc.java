package com.crud.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		

		Connection c = DriverManager.getConnection
				("jdbc:sqlite:C:\\Users\\P3INW24\\eclipse-workspace\\Jdbc_Demo\\src\\com\\db\\testjava.db");
		
		c.setAutoCommit(true);
		
		Statement statement = c.createStatement();
		 statement.execute(
				
				"create table if not exists details(id INTEGER, name text,email text)"		
				);
		
	 
		 statement.execute("insert into details (id,name,email)"
		 		+ "values(17,'Steven Smith','stevesmith49@gmail.com')");
		 
		 statement.execute("insert into details(id,name,email)"
		 		+ "values(56,'Mitchell Starc','mitchelstarc56@gmail.com')");
		 
		 statement.execute("insert into details(id,name,email)"
			 		+ "values(33,'Shane watson','shanewatson33@gmail.com')");
		 
		 statement.execute("insert into details(id,name,email)"
			 		+ "values(5,'Aaron finch','aaronfinch5@gmail.com')");
		
		
		 statement.close();
		 c.close();
		
		 System.out.println("Execution completed");
		 
	}

}
	