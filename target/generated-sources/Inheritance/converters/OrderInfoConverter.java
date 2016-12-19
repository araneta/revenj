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

public class OrderInfoConverter implements ObjectConverter<Inheritance.OrderInfo> {

	@SuppressWarnings("unchecked")
	public OrderInfoConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "OrderInfo".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		column = columns.stream().filter(it -> "URI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'URI' column in Inheritance OrderInfo. Check if DB is in sync");
		__index___URI = (int)column.get().order - 1;
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_OrderInfo_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		column = columnsExtended.stream().filter(it -> "URI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'URI' column in Inheritance OrderInfo. Check if DB is in sync");
		__index__extended_URI = (int)column.get().order - 1;
		
		container.registerInstance(OrderInfoConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.OrderInfo>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "placed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'placed' column in Inheritance OrderInfo. Check if DB is in sync");
		__index___placed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "placed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'placed' column in Inheritance OrderInfo. Check if DB is in sync");
		__index__extended_placed = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "deadline".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'deadline' column in Inheritance OrderInfo. Check if DB is in sync");
		__index___deadline = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "deadline".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'deadline' column in Inheritance OrderInfo. Check if DB is in sync");
		__index__extended_deadline = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "customerName".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'customerName' column in Inheritance OrderInfo. Check if DB is in sync");
		__index___customerName = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "customerName".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'customerName' column in Inheritance OrderInfo. Check if DB is in sync");
		__index__extended_customerName = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		metamodel.registerDataSource(Inheritance.OrderInfo.class, "\"Inheritance\".\"OrderInfo\"");
		
		metamodel.registerProperty(Inheritance.OrderInfo.class, "getURI", "\"URI\"", Inheritance.OrderInfo::getURI);
		
		container.register(Inheritance.repositories.OrderInfoRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.OrderInfo>>(){}.type, Inheritance.repositories.OrderInfoRepository::new, false);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.OrderInfo>>(){}.type, Inheritance.repositories.OrderInfoRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.OrderInfo>>(){}.type, Inheritance.repositories.OrderInfoRepository::new, false);
		
		metamodel.registerProperty(Inheritance.OrderInfo.class, "getPlaced", "\"placed\"", Inheritance.OrderInfo::getPlaced);
		
		metamodel.registerProperty(Inheritance.OrderInfo.class, "getDeadline", "\"deadline\"", Inheritance.OrderInfo::getDeadline);
		
		metamodel.registerProperty(Inheritance.OrderInfo.class, "getCustomerName", "\"customerName\"", Inheritance.OrderInfo::getCustomerName);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"OrderInfo\"";
	}

	@Override
	public PostgresTuple to(Inheritance.OrderInfo instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	
	@Override
	public Inheritance.OrderInfo from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.OrderInfo result = from(reader, context, context == 0 ? 1 : context << 1);
		reader.read();
		return result;
	}

	public Inheritance.OrderInfo from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		reader.read(outerContext);
		int i = 0;
		
		String _URI_ = null;
		java.time.LocalDate _placed_ = null;
		java.time.LocalDate _deadline_ = null;
		String _customerName_ = null;
		while(i < columnCount) {
			final int started = i;
			
			if (__index___URI == i) { _URI_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); i++; }
			if (__index___placed == i) { _placed_ = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); i++; }
			if (__index___deadline == i) { _deadline_ = org.revenj.database.postgres.converters.DateConverter.parse(reader, true); i++; }
			if (__index___customerName == i) { _customerName_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); i++; }
			if (i == started) {
				org.revenj.database.postgres.converters.StringConverter.skip(reader, context);
				i++;
			}
		}
		Inheritance.OrderInfo instance = new Inheritance.OrderInfo(_URI_, _placed_, _deadline_, _customerName_);
		reader.read(outerContext);
		return instance;
	}
	private final int __index___URI;
	private final int columnCountExtended;
	
	public PostgresTuple toExtended(Inheritance.OrderInfo instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		return RecordTuple.from(items);
	}

	public Inheritance.OrderInfo fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.OrderInfo result = fromExtended(reader, context, context == 0 ? 1 : context << 1);
		reader.read();
		return result;
	}

	public Inheritance.OrderInfo fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		reader.read(outerContext);
		int i = 0;
		
		String _URI_ = null;
		java.time.LocalDate _placed_ = null;
		java.time.LocalDate _deadline_ = null;
		String _customerName_ = null;
		while(i < columnCountExtended) {
			final int started = i;
			
			if (__index__extended_URI == i) { _URI_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); i++; }
			if (__index__extended_placed == i) { _placed_ = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); i++; }
			if (__index__extended_deadline == i) { _deadline_ = org.revenj.database.postgres.converters.DateConverter.parse(reader, true); i++; }
			if (__index__extended_customerName == i) { _customerName_ = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); i++; }
			if (i == started) {
				org.revenj.database.postgres.converters.StringConverter.skip(reader, context);
				i++;
			}
		}
		Inheritance.OrderInfo instance = new Inheritance.OrderInfo(_URI_, _placed_, _deadline_, _customerName_);
		reader.read(outerContext);
		return instance;
	}
	private final int __index__extended_URI;
	private final int __index___placed;
	private final int __index__extended_placed;
	private final int __index___deadline;
	private final int __index__extended_deadline;
	private final int __index___customerName;
	private final int __index__extended_customerName;
}
