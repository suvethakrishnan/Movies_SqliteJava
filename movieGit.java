import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class movieGit {

	public static void main(String[] args) throws SQLException {
		String continueData="";
		//create database
		String jdbcUrl ="jdbc:sqlite:C:\\Users\\fssdesktop71\\Videos\\MulesoftTech\\moviesdb.db";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Scanner ip3 = new Scanner(System.in);
	
		do {
			System.out.println("Welcome! Database created as moviesdb \nSelect options from below menu");
			System.out.println("Press 1 to Create table \nPress 2 to Insert data  \nPress 3 to Select data \nPress 4 to select data based on actor");
			Scanner ip0 = new Scanner(System.in);
			int cnt = ip0.nextInt();
			switch(cnt) {
			case 1:
				try {
					conn = DriverManager.getConnection(jdbcUrl);
					String createSql = "create table Movies (name varchar(50),actor varchar(50),actress varchar(50),director varchar(50),year_of_release varchar(10) )";
					//String createSql ="Drop table Movies"; 
					stmt = conn.createStatement();
					 stmt.executeUpdate(createSql);
					 System.out.println("Table created Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Already table exists");
				}
				finally {
					if(stmt!=null)
						stmt.close();
					if(conn!=null)
						conn.close();
				}
				break;
			case 2:
				try {
					conn = DriverManager.getConnection(jdbcUrl);					
					  System.out.println("Enter Movie Name"); 
					  String mname=ip3.nextLine();
					  System.out.println("Enter Actor Name"); 
					  String actor=ip3.nextLine(); 
					  System.out.println("Enter Actress Name");
					  String actress=ip3.nextLine();
					  System.out.println("Enter Director Name");
					  String dir=ip3.nextLine(); 
					  System.out.println("Enter Year of Release"); 
					  String yor=ip3.nextLine();
					 
					String insertSql = "insert into Movies"+"(name,actor,actress,director,year_of_release) values (?,?,?,?,?);";	
					//String insertSql = "insert into Movies (name,actor,actress,director,year_of_release) values('abc','well','aa','car','1996') ";	
					 pstmt = conn.prepareStatement(insertSql);
						
						  pstmt.setString(1, mname);
						  pstmt.setString(2, actor); 
						  pstmt.setString(3,actress); 
						  pstmt.setString(4, dir);
						  pstmt.setString(5, yor);
						 
					 int row =pstmt.executeUpdate();					
					 if (row >0)
					 System.out.println("Data Inserted Successfully");
					 else
						 System.out.println("Data Insertion error");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				}
				break;
			case 3:
				try {
					conn = DriverManager.getConnection(jdbcUrl);
					String createSql = "select * from Movies";
					 stmt = conn.createStatement();
					 rs=stmt.executeQuery(createSql);
					 while(rs.next()) {
						 String name= rs.getString("name");
						 String actor= rs.getString("actor");
						 String actress= rs.getString("actress");
						 String director= rs.getString("director");
						 String yearofrelease= rs.getString("year_of_release");
						 System.out.println(name +"|"+ actor +"|"+ actress +"|"+ director +"|"+ yearofrelease);
					 }
					 
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
					if(conn!=null)
						conn.close();
				}
				break;
			case 4:
				try {
					conn = DriverManager.getConnection(jdbcUrl);
					System.out.println("Enter Actor Name");
					String actor1 =ip3.nextLine();
					String createSql = "select * from Movies where actor in ('"+actor1+"') ";
					 stmt = conn.createStatement();
					 rs=stmt.executeQuery(createSql);
					 System.out.println("select statement based on actor");
					 while(rs.next()) {
						 String name= rs.getString("name");
						 String actor= rs.getString("actor");
						 String actress= rs.getString("actress");
						 String director= rs.getString("director");
						 String yearofrelease= rs.getString("year_of_release");
						 System.out.println(name +"|"+ actor +"|"+ actress +"|"+ director +"|"+ yearofrelease);
					 }
					 
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
					if(conn!=null)
						conn.close();
				}
				break;
				
				default:
					System.out.println("You have selected wrong input!!!"); 
					break;
			}//swit end
			System.out.println("Enter 'y' to continue  : else press other key to exit the program");
			continueData =ip3.nextLine();
			if(continueData.equals("y"))
				System.out.println("You have selected to continue");
		}//do end
		while(continueData.equals("y")) ;
			System.out.println("You have Exited the program");

	}

}
