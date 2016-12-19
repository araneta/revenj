/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package hello;



public class World   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public World() {
			
		this.ID = 0;
		this.message = "";
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
		if (obj == null || obj instanceof World == false)
			return false;
		final World other = (World) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final World other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.ID == other.ID))
			return false;
		if(!(this.message.equals(other.message)))
			return false;
		return true;
	}

	private World(World other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.message = other.message;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new World(this);
	}

	@Override
	public String toString() {
		return "World(" + URI + ')';
	}
	
	
	public World(
			final String message) {
			
		this.ID = --__SequenceCounterID__;
		setMessage(message);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private World(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("message") final String message) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.message = message == null ? "" : message;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 1173800173043155166L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private World setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		hello.repositories.WorldRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"hello\".\"World_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<World> iterator = items.iterator();
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
	
	private String message;

	
	@com.fasterxml.jackson.annotation.JsonProperty("message")
	public String getMessage()  {
		
		return message;
	}

	
	public World setMessage(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"message\" cannot be null!");
		this.message = value;
		
		return this;
	}

	private transient World __originalValue;
	
	static {
		hello.repositories.WorldRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (hello.World agg : aggregates) {
						 
						agg.URI = hello.converters.WorldConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<hello.World> oldAggregates = aggregates.getKey();
					java.util.List<hello.World> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						hello.World oldAgg = oldAggregates.get(i);
						hello.World newAgg = newAggregates.get(i);
						 
						newAgg.URI = hello.converters.WorldConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (hello.World agg : aggregates) { 
				}
			},
			agg -> { 
				
		World _res = agg.__originalValue;
		agg.__originalValue = (World)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfhello.World")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "hello.World")
		public hello.World[] value;

		public static final java.util.function.Function<hello.World[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfhello.World")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "hello.World")
		public java.util.List<hello.World> value;

		public static final java.util.function.Function<java.util.List<hello.World>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public World(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<World>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<World> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = hello.converters.WorldConverter.buildURI(reader, this);
		this.__originalValue = (World)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<World>[] readers, int __index___ID, int __index___message) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___message] = (item, reader, context) -> { item.message = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<World>[] readers, int __index__extended_ID, int __index__extended_message) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_message] = (item, reader, context) -> { item.message = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
}
