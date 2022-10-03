package ca.ericmarquis.gradingsystem.donnees;

import org.springframework.data.repository.CrudRepository;

import ca.ericmarquis.gradingsystem.modeles.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    
}
