package com.wissem;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Student")
@Table(
  name = "student",
  uniqueConstraints = {
    @UniqueConstraint(name = "student_email_unique", columnNames = "email")
  }
)
public class Student {
  @Id
  @SequenceGenerator(
    name = "student_sequence",
    sequenceName = "student_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "student_sequence"
  )
  @Column(
    name = "id",
    updatable = false
  )
  private Long id;

  @Column(
    name = "first_name",
    nullable = false,
    columnDefinition = "TEXT"
  )
  private String firstName;

  @Column(
    name = "last_name",
    nullable = false,
    columnDefinition = "TEXT"
  )
  private String lastName;

  @Column(
    name = "email",
    nullable = false,
    columnDefinition = "TEXT"
  )
  private String email;

  @Column(
    name = "age",
    nullable = false
  )
  private Integer age;

  @OneToOne(
    mappedBy = "student",
    orphanRemoval = true,
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
  )
  private StudentIdCard studentIdCard;

  @OneToMany(
    mappedBy = "student",
    orphanRemoval = true,
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
    fetch = FetchType.LAZY // Default
  )
  private List<Book> books = new ArrayList<>();

//  @ManyToMany(
//    cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//    fetch = FetchType.LAZY
//  )
//  @JoinTable(
//    name = "enrollment",
//    joinColumns = @JoinColumn(
//      name = "student_id",
//      foreignKey = @ForeignKey(name = "enrollment_student_id_fk")
//    ),
//    inverseJoinColumns = @JoinColumn(
//      name = "course_id",
//      foreignKey = @ForeignKey(name = "enrollment_course_id_fk")
//    )
//  )
//  private List<Course> courses = new ArrayList<>();

  @OneToMany(
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
    mappedBy = "student"
  )
  private List<Enrollment> enrollments = new ArrayList<>();

  public void addBook(Book book) {
    if(!this.books.contains(book)) {
      this.books.add(book);
      book.setStudent(this);
    }
  }

  public void removeBook(Book book) {
    if(this.books.contains(book)){
      this.books.remove(book);
      book.setStudent(null);
    }
  }

//  public void enrollToCourse(Course course) {
//    courses.add(course);
//    course.getStudents().add(this);
//  }
//
//  public void unEnrollFromCourse(Course course) {
//    courses.remove(course);
//    course.getStudents().remove(this);
//  }

  public void addEnrollment(Enrollment enrollment) {
    if(!enrollments.contains(enrollment)) {
      enrollments.add(enrollment);
    }
  }
  public void removeEnrollment(Enrollment enrollment) {
   enrollments.remove(enrollment);
  }

  public Student(String firstName, String lastName, String email, Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", age=" + age +
      '}';
  }
}
