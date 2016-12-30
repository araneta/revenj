/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package Inheritance.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class WaiterConverter implements ObjectConverter<Inheritance.Waiter> {

	@SuppressWarnings("unchecked")
	public WaiterConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "Waiter_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_Waiter_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(WaiterConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.Waiter>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Waiter_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Waiter. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.Waiter>>(){}.type, Inheritance.repositories.WaiterRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.Waiter>>(){}.type, Inheritance.repositories.WaiterRepository::new, false);
		
		column = columns.stream().filter(it -> "EmployeeURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'EmployeeURI' column in Inheritance Waiter_entity. Check if DB is in sync");
		__index___EmployeeURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "EmployeeURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'EmployeeURI' column in Inheritance Waiter. Check if DB is in sync");
		__index__extended_EmployeeURI = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "EmployeeID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'EmployeeID' column in Inheritance Waiter_entity. Check if DB is in sync");
		__index___EmployeeID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "EmployeeID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'EmployeeID' column in Inheritance Waiter. Check if DB is in sync");
		__index__extended_EmployeeID = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Inheritance.Waiter.__configureConverter(readers, __index___ID, __index___EmployeeURI, __index___EmployeeID);
		
		Inheritance.Waiter.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_EmployeeURI, __index__extended_EmployeeID);
		
		metamodel.registerDataSource(Inheritance.Waiter.class, "\"Inheritance\".\"Waiter_entity\"");
		
		metamodel.registerProperty(Inheritance.Waiter.class, "getURI", "\"URI\"", Inheritance.Waiter::getURI);
		
		metamodel.registerProperty(Inheritance.Waiter.class, "getID", "\"ID\"", Inheritance.Waiter::getID);
		
		container.register(Inheritance.repositories.WaiterRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.Waiter>>(){}.type, Inheritance.repositories.WaiterRepository::new, false);
		
		metamodel.registerProperty(Inheritance.Waiter.class, "getEmployee", "\"Employee\"", Inheritance.Waiter::getEmployee);
		
		metamodel.registerProperty(Inheritance.Waiter.class, "getEmployeeID", "\"EmployeeID\"", Inheritance.Waiter::getEmployeeID);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Inheritance.Waiter>>(){}.type, Inheritance.repositories.WaiterRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"Waiter_entity\"";
	}

	@Override
	public Inheritance.Waiter from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.Waiter from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.Waiter>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.Waiter instance = new Inheritance.Waiter(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.Waiter instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		if (instance.getEmployeeURI() != null)items[__index___EmployeeURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getEmployeeURI());;
		items[__index___EmployeeID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getEmployeeID());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.Waiter>[] readers;
	
	public Inheritance.Waiter from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Waiter instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.Waiter from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Inheritance.Waiter instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		if (instance.getEmployeeURI() != null)items[__index__extended_EmployeeURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getEmployeeURI());;
		items[__index__extended_EmployeeID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getEmployeeID());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Inheritance.Waiter>[] readersExtended;
	
	public Inheritance.Waiter fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Waiter instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Inheritance.Waiter fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Inheritance.Waiter instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___EmployeeURI;
	private final int __index__extended_EmployeeURI;
	private final int __index___EmployeeID;
	private final int __index__extended_EmployeeID;
}
