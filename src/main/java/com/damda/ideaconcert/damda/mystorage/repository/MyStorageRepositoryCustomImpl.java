package com.damda.ideaconcert.damda.mystorage.repository;

import com.damda.ideaconcert.damda.mystorage.domain.MyStorage;
import com.damda.ideaconcert.damda.mystorage.domain.QMyStorage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.damda.ideaconcert.damda.mystorage.domain.QMyStorage.myStorage;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class MyStorageRepositoryCustomImpl implements MyStorageRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MyStorage> myStorageByList(int userId) {
        return jpaQueryFactory.select(new QMyStorage(myStorage))
                .from(myStorage)
                .where(myStorage.user_id.eq(userId),myStorage.delYn.eq("N"))
                .orderBy(myStorage.regdate.desc())
                .fetch();
    }

    @Override
    public MyStorage myStorageByIds(int myStorageNo) {
        return jpaQueryFactory.select(new QMyStorage(myStorage))
                .from(myStorage)
                .where(myStorage.no.eq(myStorageNo))
                .fetchOne();
    }
}
