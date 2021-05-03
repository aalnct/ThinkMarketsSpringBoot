package com.thinkmarkets.ThinkMarketsSpringBoot.batchconfiguration;

import com.thinkmarkets.ThinkMarketsSpringBoot.data.MatchInput;
import com.thinkmarkets.ThinkMarketsSpringBoot.entity.Match;
import com.thinkmarkets.ThinkMarketsSpringBoot.processor.MatchDataProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    private final String[] FIELD = new String[] {
            "id",
            "city",
            "date",
            "player_of_match",
            "venue",
            "neutral_venue",
            "team1",
            "team2",
            "toss_winner",
            "toss_decision",
            "winner",
            "result",
            "result_margin",
            "eliminator",
            "method",
            "umpire1",
            "umpire2"
    };

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("matchItemReader")
                .resource(new ClassPathResource("match-data.csv"))
                .delimited()
                .names(FIELD)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {{
                    setTargetType(MatchInput.class);
                }})
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new
                        BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match_details (" +
                        "id," +
                        "city," +
                        "match_date," +
                        "player_Of_Match," +
                        "venue," +
                        "team1," +
                        "team2,\n" +
                        "toss_Winner,\n" +
                        "toss_Decision,\n" +
                        "match_Winner,\n" +
                        "result,\n" +
                        "result_Margin,\n" +
                        "umpire1,\n" +
                        "umpire2)" +

                        "VALUES (:id," +
                        ":city," +
                        ":date," +
                        ":playerOfMatch," +
                        ":venue," +
                        ":team1," +
                        ":team2," +
                        ":tossWinner," +
                        ":tossDecision, :matchWinner, :result," +
                        ":resultMargin, :umpire1, :umpire2)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> matchWriter) {
        return stepBuilderFactory.get("step1")
                .<MatchInput, Match> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(matchWriter)
                .build();
    }
}
