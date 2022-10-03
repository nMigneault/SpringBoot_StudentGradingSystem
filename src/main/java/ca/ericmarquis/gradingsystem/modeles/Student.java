package ca.ericmarquis.gradingsystem.modeles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "GradeSys_Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_student")
    @SequenceGenerator(name = "gen_student", sequenceName = "SEQ_STUDENT", allocationSize = 1)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "GradeSysStudentCourse",
            joinColumns = {
                @JoinColumn(name = "studentId")},
            inverseJoinColumns = {
                @JoinColumn(name = "courseId")}
    )
    private Set<Course> courses;

    public Student() {
    }

    public Student(Long studentId, String firstName, String lastName, String address, String city) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }

    public Student(String firstName, String lastName, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.courses = new HashSet<Course>();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
}