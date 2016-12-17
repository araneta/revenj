/*
* Created by DSL Platform
* v1.7.6193.30391 
*/

package Security;



public class GlobalPermission   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public GlobalPermission() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.Name = "";
		this.IsAllowed = false;
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
		if (obj == null || obj instanceof GlobalPermission == false)
			return false;
		final GlobalPermission other = (GlobalPermission) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final GlobalPermission other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.Name.equals(other.Name)))
			return false;
		if(!(this.IsAllowed == other.IsAllowed))
			return false;
		return true;
	}

	private GlobalPermission(GlobalPermission other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.Name = other.Name;
		this.IsAllowed = other.IsAllowed;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new GlobalPermission(this);
	}

	@Override
	public String toString() {
		return "GlobalPermission(" + URI + ')';
	}
	
	
	public GlobalPermission(
			final String Name,
			final boolean IsAllowed) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setName(Name);
		setIsAllowed(IsAllowed);
		this.URI = this.Name;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private GlobalPermission(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("Name") final String Name,
			@com.fasterxml.jackson.annotation.JsonProperty("IsAllowed") final boolean IsAllowed) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.Name = Name == null ? "" : Name;
		this.IsAllowed = IsAllowed;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -1549831374690393296L;
	
	private String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		return Name;
	}

	
	public GlobalPermission setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 200);
		this.Name = value;
		
		return this;
	}

	
	private boolean IsAllowed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("IsAllowed")
	public boolean getIsAllowed()  {
		
		return IsAllowed;
	}

	
	public GlobalPermission setIsAllowed(final boolean value) {
		
		this.IsAllowed = value;
		
		return this;
	}

	private transient GlobalPermission __originalValue;
	
	static {
		Security.repositories.GlobalPermissionRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Security.GlobalPermission agg : aggregates) {
						 
						agg.URI = Security.converters.GlobalPermissionConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Security.GlobalPermission> oldAggregates = aggregates.getKey();
					java.util.List<Security.GlobalPermission> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Security.GlobalPermission oldAgg = oldAggregates.get(i);
						Security.GlobalPermission newAgg = newAggregates.get(i);
						 
						newAgg.URI = Security.converters.GlobalPermissionConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Security.GlobalPermission agg : aggregates) { 
				}
			},
			agg -> { 
				
		GlobalPermission _res = agg.__originalValue;
		agg.__originalValue = (GlobalPermission)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.GlobalPermission")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.GlobalPermission")
		public Security.GlobalPermission[] value;

		public static final java.util.function.Function<Security.GlobalPermission[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.GlobalPermission")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.GlobalPermission")
		public java.util.List<Security.GlobalPermission> value;

		public static final java.util.function.Function<java.util.List<Security.GlobalPermission>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public GlobalPermission(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<GlobalPermission>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<GlobalPermission> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Security.converters.GlobalPermissionConverter.buildURI(reader, this);
		this.__originalValue = (GlobalPermission)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<GlobalPermission>[] readers, int __index___Name, int __index___IsAllowed) {
		
		readers[__index___Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___IsAllowed] = (item, reader, context) -> { item.IsAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<GlobalPermission>[] readers, int __index__extended_Name, int __index__extended_IsAllowed) {
		
		readers[__index__extended_Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_IsAllowed] = (item, reader, context) -> { item.IsAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
}
