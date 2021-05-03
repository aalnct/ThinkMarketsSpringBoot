package com.thinkmarkets.ThinkMarketsSpringBoot.batchconfiguration;

import com.thinkmarkets.ThinkMarketsSpringBoot.entity.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log =
            LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private EntityManager entityManager;

    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        Map<String, Team> teamMap = new HashMap<>();
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info(" Job Finished!!! Time to verify the result ... ");
            // team name and number of matches played by team as team 1
            this.entityManager.createQuery(
                    "select m.team1,count(m) from Match m" +
                            " group by m.team1", Object[].class
            ).getResultList().stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .forEach(team -> teamMap.put(team.getTeamName(), team));

            // for team 2
            this.entityManager.createQuery(
                    "select m.team2,count(m) from Match m" +
                            " group by m.team2", Object[].class
            ).getResultList()
            .stream()
            .forEach(e -> {
                Team team = teamMap.get((String) e[0]);
                team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
            });

            this.entityManager.createQuery(
                    "select m.matchWinner,count(m) from Match m" +
                            " group by m.matchWinner", Object[].class
            ).getResultList()
                    .stream()
                    .forEach(e -> {
                        Team team = teamMap.get((String) e[0]);
                        if (team!=null) team.setTotalWins((long) e[1]);
                    });

            teamMap.values().forEach(team -> entityManager.persist(team));

        }
    }
}
