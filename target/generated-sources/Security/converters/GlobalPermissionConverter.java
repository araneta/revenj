/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Security.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class GlobalPermissionConverter implements ObjectConverter<Security.GlobalPermission> {

	@SuppressWarnings("unchecked")
	public GlobalPermissionConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "GlobalPermission_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "-ngs_GlobalPermission_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(GlobalPermissionConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Security.GlobalPermission>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Security.GlobalPermission>>(){}.type, Security.repositories.GlobalPermissionRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Security.GlobalPermission>>(){}.type, Security.repositories.GlobalPermissionRepository::new, false);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security GlobalPermission_entity. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security GlobalPermission. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "IsAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'IsAllowed' column in Security GlobalPermission_entity. Check if DB is in sync");
		__index___IsAllowed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "IsAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'IsAllowed' column in Security GlobalPermission. Check if DB is in sync");
		__index__extended_IsAllowed = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Security.GlobalPermission.__configureConverter(readers, __index___Name, __index___IsAllowed);
		
		Security.GlobalPermission.__configureConverterExtended(readersExtended, __index__extended_Name, __index__extended_IsAllowed);
		
		metamodel.registerDataSource(Security.GlobalPermission.class, "\"Security\".\"GlobalPermission_entity\"");
		
		metamodel.registerProperty(Security.GlobalPermission.class, "getURI", "\"URI\"", Security.GlobalPermission::getURI);
		
		container.register(Security.repositories.GlobalPermissionRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Security.GlobalPermission>>(){}.type, Security.repositories.GlobalPermissionRepository::new, false);
		
		metamodel.registerProperty(Security.GlobalPermission.class, "getName", "\"Name\"", Security.GlobalPermission::getName);
		
		metamodel.registerProperty(Security.GlobalPermission.class, "getIsAllowed", "\"IsAllowed\"", Security.GlobalPermission::getIsAllowed);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Security.GlobalPermission>>(){}.type, Security.repositories.GlobalPermissionRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Security\".\"GlobalPermission_entity\"";
	}

	@Override
	public Security.GlobalPermission from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Security.GlobalPermission from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Security.GlobalPermission>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Security.GlobalPermission instance = new Security.GlobalPermission(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Security.GlobalPermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index___IsAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Security.GlobalPermission>[] readers;
	
	public Security.GlobalPermission from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.GlobalPermission instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Security.GlobalPermission from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Security.GlobalPermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index__extended_IsAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Security.GlobalPermission>[] readersExtended;
	
	public Security.GlobalPermission fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.GlobalPermission instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Security.GlobalPermission fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Security.GlobalPermission instance) throws java.io.IOException {
		return buildURI(instance.getName(), _sw);
	}
	public static String buildURI(String Name, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.StringConverter.serializeURI(_sw, Name);
		return _sw.bufferToString();
	}
	private final int __index___Name;
	private final int __index__extended_Name;
	private final int __index___IsAllowed;
	private final int __index__extended_IsAllowed;
}
