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

public class InheritedRoleConverter implements ObjectConverter<Security.InheritedRole> {

	@SuppressWarnings("unchecked")
	public InheritedRoleConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "InheritedRole_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "-ngs_InheritedRole_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(InheritedRoleConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Security.InheritedRole>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Security.InheritedRole>>(){}.type, Security.repositories.InheritedRoleRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Security.InheritedRole>>(){}.type, Security.repositories.InheritedRoleRepository::new, false);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security InheritedRole_entity. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security InheritedRole. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "ParentName".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ParentName' column in Security InheritedRole_entity. Check if DB is in sync");
		__index___ParentName = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ParentName".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ParentName' column in Security InheritedRole. Check if DB is in sync");
		__index__extended_ParentName = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "RoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleURI' column in Security InheritedRole_entity. Check if DB is in sync");
		__index___RoleURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "RoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleURI' column in Security InheritedRole. Check if DB is in sync");
		__index__extended_RoleURI = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "ParentRoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ParentRoleURI' column in Security InheritedRole_entity. Check if DB is in sync");
		__index___ParentRoleURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ParentRoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ParentRoleURI' column in Security InheritedRole. Check if DB is in sync");
		__index__extended_ParentRoleURI = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Security.InheritedRole.__configureConverter(readers, __index___Name, __index___ParentName, __index___RoleURI, __index___ParentRoleURI);
		
		Security.InheritedRole.__configureConverterExtended(readersExtended, __index__extended_Name, __index__extended_ParentName, __index__extended_RoleURI, __index__extended_ParentRoleURI);
		
		metamodel.registerDataSource(Security.InheritedRole.class, "\"Security\".\"InheritedRole_entity\"");
		
		metamodel.registerProperty(Security.InheritedRole.class, "getURI", "\"URI\"", Security.InheritedRole::getURI);
		
		container.register(Security.repositories.InheritedRoleRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Security.InheritedRole>>(){}.type, Security.repositories.InheritedRoleRepository::new, false);
		
		metamodel.registerProperty(Security.InheritedRole.class, "getName", "\"Name\"", Security.InheritedRole::getName);
		
		metamodel.registerProperty(Security.InheritedRole.class, "getParentName", "\"ParentName\"", Security.InheritedRole::getParentName);
		
		metamodel.registerProperty(Security.InheritedRole.class, "getRole", "\"Role\"", Security.InheritedRole::getRole);
		
		metamodel.registerProperty(Security.InheritedRole.class, "getParentRole", "\"ParentRole\"", Security.InheritedRole::getParentRole);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Security.InheritedRole>>(){}.type, Security.repositories.InheritedRoleRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Security\".\"InheritedRole_entity\"";
	}

	@Override
	public Security.InheritedRole from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Security.InheritedRole from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Security.InheritedRole>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Security.InheritedRole instance = new Security.InheritedRole(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Security.InheritedRole instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index___ParentName] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getParentName());
		if (instance.getRoleURI() != null)items[__index___RoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getRoleURI());;
		if (instance.getParentRoleURI() != null)items[__index___ParentRoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getParentRoleURI());;
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Security.InheritedRole>[] readers;
	
	public Security.InheritedRole from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.InheritedRole instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Security.InheritedRole from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Security.InheritedRole instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index__extended_ParentName] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getParentName());
		if (instance.getRoleURI() != null)items[__index__extended_RoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getRoleURI());;
		if (instance.getParentRoleURI() != null)items[__index__extended_ParentRoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getParentRoleURI());;
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Security.InheritedRole>[] readersExtended;
	
	public Security.InheritedRole fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.InheritedRole instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Security.InheritedRole fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Security.InheritedRole instance) throws java.io.IOException {
		return buildURI(instance.getName(), instance.getParentName(), _sw);
	}
	public static String buildURI(String Name, String ParentName, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.StringConverter.serializeCompositeURI(_sw, Name);
		_sw.addToBuffer('/');org.revenj.database.postgres.converters.StringConverter.serializeCompositeURI(_sw, ParentName);
		return _sw.bufferToString();
	}
	private final int __index___Name;
	private final int __index__extended_Name;
	private final int __index___ParentName;
	private final int __index__extended_ParentName;
	private final int __index___RoleURI;
	private final int __index__extended_RoleURI;
	private final int __index___ParentRoleURI;
	private final int __index__extended_ParentRoleURI;
}
