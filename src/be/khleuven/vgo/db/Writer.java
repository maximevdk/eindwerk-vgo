package be.khleuven.vgo.db;

import java.util.List;

public interface Writer {
	public void writeToFile(List<String> sourcecode, String filename) throws DatabaseException;
}
