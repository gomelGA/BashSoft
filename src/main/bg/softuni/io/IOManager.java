package main.bg.softuni.io;

import main.bg.softuni.contracts.DirectoryManager;
import main.bg.softuni.staticData.SessionData;
import main.bg.softuni.exceptions.InvalidFileNameException;
import main.bg.softuni.exceptions.InvalidPathException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class IOManager implements DirectoryManager {

    public void traverseDirectory(int depth) {
        LinkedList<File> subFolders = new LinkedList<File>();

        String path = SessionData.currentPath;
        int initialIndentation = path.split("\\\\").length;

        try {
            File root = new File(path);
            subFolders.add(root);

            while (subFolders.size() != 0) {
                File currentFolder = subFolders.removeFirst();
                int currentIndentation = currentFolder.toString().split("\\\\").length - initialIndentation;
                File[] files = currentFolder.listFiles();

                if (depth - currentIndentation < 0) {
                    break;
                }

                OutputWriter.writeMessageOnNewLine(currentFolder.toString());

                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            subFolders.add(file);
                        } else {
                            int indexOfLastSlash = file.toString().lastIndexOf("\\");
                            for (int i = 0; i < indexOfLastSlash; i++) {
                                OutputWriter.writeMessage("-");
                            }

                            OutputWriter.writeMessageOnNewLine(file.getName());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Access denied");
        }
    }

    public void createDirectoryInCurrentFolder(String name) {
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        boolean wasDirMade = file.mkdir();
        if (!wasDirMade) {
            throw new InvalidFileNameException();
        }
    }

    public static String getCurrentDirectoryPath() {
        String currentPath = SessionData.currentPath;
        return currentPath;
    }

    public void changeCurrentDirRelativePath(String relativePath) throws IOException {
        if (relativePath.equals("..")) {
            try {
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.lastIndexOf("\\");
                String newPath = currentPath.substring(0, indexOfLastSlash);
                SessionData.currentPath = newPath;
            } catch (InvalidPathException ipe) {
                throw new InvalidPathException();
            }
        } else {
            String currentPath = SessionData.currentPath;
            currentPath += "\\" + relativePath;
            changeCurrentDirAbsolute(currentPath);
        }
    }

    public void changeCurrentDirAbsolute(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new InvalidPathException();
        }

        SessionData.currentPath = absolutePath;
    }
}
