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

public class UserConverter implements ObjectConverter<security.User> {

	@SuppressWarnings("unchecked")
	public UserConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "User_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "security".equals(it.typeSchema) && "-ngs_User_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(UserConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<security.User>>(){}.type, this, true);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<security.User>>(){}.type, security.repositories.UserRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<security.User>>(){}.type, security.repositories.UserRepository::new, false);
		
		column = columns.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in security User_entity. Check if DB is in sync");
		__index___name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in security User. Check if DB is in sync");
		__index__extended_name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "roles".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'roles' column in security User_entity. Check if DB is in sync");
		__index___roles = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "roles".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'roles' column in security User. Check if DB is in sync");
		__index__extended_roles = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "password".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'password' column in security User_entity. Check if DB is in sync");
		__index___password = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "password".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'password' column in security User. Check if DB is in sync");
		__index__extended_password = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "isAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'isAllowed' column in security User_entity. Check if DB is in sync");
		__index___isAllowed = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "isAllowed".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'isAllowed' column in security User. Check if DB is in sync");
		__index__extended_isAllowed = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		security.User.__configureConverter(readers, __index___name, __index___roles, __index___password, __index___isAllowed);
		
		security.User.__configureConverterExtended(readersExtended, __index__extended_name, __index__extended_roles, __index__extended_password, __index__extended_isAllowed);
		
		metamodel.registerDataSource(security.User.class, "\"security\".\"User_entity\"");
		
		metamodel.registerProperty(security.User.class, "getURI", "\"URI\"", security.User::getURI);
		
		container.register(security.repositories.UserRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<security.User>>(){}.type, security.repositories.UserRepository::new, false);
		
		metamodel.registerProperty(security.User.class, "getName", "\"name\"", security.User::getName);
		
		metamodel.registerProperty(security.User.class, "getRoles", "\"roles\"", security.User::getRoles);
		
		metamodel.registerProperty(security.User.class, "getPassword", "\"password\"", security.User::getPassword);
		
		metamodel.registerProperty(security.User.class, "getIsAllowed", "\"isAllowed\"", security.User::getIsAllowed);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<security.User>>(){}.type, security.repositories.UserRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"security\".\"User_entity\"";
	}

	@Override
	public security.User from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private security.User from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<security.User>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		security.User instance = new security.User(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(security.User instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index___roles] = org.revenj.database.postgres.converters.ArrayTuple.create(instance.getRoles(), org.revenj.database.postgres.converters.StringConverter::toTuple);
		items[__index___password] = org.revenj.database.postgres.converters.ByteaConverter.toTuple(instance.getPassword());
		items[__index___isAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<security.User>[] readers;
	
	public security.User from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.User instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public security.User from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(security.User instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index__extended_roles] = org.revenj.database.postgres.converters.ArrayTuple.create(instance.getRoles(), org.revenj.database.postgres.converters.StringConverter::toTuple);
		items[__index__extended_password] = org.revenj.database.postgres.converters.ByteaConverter.toTuple(instance.getPassword());
		items[__index__extended_isAllowed] = org.revenj.database.postgres.converters.BoolConverter.toTuple(instance.getIsAllowed());
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<security.User>[] readersExtended;
	
	public security.User fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		security.User instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public security.User fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, security.User instance) throws java.io.IOException {
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
	private final int __index___roles;
	private final int __index__extended_roles;
	private final int __index___password;
	private final int __index__extended_password;
	private final int __index___isAllowed;
	private final int __index__extended_isAllowed;
}
