package ex02;

import java.io.IOException;
import java.nio.file.*;

public class FileUtility {
    private Path currentDirectory;

    public FileUtility(String path) {
        Path dir = Paths.get(path);
        if (Files.isDirectory(dir)) {
            currentDirectory = dir;
        } else {
            throw new RuntimeException("The entered path is not a directory");
        }
    }

    void commandLS() throws IOException {
        DirectoryStream<Path> fileAndFolders = Files.newDirectoryStream(currentDirectory);

        for (Path path : fileAndFolders) {
            System.out.println(path.getFileName() + " " + (Files.size(path) / 1024) + " KB");
        }
    }

    void commandCD(String path) {
        Path cdPath = Paths.get(path);
        cdPath = currentDirectory.resolve(cdPath).normalize();
        if (Files.isDirectory(cdPath)) {
            currentDirectory = cdPath;
            System.out.println(currentDirectory);
        } else {
            System.out.println("cd: not a directory: " + path);
        }
    }

    void commandMV(String arg1, String arg2) {
        Path replaceable = currentDirectory.resolve(arg1);
        Path replacing = currentDirectory.resolve(arg2);

        if (!Files.exists(replaceable)) {
            System.out.println("mv: rename " + replaceable.getFileName() + " to " + replacing.getFileName() + ": No such file or directory");
            return;
        }

        if (Files.isDirectory(replacing)) {
            replacing = replacing.resolve(replaceable.getFileName());
        }

        try {
            Files.move(replaceable, replacing, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void readCommand(String inputString) throws IOException {
        String[] array = inputString.split(" ");
        if (array.length == 0) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        try {
            switch (array[0]) {
                case "ls" -> commandLS();
                case "cd" -> commandCD(array[1]);
                case "mv" -> commandMV(array[1], array[2]);
                default -> System.out.println("command not found: " + array[0]);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Pass arguments to the command");
        }
    }
}
