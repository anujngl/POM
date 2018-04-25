package TestCases;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Functionlib {

	public static Connection conn = null;
	
	public static Statement stmtTests = null;	
	public static Statement stmtSteps = null;	
	public static Statement stmt = null;
	public static Statement stmtsid = null;
	public static Statement stmtSDS = null;
	public static Statement stmtTDS = null;
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
	
	public static void EstablishConnection() {
		try {
			

					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn  = DriverManager.getConnection("jdbc:sqlserver://TGS-DPC-2295\\SQLEXP2012;databaseName=AutomationCS;integratedSecurity=true");
					//TestAttributes.conn  = DriverManager.getConnection("jdbc:sqlserver://WEV-AUDT-QSQL10;databaseName=CSAutomationTestDB;integratedSecurity=true");
					stmtTests = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					stmtSteps = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					stmtsid = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
					stmtSDS = conn.createStatement();
					stmtTDS = conn.createStatement();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
