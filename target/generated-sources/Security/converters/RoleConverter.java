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

public class RoleConverter implements ObjectConverter<Security.Role> {

	@SuppressWarnings("unchecked")
	public RoleConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "Role_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "-ngs_Role_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(RoleConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Security.Role>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Security.Role>>(){}.type, Security.repositories.RoleRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Security.Role>>(){}.type, Security.repositories.RoleRepository::new, false);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security Role_entity. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security Role. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Security.Role.__configureConverter(readers, __index___Name);
		
		Security.Role.__configureConverterExtended(readersExtended, __index__extended_Name);
		
		metamodel.registerDataSource(Security.Role.class, "\"Security\".\"Role_entity\"");
		
		metamodel.registerProperty(Security.Role.class, "getURI", "\"URI\"", Security.Role::getURI);
		
		container.register(Security.repositories.RoleRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Security.Role>>(){}.type, Security.repositories.RoleRepository::new, false);
		
		metamodel.registerProperty(Security.Role.class, "getName", "\"Name\"", Security.Role::getName);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Security.Role>>(){}.type, Security.repositories.RoleRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Security\".\"Role_entity\"";
	}

	@Override
	public Security.Role from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Security.Role from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Security.Role>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Security.Role instance = new Security.Role(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Security.Role instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Security.Role>[] readers;
	
	public Security.Role from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.Role instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Security.Role from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Security.Role instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Security.Role>[] readersExtended;
	
	public Security.Role fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.Role instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Security.Role fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Security.Role instance) throws java.io.IOException {
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
}
