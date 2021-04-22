package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.auth.domain.Person;

/**
 * Class PersonRepository
 *
 * @author Dmitry Razumov
 * @version 1
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
