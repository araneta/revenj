/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package security.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class RegisterUserConverter implements ObjectConverter<security.RegisterUser> {

	@SuppressWarnings("unchecked")
	public RegisterUserConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "RegisterUser_event".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(RegisterUserConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<security.RegisterUser>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "_event_id".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find '_event_id' column in security RegisterUser_event. Check if DB is in sync");
		__index____event_id = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "QueuedAt".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'QueuedAt' column in security RegisterUser_event. Check if DB is in sync");
		__index___QueuedAt = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "ProcessedAt".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ProcessedAt' column in security RegisterUser_event. Check if DB is in sync");
		__index___ProcessedAt = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "username".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'username' column in security RegisterUser_event. Check if DB is in sync");
		__index___username = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "password".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'password' column in security RegisterUser_event. Check if DB is in sync");
		__index___password = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		security.RegisterUser.__configureConverter(readers, __index____event_id, __index___QueuedAt, __index___ProcessedAt, __index___username, __index___password);
		
		metamodel.registerDataSource(security.RegisterUser.class, "\"security\".\"RegisterUser_event\"");
		
		metamodel.registerProperty(security.RegisterUser.class, "getURI", "\"URI\"", security.RegisterUser::getURI);
		
		metamodel.registerProperty(security.RegisterUser.class, "getQueuedAt", "\"QueuedAt\"", security.RegisterUser::getQueuedAt);
		
		metamodel.registerProperty(security.RegisterUser.class, "getProcessedAt", "\"ProcessedAt\"", security.RegisterUser::getProcessedAt);
		
		container.register(security.repositories.RegisterUserRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<security.RegisterUser>>(){}.type, security.repositories.RegisterUserRepository::new, false);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<security.RegisterUser>>(){}.type, security.repositories.RegisterUserRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<security.RegisterUser>>(){}.type, security.repositories.RegisterUserRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventStore<security.RegisterUser>>(){}.type, security.repositories.RegisterUserRepository::new, false);
		try {
			org.revenj.Revenj.registerEvents(container, plugins, security.RegisterUser.class, security.RegisterUser[].class);
		} catch (java.lang.Exception e) {
			throw new java.io.IOException(e);
		}
		
		metamodel.registerProperty(security.RegisterUser.class, "getUsername", "\"username\"", security.RegisterUser::getUsername);
		
		metamodel.registerProperty(security.RegisterUser.class, "getPassword", "\"password\"", security.RegisterUser::getPassword);
	}

	@Override
	public String getDbName() {
		return "\"security\".\"RegisterUser_event\"";
	}

	@Override
	public security.RegisterUser from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private security.RegisterUser from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<security.RegisterUser>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		security.RegisterUser instance = new security.RegisterUser(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(security.RegisterUser instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index____event_id] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getURI());
		items[__index___QueuedAt] = org.revenj.database.postgres.converters.TimestampConverter.toTuple(instance.getQueuedAt());
		items[__index___ProcessedAt] = org.revenj.database.postgres.converters.TimestampConverter.toTuple(instance.getProcessedAt());
		items[__index___username] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getUsername());
		items[__index___password] = org.revenj.database.postgres.converters.ByteaConverter.toTuple(instance.getPassword());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<security.RegisterUser>[] readers;
	
	public security.RegisterUser from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.RegisterUser instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public security.RegisterUser from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	private final int __index____event_id;
	private final int __index___QueuedAt;
	private final int __index___ProcessedAt;
	private final int __index___username;
	private final int __index___password;
}
