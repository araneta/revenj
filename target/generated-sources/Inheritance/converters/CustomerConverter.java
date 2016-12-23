/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package Inheritance.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class CustomerConverter implements ObjectConverter<Inheritance.Customer> {

	@SuppressWarnings("unchecked")
	public CustomerConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "Customer_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_Customer_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(CustomerConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.Customer>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Customer_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Customer. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.Customer>>(){}.type, Inheritance.repositories.CustomerRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.Customer>>(){}.type, Inheritance.repositories.CustomerRepository::new, false);
		
		column = columns.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in Inheritance Customer_entity. Check if DB is in sync");
		__index___name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in Inheritance Customer. Check if DB is in sync");
		__index__extended_name = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Inheritance.Customer.__configureConverter(readers, __index___ID, __index___name);
		
		Inheritance.Customer.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_name);
		
		metamodel.registerDataSource(Inheritance.Customer.class, "\"Inheritance\".\"Customer_entity\"");
		
		metamodel.registerProperty(Inheritance.Customer.class, "getURI", "\"URI\"", Inheritance.Customer::getURI);
		
		metamodel.registerProperty(Inheritance.Customer.class, "getID", "\"ID\"", Inheritance.Customer::getID);
		
		container.register(Inheritance.repositories.CustomerRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.Customer>>(){}.type, Inheritance.repositories.CustomerRepository::new, false);
		
		metamodel.registerProperty(Inheritance.Customer.class, "getName", "\"name\"", Inheritance.Customer::getName);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Inheritance.Customer>>(){}.type, Inheritance.repositories.CustomerRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"Customer_entity\"";
	}

	@Override
	public Inheritance.Customer from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.Customer from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.Customer>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.Customer instance = new Inheritance.Customer(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.Customer instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index___name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.Customer>[] readers;
	
	public Inheritance.Customer from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Customer instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.Customer from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Inheritance.Customer instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index__extended_name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Inheritance.Customer>[] readersExtended;
	
	public Inheritance.Customer fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Customer instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Inheritance.Customer fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Inheritance.Customer instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___name;
	private final int __index__extended_name;
}
