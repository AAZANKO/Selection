package by.auditsalution.selection.test;

import java.util.Arrays;
import java.util.List;

public class SortedDemo {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        integers.stream()
                .filter(it -> it % 2 != 0)
                .forEach(System.out::println);

    }

}
