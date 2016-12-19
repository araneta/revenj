/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Inheritance;



public final class OrderInfo   implements java.io.Serializable, org.revenj.patterns.Identifiable {
	
	
	@com.fasterxml.jackson.annotation.JsonCreator 
	public OrderInfo(
			@com.fasterxml.jackson.annotation.JsonProperty("URI")  final String URI,
			@com.fasterxml.jackson.annotation.JsonProperty("placed")  final java.time.LocalDate placed,
			@com.fasterxml.jackson.annotation.JsonProperty("deadline")  final java.time.LocalDate deadline,
			@com.fasterxml.jackson.annotation.JsonProperty("customerName")  final String customerName) {
			
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.placed = placed != null ? placed : org.revenj.Utils.MIN_LOCAL_DATE;
		this.deadline = deadline;
		this.customerName = customerName;
	}

	
	
	public OrderInfo() {
			
		this.URI = java.util.UUID.randomUUID().toString();
		this.placed = java.time.LocalDate.now();
		this.deadline = null;
		this.customerName = null;
	}

	
	private final String URI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("URI")
	public String getURI()  {
		
		return this.URI;
	}

	
	@Override
	public int hashCode() {
		return URI.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;
		final OrderInfo other = (OrderInfo) obj;

		return URI.equals(other.URI);
	}

	@Override
	public String toString() {
		return "OrderInfo(" + URI + ')';
	}
	private static final long serialVersionUID = -3876112036961590127L;
	
	private final java.time.LocalDate placed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("placed")
	public java.time.LocalDate getPlaced()  {
		
		return this.placed;
	}

	
	private final java.time.LocalDate deadline;

	
	@com.fasterxml.jackson.annotation.JsonProperty("deadline")
	public java.time.LocalDate getDeadline()  {
		
		return this.deadline;
	}

	
	private final String customerName;

	
	@com.fasterxml.jackson.annotation.JsonProperty("customerName")
	public String getCustomerName()  {
		
		return this.customerName;
	}

	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.OrderInfo")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.OrderInfo")
		public Inheritance.OrderInfo[] value;

		public static final java.util.function.Function<Inheritance.OrderInfo[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.OrderInfo")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.OrderInfo")
		public java.util.List<Inheritance.OrderInfo> value;

		public static final java.util.function.Function<java.util.List<Inheritance.OrderInfo>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
}
