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

public class LineItemConverter implements ObjectConverter<Inheritance.LineItem> {

	@SuppressWarnings("unchecked")
	public LineItemConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "LineItem_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Inheritance".equals(it.typeSchema) && "-ngs_LineItem_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(LineItemConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Inheritance.LineItem>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "productName".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'productName' column in Inheritance LineItem_entity. Check if DB is in sync");
		__index___productName = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "productName".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'productName' column in Inheritance LineItem. Check if DB is in sync");
		__index__extended_productName = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "quantity".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'quantity' column in Inheritance LineItem_entity. Check if DB is in sync");
		__index___quantity = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "quantity".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'quantity' column in Inheritance LineItem. Check if DB is in sync");
		__index__extended_quantity = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "OrderID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'OrderID' column in Inheritance LineItem_entity. Check if DB is in sync");
		__index___OrderID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "OrderID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'OrderID' column in Inheritance LineItem. Check if DB is in sync");
		__index__extended_OrderID = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "Index".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Index' column in Inheritance LineItem_entity. Check if DB is in sync");
		__index___Index = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Index".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Index' column in Inheritance LineItem. Check if DB is in sync");
		__index__extended_Index = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Inheritance.LineItem.__configureConverter(readers, __index___productName, __index___quantity, __index___OrderID, __index___Index);
		
		Inheritance.LineItem.__configureConverterExtended(readersExtended, __index__extended_productName, __index__extended_quantity, __index__extended_OrderID, __index__extended_Index);
		
		metamodel.registerDataSource(Inheritance.LineItem.class, "\"Inheritance\".\"LineItem_entity\"");
		
		metamodel.registerProperty(Inheritance.LineItem.class, "getURI", "\"URI\"", Inheritance.LineItem::getURI);
		
		metamodel.registerProperty(Inheritance.LineItem.class, "getProductName", "\"productName\"", Inheritance.LineItem::getProductName);
		
		metamodel.registerProperty(Inheritance.LineItem.class, "getQuantity", "\"quantity\"", Inheritance.LineItem::getQuantity);
		
		metamodel.registerProperty(Inheritance.LineItem.class, "getOrderID", "\"OrderID\"", Inheritance.LineItem::getOrderID);
		
		metamodel.registerProperty(Inheritance.LineItem.class, "getIndex", "\"Index\"", Inheritance.LineItem::getIndex);
	}

	@Override
	public String getDbName() {
		return "\"Inheritance\".\"LineItem_entity\"";
	}

	@Override
	public Inheritance.LineItem from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Inheritance.LineItem from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Inheritance.LineItem>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Inheritance.LineItem instance = new Inheritance.LineItem(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Inheritance.LineItem instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___productName] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getProductName());
		items[__index___quantity] = org.revenj.database.postgres.converters.DecimalConverter.toTuple(instance.getQuantity());
		items[__index___OrderID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getOrderID());
		items[__index___Index] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getIndex());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Inheritance.LineItem>[] readers;
	
	public Inheritance.LineItem from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.LineItem instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Inheritance.LineItem from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Inheritance.LineItem instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_productName] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getProductName());
		items[__index__extended_quantity] = org.revenj.database.postgres.converters.DecimalConverter.toTuple(instance.getQuantity());
		items[__index__extended_OrderID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getOrderID());
		items[__index__extended_Index] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getIndex());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Inheritance.LineItem>[] readersExtended;
	
	public Inheritance.LineItem fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Inheritance.LineItem instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Inheritance.LineItem fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___productName;
	private final int __index__extended_productName;
	private final int __index___quantity;
	private final int __index__extended_quantity;
	private final int __index___OrderID;
	private final int __index__extended_OrderID;
	private final int __index___Index;
	private final int __index__extended_Index;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Inheritance.LineItem instance) throws java.io.IOException {
		return buildURI(instance.getOrderID(), instance.getIndex(), _sw);
	}
	public static String buildURI(int OrderID, int Index, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, OrderID);
		_sw.addToBuffer('/');org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, Index);
		return _sw.bufferToString();
	}
}
