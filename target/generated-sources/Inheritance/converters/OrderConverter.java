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

public class OrderConverter implements ObjectConverter<Inheritance.Order> {

	@SuppressWarnings("unchecked")
	public OrderConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "Order_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_Order_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(OrderConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.Order>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Order_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Inheritance Order. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Inheritance.Order>>(){}.type, Inheritance.repositories.OrderRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Inheritance.Order>>(){}.type, Inheritance.repositories.OrderRepository::new, false);
		
		column = columns.stream().filter(it -> "placed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'placed' column in Inheritance Order_entity. Check if DB is in sync");
		__index___placed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "placed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'placed' column in Inheritance Order. Check if DB is in sync");
		__index__extended_placed = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "deadline".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'deadline' column in Inheritance Order_entity. Check if DB is in sync");
		__index___deadline = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "deadline".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'deadline' column in Inheritance Order. Check if DB is in sync");
		__index__extended_deadline = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "customerURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'customerURI' column in Inheritance Order_entity. Check if DB is in sync");
		__index___customerURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "customerURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'customerURI' column in Inheritance Order. Check if DB is in sync");
		__index__extended_customerURI = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "customerID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'customerID' column in Inheritance Order_entity. Check if DB is in sync");
		__index___customerID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "customerID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'customerID' column in Inheritance Order. Check if DB is in sync");
		__index__extended_customerID = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "items".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'items' column in Inheritance Order_entity. Check if DB is in sync");
		__index___items = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "items".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'items' column in Inheritance Order. Check if DB is in sync");
		__index__extended_items = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		__converter_items = container.resolve(Inheritance.converters.LineItemConverter.class);
		
		
		Inheritance.Order.__configureConverter(readers, __index___ID, __index___placed, __index___deadline, __index___customerURI, __index___customerID, __converter_items, __index___items);
		
		Inheritance.Order.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_placed, __index__extended_deadline, __index__extended_customerURI, __index__extended_customerID, __converter_items, __index__extended_items);
		
		metamodel.registerDataSource(Inheritance.Order.class, "\"Inheritance\".\"Order_entity\"");
		
		metamodel.registerProperty(Inheritance.Order.class, "getURI", "\"URI\"", Inheritance.Order::getURI);
		
		metamodel.registerProperty(Inheritance.Order.class, "getID", "\"ID\"", Inheritance.Order::getID);
		
		container.register(Inheritance.repositories.OrderRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Inheritance.Order>>(){}.type, Inheritance.repositories.OrderRepository::new, false);
		
		metamodel.registerProperty(Inheritance.Order.class, "getPlaced", "\"placed\"", Inheritance.Order::getPlaced);
		
		metamodel.registerProperty(Inheritance.Order.class, "getDeadline", "\"deadline\"", Inheritance.Order::getDeadline);
		
		metamodel.registerProperty(Inheritance.Order.class, "getCustomer", "\"customer\"", Inheritance.Order::getCustomer);
		
		metamodel.registerProperty(Inheritance.Order.class, "getCustomerID", "\"customerID\"", Inheritance.Order::getCustomerID);
		
		metamodel.registerProperty(Inheritance.Order.class, "getItems", "\"items\"", Inheritance.Order::getItems);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Inheritance.Order>>(){}.type, Inheritance.repositories.OrderRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"Order_entity\"";
	}

	@Override
	public Inheritance.Order from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.Order from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.Order>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.Order instance = new Inheritance.Order(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.Order instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index___placed] = org.revenj.database.postgres.converters.DateConverter.toTuple(instance.getPlaced());
		items[__index___deadline] = org.revenj.database.postgres.converters.DateConverter.toTuple(instance.getDeadline());
		if (instance.getCustomerURI() != null)items[__index___customerURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getCustomerURI());;
		items[__index___customerID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getCustomerID());
		items[__index___items] = org.revenj.database.postgres.converters.ArrayTuple.create(instance.getItems(), __converter_items::to);
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.Order>[] readers;
	
	public Inheritance.Order from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Order instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.Order from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Inheritance.Order instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index__extended_placed] = org.revenj.database.postgres.converters.DateConverter.toTuple(instance.getPlaced());
		items[__index__extended_deadline] = org.revenj.database.postgres.converters.DateConverter.toTuple(instance.getDeadline());
		if (instance.getCustomerURI() != null)items[__index__extended_customerURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getCustomerURI());;
		items[__index__extended_customerID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getCustomerID());
		items[__index__extended_items] = org.revenj.database.postgres.converters.ArrayTuple.create(instance.getItems(), __converter_items::toExtended);
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Inheritance.Order>[] readersExtended;
	
	public Inheritance.Order fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.Order instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Inheritance.Order fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Inheritance.Order instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___placed;
	private final int __index__extended_placed;
	private final int __index___deadline;
	private final int __index__extended_deadline;
	private final int __index___customerURI;
	private final int __index__extended_customerURI;
	private final int __index___customerID;
	private final int __index__extended_customerID;
	private Inheritance.converters.LineItemConverter __converter_items;
	private final int __index___items;
	private final int __index__extended_items;
}
