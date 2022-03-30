package org.sunshine.lc.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String args[]){
        List<Student> stuList = new ArrayList<>(10);
        stuList.add(new Student("刘一", 85));
        stuList.add(new Student("陈二", 90));
        stuList.add(new Student("张三", 98));
        stuList.add(new Student("李四", 88));
        stuList.add(new Student("王五", 83));
        stuList.add(new Student("赵六", 95));
        stuList.add(new Student("孙七", 87));
        stuList.add(new Student("周八", 84));
        stuList.add(new Student("吴九", 100));
        stuList.add(new Student("郑十十", 95));

        List<Student> stus = stuList.stream()
                .filter(stu -> stu.getScore() >= 90)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .collect(Collectors.toList());
        System.out.println(stus);

        List<Integer> names = stuList.stream()
                .map(Student::getName)
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(names);

        Optional<Integer> optional =  stuList.stream().map(Student::getScore).reduce((a, b) -> a + b);
        System.out.println(optional.get());

        Optional<Integer> optional1 =  stuList.stream().map(Student::getScore).reduce(Integer::max);
        Optional<Integer> optional2 = stuList.stream().map(Student::getScore).reduce(Integer::min);

        System.out.println(optional.get());
    }
}
