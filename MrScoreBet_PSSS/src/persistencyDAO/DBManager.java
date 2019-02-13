package persistencyDAO;

import java.sql.*;
import java.util.concurrent.locks.*;
 
public class DBManager {
	
	protected Connection connection;	
	private static DBManager instance = null;
	private final Lock lock = new ReentrantLock();
	
	public static DBManager getInstance() {
		synchronized(instance) {
			if (instance == null) {
				instance = new DBManager();
			}
			return instance;
		}
	}
	
	public Connection getConnection() throws SQLException {
		lock.lock();
		if (connection == null) {
			String url = "jdbc:sqlite:C:/utility/db/MrScoreBet.db";        
			connection = DriverManager.getConnection(url);			
		}
		return connection;
	}
	
	public void unLock() {
		lock.unlock();
	}
	
	public void closeConnection() throws SQLException {
		if(lock.tryLock())
			if (connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
				lock.unlock();
			}
	}
	
}
    
