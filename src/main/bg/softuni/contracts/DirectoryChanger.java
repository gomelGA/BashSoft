package main.bg.softuni.contracts;

import java.io.IOException;

public interface DirectoryChanger {

    void changeCurrentDirRelativePath(String relativePath) throws IOException;

    void changeCurrentDirAbsolute(String absolutePath) throws IOException;
}
