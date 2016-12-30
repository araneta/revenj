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

public class EmployeeConverter implements ObjectConverter<Inheritance.Employee> {

	@SuppressWarnings("unchecked")
	public EmployeeConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "Employee_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_Employee_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(EmployeeConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.Employee>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Employee_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Employee. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.Employee>>(){}.type, Inheritance.repositories.EmployeeRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.Employee>>(){}.type, Inheritance.repositories.EmployeeRepository::new, false);
		
		column = columns.stream().filter(it -> "StartedWorking".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'StartedWorking' column in Inheritance Employee_entity. Check if DB is in sync");
		__index___StartedWorking = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "StartedWorking".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'StartedWorking' column in Inheritance Employee. Check if DB is in sync");
		__index__extended_StartedWorking = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "PersonURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'PersonURI' column in Inheritance Employee_entity. Check if DB is in sync");
		__index___PersonURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "PersonURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'PersonURI' column in Inheritance Employee. Check if DB is in sync");
		__index__extended_PersonURI = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "PersonID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'PersonID' column in Inheritance Employee_entity. Check if DB is in sync");
		__index___PersonID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "PersonID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'PersonID' column in Inheritance Employee. Check if DB is in sync");
		__index__extended_PersonID = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Inheritance.Employee.__configureConverter(readers, __index___ID, __index___StartedWorking, __index___PersonURI, __index___PersonID);
		
		Inheritance.Employee.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_StartedWorking, __index__extended_PersonURI, __index__extended_PersonID);
		
		metamodel.registerDataSource(Inheritance.Employee.class, "\"Inheritance\".\"Employee_entity\"");
		
		metamodel.registerProperty(Inheritance.Employee.class, "getURI", "\"URI\"", Inheritance.Employee::getURI);
		
		metamodel.registerProperty(Inheritance.Employee.class, "getID", "\"ID\"", Inheritance.Employee::getID);
		
		container.register(Inheritance.repositories.EmployeeRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.Employee>>(){}.type, Inheritance.repositories.EmployeeRepository::new, false);
		
		metamodel.registerProperty(Inheritance.Employee.class, "getStartedWorking", "\"StartedWorking\"", Inheritance.Employee::getStartedWorking);
		
		metamodel.registerProperty(Inheritance.Employee.class, "getPerson", "\"Person\"", Inheritance.Employee::getPerson);
		
		metamodel.registerProperty(Inheritance.Employee.class, "getPersonID", "\"PersonID\"", Inheritance.Employee::getPersonID);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Inheritance.Employee>>(){}.type, Inheritance.repositories.EmployeeRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"Employee_entity\"";
	}

	@Override
	public Inheritance.Employee from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.Employee from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.Employee>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.Employee instance = new Inheritance.Employee(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.Employee instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index___StartedWorking] = org.revenj.database.postgres.converters.DateConverter.toTuple(instance.getStartedWorking());
		if (instance.getPersonURI() != null)items[__index___PersonURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getPersonURI());;
		items[__index___PersonID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getPersonID());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.Employee>[] readers;
	
	public Inheritance.Employee from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Employee instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.Employee from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Inheritance.Employee instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index__extended_StartedWorking] = org.revenj.database.postgres.converters.DateConverter.toTuple(instance.getStartedWorking());
		if (instance.getPersonURI() != null)items[__index__extended_PersonURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getPersonURI());;
		items[__index__extended_PersonID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getPersonID());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Inheritance.Employee>[] readersExtended;
	
	public Inheritance.Employee fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Employee instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Inheritance.Employee fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Inheritance.Employee instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___StartedWorking;
	private final int __index__extended_StartedWorking;
	private final int __index___PersonURI;
	private final int __index__extended_PersonURI;
	private final int __index___PersonID;
	private final int __index__extended_PersonID;
}
