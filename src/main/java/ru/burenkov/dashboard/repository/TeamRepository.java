package ru.burenkov.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import ru.burenkov.dashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team,Long> {
    Team findByTeamName(String teamName);
}
