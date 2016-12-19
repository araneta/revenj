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

public class PersonConverter implements ObjectConverter<Inheritance.Person> {

	@SuppressWarnings("unchecked")
	public PersonConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "Person_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_Person_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(PersonConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.Person>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Person_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Person. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.Person>>(){}.type, Inheritance.repositories.PersonRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.Person>>(){}.type, Inheritance.repositories.PersonRepository::new, false);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Inheritance Person_entity. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Inheritance Person. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Inheritance.Person.__configureConverter(readers, __index___ID, __index___Name);
		
		Inheritance.Person.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_Name);
		
		metamodel.registerDataSource(Inheritance.Person.class, "\"Inheritance\".\"Person_entity\"");
		
		metamodel.registerProperty(Inheritance.Person.class, "getURI", "\"URI\"", Inheritance.Person::getURI);
		
		metamodel.registerProperty(Inheritance.Person.class, "getID", "\"ID\"", Inheritance.Person::getID);
		
		container.register(Inheritance.repositories.PersonRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.Person>>(){}.type, Inheritance.repositories.PersonRepository::new, false);
		
		metamodel.registerProperty(Inheritance.Person.class, "getName", "\"Name\"", Inheritance.Person::getName);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Inheritance.Person>>(){}.type, Inheritance.repositories.PersonRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"Person_entity\"";
	}

	@Override
	public Inheritance.Person from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.Person from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.Person>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.Person instance = new Inheritance.Person(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.Person instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index___Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.Person>[] readers;
	
	public Inheritance.Person from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Person instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.Person from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Inheritance.Person instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index__extended_Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Inheritance.Person>[] readersExtended;
	
	public Inheritance.Person fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Person instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Inheritance.Person fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Inheritance.Person instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___Name;
	private final int __index__extended_Name;
}
