package ru.burenkov.dashboard.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import ru.burenkov.dashboard.model.Match;

import java.time.LocalDate;



public class MatchDataProcessor implements ItemProcessor<MatchData, Match> {
    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);



    @Override
    public Match process(final MatchData matchData) throws Exception {

        Match match = new Match();
        match.setId(Long.parseLong(matchData.getId()));
        match.setDate(LocalDate.parse(matchData.getDateTime()));
        match.setSeason(matchData.getSeason());
        match.setHomeTeam(matchData.getHomeTeam());
        match.setAwayTeam(matchData.getAwayTeam());

        String winner;
        if("H".equals(matchData.getFTR())) winner = matchData.getHomeTeam();
        else if("A".equals(matchData.getFTR())) winner = matchData.getAwayTeam();
        else winner = "Draft";
        match.setWinner(winner);

        match.setHomeGoal(Integer.parseInt(matchData.getFTHG()));
        match.setAwayGoal(Integer.parseInt(matchData.getFTAG()));

        return match;
    }

}


