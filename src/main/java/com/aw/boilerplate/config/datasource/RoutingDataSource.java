package com.aw.boilerplate.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final String WRITER = "WRITER";
    private static final String READER = "READER";

    public RoutingDataSource(DataSource writerDataSource, DataSource readerDataSource) {
        super();
        setDefaultTargetDataSource(readerDataSource);
        setTargetDataSources(targetDataSources(writerDataSource, readerDataSource));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        final Object dataSource = isCurrentTransactionReadOnly() ? READER : WRITER;
        log.debug("Determined connection: {}", dataSource);
        return dataSource;
    }

    private Map<Object, Object> targetDataSources(
            final DataSource writerDataSource,
            final DataSource readerDataSource
    ) {
        final Map<Object, Object> result = new HashMap<>();
        result.put(WRITER, writerDataSource);
        result.put(READER, readerDataSource);
        return result;
    }

}