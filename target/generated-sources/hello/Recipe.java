/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package hello;



public class Recipe   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Recipe() {
			
		this.ID = 0;
		this.name = "";
		this.ingredients = new String[] { };
		this.URI = java.lang.Integer.toString(this.ID);
	}

	
	private String URI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("URI")
	public String getURI()  {
		
		return this.URI;
	}

	
	@Override
	public int hashCode() {
		return URI.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj instanceof Recipe == false)
			return false;
		final Recipe other = (Recipe) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Recipe other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.ID == other.ID))
			return false;
		if(!(this.name.equals(other.name)))
			return false;
		if(!(java.util.Arrays.equals(this.ingredients, other.ingredients)))
			return false;
		return true;
	}

	private Recipe(Recipe other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.name = other.name;
		this.ingredients = java.util.Arrays.copyOf(other.ingredients, other.ingredients.length);
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Recipe(this);
	}

	@Override
	public String toString() {
		return "Recipe(" + URI + ')';
	}
	
	
	public Recipe(
			final String name,
			final String[] ingredients) {
			
		this.ID = --__SequenceCounterID__;
		setName(name);
		setIngredients(ingredients);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private Recipe(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("name") final String name,
			@com.fasterxml.jackson.annotation.JsonProperty("ingredients") final String[] ingredients) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.name = name == null ? "" : name;
		this.ingredients = ingredients == null ? new String[] { } : ingredients;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -7183501481382557664L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private Recipe setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		hello.repositories.RecipeRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"hello\".\"Recipe_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<Recipe> iterator = items.iterator();
					while (rs.next()) {
						iterator.next().setID(rs.getInt(1));
					}
				}
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	private static int __SequenceCounterID__;
	
	private String name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("name")
	public String getName()  {
		
		return name;
	}

	
	public Recipe setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
		this.name = value;
		
		return this;
	}

	private static final String[] _defaultingredients = new String[] { };
	
	private String[] ingredients;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ingredients")
	public String[] getIngredients()  {
		
		return ingredients;
	}

	
	public Recipe setIngredients(final String[] value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"ingredients\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.ingredients = value;
		
		return this;
	}

	private transient Recipe __originalValue;
	
	static {
		hello.repositories.RecipeRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (hello.Recipe agg : aggregates) {
						 
						agg.URI = hello.converters.RecipeConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<hello.Recipe> oldAggregates = aggregates.getKey();
					java.util.List<hello.Recipe> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						hello.Recipe oldAgg = oldAggregates.get(i);
						hello.Recipe newAgg = newAggregates.get(i);
						 
						newAgg.URI = hello.converters.RecipeConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (hello.Recipe agg : aggregates) { 
				}
			},
			agg -> { 
				
		Recipe _res = agg.__originalValue;
		agg.__originalValue = (Recipe)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfhello.Recipe")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "hello.Recipe")
		public hello.Recipe[] value;

		public static final java.util.function.Function<hello.Recipe[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfhello.Recipe")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "hello.Recipe")
		public java.util.List<hello.Recipe> value;

		public static final java.util.function.Function<java.util.List<hello.Recipe>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Recipe(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Recipe>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Recipe> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = hello.converters.RecipeConverter.buildURI(reader, this);
		this.__originalValue = (Recipe)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Recipe>[] readers, int __index___ID, int __index___name, int __index___ingredients) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___ingredients] = (item, reader, context) -> { { java.util.List<String> __list = org.revenj.database.postgres.converters.StringConverter.parseCollection(reader, context, false); if(__list != null) {item.ingredients = __list.toArray(new String[0]);} else item.ingredients = new String[] { }; }; return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Recipe>[] readers, int __index__extended_ID, int __index__extended_name, int __index__extended_ingredients) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_ingredients] = (item, reader, context) -> { { java.util.List<String> __list = org.revenj.database.postgres.converters.StringConverter.parseCollection(reader, context, false); if(__list != null) {item.ingredients = __list.toArray(new String[0]);} else item.ingredients = new String[] { }; }; return item; };
	}
}
