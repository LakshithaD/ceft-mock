package com.example.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class    StudentStream {

    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

        List<Student> nameStartsWithA = list.stream()
                .filter((student -> student.getFirstName().startsWith("A")))
                .collect(Collectors.toList());
        System.out.println(nameStartsWithA);

        Map<String, List<Student>> groups = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName));
        System.out.println(groups);

        Long count = list.stream().count();
        System.out.println(count);

        int c = list.stream().mapToInt(Student::getAge).max().getAsInt();
        Student s = list.stream().min(Comparator.comparing(Student::getRank)).get();
        System.out.println();


        List<String> deps = list.stream().map(Student::getDepartmantName).distinct().collect(Collectors.toList());
        System.out.println(deps);

        Map<String, Long> ms = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
        System.out.println(ms);

        List<Student> students = list.stream().filter(student -> student.getAge() < 30).collect(Collectors.toList());
        System.out.println(students);

        list.stream().filter(std -> std.getRank() < 100 && std.getRank() > 50).collect(Collectors.toList());

        Map<String, Double> d = list.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        System.out.println(d);

        long v = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        System.out.println(v);

      //  list.stream().filter(s -> s.getCity().equalsIgnoreCase("Dii")).sorted(Comparator.comparing(Student::getFirstName));

        list.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getRank)));

       // list.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).().get();
    }


}


@AllArgsConstructor
@Getter
@Setter
@ToString
class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String departmantName;
    private int joinedYear;
    private String city;
    private int rank;
}