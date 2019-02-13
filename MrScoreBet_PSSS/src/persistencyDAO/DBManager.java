package persistencyDAO;

import java.sql.*;
import java.util.concurrent.locks.*;
 
public class DBManager {
	
	protected Connection connection;	
	private static DBManager instance = null;
	private final Lock lock = new ReentrantLock();
	
	public static DBManager getInstance() {
			if (instance == null) {
				instance = new DBManager();
			}
			return instance;
	}
	
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			String url = "jdbc:sqlite:C:/utility/db/MrScorebet.db";        
			connection = DriverManager.getConnection(url);			
		}
		return connection;
	}
		
	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
		}
				
	}
	
	public void LockDB() { this.lock.lock();}
	public void UnlockDB() { this.lock.unlock();}
	
}
    
