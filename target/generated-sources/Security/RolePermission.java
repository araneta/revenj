/*
* Created by DSL Platform
* v1.7.6193.30391 
*/

package Security;



public class RolePermission   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public RolePermission() {
			
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
		if (obj == null || obj instanceof RolePermission == false)
			return false;
		final RolePermission other = (RolePermission) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final RolePermission other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.Name.equals(other.Name)))
			return false;
		if(!(this.RoleURI == other.RoleURI || this.RoleURI != null && this.RoleURI.equals(other.RoleURI)))
			return false;
		if(!(this.RoleID.equals(other.RoleID)))
			return false;
		if(!(this.IsAllowed == other.IsAllowed))
			return false;
		return true;
	}

	private RolePermission(RolePermission other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.Name = other.Name;
		this.RoleURI = other.RoleURI;
		this.RoleID = other.RoleID;
		this.IsAllowed = other.IsAllowed;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new RolePermission(this);
	}

	@Override
	public String toString() {
		return "RolePermission(" + URI + ')';
	}
	
	
	public RolePermission(
			final String Name,
			final Security.Role Role,
			final boolean IsAllowed) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setName(Name);
		setRole(Role);
		setIsAllowed(IsAllowed);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private RolePermission(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("Name") final String Name,
			@com.fasterxml.jackson.annotation.JsonProperty("RoleURI") final String RoleURI,
			@com.fasterxml.jackson.annotation.JsonProperty("RoleID") final String RoleID,
			@com.fasterxml.jackson.annotation.JsonProperty("IsAllowed") final boolean IsAllowed) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.Name = Name == null ? "" : Name;
		this.RoleURI = RoleURI;
		this.RoleID = RoleID == null ? "" : RoleID;
		this.IsAllowed = IsAllowed;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 8228233489683195825L;
	
	private String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		return Name;
	}

	
	public RolePermission setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 200);
		this.Name = value;
		
		return this;
	}

	
	private Security.Role Role;

	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Security.Role getRole()  {
		
		
		if (__locator.isPresent() && (Role != null && !Role.getURI().equals(RoleURI) || Role == null && RoleURI != null)) {
			Security.repositories.RoleRepository repository = __locator.get().resolve(Security.repositories.RoleRepository.class);
			Role = repository.find(RoleURI).orElse(null);
		}
		return Role;
	}

	
	public RolePermission setRole(final Security.Role value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Role\" cannot be null!");
		
		if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Security.Role\" for property \"Role\" must be persisted before it's assigned");
		this.Role = value;
		
		
		this.RoleID = value.getName();
		this.RoleURI = value.getURI();
		return this;
	}

	
	private String RoleURI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("RoleURI")
	public String getRoleURI()  {
		
		if (this.Role != null) this.RoleURI = this.Role.getURI();
		return this.RoleURI;
	}

	
	private String RoleID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("RoleID")
	public String getRoleID()  {
		
		if (this.Role != null) this.RoleID = this.Role.getName();
		return RoleID;
	}

	
	private RolePermission setRoleID(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"RoleID\" cannot be null!");
		org.revenj.Guards.checkLength(value, 100);
		this.RoleID = value;
		
		return this;
	}

	
	private boolean IsAllowed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("IsAllowed")
	public boolean getIsAllowed()  {
		
		return IsAllowed;
	}

	
	public RolePermission setIsAllowed(final boolean value) {
		
		this.IsAllowed = value;
		
		return this;
	}

	private transient RolePermission __originalValue;
	
	static {
		Security.repositories.RolePermissionRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Security.RolePermission agg : aggregates) {
						 
						agg.URI = Security.converters.RolePermissionConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Security.RolePermission> oldAggregates = aggregates.getKey();
					java.util.List<Security.RolePermission> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Security.RolePermission oldAgg = oldAggregates.get(i);
						Security.RolePermission newAgg = newAggregates.get(i);
						 
						newAgg.URI = Security.converters.RolePermissionConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Security.RolePermission agg : aggregates) { 
				}
			},
			agg -> { 
				
		RolePermission _res = agg.__originalValue;
		agg.__originalValue = (RolePermission)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.RolePermission")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.RolePermission")
		public Security.RolePermission[] value;

		public static final java.util.function.Function<Security.RolePermission[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.RolePermission")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.RolePermission")
		public java.util.List<Security.RolePermission> value;

		public static final java.util.function.Function<java.util.List<Security.RolePermission>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public RolePermission(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<RolePermission>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<RolePermission> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Security.converters.RolePermissionConverter.buildURI(reader, this);
		this.__originalValue = (RolePermission)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<RolePermission>[] readers, int __index___Name, int __index___RoleURI, int __index___RoleID, int __index___IsAllowed) {
		
		readers[__index___Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___RoleURI] = (item, reader, context) -> { item.RoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index___RoleID] = (item, reader, context) -> { item.RoleID = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___IsAllowed] = (item, reader, context) -> { item.IsAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<RolePermission>[] readers, int __index__extended_Name, int __index__extended_RoleURI, int __index__extended_RoleID, int __index__extended_IsAllowed) {
		
		readers[__index__extended_Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_RoleURI] = (item, reader, context) -> { item.RoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index__extended_RoleID] = (item, reader, context) -> { item.RoleID = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_IsAllowed] = (item, reader, context) -> { item.IsAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
}
