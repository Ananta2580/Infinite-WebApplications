package com.java.jdbc.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.java.jdbc.dao.EmployDao;
import com.java.jdbc.dao.EmployDaoImpl;
import com.java.jdbc.model.Employ;
import com.java.jdbc.model.Gender;

public class MainProgram {
	
	public static void addEmployMain() {
		
		Scanner sc = new Scanner(System.in);
		Employ employ = new Employ();
		System.out.print("Enter EmpNo: ");
		employ.setEmpId(sc.nextInt());
		sc.nextLine(); // consume newline

		System.out.print("Enter Name: ");
		employ.setName(sc.nextLine());

		System.out.print("Enter Gender (MALE/FEMALE): ");
		employ.setGender(Gender.valueOf(sc.nextLine()));

		System.out.print("Enter Department: ");
		employ.setDept(sc.nextLine());

		System.out.print("Enter Designation: ");
		employ.setDesig(sc.nextLine());

		System.out.print("Enter Basic Salary: ");
		employ.setBasic(sc.nextDouble());
		
		EmployDao dao = new EmployDaoImpl();
		try {
			System.out.println(dao.addEmploydao(employ));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void searchEmployMain() {
		int empno;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employ no");
		empno = sc.nextInt();
		EmployDao dao = new EmployDaoImpl();
		try {
			Employ employ = dao.searchEmployDao(empno);
			if(employ != null) {
				System.out.println(employ);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	
	public static void deleteEmployMain() {
		int empno;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter employ no");
		empno = sc.nextInt();
		EmployDao dao = new EmployDaoImpl();
		
		try {
			System.out.println(dao.deleteEmployDao(empno));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateEmployMain() {
		Employ employ = new Employ();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter EmpNo: ");
		employ.setEmpId(sc.nextInt());
		sc.nextLine(); // consume newline

		System.out.print("Enter Name: ");
		employ.setName(sc.nextLine());

		System.out.print("Enter Gender (MALE/FEMALE): ");
		employ.setGender(Gender.valueOf(sc.nextLine()));

		System.out.print("Enter Department: ");
		employ.setDept(sc.nextLine());

		System.out.print("Enter Designation: ");
		employ.setDesig(sc.nextLine());

		System.out.print("Enter Basic Salary: ");
		employ.setBasic(sc.nextDouble());
		
		EmployDao dao = new EmployDaoImpl();
		
		try {
			System.out.println(dao.updateEmployDao(employ));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void showEmployMain() {
		EmployDao dao = new EmployDaoImpl();
		
		try {
			List<Employ> emploList = dao.showEmployDao();
			emploList.forEach(System.out::println);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void addUserMain() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter UserName  ");
		String user = sc.next();
		System.out.println("Enter Password  ");
		String pwd = sc.next();
		EmployDao dao = new EmployDaoImpl();
		
		try {
			System.out.println(dao.addUser(user, pwd));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loginUserMain() {
		String user, pwd;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter User  ");
		user = sc.next();
		System.out.println("Enter Password  ");
		pwd = sc.next();
		EmployDao dao = new EmployDaoImpl();
		try {
			int count =dao.authenticate(user, pwd);
			if (count==1) {
				System.out.println("Correct Credentials...");
			} else {
				System.out.println("Invalid Credentials...");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
//		showEmployMain();
//		searchEmployMain();
//		addEmployMain();
//		showEmployMain();
//		deleteEmployMain();
//		showEmployMain();
//		updateEmployMain();
//		showEmployMain();
		
//		addUserMain();
		loginUserMain();
		
		
	}
}
