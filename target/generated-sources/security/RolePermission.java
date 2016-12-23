/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package security;



public class RolePermission   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot, org.revenj.security.RolePermission {
	
	
	
	public RolePermission() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.name = "";
		this.roleID = "";
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
		
		if(!(this.name.equals(other.name)))
			return false;
		if(!(this.roleID.equals(other.roleID)))
			return false;
		if(!(this.isAllowed == other.isAllowed))
			return false;
		return true;
	}

	private RolePermission(RolePermission other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.name = other.name;
		this.roleID = other.roleID;
		this.isAllowed = other.isAllowed;
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
			final String name,
			final String roleID,
			final boolean isAllowed) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setName(name);
		setRoleID(roleID);
		setIsAllowed(isAllowed);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private RolePermission(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("name") final String name,
			@com.fasterxml.jackson.annotation.JsonProperty("roleID") final String roleID,
			@com.fasterxml.jackson.annotation.JsonProperty("isAllowed") final boolean isAllowed) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.name = name == null ? "" : name;
		this.roleID = roleID == null ? "" : roleID;
		this.isAllowed = isAllowed;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = -2371650483310437640L;
	
	private String name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("name")
	public String getName()  {
		
		return name;
	}

	
	public RolePermission setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 200);
		this.name = value;
		
		return this;
	}

	
	private String roleID;

	
	@com.fasterxml.jackson.annotation.JsonProperty("roleID")
	public String getRoleID()  {
		
		return roleID;
	}

	
	public RolePermission setRoleID(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"roleID\" cannot be null!");
		this.roleID = value;
		
		return this;
	}

	
	private boolean isAllowed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("isAllowed")
	public boolean getIsAllowed()  {
		
		return isAllowed;
	}

	
	public RolePermission setIsAllowed(final boolean value) {
		
		this.isAllowed = value;
		
		return this;
	}

	

public static class ForRole   implements java.io.Serializable, org.revenj.patterns.Specification<RolePermission> {
	
	
	
	public ForRole(
			 final String role) {
			
		setRole(role);
	}

	
	
	public ForRole() {
			
		this.role = "";
	}

	private static final long serialVersionUID = -672323294001189547L;
	
	private String role;

	
	@com.fasterxml.jackson.annotation.JsonProperty("role")
	public String getRole()  {
		
		return role;
	}

	
	public ForRole setRole(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"role\" cannot be null!");
		this.role = value;
		
		return this;
	}

	
		public boolean test(security.RolePermission it) {
			return it.getRoleID().equals(this.getRole());
		}
	
		public org.revenj.patterns.Specification<RolePermission> rewriteLambda() {
			String _role_ = this.getRole();
			return it -> it.getRoleID().equals(_role_);
		}
}

	private transient RolePermission __originalValue;
	
	static {
		security.repositories.RolePermissionRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (security.RolePermission agg : aggregates) {
						 
						agg.URI = security.converters.RolePermissionConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<security.RolePermission> oldAggregates = aggregates.getKey();
					java.util.List<security.RolePermission> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						security.RolePermission oldAgg = oldAggregates.get(i);
						security.RolePermission newAgg = newAggregates.get(i);
						 
						newAgg.URI = security.converters.RolePermissionConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (security.RolePermission agg : aggregates) { 
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
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.RolePermission")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.RolePermission")
		public security.RolePermission[] value;

		public static final java.util.function.Function<security.RolePermission[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.RolePermission")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.RolePermission")
		public java.util.List<security.RolePermission> value;

		public static final java.util.function.Function<java.util.List<security.RolePermission>, _ListXML> convert = s -> {
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
		URI = security.converters.RolePermissionConverter.buildURI(reader, this);
		this.__originalValue = (RolePermission)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<RolePermission>[] readers, int __index___name, int __index___roleID, int __index___isAllowed) {
		
		readers[__index___name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___roleID] = (item, reader, context) -> { item.roleID = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___isAllowed] = (item, reader, context) -> { item.isAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<RolePermission>[] readers, int __index__extended_name, int __index__extended_roleID, int __index__extended_isAllowed) {
		
		readers[__index__extended_name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_roleID] = (item, reader, context) -> { item.roleID = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_isAllowed] = (item, reader, context) -> { item.isAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
}
