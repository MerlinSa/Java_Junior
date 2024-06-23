package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Homework testing;
        int[] array = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000))
                .map(it -> it + 1)
                .map(it -> it * 5)
                .filter(it -> it > 10)
                .limit(50)
                .toArray();


        List<Homework_1.Department> departments = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            departments.add(new Homework_1.Department("Department #" + i));
        }

        List<Homework_1.Person> persons = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            int randomDepartmentIndex = ThreadLocalRandom.current().nextInt(departments.size());
            Homework_1.Department department = departments.get(randomDepartmentIndex);

            Homework_1.Person person = new Homework_1.Person();
            person.setName("Employee #" + i);
            person.setAge(ThreadLocalRandom.current().nextInt(30, 60));
            person.setSalary(ThreadLocalRandom.current().nextInt(30_000, 100_000) * 1.0);
            person.setDepartment(department);

            persons.add(person);
        }
        AtomicInteger counter = new AtomicInteger(1);
        persons.parallelStream().forEach(s->{
            System.out.print(s.toString());
            if (counter.get() %3==0) System.out.println();
            counter.getAndIncrement();
        });
        System.out.println();
        System.out.println("*******Количество  сотрудников, старше 35 лет с зарплатой больше, чем 50k ***********");
        System.out.println(Homework_1.countPersons(persons,35,50000));
        System.out.println();
        System.out.println("******* средняя зп ***********");
        System.out.println(Homework_1.averageSalary(persons, 7));
        System.out.println();
        System.out.println("groupByDepartment");
        Homework_1.groupByDepartment(persons).entrySet().stream()
                .forEach(System.out::println);
        System.out.println("**********************");
        System.out.println("~~~~~maxSalaryByDepartment~~~~~~~~~~~~~~~~");
        System.out.println(Homework_1.maxSalaryByDepartment(persons));
        System.out.println();
        System.out.println("~~~~~groupPersonNamesByDepartment~~~~~~~~~~~~~~~~");
        System.out.println(Homework_1.groupPersonNamesByDepartment(persons));
        System.out.println();
        System.out.println("~~~~~minSalaryPersons~~~~~~~~~~~~~~~~");
        System.out.println(Homework_1.minSalaryPersons(persons));
    }

}