package ca.ericmarquis.gradingsystem.donnees;

import org.springframework.data.repository.CrudRepository;

import ca.ericmarquis.gradingsystem.modeles.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    
}
