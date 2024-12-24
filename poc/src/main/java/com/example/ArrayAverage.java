package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

public class ArrayAverage {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,4,5,37,86,3,23,56,75);
        double average = nums.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);
        System.out.println(average);

        List<Student> students = Arrays.asList(new Student("Kamal",25,68),
                new Student("Kamal",25,68),
                new Student("Kamal",30,68),
                new Student("Kamal",35,68));

        double ma = students.stream().map((std) -> std.getEnglishMarks()).mapToDouble(Integer::doubleValue).average().orElse(24.5);
        System.out.println(ma);
    }


}


@AllArgsConstructor
@Getter
@Setter
@ToString
class Student {
    String name;

    Integer englishMarks;

    Integer sinhalaMarks;


}
