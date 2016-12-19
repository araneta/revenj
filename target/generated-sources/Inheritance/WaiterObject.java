/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Inheritance;



public final class WaiterObject   implements java.io.Serializable, org.revenj.patterns.Identifiable {
	
	
	@com.fasterxml.jackson.annotation.JsonCreator 
	public WaiterObject(
			@com.fasterxml.jackson.annotation.JsonProperty("URI")  final String URI,
			@com.fasterxml.jackson.annotation.JsonProperty("Name")  final String Name,
			@com.fasterxml.jackson.annotation.JsonProperty("StartedWorking")  final java.time.LocalDate StartedWorking) {
			
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.Name = Name != null ? Name : "";
		this.StartedWorking = StartedWorking != null ? StartedWorking : org.revenj.Utils.MIN_LOCAL_DATE;
	}

	
	
	public WaiterObject() {
			
		this.URI = java.util.UUID.randomUUID().toString();
		this.Name = "";
		this.StartedWorking = java.time.LocalDate.now();
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
		final WaiterObject other = (WaiterObject) obj;

		return URI.equals(other.URI);
	}

	@Override
	public String toString() {
		return "WaiterObject(" + URI + ')';
	}
	private static final long serialVersionUID = 3213319274110849216L;
	
	private final String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		return this.Name;
	}

	
	private final java.time.LocalDate StartedWorking;

	
	@com.fasterxml.jackson.annotation.JsonProperty("StartedWorking")
	public java.time.LocalDate getStartedWorking()  {
		
		return this.StartedWorking;
	}

	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.WaiterObject")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.WaiterObject")
		public Inheritance.WaiterObject[] value;

		public static final java.util.function.Function<Inheritance.WaiterObject[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfInheritance.WaiterObject")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Inheritance.WaiterObject")
		public java.util.List<Inheritance.WaiterObject> value;

		public static final java.util.function.Function<java.util.List<Inheritance.WaiterObject>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
}
