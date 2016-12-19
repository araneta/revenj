/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Inheritance;



public class Order   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Order() {
			
		this.ID = 0;
		this.placed = java.time.LocalDate.now();
		this.items = new java.util.ArrayList<Inheritance.LineItem>(4);
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
		if (obj == null || obj instanceof Order == false)
			return false;
		final Order other = (Order) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Order other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.ID == other.ID))
			return false;
		if(!(this.placed.equals(other.placed)))
			return false;
		if(!(this.deadline == other.deadline || this.deadline != null && this.deadline.equals(other.deadline)))
			return false;
		if(!(this.customerURI == other.customerURI || this.customerURI != null && this.customerURI.equals(other.customerURI)))
			return false;
		if(!(this.customerID == other.customerID || this.customerID != null && this.customerID.equals(other.customerID)))
			return false;
		if(!((this.items == other.items || this.items != null && this.items.equals(other.items))))
			return false;
		return true;
	}

	private Order(Order other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.ID = other.ID;
		this.placed = other.placed;
		this.deadline = other.deadline;
		this.customerURI = other.customerURI;
		this.customerID = other.customerID;
		this.items = new java.util.ArrayList<Inheritance.LineItem>(other.items.size());
			if (other.items != null) {
				for (Inheritance.LineItem it : other.items) {
					this.items.add((Inheritance.LineItem)it.clone());
				}
			};
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Order(this);
	}

	@Override
	public String toString() {
		return "Order(" + URI + ')';
	}
	
	
	public Order(
			final java.time.LocalDate placed,
			final java.time.LocalDate deadline,
			final Inheritance.Customer customer,
			final java.util.List<Inheritance.LineItem> items) {
			
		this.ID = --__SequenceCounterID__;
		setPlaced(placed);
		setDeadline(deadline);
		setCustomer(customer);
		setItems(items);
		this.URI = java.lang.Integer.toString(this.ID);
		this.URI = java.lang.Integer.toString(this.ID);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private Order(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("ID") final int ID,
			@com.fasterxml.jackson.annotation.JsonProperty("placed") final java.time.LocalDate placed,
			@com.fasterxml.jackson.annotation.JsonProperty("deadline") final java.time.LocalDate deadline,
			@com.fasterxml.jackson.annotation.JsonProperty("customerURI") final String customerURI,
			@com.fasterxml.jackson.annotation.JsonProperty("customerID") final Integer customerID,
			@com.fasterxml.jackson.annotation.JsonProperty("items") final java.util.List<Inheritance.LineItem> items) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.ID = ID;
		this.placed = placed == null ? org.revenj.Utils.MIN_LOCAL_DATE : placed;
		this.deadline = deadline;
		this.customerURI = customerURI;
		this.customerID = customerID;
		this.items = items == null ? new java.util.ArrayList<Inheritance.LineItem>(4) : items;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -5400249900882737892L;
	
	private int ID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ID")
	public int getID()  {
		
		return ID;
	}

	
	private Order setID(final int value) {
		
		this.ID = value;
		
		return this;
	}

	
	static {
		Inheritance.repositories.OrderRepository.__setupSequenceID((items, connection) -> {
			try (java.sql.PreparedStatement st = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT nextval('\"Inheritance\".\"Order_ID_seq\"'::regclass)::int FROM generate_series(1, ?)")) {
				st.setInt(1, items.size());
				try (java.sql.ResultSet rs = st.executeQuery()) {
					java.util.Iterator<Order> iterator = items.iterator();
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
	
	private java.time.LocalDate placed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("placed")
	public java.time.LocalDate getPlaced()  {
		
		return placed;
	}

	
	public Order setPlaced(final java.time.LocalDate value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"placed\" cannot be null!");
		this.placed = value;
		
		return this;
	}

	
	private java.time.LocalDate deadline;

	
	@com.fasterxml.jackson.annotation.JsonProperty("deadline")
	public java.time.LocalDate getDeadline()  {
		
		return deadline;
	}

	
	public Order setDeadline(final java.time.LocalDate value) {
		
		this.deadline = value;
		
		return this;
	}

	
	private Inheritance.Customer customer;

	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Inheritance.Customer getCustomer()  {
		
		
		if (__locator.isPresent() && (customer != null && !customer.getURI().equals(customerURI) || customer == null && customerURI != null)) {
			Inheritance.repositories.CustomerRepository repository = __locator.get().resolve(Inheritance.repositories.CustomerRepository.class);
			customer = repository.find(customerURI).orElse(null);
		}
		if (this.customerURI == null && this.customer != null) this.customer = null;
		return customer;
	}

	
	public Order setCustomer(final Inheritance.Customer value) {
		
		
		if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Inheritance.Customer\" for property \"customer\" must be persisted before it's assigned");
		this.customer = value;
		
		
		if (value == null && this.customerID != null) {
			this.customerID = null;
		} else if (value != null) {
			this.customerID = value.getID();
		}
		this.customerURI = value != null ? value.getURI() : null;
		return this;
	}

	
	private String customerURI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("customerURI")
	public String getCustomerURI()  {
		
		if (this.customer != null) this.customerURI = this.customer.getURI();
		return this.customerURI;
	}

	
	private Integer customerID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("customerID")
	public Integer getCustomerID()  {
		
		if (this.customer != null) this.customerID = this.customer.getID();
		return customerID;
	}

	
	private Order setCustomerID(final Integer value) {
		
		this.customerID = value;
		
		return this;
	}

	
	static void __bindToitems(java.util.function.BiConsumer<Inheritance.Order, java.util.Map.Entry<org.revenj.database.postgres.PostgresWriter, org.revenj.patterns.ServiceLocator>> binder) {
		__binderitems = binder;
	}

	private static java.util.function.BiConsumer<Inheritance.Order, java.util.Map.Entry<org.revenj.database.postgres.PostgresWriter, org.revenj.patterns.ServiceLocator>> __binderitems;
	
	private java.util.List<Inheritance.LineItem> items;

	
	@com.fasterxml.jackson.annotation.JsonProperty("items")
	public java.util.List<Inheritance.LineItem> getItems()  {
		
		return items;
	}

	
	public Order setItems(final java.util.List<Inheritance.LineItem> value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"items\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.items = value;
		
		return this;
	}

	private transient Order __originalValue;
	
	static {
		Inheritance.repositories.OrderRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Inheritance.Order agg : aggregates) {
						
						__binderitems.accept(agg, arg); 
						agg.URI = Inheritance.converters.OrderConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Inheritance.Order> oldAggregates = aggregates.getKey();
					java.util.List<Inheritance.Order> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Inheritance.Order oldAgg = oldAggregates.get(i);
						Inheritance.Order newAgg = newAggregates.get(i);
						
						__binderitems.accept(newAgg, arg); 
						newAgg.URI = Inheritance.converters.OrderConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Inheritance.Order agg : aggregates) { 
				}
			},
			agg -> { 
				
		Order _res = agg.__originalValue;
		agg.__originalValue = (Order)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Order")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Order")
		public Inheritance.Order[] value;

		public static final java.util.function.Function<Inheritance.Order[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.Order")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.Order")
		public java.util.List<Inheritance.Order> value;

		public static final java.util.function.Function<java.util.List<Inheritance.Order>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Order(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Order>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Order> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Inheritance.converters.OrderConverter.buildURI(reader, this);
		this.__originalValue = (Order)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Order>[] readers, int __index___ID, int __index___placed, int __index___deadline, int __index___customerURI, int __index___customerID, Inheritance.converters.LineItemConverter __converter_items, int __index___items) {
		
		readers[__index___ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___placed] = (item, reader, context) -> { item.placed = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); return item; };
		readers[__index___deadline] = (item, reader, context) -> { item.deadline = org.revenj.database.postgres.converters.DateConverter.parse(reader, true); return item; };
		readers[__index___customerURI] = (item, reader, context) -> { item.customerURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index___customerID] = (item, reader, context) -> { item.customerID = org.revenj.database.postgres.converters.IntConverter.parseNullable(reader); return item; };
		readers[__index___items] = (item, reader, context) -> { { java.util.List<Inheritance.LineItem> __list = org.revenj.database.postgres.converters.ArrayTuple.parse(reader, context, __converter_items::from); if (__list != null) {item.items = __list;} else item.items = new java.util.ArrayList<Inheritance.LineItem>(4); }; return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Order>[] readers, int __index__extended_ID, int __index__extended_placed, int __index__extended_deadline, int __index__extended_customerURI, int __index__extended_customerID, final Inheritance.converters.LineItemConverter __converter_items, int __index__extended_items) {
		
		readers[__index__extended_ID] = (item, reader, context) -> { item.ID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_placed] = (item, reader, context) -> { item.placed = org.revenj.database.postgres.converters.DateConverter.parse(reader, false); return item; };
		readers[__index__extended_deadline] = (item, reader, context) -> { item.deadline = org.revenj.database.postgres.converters.DateConverter.parse(reader, true); return item; };
		readers[__index__extended_customerURI] = (item, reader, context) -> { item.customerURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index__extended_customerID] = (item, reader, context) -> { item.customerID = org.revenj.database.postgres.converters.IntConverter.parseNullable(reader); return item; };
		readers[__index__extended_items] = (item, reader, context) -> { { java.util.List<Inheritance.LineItem> __list = org.revenj.database.postgres.converters.ArrayTuple.parse(reader, context, __converter_items::fromExtended); if (__list != null) {item.items = __list;} else item.items = new java.util.ArrayList<Inheritance.LineItem>(4); }; return item; };
	}
}
