package ca.ericmarquis.gradingsystem.donnees;

import org.springframework.data.repository.CrudRepository;

import ca.ericmarquis.gradingsystem.modeles.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
    
}
