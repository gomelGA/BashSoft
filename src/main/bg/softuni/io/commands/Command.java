package main.bg.softuni.io.commands;

import main.bg.softuni.contracts.Executable;
import main.bg.softuni.exceptions.InvalidInputException;

public abstract class Command implements Executable {

    private String input;
    private String[] data;

//    private Database bg.softuni.repository;
//    private ContentComparer tester;
//    private DirectoryManager ioManager;
//    private AsynchDownloader downloadManager;

    protected Command(String input, String[] data) {
        this.setInput(input);
        this.setData(data);
    }

//    protected Command(String input,
//                      String[] data,
//                      Database bg.softuni.repository,
//                      ContentComparer tester,
//                      DirectoryManager ioManager,
//                      AsynchDownloader downloadManager) {
//        this.setInput(input);
//        this.setData(data);
//        this.bg.softuni.repository = bg.softuni.repository;
//        this.tester = tester;
//        this.ioManager = ioManager;
//        this.downloadManager = downloadManager;
//    }

    protected String getInput() {
        return this.input;
    }

    protected String[] getData() {
        return this.data;
    }

//    protected Database getRepository() {
//        return this.bg.softuni.repository;
//    }
//
//    protected ContentComparer getTester() {
//        return this.tester;
//    }
//
//    protected DirectoryManager getIoManager() {
//        return this.ioManager;
//    }
//
//    protected AsynchDownloader getDownloadManager() {
//        return this.downloadManager;
//    }

    private void setInput(String input) {
        if (input == null || input.equals("")) {
            throw new InvalidInputException(this.getInput());
        }
        this.input = input;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidInputException(this.getInput());
        }
        this.data = data;
    }

    public abstract void execute() throws Exception;
}
