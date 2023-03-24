package com.crud.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert_Demo {

	public static Statement s;

	public static Connection c;

	public static void main(String[] args) throws SQLException {

		try {

			c = DriverManager.getConnection(
					"jdbc:sqlite:C:\\Users\\P3INW24\\eclipse-workspace\\Jdbc_Demo\\src\\com\\db\\testjava.db");

			s = c.createStatement();

			s.execute("create table if not exists data"
					+ "(id Integer,firstname text," + "secondname text ,team text)");

			s.execute("insert into data (id,firstname,secondname,team)" + "values"
					+ "(17,'Steven', 'Smith','Australia')");
			
			s.execute("insert into data (id,firstname,secondname,team)" + "values"
					+ "(56,'Mitchell', 'Starc','Australia')");
			
			s.execute("insert into data (id,firstname,secondname,team)" + "values"
					+ "(55,'Ben', 'Stokes','England')");
			
			s.execute("insert into data (id,firstname,secondname,team)" + "values"
					+ "(33,'Jofra', 'Archer','England')");
			
		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		finally {

			s.close();
			c.close();

		}

	}

}
