package com.bl.jdbcassignment;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DBDemo {
	public static void main(String[] args) {
		String jdbcurl =  "jdbc:mysql://localhost:3306/emp_payroll?useSSL=false";
		String userName = "root";
		String password = "root";
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded Successfully");
		} catch (ClassNotFoundException exception) {
			throw new IllegalStateException("Cannot find driver in the classpath", exception);
		}
		listDrivers();
		try {
			System.out.println("Connecting to database: " + jdbcurl);
			connection = DriverManager.getConnection(jdbcurl, userName, password);
			System.out.println("Connection is successful: " + connection);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println("  " + driverClass.getClass().getName());
		}
	}
}