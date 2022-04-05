package ru.burenkov.dashboard.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.burenkov.dashboard.model.Match;


import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {

    List<Match> getByHomeTeamOrAwayTeamOrderByDateDesc(String homeTeam, String awayTeam, Pageable pageable);
    List<Match> getByHomeTeamAndDateBetweenOrAwayTeamAndDateBetweenOrderByDateDesc(String homeTeam,
                                                                                   LocalDate startDate1,
                                                                                   LocalDate endDate1,
                                                                                   String awayTeam,
                                                                                   LocalDate startDate2,
                                                                                   LocalDate endDate2);

    default List<Match> findLatestMatchesByTeam(String teamName,int count){
        return getByHomeTeamOrAwayTeamOrderByDateDesc(teamName,teamName,PageRequest.of(0,count));
    }

}
