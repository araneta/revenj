/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package Inheritance;



public class Person   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Person() {
			
		this.ID = 0;
		this.Name = "";
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
		if (obj == null || obj instanceof Person == false)
			return false;
		final Person other = (Person) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Person other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.ID == other.ID))
			return false;
		if(!(this.Name.equals(other.Name)))
			return false;
		return true;
	}

	private Person(Person other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.Name = other.Name;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Person(this);
	}

	@Override
	public String toString() {
		return "Person(" + URI + ')';
	}
	
	
	public Person(
			final String Name) {
			
		this.ID = --__SequenceCounterID__;
		setName(Name);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private Person(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("Name") final String Name) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.Name = Name == null ? "" : Name;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 506324217543724323L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private Person setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.PersonRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"Inheritance\".\"Person_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<Person> iterator = items.iterator();
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
	
	private String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		return Name;
	}

	
	public Person setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Name\" cannot be null!");
		this.Name = value;
		
		return this;
	}

	private transient Person __originalValue;
	
	static {
		Inheritance.repositories.PersonRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Inheritance.Person agg : aggregates) {
						 
						agg.URI = Inheritance.converters.PersonConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Inheritance.Person> oldAggregates = aggregates.getKey();
					java.util.List<Inheritance.Person> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Inheritance.Person oldAgg = oldAggregates.get(i);
						Inheritance.Person newAgg = newAggregates.get(i);
						 
						newAgg.URI = Inheritance.converters.PersonConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Inheritance.Person agg : aggregates) { 
				}
			},
			agg -> { 
				
		Person _res = agg.__originalValue;
		agg.__originalValue = (Person)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Person")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Person")
		public Inheritance.Person[] value;

		public static final java.util.function.Function<Inheritance.Person[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Person")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Person")
		public java.util.List<Inheritance.Person> value;

		public static final java.util.function.Function<java.util.List<Inheritance.Person>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Person(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Person>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Person> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Inheritance.converters.PersonConverter.buildURI(reader, this);
		this.__originalValue = (Person)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Person>[] readers, int __index___ID, int __index___Name) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Person>[] readers, int __index__extended_ID, int __index__extended_Name) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
}
