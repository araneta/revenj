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

public class WaiterObjectConverter implements ObjectConverter<Inheritance.WaiterObject> {

	@SuppressWarnings("unchecked")
	public WaiterObjectConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "WaiterObject".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		column = columns.stream().filter(it -> "URI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'URI' column in Inheritance WaiterObject. Check if DB is in sync");
		__index___URI = (int)column.get().order - 1;
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_WaiterObject_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		column = columnsExtended.stream().filter(it -> "URI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'URI' column in Inheritance WaiterObject. Check if DB is in sync");
		__index__extended_URI = (int)column.get().order - 1;
		
		container.registerInstance(WaiterObjectConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.WaiterObject>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Inheritance WaiterObject. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Inheritance WaiterObject. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "StartedWorking".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'StartedWorking' column in Inheritance WaiterObject. Check if DB is in sync");
		__index___StartedWorking = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "StartedWorking".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'StartedWorking' column in Inheritance WaiterObject. Check if DB is in sync");
		__index__extended_StartedWorking = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		metamodel.registerDataSource(Inheritance.WaiterObject.class, "\"Inheritance\".\"WaiterObject\"");
		
		metamodel.registerProperty(Inheritance.WaiterObject.class, "getURI", "\"URI\"", Inheritance.WaiterObject::getURI);
		
		container.register(Inheritance.repositories.WaiterObjectRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.WaiterObject>>(){}.type, Inheritance.repositories.WaiterObjectRepository::new, false);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.WaiterObject>>(){}.type, Inheritance.repositories.WaiterObjectRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.WaiterObject>>(){}.type, Inheritance.repositories.WaiterObjectRepository::new, false);
		
		metamodel.registerProperty(Inheritance.WaiterObject.class, "getName", "\"Name\"", Inheritance.WaiterObject::getName);
		
		metamodel.registerProperty(Inheritance.WaiterObject.class, "getStartedWorking", "\"StartedWorking\"", Inheritance.WaiterObject::getStartedWorking);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"WaiterObject\"";
	}

	@Override
	public PostgresTuple to(Inheritance.WaiterObject instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	
	@Override
	public Inheritance.WaiterObject from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.WaiterObject result = from(reader, context, context == 0 ? 1 : context << 1);
		reader.read();
		return result;
	}

	public Inheritance.WaiterObject from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		reader.read(outerContext);
		int i = 0;
		
		String _URI_ = null;
		String _Name_ = null;
		java.time.LocalDate _StartedWorking_ = null;
		while(i < columnCount) {
			final int started = i;
			
			if (__index___URI == i) { _URI_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); i++; }
			if (__index___Name == i) { _Name_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); i++; }
			if (__index___StartedWorking == i) { _StartedWorking_ = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); i++; }
			if (i == started) {
				org.revenj.database.postgres.converters.StringConverter.skip(reader, context);
				i++;
			}
		}
		Inheritance.WaiterObject instance = new Inheritance.WaiterObject(_URI_, _Name_, _StartedWorking_);
		reader.read(outerContext);
		return instance;
	}
	private final int __index___URI;
	private final int columnCountExtended;
	
	public PostgresTuple toExtended(Inheritance.WaiterObject instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		return RecordTuple.from(items);
	}

	public Inheritance.WaiterObject fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.WaiterObject result = fromExtended(reader, context, context == 0 ? 1 : context << 1);
		reader.read();
		return result;
	}

	public Inheritance.WaiterObject fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		reader.read(outerContext);
		int i = 0;
		
		String _URI_ = null;
		String _Name_ = null;
		java.time.LocalDate _StartedWorking_ = null;
		while(i < columnCountExtended) {
			final int started = i;
			
			if (__index__extended_URI == i) { _URI_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); i++; }
			if (__index__extended_Name == i) { _Name_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); i++; }
			if (__index__extended_StartedWorking == i) { _StartedWorking_ = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); i++; }
			if (i == started) {
				org.revenj.database.postgres.converters.StringConverter.skip(reader, context);
				i++;
			}
		}
		Inheritance.WaiterObject instance = new Inheritance.WaiterObject(_URI_, _Name_, _StartedWorking_);
		reader.read(outerContext);
		return instance;
	}
	private final int __index__extended_URI;
	private final int __index___Name;
	private final int __index__extended_Name;
	private final int __index___StartedWorking;
	private final int __index__extended_StartedWorking;
}
