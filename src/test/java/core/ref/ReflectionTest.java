package core.ref;

import jwp.model.Question;
import jwp.model.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTest {

    @Test
    void showClass() {
        Class<Question> clazz = Question.class;
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));
        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println(Arrays.toString(clazz.getDeclaredConstructors()));
    }

    @Test
    public void newInstanceWithConstructorArgs() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<User> clazz = User.class;
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor:
                constructors) {
            User expected = new User("userId", "password", "name", "javajigi@email.com");
            Object user = constructor.newInstance(expected.getUserId(), expected.getPassword(), expected.getName(), expected.getEmail());
            User actual = (User) user;
            assertEquals(expected,actual);
        }
    }

    @Test
    public void privateFieldAccess() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> clazz = Student.class;
        Student student = new Student();
        Field name = clazz.getDeclaredField("name");
        Field age = clazz.getDeclaredField("age");
        name.setAccessible(true);
        age.setAccessible(true);
        name.set(student,"학생");
        age.set(student,25);

        assertEquals("학생",student.getName());
        assertEquals(25,student.getAge());
    }

}
