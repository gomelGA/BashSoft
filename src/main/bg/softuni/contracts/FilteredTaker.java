package main.bg.softuni.contracts;

public interface FilteredTaker {

    void filterAndTake(String courseName, String filter, Integer studentsToTake);
}
