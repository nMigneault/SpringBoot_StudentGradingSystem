package ca.ericmarquis.gradingsystem.modeles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GradeSys_Grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_grade")
    @SequenceGenerator(name = "gen_grade", sequenceName = "SEQ_GRADE", allocationSize = 1)
    @Column(name = "grade_id")
    private Long gradeId;

    @ManyToOne()
    private Student student;

    @ManyToOne()
    private Course course;

    @Column(name = "mark")
    private int mark;

    public Grade() {
    }

    public Grade(Student student, Course course, int mark) {
        this.student = student;
        this.course = course;
        this.mark = mark;
    }

    public Grade(Long gradeId, Student student, Course course, int mark) {
        this.gradeId = gradeId;
        this.student = student;
        this.course = course;
        this.mark = mark;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Grade{" + "gradeId=" + gradeId + ", student=" + student + ", course=" + course + ", mark=" + mark + '}';
    }

}
