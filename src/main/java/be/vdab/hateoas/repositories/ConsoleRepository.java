package be.vdab.hateoas.repositories;

import be.vdab.hateoas.domain.Console;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsoleRepository extends JpaRepository<Console, Long> {
}
