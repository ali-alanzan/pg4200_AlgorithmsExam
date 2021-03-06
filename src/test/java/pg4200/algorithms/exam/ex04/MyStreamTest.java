package pg4200.algorithms.exam.ex04;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class MyStreamTest {
    @Test
    public void testCollect() {

        MyStreamCollectionList<Student> students = new MyStreamCollectionList<>();
        Student student = randomStudent();
        Student anotherStudent = randomStudent();
        Student thirdStudent = randomStudent();
        students.add(student);
        students.add(anotherStudent);
        students.add(thirdStudent);

        MyStreamCollectionList<Student> copy = students.stream().collectToList();

        assertEquals(students.size(), copy.size());

        for (int i = 0; i < students.size(); i++) {
            assertEquals(students.get(i), copy.get(i));
        }
    }


    @Test
    public void testForEach() {

        MyStreamCollectionList<Student> students = new MyStreamCollectionList<>();
        Student student = randomStudent();
        Student anotherStudent = randomStudent();
        Student thirdStudent = randomStudent();
        students.add(student);
        students.add(anotherStudent);
        students.add(thirdStudent);

//        MyStreamCollectionList<Student> students = new MyStreamCollectionList<>();
//
//        list.stream().forEach(s -> upper.add(s.toUpperCase()));
//
//        assertEquals(3, upper.size());
//        assertEquals("A", upper.get(0));
//        assertEquals("B", upper.get(1));
//        assertEquals("C", upper.get(2));
    }


    // ------------------------------------
    @Test
    public void testCollect1() {

        MyStreamCollectionList<String> list = new MyStreamCollectionList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        MyStreamCollectionList<String> copy = list.stream().collectToList();

        assertEquals(list.size(), copy.size());

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), copy.get(i));
        }
    }

    @Test
    public void testForEach1() {

        MyStreamCollectionList<String> list = new MyStreamCollectionList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        MyStreamCollectionList<String> upper = new MyStreamCollectionList<>();

        list.stream().forEach(s -> upper.add(s.toUpperCase()));

        assertEquals(3, upper.size());
        assertEquals("A", upper.get(0));
        assertEquals("B", upper.get(1));
        assertEquals("C", upper.get(2));
    }


    @Test
    public void testFilter() {

        MyStreamCollectionList<String> list = new MyStreamCollectionList<>();
        list.add("x");
        list.add("y");
        list.add("foo");
        list.add("bar");
        list.add("c");

        MyStreamCollectionList<String> longAtLeastThree = list.stream()
                .filter(s -> s.length() >= 3)
                .collectToList();

        assertEquals(2, longAtLeastThree.size());
        assertEquals("foo", longAtLeastThree.get(0));
        assertEquals("bar", longAtLeastThree.get(1));
    }

    private class User {
        public String name;
        public String surname;
        public String nationality;

        public User(String name, String surname, String nationality) {
            this.name = name;
            this.surname = surname;
            this.nationality = nationality;
        }
    }

    private MyStreamCollectionList<User> getSomeUsers() {
        MyStreamCollectionList<User> list = new MyStreamCollectionList<>();
        list.add(new User("John", "Smith", "USA"));
        list.add(new User("Foo", "Hansen", "Norway"));
        list.add(new User("Bar", "Olsen", "Norway"));
        list.add(new User("Marco", "Rossi", "Italy"));
        return list;
    }

    @Test
    public void testMap() {

        MyStreamCollectionList<User> list = getSomeUsers();

        MyStreamCollectionList<String> names = list.stream()
                .map(u -> u.name)
                .collectToList();

        assertEquals(4, names.size());
        assertEquals("John", names.get(0));
        assertEquals("Foo", names.get(1));
        assertEquals("Bar", names.get(2));
        assertEquals("Marco", names.get(3));
    }


    @Test
    public void testFilteredMap() {

        MyStreamCollectionList<User> list = getSomeUsers();

        MyStreamCollectionList<String> names = list.stream()
                .filter(u -> u.nationality.equalsIgnoreCase("norway"))
                .map(u -> u.name)
                .collectToList();

        assertEquals(2, names.size());
        assertEquals("Foo", names.get(0));
        assertEquals("Bar", names.get(1));
    }

    @Test
    public void testFilteredMapWithoutStream() {

        MyStreamCollectionList<User> list = getSomeUsers();

        MyStreamCollectionList<String> names = new MyStreamCollectionList();
        for (User user : list) {
            if (!user.nationality.equalsIgnoreCase("norway")) {
                continue;
            }
            names.add(user.name);
        }

        assertEquals(2, names.size());
        assertEquals("Foo", names.get(0));
        assertEquals("Bar", names.get(1));
    }


    @Test
    public void testFlatMap() {

        MyStreamCollectionHashMap<String, MyStreamCollectionList<User>> usersByNationality = new MyStreamCollectionHashMap<>();

        MyStreamCollectionList<User> norway = new MyStreamCollectionList<>();
        norway.add(new User("Foo", "Hansen", "Norway"));
        norway.add(new User("Bar", "Olsen", "Norway"));

        MyStreamCollectionList<User> usa = new MyStreamCollectionList<>();
        usa.add(new User("John", "Smith", "USA"));
        usa.add(new User("Robert", "Black", "USA"));

        MyStreamCollectionList<User> italy = new MyStreamCollectionList<>();
        italy.add(new User("Marco", "Rossi", "Italy"));

        usersByNationality.put("Norway", norway);
        usersByNationality.put("USA", usa);
        usersByNationality.put("Italy", italy);

        /*
            Now let's get surname from all countries
         */

        MyStreamCollectionList<String> surnames = usersByNationality.stream()
                .flatMap(l -> l.stream())
                .map(u -> u.surname)
                .collectToList();

        assertEquals(5, surnames.size());
        /*
            Note: due to iteration on a hash map,
            cannot guarantee the order of those surnames
            in the list
         */
        assertTrue(surnames.contains("Hansen"));
        assertTrue(surnames.contains("Olsen"));
        assertTrue(surnames.contains("Smith"));
        assertTrue(surnames.contains("Black"));
        assertTrue(surnames.contains("Rossi"));
    }

    private static Course randomCourse() {
        Course course = new Course();
        course.setCourse_code(pickOne("pg4200", "pg2400", "pg4200"));
        course.setEvaluation(pickOne("exam", "project"));
        course.setTopics(pickOne("programming", "Algorithms And Data Structure"));
        Map<Student, Integer> points= new HashMap<>();
        course.setPoints(points);
        return course;
    }

    private static Student randomStudent() {
        Student student = new Student();
        student.setStudent_id(Integer.parseInt(pickOne("1", "2", "3", "4", "5")));
        student.setName(pickOne("John", "Samar", "Sherry", "Antoni", "Lyz"));
        Map<String, Double> examPoints= new HashMap<>();
        examPoints.put("programming", 50d);
        examPoints.put("Front-end", 40d);
        examPoints.put("Algorithms And DataStructure", 50d);
        examPoints.put("pg4200", 45d);
        student.setExamPoints(examPoints);
        return student;
    }

    private static Random random = new Random();
    public static String pickOne(String... alternates) {
        return alternates[random.nextInt(alternates.length)];
    }
}