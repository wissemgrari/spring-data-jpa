package com.wissem;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Book")
@Table(name = "book")
public class Book {

  @Id
  @SequenceGenerator(
    name = "book_sequence",
    sequenceName = "book_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = SEQUENCE,
    generator = "book_sequence"
  )
  @Column(
    name = "id",
    updatable = false
  )
  private Long id;

  @Column(
    name = "book_name",
    nullable = false
  )
  private String bookName;

  @Column(
    name = "created_at",
    nullable = false,
    columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
  )
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(
    name = "student_id",
    nullable = false,
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "student_book_fk")
  )
  private Student student;

  public Book(String bookName, LocalDateTime createdAt) {
    this.bookName = bookName;
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Book{" +
      "id=" + id +
      ", bookName='" + bookName + '\'' +
      ", createdAt=" + createdAt +
      ", student=" + student +
      '}';
  }
}
