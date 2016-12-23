/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package Inheritance;



public class LineItem   implements java.lang.Cloneable, java.io.Serializable {
	
	
	
	public LineItem() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.productName = "";
		this.quantity = java.math.BigDecimal.ZERO;
		this.OrderID = 0;
		this.Index = 0;
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
		if (obj == null || obj instanceof LineItem == false)
			return false;
		final LineItem other = (LineItem) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final LineItem other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.productName.equals(other.productName)))
			return false;
		if(!(this.quantity == other.quantity || this.quantity != null && other.quantity != null && this.quantity.compareTo(other.quantity) == 0))
			return false;
		if(!(this.OrderID == other.OrderID))
			return false;
		if(!(this.Index == other.Index))
			return false;
		return true;
	}

	private LineItem(LineItem other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.productName = other.productName;
		this.quantity = other.quantity;
		this.OrderID = other.OrderID;
		this.Index = other.Index;
	}

	@Override
	public Object clone() {
		return new LineItem(this);
	}

	@Override
	public String toString() {
		return "LineItem(" + URI + ')';
	}
	
	
	public LineItem(
			final String productName,
			final java.math.BigDecimal quantity) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setProductName(productName);
		setQuantity(quantity);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private LineItem(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("productName") final String productName,
			@com.fasterxml.jackson.annotation.JsonProperty("quantity") final java.math.BigDecimal quantity,
			@com.fasterxml.jackson.annotation.JsonProperty("OrderID") final int OrderID,
			@com.fasterxml.jackson.annotation.JsonProperty("Index") final int Index) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.productName = productName == null ? "" : productName;
		this.quantity = quantity == null ? java.math.BigDecimal.ZERO : quantity;
		this.OrderID = OrderID;
		this.Index = Index;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 1110068904377445895L;
	
	private String productName;

	
	@com.fasterxml.jackson.annotation.JsonProperty("productName")
	public String getProductName()  {
		
		return productName;
	}

	
	public LineItem setProductName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"productName\" cannot be null!");
		this.productName = value;
		
		return this;
	}

	
	private java.math.BigDecimal quantity;

	
	@com.fasterxml.jackson.annotation.JsonProperty("quantity")
	public java.math.BigDecimal getQuantity()  {
		
		return quantity;
	}

	
	public LineItem setQuantity(final java.math.BigDecimal value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"quantity\" cannot be null!");
		this.quantity = value;
		
		return this;
	}

	
	private int OrderID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("OrderID")
	public int getOrderID()  {
		
		return OrderID;
	}

	
	private LineItem setOrderID(final int value) {
		
		this.OrderID = value;
		
		return this;
	}

	
	private int Index;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Index")
	public int getIndex()  {
		
		return Index;
	}

	
	private LineItem setIndex(final int value) {
		
		this.Index = value;
		
		return this;
	}

	
	static {
		Inheritance.Order.__bindToitems((parent, arg) -> {
			try {
				int i = 0;
				for (Inheritance.LineItem e : parent.getItems()) { 
					e.OrderID = parent.getID();
					e.Index = i++; 
					e.URI = Inheritance.converters.LineItemConverter.buildURI(arg.getKey(), e);
				}
			} catch (java.io.IOException ex) {
				throw new RuntimeException(ex);
			}
		});
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.LineItem")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.LineItem")
		public Inheritance.LineItem[] value;

		public static final java.util.function.Function<Inheritance.LineItem[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.LineItem")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.LineItem")
		public java.util.List<Inheritance.LineItem> value;

		public static final java.util.function.Function<java.util.List<Inheritance.LineItem>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public LineItem(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<LineItem>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<LineItem> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Inheritance.converters.LineItemConverter.buildURI(reader, this);
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<LineItem>[] readers, int __index___productName, int __index___quantity, int __index___OrderID, int __index___Index) {
		
		readers[__index___productName] = (item, reader, context) -> { item.productName = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___quantity] = (item, reader, context) -> { item.quantity = org.revenj.database.postgres.converters.DecimalConverter.parse(reader, false); return item; };
		readers[__index___OrderID] = (item, reader, context) -> { item.OrderID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index___Index] = (item, reader, context) -> { item.Index = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<LineItem>[] readers, int __index__extended_productName, int __index__extended_quantity, int __index__extended_OrderID, int __index__extended_Index) {
		
		readers[__index__extended_productName] = (item, reader, context) -> { item.productName = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_quantity] = (item, reader, context) -> { item.quantity = org.revenj.database.postgres.converters.DecimalConverter.parse(reader, false); return item; };
		readers[__index__extended_OrderID] = (item, reader, context) -> { item.OrderID = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
		readers[__index__extended_Index] = (item, reader, context) -> { item.Index = org.revenj.database.postgres.converters.IntConverter.parse(reader); return item; };
	}
}
