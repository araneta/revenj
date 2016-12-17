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

public class UserConverter implements ObjectConverter<Security.User> {

	@SuppressWarnings("unchecked")
	public UserConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "User_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Security".equals(it.typeSchema) && "-ngs_User_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(UserConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Security.User>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Security.User>>(){}.type, Security.repositories.UserRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Security.User>>(){}.type, Security.repositories.UserRepository::new, false);
		
		column = columns.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security User_entity. Check if DB is in sync");
		__index___Name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "Name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'Name' column in Security User. Check if DB is in sync");
		__index__extended_Name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "RoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleURI' column in Security User_entity. Check if DB is in sync");
		__index___RoleURI = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "RoleURI".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'RoleURI' column in Security User. Check if DB is in sync");
		__index__extended_RoleURI = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "PasswordHash".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'PasswordHash' column in Security User_entity. Check if DB is in sync");
		__index___PasswordHash = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "PasswordHash".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'PasswordHash' column in Security User. Check if DB is in sync");
		__index__extended_PasswordHash = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "IsAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'IsAllowed' column in Security User_entity. Check if DB is in sync");
		__index___IsAllowed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "IsAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'IsAllowed' column in Security User. Check if DB is in sync");
		__index__extended_IsAllowed = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Security.User.__configureConverter(readers, __index___Name, __index___RoleURI, __index___PasswordHash, __index___IsAllowed);
		
		Security.User.__configureConverterExtended(readersExtended, __index__extended_Name, __index__extended_RoleURI, __index__extended_PasswordHash, __index__extended_IsAllowed);
		
		metamodel.registerDataSource(Security.User.class, "\"Security\".\"User_entity\"");
		
		metamodel.registerProperty(Security.User.class, "getURI", "\"URI\"", Security.User::getURI);
		
		container.register(Security.repositories.UserRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Security.User>>(){}.type, Security.repositories.UserRepository::new, false);
		
		metamodel.registerProperty(Security.User.class, "getName", "\"Name\"", Security.User::getName);
		
		metamodel.registerProperty(Security.User.class, "getRole", "\"Role\"", Security.User::getRole);
		
		metamodel.registerProperty(Security.User.class, "getPasswordHash", "\"PasswordHash\"", Security.User::getPasswordHash);
		
		metamodel.registerProperty(Security.User.class, "getIsAllowed", "\"IsAllowed\"", Security.User::getIsAllowed);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Security.User>>(){}.type, Security.repositories.UserRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Security\".\"User_entity\"";
	}

	@Override
	public Security.User from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Security.User from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Security.User>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Security.User instance = new Security.User(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Security.User instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		if (instance.getRoleURI() != null)items[__index___RoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getRoleURI());;
		items[__index___PasswordHash] = org.revenj.database.postgres.converters.ByteaConverter.toTuple(instance.getPasswordHash());
		items[__index___IsAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Security.User>[] readers;
	
	public Security.User from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.User instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Security.User from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Security.User instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_Name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		if (instance.getRoleURI() != null)items[__index__extended_RoleURI] = new org.revenj.database.postgres.converters.ValueTuple(instance.getRoleURI());;
		items[__index__extended_PasswordHash] = org.revenj.database.postgres.converters.ByteaConverter.toTuple(instance.getPasswordHash());
		items[__index__extended_IsAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Security.User>[] readersExtended;
	
	public Security.User fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Security.User instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Security.User fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Security.User instance) throws java.io.IOException {
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
	private final int __index___RoleURI;
	private final int __index__extended_RoleURI;
	private final int __index___PasswordHash;
	private final int __index__extended_PasswordHash;
	private final int __index___IsAllowed;
	private final int __index__extended_IsAllowed;
}
