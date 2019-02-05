package de.lathspell.test.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.lathspell.test.model.Routing;

@Repository
public interface RoutingRepository extends JpaRepository<Routing, Integer> {

}
