/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package Inheritance;



public class Employee   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Employee() {
			
		this.ID = 0;
		this.StartedWorking = java.time.LocalDate.now();
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
		if (obj == null || obj instanceof Employee == false)
			return false;
		final Employee other = (Employee) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Employee other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.ID == other.ID))
			return false;
		if(!(this.StartedWorking.equals(other.StartedWorking)))
			return false;
		if(!(this.PersonURI == other.PersonURI || this.PersonURI != null && this.PersonURI.equals(other.PersonURI)))
			return false;
		if(!(this.PersonID == other.PersonID))
			return false;
		return true;
	}

	private Employee(Employee other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.StartedWorking = other.StartedWorking;
		this.PersonURI = other.PersonURI;
		this.PersonID = other.PersonID;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Employee(this);
	}

	@Override
	public String toString() {
		return "Employee(" + URI + ')';
	}
	
	@com.fasterxml.jackson.annotation.JsonCreator private Employee(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("StartedWorking") final java.time.LocalDate StartedWorking,
			@com.fasterxml.jackson.annotation.JsonProperty("PersonURI") final String PersonURI,
			@com.fasterxml.jackson.annotation.JsonProperty("PersonID") final int PersonID) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.StartedWorking = StartedWorking == null ? org.revenj.Utils.MIN_LOCAL_DATE : StartedWorking;
		this.PersonURI = PersonURI;
		this.PersonID = PersonID;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 5364138656247266583L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private Employee setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.EmployeeRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"Inheritance\".\"Employee_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<Employee> iterator = items.iterator();
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
	
	private java.time.LocalDate StartedWorking;

	
	@com.fasterxml.jackson.annotation.JsonProperty("StartedWorking")
	public java.time.LocalDate getStartedWorking()  {
		
		return StartedWorking;
	}

	
	public Employee setStartedWorking(final java.time.LocalDate value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"StartedWorking\" cannot be null!");
		this.StartedWorking = value;
		
		return this;
	}

	
	private Inheritance.Person Person;

	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Inheritance.Person getPerson()  {
		
		
		if (__locator.isPresent() && (Person != null && !Person.getURI().equals(PersonURI) || Person == null && PersonURI != null)) {
			Inheritance.repositories.PersonRepository repository = __locator.get().resolve(Inheritance.repositories.PersonRepository.class);
			Person = repository.find(PersonURI).orElse(null);
		}
		return Person;
	}

	
	public Employee setPerson(final Inheritance.Person value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Person\" cannot be null!");
		
		if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Inheritance.Person\" for property \"Person\" must be persisted before it's assigned");
		this.Person = value;
		
		
		this.PersonID = value.getID();
		this.PersonURI = value.getURI();
		return this;
	}

	
	private String PersonURI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("PersonURI")
	public String getPersonURI()  {
		
		if (this.Person != null) this.PersonURI = this.Person.getURI();
		return this.PersonURI;
	}

	
	private int PersonID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("PersonID")
	public int getPersonID()  {
		
		if (this.Person != null) this.PersonID = this.Person.getID();
		return PersonID;
	}

	
	private Employee setPersonID(final int value) {
		
		this.PersonID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.EmployeeRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Inheritance.Employee agg : aggregates) {
						 
						agg.URI = Inheritance.converters.EmployeeConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Inheritance.Employee> oldAggregates = aggregates.getKey();
					java.util.List<Inheritance.Employee> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Inheritance.Employee oldAgg = oldAggregates.get(i);
						Inheritance.Employee newAgg = newAggregates.get(i);
						 
						newAgg.URI = Inheritance.converters.EmployeeConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Inheritance.Employee agg : aggregates) { 
				}
			},
			agg -> { 
				
		Employee _res = agg.__originalValue;
		agg.__originalValue = (Employee)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	private transient Employee __originalValue;
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Employee")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Employee")
		public Inheritance.Employee[] value;

		public static final java.util.function.Function<Inheritance.Employee[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Employee")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Employee")
		public java.util.List<Inheritance.Employee> value;

		public static final java.util.function.Function<java.util.List<Inheritance.Employee>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Employee(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Employee>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Employee> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Inheritance.converters.EmployeeConverter.buildURI(reader, this);
		this.__originalValue = (Employee)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Employee>[] readers, int __index___ID, int __index___StartedWorking, int __index___PersonURI, int __index___PersonID) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___StartedWorking] = (item, reader, context) -> { item.StartedWorking = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); return item; };
		readers[__index___PersonURI] = (item, reader, context) -> { item.PersonURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index___PersonID] = (item, reader, context) -> { item.PersonID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Employee>[] readers, int __index__extended_ID, int __index__extended_StartedWorking, int __index__extended_PersonURI, int __index__extended_PersonID) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_StartedWorking] = (item, reader, context) -> { item.StartedWorking = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); return item; };
		readers[__index__extended_PersonURI] = (item, reader, context) -> { item.PersonURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index__extended_PersonID] = (item, reader, context) -> { item.PersonID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
	}
	
	
	public Employee(
			final java.time.LocalDate StartedWorking,
			final Inheritance.Person Person) {
			
		this.ID = --__SequenceCounterID__;
		setStartedWorking(StartedWorking);
		setPerson(Person);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

}
