package com.looksok.path;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

    public void run() {
        //get current absolute path on default filesystem
        Path path = Paths.get("").toAbsolutePath();
        System.out.println("Current working dir absolute path is: " + path);

        //analyze linux path properties
        path = Paths.get("/var/lib/jenkins/jobs/sampleJob");
        System.out.println("\nSample linux path that will be analyzed: " + path.toAbsolutePath());
        System.out.println("Number of segments in path: " + path.getNameCount());
        System.out.println("Parent in path: " + path.getParent());
        System.out.println("Root on this filesystem: " + path.getRoot());
        System.out.println("Subpath (2 elements up): " + path.subpath(path.getNameCount() - 2, path.getNameCount()));

        //resolve symlink target file location
        try {
            path = Paths.get("/var/log/logFile");
            System.out.println("\nReal symlink path: " + path.toRealPath());
        } catch (IOException e) {
            System.out.println("\nFile does not exist: " + path);
        }

        //Converting path: joining
        Path currentDir = Paths.get("");
        Path relativeConfigPath = Paths.get("conf/app.properties");
        Path propertiesFilesPath = currentDir.resolve(relativeConfigPath);
        System.out.println("\nComplete path after resolve(): " + propertiesFilesPath.toAbsolutePath());
    }
}
