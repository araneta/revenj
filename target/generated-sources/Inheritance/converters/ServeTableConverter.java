/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Inheritance.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class ServeTableConverter implements ObjectConverter<Inheritance.ServeTable> {

	@SuppressWarnings("unchecked")
	public ServeTableConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "ServeTable_event".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(ServeTableConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.ServeTable>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "_event_id".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find '_event_id' column in Inheritance ServeTable_event. Check if DB is in sync");
		__index____event_id = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "QueuedAt".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'QueuedAt' column in Inheritance ServeTable_event. Check if DB is in sync");
		__index___QueuedAt = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "ProcessedAt".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ProcessedAt' column in Inheritance ServeTable_event. Check if DB is in sync");
		__index___ProcessedAt = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "Table".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Table' column in Inheritance ServeTable_event. Check if DB is in sync");
		__index___Table = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Inheritance.ServeTable.__configureConverter(readers, __index____event_id, __index___QueuedAt, __index___ProcessedAt, __index___Table);
		
		metamodel.registerDataSource(Inheritance.ServeTable.class, "\"Inheritance\".\"ServeTable_event\"");
		
		metamodel.registerProperty(Inheritance.ServeTable.class, "getURI", "\"URI\"", Inheritance.ServeTable::getURI);
		
		metamodel.registerProperty(Inheritance.ServeTable.class, "getQueuedAt", "\"QueuedAt\"", Inheritance.ServeTable::getQueuedAt);
		
		metamodel.registerProperty(Inheritance.ServeTable.class, "getProcessedAt", "\"ProcessedAt\"", Inheritance.ServeTable::getProcessedAt);
		
		container.register(Inheritance.repositories.ServeTableRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.ServeTable>>(){}.type, Inheritance.repositories.ServeTableRepository::new, false);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.ServeTable>>(){}.type, Inheritance.repositories.ServeTableRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.ServeTable>>(){}.type, Inheritance.repositories.ServeTableRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventStore<Inheritance.ServeTable>>(){}.type, Inheritance.repositories.ServeTableRepository::new, false);
		try {
			org.revenj.Revenj.registerEvents(container, plugins, Inheritance.ServeTable.class, Inheritance.ServeTable[].class);
		} catch (java.lang.Exception e) {
			throw new java.io.IOException(e);
		}
		
		metamodel.registerProperty(Inheritance.ServeTable.class, "getTable", "\"Table\"", Inheritance.ServeTable::getTable);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"ServeTable_event\"";
	}

	@Override
	public Inheritance.ServeTable from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.ServeTable from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.ServeTable>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.ServeTable instance = new Inheritance.ServeTable(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.ServeTable instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index____event_id] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getURI());
		items[__index___QueuedAt] = org.revenj.database.postgres.converters.TimestampConverter.toTuple(instance.getQueuedAt());
		items[__index___ProcessedAt] = org.revenj.database.postgres.converters.TimestampConverter.toTuple(instance.getProcessedAt());
		items[__index___Table] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getTable());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.ServeTable>[] readers;
	
	public Inheritance.ServeTable from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.ServeTable instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.ServeTable from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	private final int __index____event_id;
	private final int __index___QueuedAt;
	private final int __index___ProcessedAt;
	private final int __index___Table;
}
