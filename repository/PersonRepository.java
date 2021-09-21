package edu.mum.apointementsystem.repository;

import edu.mum.apointementsystem.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person , Integer> {
}
