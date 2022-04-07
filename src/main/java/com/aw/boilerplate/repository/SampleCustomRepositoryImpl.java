package com.aw.boilerplate.repository;

import com.aw.boilerplate.entity.QSample;
import com.aw.boilerplate.entity.Sample;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import java.util.List;

public class SampleCustomRepositoryImpl implements SampleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SampleCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Sample> findSampleByName(String name) {
        QSample qSample = QSample.sample;
        JPAQuery<Sample> query = jpaQueryFactory.selectFrom(qSample);

        if(name != null) {
            query.where(qSample.name.eq(name));
        }

        return query.fetch();
    }

    @Override
    public Long updateSampleName(Long sampleId, String sampleName) {
        QSample qSample = QSample.sample;
        JPAUpdateClause update = jpaQueryFactory.update(qSample);

        update.set(qSample.name, sampleName)
                .where(qSample.id.eq(sampleId))
                .execute();

        return update.set(qSample.name, sampleName)
                .where(qSample.id.eq(sampleId))
                .execute();
    }
}
