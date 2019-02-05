package de.lathspell.test.db;

import de.lathspell.test.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilterRepository extends JpaRepository<Filter, Integer> {
}
