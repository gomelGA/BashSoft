package main.bg.softuni.io.commands;

import main.bg.softuni.annotations.Alias;
import main.bg.softuni.annotations.Inject;
import main.bg.softuni.contracts.Database;
import main.bg.softuni.exceptions.InvalidInputException;

@Alias("filter")
public class PrintFilteredStudentsCommand extends Command {

    @Inject
    private Database repository;

    public PrintFilteredStudentsCommand(String input,
                                        String[] data){
        super(input, data);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 3 && data.length != 4) {
            throw new InvalidInputException(this.getInput());
        }

        String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            this.repository.filterAndTake(course, filter, null);
            return;
        }

        Integer numberOfStudents = Integer.valueOf(data[3]);

        if (data.length == 4) {
            this.repository.filterAndTake(course, filter, numberOfStudents);
        }
    }
}
