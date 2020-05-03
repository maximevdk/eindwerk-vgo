package be.khleuven.vgo.db;

import java.io.File;
import java.util.List;

public interface Reader {
	public List<String> readFromFile(File htmlpage) throws DatabaseException;
}
