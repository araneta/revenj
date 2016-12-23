/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package hello.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class WorldConverter implements ObjectConverter<hello.World> {

	@SuppressWarnings("unchecked")
	public WorldConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "hello".equals(it.typeSchema) && "World_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "hello".equals(it.typeSchema) && "-ngs_World_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(WorldConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<hello.World>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in hello World_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in hello World. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<hello.World>>(){}.type, hello.repositories.WorldRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<hello.World>>(){}.type, hello.repositories.WorldRepository::new, false);
		
		column = columns.stream().filter(it -> "message".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'message' column in hello World_entity. Check if DB is in sync");
		__index___message = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "message".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'message' column in hello World. Check if DB is in sync");
		__index__extended_message = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		hello.World.__configureConverter(readers, __index___ID, __index___message);
		
		hello.World.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_message);
		
		metamodel.registerDataSource(hello.World.class, "\"hello\".\"World_entity\"");
		
		metamodel.registerProperty(hello.World.class, "getURI", "\"URI\"", hello.World::getURI);
		
		metamodel.registerProperty(hello.World.class, "getID", "\"ID\"", hello.World::getID);
		
		container.register(hello.repositories.WorldRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<hello.World>>(){}.type, hello.repositories.WorldRepository::new, false);
		
		metamodel.registerProperty(hello.World.class, "getMessage", "\"message\"", hello.World::getMessage);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<hello.World>>(){}.type, hello.repositories.WorldRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"hello\".\"World_entity\"";
	}

	@Override
	public hello.World from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private hello.World from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<hello.World>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		hello.World instance = new hello.World(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(hello.World instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index___message] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getMessage());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<hello.World>[] readers;
	
	public hello.World from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		hello.World instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public hello.World from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(hello.World instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index__extended_message] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getMessage());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<hello.World>[] readersExtended;
	
	public hello.World fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		hello.World instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public hello.World fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, hello.World instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___message;
	private final int __index__extended_message;
}
