/*
* Created by DSL Platform
* v1.7.6193.30391 
*/

package Security;



public class Role   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public Role() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.Name = "";
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
		if (obj == null || obj instanceof Role == false)
			return false;
		final Role other = (Role) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final Role other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.Name.equals(other.Name)))
			return false;
		return true;
	}

	private Role(Role other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.Name = other.Name;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new Role(this);
	}

	@Override
	public String toString() {
		return "Role(" + URI + ')';
	}
	
	
	public Role(
			final String Name) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setName(Name);
		this.URI = this.Name;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private Role(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("Name") final String Name) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.Name = Name == null ? "" : Name;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 5270946768406036747L;
	
	private String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		return Name;
	}

	
	public Role setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 100);
		this.Name = value;
		
		return this;
	}

	private transient Role __originalValue;
	
	static {
		Security.repositories.RoleRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Security.Role agg : aggregates) {
						 
						agg.URI = Security.converters.RoleConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Security.Role> oldAggregates = aggregates.getKey();
					java.util.List<Security.Role> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Security.Role oldAgg = oldAggregates.get(i);
						Security.Role newAgg = newAggregates.get(i);
						 
						newAgg.URI = Security.converters.RoleConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Security.Role agg : aggregates) { 
				}
			},
			agg -> { 
				
		Role _res = agg.__originalValue;
		agg.__originalValue = (Role)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.Role")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.Role")
		public Security.Role[] value;

		public static final java.util.function.Function<Security.Role[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.Role")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.Role")
		public java.util.List<Security.Role> value;

		public static final java.util.function.Function<java.util.List<Security.Role>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public Role(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<Role>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<Role> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Security.converters.RoleConverter.buildURI(reader, this);
		this.__originalValue = (Role)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<Role>[] readers, int __index___Name) {
		
		readers[__index___Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<Role>[] readers, int __index__extended_Name) {
		
		readers[__index__extended_Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
}
