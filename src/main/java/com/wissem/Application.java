package com.wissem;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
    return args -> {
      Faker faker =  new Faker();

      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String email = String.format("%s.%s@gmail.com",firstName.toLowerCase(), lastName.toLowerCase());
      Student student = new Student(firstName, lastName, email, faker.number().numberBetween(15, 30));

      student.addBook(new Book("Clean Code", LocalDateTime.now().minusDays(4)));
      student.addBook(new Book("Think and Grow Rich", LocalDateTime.now()));
      student.addBook(new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

      StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
      student.setStudentIdCard(studentIdCard);

//      student.enrollToCourse(new Course("Computer Science", "IT"));
//      student.enrollToCourse(new Course("Spring Data JPA", "IT"));

      student.addEnrollment(new Enrollment(
        new EnrollmentId(1L, 1L),
        student,
        new Course("Computer Science", "IT")
      ));

      student.addEnrollment(new Enrollment(
        new EnrollmentId(1L, 2L),
        student,
        new Course("Spring Data JPA", "IT")
      ));

      studentRepository.save(student);

      studentRepository.findById(1L)
        .ifPresent(s -> {
          System.out.println("Fetch books lazy...");
          List<Book> books = student.getBooks();
          books.forEach(book -> {
            System.out.println(s.getFirstName() + " Borrowed " + book.getBookName());
          });
        });

    };
  }

  private void generateRandomStudents(StudentRepository studentRepository) {
    Faker faker =  new Faker();
    for (int i = 0; i < 20; i++) {
      String firstName = faker.name().firstName();
      String lastName = faker.name().lastName();
      String email = String.format("%s.%s@gmail.com",firstName.toLowerCase(), lastName.toLowerCase());
      Student student = new Student(firstName, lastName, email, faker.number().numberBetween(15, 30));
      studentRepository.save(student);
    }
  }

}
