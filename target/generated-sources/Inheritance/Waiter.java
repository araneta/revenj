/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package Inheritance;



public class Waiter   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Waiter() {
			
		this.ID = 0;
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
		if (obj == null || obj instanceof Waiter == false)
			return false;
		final Waiter other = (Waiter) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Waiter other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.ID == other.ID))
			return false;
		if(!(this.EmployeeURI == other.EmployeeURI || this.EmployeeURI != null && this.EmployeeURI.equals(other.EmployeeURI)))
			return false;
		if(!(this.EmployeeID == other.EmployeeID))
			return false;
		return true;
	}

	private Waiter(Waiter other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.EmployeeURI = other.EmployeeURI;
		this.EmployeeID = other.EmployeeID;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Waiter(this);
	}

	@Override
	public String toString() {
		return "Waiter(" + URI + ')';
	}
	
	@com.fasterxml.jackson.annotation.JsonCreator private Waiter(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("EmployeeURI") final String EmployeeURI,
			@com.fasterxml.jackson.annotation.JsonProperty("EmployeeID") final int EmployeeID) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.EmployeeURI = EmployeeURI;
		this.EmployeeID = EmployeeID;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -4856832096173970309L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private Waiter setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.WaiterRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"Inheritance\".\"Waiter_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<Waiter> iterator = items.iterator();
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
	
	private Inheritance.Employee Employee;

	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Inheritance.Employee getEmployee()  {
		
		
		if (__locator.isPresent() && (Employee != null && !Employee.getURI().equals(EmployeeURI) || Employee == null && EmployeeURI != null)) {
			Inheritance.repositories.EmployeeRepository repository = __locator.get().resolve(Inheritance.repositories.EmployeeRepository.class);
			Employee = repository.find(EmployeeURI).orElse(null);
		}
		return Employee;
	}

	
	public Waiter setEmployee(final Inheritance.Employee value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Employee\" cannot be null!");
		
		if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Inheritance.Employee\" for property \"Employee\" must be persisted before it's assigned");
		this.Employee = value;
		
		
		this.EmployeeID = value.getID();
		this.EmployeeURI = value.getURI();
		return this;
	}

	
	private String EmployeeURI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("EmployeeURI")
	public String getEmployeeURI()  {
		
		if (this.Employee != null) this.EmployeeURI = this.Employee.getURI();
		return this.EmployeeURI;
	}

	
	private int EmployeeID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("EmployeeID")
	public int getEmployeeID()  {
		
		if (this.Employee != null) this.EmployeeID = this.Employee.getID();
		return EmployeeID;
	}

	
	private Waiter setEmployeeID(final int value) {
		
		this.EmployeeID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.WaiterRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Inheritance.Waiter agg : aggregates) {
						 
						agg.URI = Inheritance.converters.WaiterConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Inheritance.Waiter> oldAggregates = aggregates.getKey();
					java.util.List<Inheritance.Waiter> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Inheritance.Waiter oldAgg = oldAggregates.get(i);
						Inheritance.Waiter newAgg = newAggregates.get(i);
						 
						newAgg.URI = Inheritance.converters.WaiterConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Inheritance.Waiter agg : aggregates) { 
				}
			},
			agg -> { 
				
		Waiter _res = agg.__originalValue;
		agg.__originalValue = (Waiter)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	private transient Waiter __originalValue;
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Waiter")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Waiter")
		public Inheritance.Waiter[] value;

		public static final java.util.function.Function<Inheritance.Waiter[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Waiter")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Waiter")
		public java.util.List<Inheritance.Waiter> value;

		public static final java.util.function.Function<java.util.List<Inheritance.Waiter>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Waiter(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Waiter>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Waiter> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Inheritance.converters.WaiterConverter.buildURI(reader, this);
		this.__originalValue = (Waiter)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Waiter>[] readers, int __index___ID, int __index___EmployeeURI, int __index___EmployeeID) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___EmployeeURI] = (item, reader, context) -> { item.EmployeeURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index___EmployeeID] = (item, reader, context) -> { item.EmployeeID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Waiter>[] readers, int __index__extended_ID, int __index__extended_EmployeeURI, int __index__extended_EmployeeID) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_EmployeeURI] = (item, reader, context) -> { item.EmployeeURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index__extended_EmployeeID] = (item, reader, context) -> { item.EmployeeID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
	}
	
	
	public Waiter(
			final Inheritance.Employee Employee) {
			
		this.ID = --__SequenceCounterID__;
		setEmployee(Employee);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

}
