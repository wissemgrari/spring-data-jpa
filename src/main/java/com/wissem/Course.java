package com.wissem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Course")
@Table(name = "course")
public class Course {

  @Id
  @SequenceGenerator(
    name = "course_sequence",
    sequenceName = "course_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = SEQUENCE,
    generator = "course_sequence"
  )
  @Column(
    name = "id",
    updatable = false
  )
  private Long id;

  @Column(
    name = "name",
    nullable = false,
    columnDefinition = "TEXT"
  )
  private String name;

  @Column(
    name = "department",
    nullable = false,
    columnDefinition = "TEXT"
  )
  private String department;

//  @ManyToMany(
//    mappedBy = "courses"
//  )
//  private List<Student> students = new ArrayList<>();

  @OneToMany(
    mappedBy = "course",
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
  )
  private List<Enrollment> enrollments;

  public void addEnrollment(Enrollment enrollment) {
    if(!enrollments.contains(enrollment)) {
      enrollments.add(enrollment);
    }
  }
  public void removeEnrollment(Enrollment enrollment) {
    enrollments.remove(enrollment);
  }

  public Course(String name, String department) {
    this.name = name;
    this.department = department;
  }

  @Override
  public String toString() {
    return "Course{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", department='" + department + '\'' +
      '}';
  }
}
