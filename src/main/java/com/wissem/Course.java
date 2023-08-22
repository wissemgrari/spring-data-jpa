package com.wissem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Course")
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
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
