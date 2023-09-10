package com.lliscano.job;

import com.lliscano.one.entity.UsersOne;
import com.lliscano.one.repository.UsersOneRepository;
import com.lliscano.two.entity.UsersTwo;
import com.lliscano.two.repository.UsersTwoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
@Slf4j
public class BatchConfig {

    private final UsersOneRepository usersOneRepository;
    private final UsersTwoRepository usersTwoRepository;

    @Bean
    public RepositoryItemReader<UsersOne> usersRepositoryItemReader() {
        RepositoryItemReader<UsersOne> reader = new RepositoryItemReader<>();
        Map<String, Sort.Direction> sort = new HashMap<>();
        sort.put("id", Sort.Direction.ASC);
        reader.setRepository(usersOneRepository);
        reader.setMethodName("findAll");
        reader.setSort(sort);
        return reader;
    }
    @Bean
    public UsersProcessor processor() {
        return new UsersProcessor();
    }
    @Bean
    public RepositoryItemWriter<UsersTwo> usersTwoRepositoryItemWriter() {
        RepositoryItemWriter<UsersTwo> writer = new RepositoryItemWriter<>();
        writer.setRepository(usersTwoRepository);
        writer.setMethodName("save");
        return writer;
    }
    @Bean
    public Step importUsers(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("users-import",jobRepository)
                .<UsersOne, UsersTwo>chunk(1000,transactionManager)
                .reader(usersRepositoryItemReader())
                .processor(processor())
                .writer(usersTwoRepositoryItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job processJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("users", jobRepository)
                .flow(importUsers(jobRepository,transactionManager)).end().build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(1000);
        return asyncTaskExecutor;
    }

    /*@Bean
    public JobRepository jobRepository(@Qualifier("oneDataSource") DataSource dataSource,
                                       PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_DEFAULT");
        return jobRepositoryFactoryBean.getObject();
    }*/


}
