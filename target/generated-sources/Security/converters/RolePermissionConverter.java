/*
* Created by DSL Platform
* v1.7.6193.30391 
*/

package Security.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class RolePermissionConverter implements ObjectConverter<Security.RolePermission> {

	@SuppressWarnings("unchecked")
	public RolePermissionConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "RolePermission_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "-ngs_RolePermission_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(RolePermissionConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Security.RolePermission>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Security.RolePermission>>(){}.type, Security.repositories.RolePermissionRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Security.RolePermission>>(){}.type, Security.repositories.RolePermissionRepository::new, false);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security RolePermission_entity. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security RolePermission. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "RoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleURI' column in Security RolePermission_entity. Check if DB is in sync");
		__index___RoleURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "RoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleURI' column in Security RolePermission. Check if DB is in sync");
		__index__extended_RoleURI = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "RoleID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleID' column in Security RolePermission_entity. Check if DB is in sync");
		__index___RoleID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "RoleID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleID' column in Security RolePermission. Check if DB is in sync");
		__index__extended_RoleID = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "IsAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'IsAllowed' column in Security RolePermission_entity. Check if DB is in sync");
		__index___IsAllowed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "IsAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'IsAllowed' column in Security RolePermission. Check if DB is in sync");
		__index__extended_IsAllowed = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Security.RolePermission.__configureConverter(readers, __index___Name, __index___RoleURI, __index___RoleID, __index___IsAllowed);
		
		Security.RolePermission.__configureConverterExtended(readersExtended, __index__extended_Name, __index__extended_RoleURI, __index__extended_RoleID, __index__extended_IsAllowed);
		
		metamodel.registerDataSource(Security.RolePermission.class, "\"Security\".\"RolePermission_entity\"");
		
		metamodel.registerProperty(Security.RolePermission.class, "getURI", "\"URI\"", Security.RolePermission::getURI);
		
		container.register(Security.repositories.RolePermissionRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Security.RolePermission>>(){}.type, Security.repositories.RolePermissionRepository::new, false);
		
		metamodel.registerProperty(Security.RolePermission.class, "getName", "\"Name\"", Security.RolePermission::getName);
		
		metamodel.registerProperty(Security.RolePermission.class, "getRole", "\"Role\"", Security.RolePermission::getRole);
		
		metamodel.registerProperty(Security.RolePermission.class, "getRoleID", "\"RoleID\"", Security.RolePermission::getRoleID);
		
		metamodel.registerProperty(Security.RolePermission.class, "getIsAllowed", "\"IsAllowed\"", Security.RolePermission::getIsAllowed);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Security.RolePermission>>(){}.type, Security.repositories.RolePermissionRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Security\".\"RolePermission_entity\"";
	}

	@Override
	public Security.RolePermission from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Security.RolePermission from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Security.RolePermission>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Security.RolePermission instance = new Security.RolePermission(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Security.RolePermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		if (instance.getRoleURI() != null)items[__index___RoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getRoleURI());;
		items[__index___RoleID] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getRoleID());
		items[__index___IsAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Security.RolePermission>[] readers;
	
	public Security.RolePermission from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.RolePermission instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Security.RolePermission from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Security.RolePermission instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		if (instance.getRoleURI() != null)items[__index__extended_RoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getRoleURI());;
		items[__index__extended_RoleID] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getRoleID());
		items[__index__extended_IsAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Security.RolePermission>[] readersExtended;
	
	public Security.RolePermission fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.RolePermission instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Security.RolePermission fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Security.RolePermission instance) throws java.io.IOException {
		return buildURI(instance.getName(), instance.getRoleID(), _sw);
	}
	public static String buildURI(String Name, String RoleID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.StringConverter.serializeCompositeURI(_sw, Name);
		_sw.addToBuffer('/');org.revenj.database.postgres.converters.StringConverter.serializeCompositeURI(_sw, RoleID);
		return _sw.bufferToString();
	}
	private final int __index___Name;
	private final int __index__extended_Name;
	private final int __index___RoleURI;
	private final int __index__extended_RoleURI;
	private final int __index___RoleID;
	private final int __index__extended_RoleID;
	private final int __index___IsAllowed;
	private final int __index__extended_IsAllowed;
}
