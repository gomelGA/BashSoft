package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.DirectoryManager;
import main.bg.softuni.exceptions.InvalidInputException;

@Alias("cdrel")
public class ChangeRelativePathCommand extends Command {

    @Inject
    private DirectoryManager ioManager;

    public ChangeRelativePathCommand(String input,
                                     String[] data) {
        super(input, data);
    }

//    public ChangeRelativePathCommand(String input,
//                                     String[] data,
//                                     Database bg.softuni.repository,
//                                     ContentComparer tester,
//                                     DirectoryManager ioManager,
//                                     AsynchDownloader downloadManager) {
//        super(input, data, bg.softuni.repository, tester, ioManager, downloadManager);
//    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String relativePath = data[1];

        this.ioManager.changeCurrentDirRelativePath(relativePath);
    }
}

