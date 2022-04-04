package ru.burenkov.dashboard.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.burenkov.dashboard.model.Team;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamMap = new HashMap<>();
            entityManager.createQuery("select m.homeTeam,count(m.homeTeam) from Match m group by m.homeTeam",Object[].class)
                    .getResultList()
                    .stream()
                    .map(e-> new Team((String)e[0],(long)e[1]))
                    .forEach(team -> teamMap.put(team.getTeamName(),team));

            entityManager.createQuery("select m.awayTeam,count(m.awayTeam) from Match m group by m.awayTeam",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team = teamMap.get((String)e[0]);
                        team.setTotalMatches(team.getTotalMatches()+(long)e[1]);
                    });
            entityManager.createQuery("select m.winner,count(m.winner) from Match m group by m.winner",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team = teamMap.get((String)e[0]);
                        if(team != null) team.setTotalWins((long)e[1]);
                    });
            teamMap.values().forEach(team -> entityManager.persist(team));
            teamMap.values().forEach(team -> System.out.println(team));




//            jdbcTemplate.query("SELECT home_team, away_team, winner FROM match",
//                    (rs, row) -> "HomeTeam:" + rs.getString(1) + " AwayTeam:" + rs.getString(2) +
//                            " Winner:" + rs.getString(3)
//            ).forEach(str -> System.out.println(str));
       }
    }
}
