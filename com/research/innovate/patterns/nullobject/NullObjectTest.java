package com.research.inniovate.microservice.template

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;
import com.research.innovate.patterns.nullobject.model.*
import com.research.innovate.patterns.nullobject.facade.Facade;

/**
 * @author cgordon
 * @created 03/05/2019
 * @version 1.0
 * 
 * Null Object design pattern implementation.
 *
 */

public class NullObjectTest {

    public static void main(String... args){

        Course course1 = new Course(10, "basics 101", "2019");
        Course course2 = new Course(11, "intermediate 102", "2019");
        Course course3 = new Course(12, "advanced 103", "2019");

        Grade grade1 = new Grade(100, course1, 75);
        Grade grade2 = new Grade(101, course2, 85);
        Grade grade3 = new Grade(102, course3, 80);
        Grade grade0 = new Grade(0, null, null);

        Student student = new Student(1, "Jimmy Hoffa", Facade.streamToSet(Arrays.asList(grade1, grade2,grade3).stream()));
        Teacher teacher = new Teacher(10000, "Lex Luthior", Facade.streamToSet(Arrays.asList(course1, course2, course3).stream()));
        Student applicant = new Student(15, "Green Horn", Stream.of(grade0).collect(Collectors.toCollection(HashSet<Grade>::new)));

        List<Student> students = Arrays.asList(student,applicant);
        List<Teacher> staff = Arrays.asList(teacher);

        Student found = (Student)Facade.findStudent((Facade.convertEntityList(students,Entity.class)).stream(),1923);
    }
}
