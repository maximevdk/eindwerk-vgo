package be.khleuven.vgo.db;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class PagesLoader {
	public List<String> getAllPages(String projectname){
		List<String> pages = new ArrayList<String>();
		
		
	    File dir = new File("Projects/"+projectname+"/pages");  
	    File[] files = dir.listFiles(new FileFilter() {  
	        public boolean accept(File filename) {  
	            return filename.isFile();  
	        }  
	    });  
	       
	    for (File file : files) {  
	    	if(file.getPath().contains(".html")){
	        pages.add(getProjectNameFromPath(file.getPath()));  
	    	}
	    }  
		
		return pages;
	}
	
	private String getProjectNameFromPath(String pathname){
		String [] tmp = pathname.split("/");
		return tmp[3];
	}
}
