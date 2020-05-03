package be.khleuven.vgo.db;

import be.khleuven.vgo.domain.DomainException;
import be.khleuven.vgo.domain.Project;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class ProjectLoader {
    public List<Project> getAllProjects() throws DomainException, DatabaseException {
        List<Project> projects = new ArrayList<Project>();


        File dir = new File("Projects");

        File[] subDirs = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        for (File subDir : subDirs) {
            String projectname = getProjectNameFromPath(subDir.getPath());
            String[] tmp = projectname.split("-");
            try {
                Project p = new Project(tmp[0], tmp[1]);
                projects.add(p);
            } catch (Exception e) {

                subDir.delete();
            }
            {

            }
        }

        return projects;
    }

    private String getProjectNameFromPath(String pathname) {
        String[] tmp = pathname.split("/");
        return tmp[1];
    }

}
