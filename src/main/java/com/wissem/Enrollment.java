package com.wissem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Enrollment")
@Table(name = "enrollment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Enrollment {

  @EmbeddedId
  private EnrollmentId id;

  @ManyToOne
  @MapsId("studentId")
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @MapsId("courseId")
  @JoinColumn(name = "course_id")
  private Course course;

  public Enrollment(Student student, Course course) {
    this.student = student;
    this.course = course;
  }
}
