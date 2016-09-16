package test.bg.softuni.dataStructures;

import main.bg.softuni.contracts.SimpleOrderedBag;
import main.bg.softuni.dataStructures.SimpleSortedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SimpleOrderedBagTests {

    private SimpleOrderedBag<String> names;

    @Before
    public void setUp() {
        this.names = new SimpleSortedList<String>(String.class);
    }

    @Test
    public void testEmptyCtor() {
        this.names = new SimpleSortedList<String>(String.class);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInitialCapacity() {
        this.names = new SimpleSortedList<String>(String.class, 20);
        Assert.assertEquals(20, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithInitialComparer() {
        this.names = new SimpleSortedList<String>(String.class, String.CASE_INSENSITIVE_ORDER);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testCtorWithAllParams() {
        this.names = new SimpleSortedList<String>(String.class, String.CASE_INSENSITIVE_ORDER, 30);
        Assert.assertEquals(30, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testAddIncreasesSize() {
        this.names.add("Nasko");
        Assert.assertEquals(1, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullThrowsException() {
        this.names.add(null);
    }

    @Test
    public void testAddUnsortedDataIsHeldSorted() {
        String[] elements = {"Rosen", "Georgi", "Balkan"};
        Arrays.sort(elements);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
        }

        StringBuilder output = new StringBuilder();

        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");

        Iterator<String> iterator = this.names.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();
            for (String element : elements) {
                if (name.equals(element)) {
                    output.append(element);
                }
            }
        }

        Assert.assertEquals(sb.toString(), output.toString());
    }

    @Test
    public void testAddingMoreThanInitialCapacity() {
        byte numberOfElements = 17;

        for (int i = 0; i < numberOfElements; i++) {
            this.names.add("Pesho");
        }

        Assert.assertEquals(17, this.names.size());
        Assert.assertNotEquals(16, this.names.capacity());
    }

    @Test
    public void testAddingAllFromCollectionIncreasesSize() {
        List<String> names = Arrays.asList("Pesho", "Gosho");

        this.names.addAll(names);

        Assert.assertEquals(2, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAllFromNullThrowsException() {
        this.names.addAll(null);
    }

    @Test
    public void testAddAllKeepsSorted() {
        List<String> elements = Arrays.asList("Rosen", "Georgi", "Balkan");
        String sortedElements = "BalkanGeorgiRosen";
        StringBuilder output = new StringBuilder();

        this.names.addAll(elements);
        for (String name : this.names) {
            output.append(name);
        }

        Assert.assertEquals(sortedElements, output.toString());
    }

    @Test
    public void testRemoveValidElementDecreasesSize() {
        this.names.add("Gosho");
        this.names.remove("Gosho");

        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testRemoveValidElementRemovesSelectedOne() {
        boolean isRemoved = true;

        this.names.add("Ivan");
        this.names.add("Nasko");
        this.names.remove("Ivan");
        Iterator<String> iterator = this.names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.equals("Ivan")) {
                isRemoved = false;
                break;
            }
        }

        Assert.assertEquals(true, isRemoved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNullThrowsException() {
        this.names.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJoinWithNull() {
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");

        this.names.joinWith(null);
    }

    @Test
    public void testJoinWorksFine() {
        String result = "Balkan,Georgi,Rosen";

        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        String output = this.names.joinWith(",");

        Assert.assertEquals(result, output);
    }
}

