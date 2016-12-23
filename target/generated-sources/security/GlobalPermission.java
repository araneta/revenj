/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package security;



public class GlobalPermission   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot, org.revenj.security.GlobalPermission {
	
	
	
	public GlobalPermission() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.name = "";
		this.isAllowed = false;
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
		
		if(!(this.name.equals(other.name)))
			return false;
		if(!(this.isAllowed == other.isAllowed))
			return false;
		return true;
	}

	private GlobalPermission(GlobalPermission other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.name = other.name;
		this.isAllowed = other.isAllowed;
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
			final String name,
			final boolean isAllowed) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setName(name);
		setIsAllowed(isAllowed);
		this.URI = this.name;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private GlobalPermission(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("name") final String name,
			@com.fasterxml.jackson.annotation.JsonProperty("isAllowed") final boolean isAllowed) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.name = name == null ? "" : name;
		this.isAllowed = isAllowed;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -7272127041635231147L;
	
	private String name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("name")
	public String getName()  {
		
		return name;
	}

	
	public GlobalPermission setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 200);
		this.name = value;
		
		return this;
	}

	
	private boolean isAllowed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("isAllowed")
	public boolean getIsAllowed()  {
		
		return isAllowed;
	}

	
	public GlobalPermission setIsAllowed(final boolean value) {
		
		this.isAllowed = value;
		
		return this;
	}

	

public static class WithPrefix   implements java.io.Serializable, org.revenj.patterns.Specification<GlobalPermission> {
	
	
	
	public WithPrefix(
			 final String prefix) {
			
		setPrefix(prefix);
	}

	
	
	public WithPrefix() {
			
		this.prefix = "";
	}

	private static final long serialVersionUID = -144645299474456081L;
	
	private String prefix;

	
	@com.fasterxml.jackson.annotation.JsonProperty("prefix")
	public String getPrefix()  {
		
		return prefix;
	}

	
	public WithPrefix setPrefix(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"prefix\" cannot be null!");
		this.prefix = value;
		
		return this;
	}

	
		public boolean test(security.GlobalPermission it) {
			return ( it.getName().equals(this.getPrefix()) || it.getName().startsWith( (this.getPrefix() + ".")));
		}
	
		public org.revenj.patterns.Specification<GlobalPermission> rewriteLambda() {
			String _prefix_ = this.getPrefix();
			return it -> ( it.getName().equals(_prefix_) || it.getName().startsWith( (_prefix_ + ".")));
		}
}

	private transient GlobalPermission __originalValue;
	
	static {
		security.repositories.GlobalPermissionRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (security.GlobalPermission agg : aggregates) {
						 
						agg.URI = security.converters.GlobalPermissionConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<security.GlobalPermission> oldAggregates = aggregates.getKey();
					java.util.List<security.GlobalPermission> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						security.GlobalPermission oldAgg = oldAggregates.get(i);
						security.GlobalPermission newAgg = newAggregates.get(i);
						 
						newAgg.URI = security.converters.GlobalPermissionConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (security.GlobalPermission agg : aggregates) { 
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
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.GlobalPermission")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.GlobalPermission")
		public security.GlobalPermission[] value;

		public static final java.util.function.Function<security.GlobalPermission[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.GlobalPermission")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.GlobalPermission")
		public java.util.List<security.GlobalPermission> value;

		public static final java.util.function.Function<java.util.List<security.GlobalPermission>, _ListXML> convert = s -> {
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
		URI = security.converters.GlobalPermissionConverter.buildURI(reader, this);
		this.__originalValue = (GlobalPermission)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<GlobalPermission>[] readers, int __index___name, int __index___isAllowed) {
		
		readers[__index___name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___isAllowed] = (item, reader, context) -> { item.isAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<GlobalPermission>[] readers, int __index__extended_name, int __index__extended_isAllowed) {
		
		readers[__index__extended_name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_isAllowed] = (item, reader, context) -> { item.isAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
}
