package com.wissem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity(name = "Enrollment")
@Table(name = "enrollment")
@NoArgsConstructor
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

  @Column(
    name = "created_at",
    nullable = false,
    columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
  )
  private LocalDateTime createdAt;

  public Enrollment(EnrollmentId id ,Student student, Course course, LocalDateTime createdAt) {
    this.id = id;
    this.student = student;
    this.course = course;
    this.createdAt = createdAt;
  }

  public Enrollment(Student student, Course course, LocalDateTime createdAt) {
    this.student = student;
    this.course = course;
    this.createdAt = createdAt;
  }
}
