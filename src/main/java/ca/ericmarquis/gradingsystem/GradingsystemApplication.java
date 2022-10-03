package ca.ericmarquis.gradingsystem;

import ca.ericmarquis.gradingsystem.donnees.StudentRepository;
import ca.ericmarquis.gradingsystem.donnees.CourseRepository;
import ca.ericmarquis.gradingsystem.donnees.GradeRepository;
import ca.ericmarquis.gradingsystem.modeles.Student;
import ca.ericmarquis.gradingsystem.modeles.Course;
import ca.ericmarquis.gradingsystem.modeles.Grade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GradingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradingsystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner data_loader(StudentRepository repo_student, CourseRepository repo_course,
										  GradeRepository repo_grade) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				Student s1 = new Student("Eric", "Marquis", "3208 Joly", "Sorel-Tracy");
				Student s2 = new Student("Natacha", "Migneault", "123 ma rue", "Verdun");
				
				repo_student.save(s1);
				repo_student.save(s2);

				Course c1 = new Course("Programmation Java", 12);
				Course c2 = new Course("PL/SQL", 15);
				Course c3 = new Course("Qualité des applications", 15);

				c1 = repo_course.save(c1);
				c2 = repo_course.save(c2);
				c3 = repo_course.save(c3);

				repo_student.save(s1);
				repo_student.save(s2);

				s1.addCourse(c1);
				s2.addCourse(c2);
				s2.addCourse(c3);

				repo_student.save(s1);
				repo_student.save(s2);

				Grade g1 = new Grade(s1, c1, 95);
				Grade g2 = new Grade(s2, c2, 99);
				Grade g3 = new Grade(s1, c3, 88);

				repo_grade.save(g1);
				repo_grade.save(g2);
				repo_grade.save(g3);
			}
		};
	}
}