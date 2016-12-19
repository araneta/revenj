/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Cookbook.converters;



import java.io.*;
import java.util.*;
import java.util.stream.*;
import org.revenj.database.postgres.*;
import org.revenj.database.postgres.converters.*;

public class RecipeConverter implements ObjectConverter<Cookbook.Recipe> {

	@SuppressWarnings("unchecked")
	public RecipeConverter(List<ObjectConverter.ColumnInfo> allColumns, org.revenj.extensibility.Container container) throws java.io.IOException {
		Optional<ObjectConverter.ColumnInfo> column;
		
		
		final java.util.List<ObjectConverter.ColumnInfo> columns =
				allColumns.stream().filter(it -> "Cookbook".equals(it.typeSchema) && "Recipe_entity".equals(it.typeName))
				.collect(Collectors.toList());
		columnCount = columns.size();
		
		readers = new ObjectConverter.Reader[columnCount > 0 ? columnCount : 1];
		for (int i = 0; i < readers.length; i++) {
			readers[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		final java.util.List<ObjectConverter.ColumnInfo> columnsExtended =
				allColumns.stream().filter(it -> "Cookbook".equals(it.typeSchema) && "-ngs_Recipe_type-".equals(it.typeName))
				.collect(Collectors.toList());
		columnCountExtended = columnsExtended.size();
		
		readersExtended = new ObjectConverter.Reader[columnCountExtended > 0 ? columnCountExtended : 1];
		for (int i = 0; i < readersExtended.length; i++) {
			readersExtended[i] = (instance, rdr, ctx) -> { StringConverter.skip(rdr, ctx); return instance; };
		}
		
		container.registerInstance(RecipeConverter.class, this, true);
		container.registerInstance(new org.revenj.patterns.Generic<org.revenj.database.postgres.ObjectConverter<Cookbook.Recipe>>(){}.type, this, true);
		
		column = columns.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Cookbook Recipe_entity. Check if DB is in sync");
		__index___ID = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ID".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ID' column in Cookbook Recipe. Check if DB is in sync");
		__index__extended_ID = (int)column.get().order - 1;
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.Repository<Cookbook.Recipe>>(){}.type, Cookbook.repositories.RecipeRepository::new, false);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.database.postgres.BulkRepository<Cookbook.Recipe>>(){}.type, Cookbook.repositories.RecipeRepository::new, false);
		
		column = columns.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in Cookbook Recipe_entity. Check if DB is in sync");
		__index___name = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "name".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'name' column in Cookbook Recipe. Check if DB is in sync");
		__index__extended_name = (int)column.get().order - 1;
		
		column = columns.stream().filter(it -> "ingredients".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ingredients' column in Cookbook Recipe_entity. Check if DB is in sync");
		__index___ingredients = (int)column.get().order - 1;
		
		column = columnsExtended.stream().filter(it -> "ingredients".equals(it.columnName)).findAny();
		if (!column.isPresent()) throw new java.io.IOException("Unable to find 'ingredients' column in Cookbook Recipe. Check if DB is in sync");
		__index__extended_ingredients = (int)column.get().order - 1;
	}

	public void __configure(org.revenj.extensibility.Container container, org.revenj.extensibility.PluginLoader plugins, org.revenj.database.postgres.jinq.JinqMetaModel metamodel) throws java.io.IOException {
		
		
		
		Cookbook.Recipe.__configureConverter(readers, __index___ID, __index___name, __index___ingredients);
		
		Cookbook.Recipe.__configureConverterExtended(readersExtended, __index__extended_ID, __index__extended_name, __index__extended_ingredients);
		
		metamodel.registerDataSource(Cookbook.Recipe.class, "\"Cookbook\".\"Recipe_entity\"");
		
		metamodel.registerProperty(Cookbook.Recipe.class, "getURI", "\"URI\"", Cookbook.Recipe::getURI);
		
		metamodel.registerProperty(Cookbook.Recipe.class, "getID", "\"ID\"", Cookbook.Recipe::getID);
		
		container.register(Cookbook.repositories.RecipeRepository.class);
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.SearchableRepository<Cookbook.Recipe>>(){}.type, Cookbook.repositories.RecipeRepository::new, false);
		
