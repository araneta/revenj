/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package Inheritance;



public class Customer   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Customer() {
			
		this.ID = 0;
		this.name = "";
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
		if (obj == null || obj instanceof Customer == false)
			return false;
		final Customer other = (Customer) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Customer other) {
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
		return true;
	}

	private Customer(Customer other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.name = other.name;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Customer(this);
	}

	@Override
	public String toString() {
		return "Customer(" + URI + ')';
	}
	
	@com.fasterxml.jackson.annotation.JsonCreator private Customer(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("name") final String name) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.name = name == null ? "" : name;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -3888212827959324734L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private Customer setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.CustomerRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"Inheritance\".\"Customer_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<Customer> iterator = items.iterator();
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

	
	public Customer setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
		this.name = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.CustomerRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Inheritance.Customer agg : aggregates) {
						 
						agg.URI = Inheritance.converters.CustomerConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Inheritance.Customer> oldAggregates = aggregates.getKey();
					java.util.List<Inheritance.Customer> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Inheritance.Customer oldAgg = oldAggregates.get(i);
						Inheritance.Customer newAgg = newAggregates.get(i);
						 
						newAgg.URI = Inheritance.converters.CustomerConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Inheritance.Customer agg : aggregates) { 
				}
			},
			agg -> { 
				
		Customer _res = agg.__originalValue;
		agg.__originalValue = (Customer)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	private transient Customer __originalValue;
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Customer")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Customer")
		public Inheritance.Customer[] value;

		public static final java.util.function.Function<Inheritance.Customer[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Customer")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Customer")
		public java.util.List<Inheritance.Customer> value;

		public static final java.util.function.Function<java.util.List<Inheritance.Customer>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Customer(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Customer>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Customer> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Inheritance.converters.CustomerConverter.buildURI(reader, this);
		this.__originalValue = (Customer)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Customer>[] readers, int __index___ID, int __index___name) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Customer>[] readers, int __index__extended_ID, int __index__extended_name) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
	
	
	public Customer(
			final String name) {
			
		this.ID = --__SequenceCounterID__;
		setName(name);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

}
