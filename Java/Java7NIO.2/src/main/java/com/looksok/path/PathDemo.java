package com.looksok.path;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

    public void run() {
        //get current absolute path on default filesystem
        Path currentPath = Paths.get("").toAbsolutePath();
        System.out.println("Current working dir absolute path is: " + currentPath);

        //analyze linux path properties
        Path path = Paths.get("/var/lib/jenkins/jobs/sampleJob");
        System.out.println("\nSample linux path that will be analyzed: " + path.toAbsolutePath());
        System.out.println("Number of segments in path: " + path.getNameCount());
        System.out.println("Parent in path: " + path.getParent());
        System.out.println("Root on this filesystem: " + path.getRoot());
        System.out.println("Subpath (2 elements up): " + path.subpath(path.getNameCount() - 2, path.getNameCount()));

        //resolve symlink target file location
        try {
            Path symLinkPath = Paths.get("/var/log/logFile");
            System.out.println("\nReal symlink path: " + symLinkPath.toRealPath());
        } catch (IOException e) {
            System.out.println("\nFile does not exist: " + e.getMessage());
        }

        //Converting path: joining
        Path currentDir = Paths.get("");
        Path relativeConfigPath = Paths.get("conf/app.properties");
        Path propertiesFilesPath = currentDir.resolve(relativeConfigPath);
        System.out.println("\nComplete path after resolve(): " + propertiesFilesPath.toAbsolutePath());

        //get relative Path between two other Paths
        Path logsDir = Paths.get("/var/log/myapp");
        Path appDir = Paths.get("/var/lib/myapp");
        Path relativePathToLogs = appDir.relativize(logsDir);
        System.out.println("\nRelative path from app to logs: " + relativePathToLogs);

        //Interfacing to Java's existing File class
        File file = new File("./PathDemo.java");
        Path pathFromFile = file.toPath();
        System.out.println("\nPath created from file: " + pathFromFile.toAbsolutePath().normalize());
        file = pathFromFile.toFile();
        System.out.println("\nfile created from Path: " + file);
    }
}
