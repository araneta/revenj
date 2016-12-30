/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package security.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class RolePermissionConverter implements ObjectConverter<security.RolePermission> {

	@SuppressWarnings("unchecked")
	public RolePermissionConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "RolePermission_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "-ngs_RolePermission_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(RolePermissionConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<security.RolePermission>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<security.RolePermission>>(){}.type, security.repositories.RolePermissionRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<security.RolePermission>>(){}.type, security.repositories.RolePermissionRepository::new, false);
		
		column = columns.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in security RolePermission_entity. Check if DB is in sync");
		__index___name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in security RolePermission. Check if DB is in sync");
		__index__extended_name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "roleID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'roleID' column in security RolePermission_entity. Check if DB is in sync");
		__index___roleID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "roleID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'roleID' column in security RolePermission. Check if DB is in sync");
		__index__extended_roleID = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "isAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'isAllowed' column in security RolePermission_entity. Check if DB is in sync");
		__index___isAllowed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "isAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'isAllowed' column in security RolePermission. Check if DB is in sync");
		__index__extended_isAllowed = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		security.RolePermission.__configureConverter(readers, __index___name, __index___roleID, __index___isAllowed);
		
		security.RolePermission.__configureConverterExtended(readersExtended, __index__extended_name, __index__extended_roleID, __index__extended_isAllowed);
		
		metamodel.registerDataSource(security.RolePermission.class, "\"security\".\"RolePermission_entity\"");
		
		metamodel.registerProperty(security.RolePermission.class, "getURI", "\"URI\"", security.RolePermission::getURI);
		
		container.register(security.repositories.RolePermissionRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<security.RolePermission>>(){}.type, security.repositories.RolePermissionRepository::new, false);
		
		metamodel.registerProperty(security.RolePermission.class, "getName", "\"name\"", security.RolePermission::getName);
		
		metamodel.registerProperty(security.RolePermission.class, "getRoleID", "\"roleID\"", security.RolePermission::getRoleID);
		
		metamodel.registerProperty(security.RolePermission.class, "getIsAllowed", "\"isAllowed\"", security.RolePermission::getIsAllowed);
		
		if (org.revenj.patterns.DataSource.class.isAssignableFrom(org.revenj.security.RolePermission.class)) {
			container.registerFactory(org.revenj.Utils.makeGenericType(org.revenj.patterns.SearchableRepository.class, org.revenj.security.RolePermission.class), c -> c.resolve(security.repositories.RolePermissionRepository.class), false);
			container.registerFactory(org.revenj.Utils.makeGenericType(org.revenj.patterns.Query.class, org.revenj.security.RolePermission.class), c -> c.resolve(security.repositories.RolePermissionRepository.class).query(), false);
		}
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<security.RolePermission>>(){}.type, security.repositories.RolePermissionRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"security\".\"RolePermission_entity\"";
	}

	@Override
	public security.RolePermission from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private security.RolePermission from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<security.RolePermission>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		security.RolePermission instance = new security.RolePermission(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(security.RolePermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index___roleID] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getRoleID());
		items[__index___isAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<security.RolePermission>[] readers;
	
	public security.RolePermission from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.RolePermission instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public security.RolePermission from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(security.RolePermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index__extended_roleID] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getRoleID());
		items[__index__extended_isAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<security.RolePermission>[] readersExtended;
	
	public security.RolePermission fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.RolePermission instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public security.RolePermission fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, security.RolePermission instance) throws java.io.IOException {
		return buildURI(instance.getName(), instance.getRoleID(), _sw);
	}
	public static String buildURI(String name, String roleID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.StringConverter.serializeCompositeURI(_sw, name);
		_sw.addToBuffer('/');org.revenj.database.postgres.converters.StringConverter.serializeCompositeURI(_sw, roleID);
		return _sw.bufferToString();
	}
	private final int __index___name;
	private final int __index__extended_name;
	private final int __index___roleID;
	private final int __index__extended_roleID;
	private final int __index___isAllowed;
	private final int __index__extended_isAllowed;
}
