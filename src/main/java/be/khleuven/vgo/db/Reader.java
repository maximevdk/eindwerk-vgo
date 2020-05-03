package be.khleuven.vgo.db;

import java.io.File;
import java.util.List;

public interface Reader {
    List<String> readFromFile(File htmlpage) throws DatabaseException;
}
