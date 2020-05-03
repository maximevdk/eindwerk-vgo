package be.khleuven.vgo.db;

public class DatabaseException extends Exception {
	public DatabaseException(){
		super();
	}
	
	public DatabaseException(String message){
		super(message);
	}
	
	public DatabaseException(String message, Exception exception) {
		super(message,exception);
	}
}
