/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package security.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class UserRegisteredConverter implements ObjectConverter<security.UserRegistered> {

	@SuppressWarnings("unchecked")
	public UserRegisteredConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "UserRegistered_event".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(UserRegisteredConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<security.UserRegistered>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "_event_id".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find '_event_id' column in security UserRegistered_event. Check if DB is in sync");
		__index____event_id = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "QueuedAt".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'QueuedAt' column in security UserRegistered_event. Check if DB is in sync");
		__index___QueuedAt = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "ProcessedAt".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ProcessedAt' column in security UserRegistered_event. Check if DB is in sync");
		__index___ProcessedAt = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "username".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'username' column in security UserRegistered_event. Check if DB is in sync");
		__index___username = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		security.UserRegistered.__configureConverter(readers, __index____event_id, __index___QueuedAt, __index___ProcessedAt, __index___username);
		
		metamodel.registerDataSource(security.UserRegistered.class, "\"security\".\"UserRegistered_event\"");
		
		metamodel.registerProperty(security.UserRegistered.class, "getURI", "\"URI\"", security.UserRegistered::getURI);
		
		metamodel.registerProperty(security.UserRegistered.class, "getQueuedAt", "\"QueuedAt\"", security.UserRegistered::getQueuedAt);
		
		metamodel.registerProperty(security.UserRegistered.class, "getProcessedAt", "\"ProcessedAt\"", security.UserRegistered::getProcessedAt);
		
		container.register(security.repositories.UserRegisteredRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<security.UserRegistered>>(){}.type, security.repositories.UserRegisteredRepository::new, false);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<security.UserRegistered>>(){}.type, security.repositories.UserRegisteredRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<security.UserRegistered>>(){}.type, security.repositories.UserRegisteredRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventStore<security.UserRegistered>>(){}.type, security.repositories.UserRegisteredRepository::new, false);
		try {
			org.revenj.Revenj.registerEvents(container, plugins, security.UserRegistered.class, security.UserRegistered[].class);
		} catch (java.lang.Exception e) {
			throw new java.io.IOException(e);
		}
		
		metamodel.registerProperty(security.UserRegistered.class, "getUsername", "\"username\"", security.UserRegistered::getUsername);
	}

	@Override
	public String getDbName() {
		return "\"security\".\"UserRegistered_event\"";
	}

	@Override
	public security.UserRegistered from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private security.UserRegistered from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<security.UserRegistered>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		security.UserRegistered instance = new security.UserRegistered(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(security.UserRegistered instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index____event_id] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getURI());
		items[__index___QueuedAt] = org.revenj.database.postgres.converters.TimestampConverter.toTuple(instance.getQueuedAt());
		items[__index___ProcessedAt] = org.revenj.database.postgres.converters.TimestampConverter.toTuple(instance.getProcessedAt());
		items[__index___username] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getUsername());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<security.UserRegistered>[] readers;
	
	public security.UserRegistered from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.UserRegistered instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public security.UserRegistered from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	private final int __index____event_id;
	private final int __index___QueuedAt;
	private final int __index___ProcessedAt;
	private final int __index___username;
}
