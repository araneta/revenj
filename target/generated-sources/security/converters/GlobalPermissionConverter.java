/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package security.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class GlobalPermissionConverter implements ObjectConverter<security.GlobalPermission> {

	@SuppressWarnings("unchecked")
	public GlobalPermissionConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "GlobalPermission_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "-ngs_GlobalPermission_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(GlobalPermissionConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<security.GlobalPermission>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<security.GlobalPermission>>(){}.type, security.repositories.GlobalPermissionRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<security.GlobalPermission>>(){}.type, security.repositories.GlobalPermissionRepository::new, false);
		
		column = columns.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in security GlobalPermission_entity. Check if DB is in sync");
		__index___name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in security GlobalPermission. Check if DB is in sync");
		__index__extended_name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "isAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'isAllowed' column in security GlobalPermission_entity. Check if DB is in sync");
		__index___isAllowed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "isAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'isAllowed' column in security GlobalPermission. Check if DB is in sync");
		__index__extended_isAllowed = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		security.GlobalPermission.__configureConverter(readers, __index___name, __index___isAllowed);
		
		security.GlobalPermission.__configureConverterExtended(readersExtended, __index__extended_name, __index__extended_isAllowed);
		
		metamodel.registerDataSource(security.GlobalPermission.class, "\"security\".\"GlobalPermission_entity\"");
		
		metamodel.registerProperty(security.GlobalPermission.class, "getURI", "\"URI\"", security.GlobalPermission::getURI);
		
		container.register(security.repositories.GlobalPermissionRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<security.GlobalPermission>>(){}.type, security.repositories.GlobalPermissionRepository::new, false);
		
		metamodel.registerProperty(security.GlobalPermission.class, "getName", "\"name\"", security.GlobalPermission::getName);
		
		metamodel.registerProperty(security.GlobalPermission.class, "getIsAllowed", "\"isAllowed\"", security.GlobalPermission::getIsAllowed);
		
		if (org.revenj.patterns.DataSource.class.isAssignableFrom(org.revenj.security.GlobalPermission.class)) {
			container.registerFactory(org.revenj.Utils.makeGenericType(org.revenj.patterns.SearchableRepository.class, org.revenj.security.GlobalPermission.class), c -> c.resolve(security.repositories.GlobalPermissionRepository.class), false);
			container.registerFactory(org.revenj.Utils.makeGenericType(org.revenj.patterns.Query.class, org.revenj.security.GlobalPermission.class), c -> c.resolve(security.repositories.GlobalPermissionRepository.class).query(), false);
		}
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<security.GlobalPermission>>(){}.type, security.repositories.GlobalPermissionRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"security\".\"GlobalPermission_entity\"";
	}

	@Override
	public security.GlobalPermission from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private security.GlobalPermission from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<security.GlobalPermission>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		security.GlobalPermission instance = new security.GlobalPermission(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(security.GlobalPermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index___isAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<security.GlobalPermission>[] readers;
	
	public security.GlobalPermission from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.GlobalPermission instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public security.GlobalPermission from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(security.GlobalPermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index__extended_isAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<security.GlobalPermission>[] readersExtended;
	
	public security.GlobalPermission fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.GlobalPermission instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public security.GlobalPermission fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, security.GlobalPermission instance) throws java.io.IOException {
		return buildURI(instance.getName(), _sw);
	}
	public static String buildURI(String name, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.StringConverter.serializeURI(_sw, name);
		return _sw.bufferToString();
	}
	private final int __index___name;
	private final int __index__extended_name;
	private final int __index___isAllowed;
	private final int __index__extended_isAllowed;
}
