/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ericmarquis.gradingsystem.modeles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GradeSys_Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_course")
    @SequenceGenerator(name = "gen_course", sequenceName = "SEQ_COURSE", allocationSize = 1)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "credit_number")
    private int creditNumber;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course() {
    }

    public Course(String courseName, int creditNumber) {
        this.courseName = courseName;
        this.creditNumber = creditNumber;
    }

    public Course(Long courseId, String courseName, int creditNumber) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditNumber = creditNumber;
        this.students = new HashSet<>();
    }

    public Course(Long courseId, String courseName, int creditNumber, Set<Student> students) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditNumber = creditNumber;
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", courseName=" + courseName + ", creditNumber=" + creditNumber + '}';
    }
}