		metamodel.registerProperty(Cookbook.Recipe.class, "getName", "\"name\"", Cookbook.Recipe::getName);
		
		metamodel.registerProperty(Cookbook.Recipe.class, "getIngredients", "\"ingredients\"", Cookbook.Recipe::getIngredients);
		
		container.registerFactory(new org.revenj.patterns.Generic<org.revenj.patterns.PersistableRepository<Cookbook.Recipe>>(){}.type, Cookbook.repositories.RecipeRepository::new, false);
	}

	@Override
	public String getDbName() {
		return "\"Cookbook\".\"Recipe_entity\"";
	}

	@Override
	public Cookbook.Recipe from(PostgresReader reader) throws java.io.IOException {
		return from(reader, 0);
	}

	private Cookbook.Recipe from(PostgresReader reader, int outerContext, int context, ObjectConverter.Reader<Cookbook.Recipe>[] readers) throws java.io.IOException {
		reader.read(outerContext);
		Cookbook.Recipe instance = new Cookbook.Recipe(reader, context, readers);
		reader.read(outerContext);
		return instance;
	}

	@Override
	public PostgresTuple to(Cookbook.Recipe instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCount];
		
		items[__index___ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index___name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index___ingredients] = org.revenj.database.postgres.converters.ArrayTuple.create(instance.getIngredients(), org.revenj.database.postgres.converters.StringConverter::toTuple);
		return RecordTuple.from(items);
	}

	
	private final int columnCount;
	private final ObjectConverter.Reader<Cookbook.Recipe>[] readers;
	
	public Cookbook.Recipe from(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Cookbook.Recipe instance = from(reader, context, context == 0 ? 1 : context << 1, readers);
		reader.read();
		return instance;
	}

	public Cookbook.Recipe from(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readers);
	}
	
	public PostgresTuple toExtended(Cookbook.Recipe instance) {
		if (instance == null) return null;
		PostgresTuple[] items = new PostgresTuple[columnCountExtended];
		
		items[__index__extended_ID] = org.revenj.database.postgres.converters.IntConverter.toTuple(instance.getID());
		items[__index__extended_name] = org.revenj.database.postgres.converters.StringConverter.toTuple(instance.getName());
		items[__index__extended_ingredients] = org.revenj.database.postgres.converters.ArrayTuple.create(instance.getIngredients(), org.revenj.database.postgres.converters.StringConverter::toTuple);
		return RecordTuple.from(items);
	}
	private final int columnCountExtended;
	private final ObjectConverter.Reader<Cookbook.Recipe>[] readersExtended;
	
	public Cookbook.Recipe fromExtended(PostgresReader reader, int context) throws java.io.IOException {
		int cur = reader.read();
		if (cur == ',' || cur == ')') return null;
		Cookbook.Recipe instance = from(reader, context, context == 0 ? 1 : context << 1, readersExtended);
		reader.read();
		return instance;
	}

	public Cookbook.Recipe fromExtended(PostgresReader reader, int outerContext, int context) throws java.io.IOException {
		return from(reader, outerContext, context, readersExtended);
	}
	private final int __index___ID;
	private final int __index__extended_ID;
	
	public static String buildURI(org.revenj.database.postgres.PostgresBuffer _sw, Cookbook.Recipe instance) throws java.io.IOException {
		return buildURI(instance.getID(), _sw);
	}
	public static String buildURI(int ID, org.revenj.database.postgres.PostgresBuffer _sw) throws java.io.IOException {
		_sw.initBuffer();
		String _tmp;
		org.revenj.database.postgres.converters.IntConverter.serializeURI(_sw, ID);
		return _sw.bufferToString();
	}
	private final int __index___name;
	private final int __index__extended_name;
	private final int __index___ingredients;
	private final int __index__extended_ingredients;
}
