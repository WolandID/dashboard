package ru.burenkov.dashboard.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.burenkov.dashboard.model.Match;


import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {

    List<Match> getByHomeTeamOrAwayTeamOrderByDateDesc(String homeTeam, String awayTeam, Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName,int count){
        return getByHomeTeamOrAwayTeamOrderByDateDesc(teamName,teamName,PageRequest.of(0,count));
    }
}
