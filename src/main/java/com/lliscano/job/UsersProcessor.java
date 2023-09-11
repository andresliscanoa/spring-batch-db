package com.lliscano.job;


import com.lliscano.databases.one.entity.UsersOne;
import com.lliscano.databases.two.entity.UsersTwo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class UsersProcessor implements ItemProcessor<UsersOne, UsersTwo> {
    @Override
    public UsersTwo process(UsersOne item) throws Exception {
        log.debug("ITEM: {}",item);
        final UsersTwo userTwo = new UsersTwo();
        userTwo.setFullName(item.getFirstname().toUpperCase()+" "+item.getLastname().toUpperCase());
        userTwo.setGender(item.getGender());
        log.debug("UserTwo: {}",userTwo);
        return userTwo;
    }
}
