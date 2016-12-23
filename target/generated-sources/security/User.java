/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package security;



public class User   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public User() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.name = "";
		this.roles = new java.util.LinkedHashSet<String>(4);
		this.password = org.revenj.Utils.EMPTY_BINARY;
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
		if (obj == null || obj instanceof User == false)
			return false;
		final User other = (User) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final User other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.name.equals(other.name)))
			return false;
		if(!((this.roles == other.roles || this.roles != null && this.roles.equals(other.roles))))
			return false;
		if(!(java.util.Arrays.equals(this.password, other.password)))
			return false;
		if(!(this.isAllowed == other.isAllowed))
			return false;
		return true;
	}

	private User(User other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.name = other.name;
		this.roles = new java.util.LinkedHashSet<String>(other.roles);
		this.password = other.password != null ? java.util.Arrays.copyOf(other.password, other.password.length) : null;
		this.isAllowed = other.isAllowed;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new User(this);
	}

	@Override
	public String toString() {
		return "User(" + URI + ')';
	}
	
	
	public User(
			final String name,
			final java.util.Set<String> roles,
			final byte[] password,
			final boolean isAllowed) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setName(name);
		setRoles(roles);
		setPassword(password);
		setIsAllowed(isAllowed);
		this.URI = this.name;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private User(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("name") final String name,
			@com.fasterxml.jackson.annotation.JsonProperty("roles") final java.util.Set<String> roles,
			@com.fasterxml.jackson.annotation.JsonProperty("password") final byte[] password,
			@com.fasterxml.jackson.annotation.JsonProperty("isAllowed") final boolean isAllowed) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.name = name == null ? "" : name;
		this.roles = roles == null ? new java.util.LinkedHashSet<String>(4) : roles;
		this.password = password == null ? org.revenj.Utils.EMPTY_BINARY : password;
		this.isAllowed = isAllowed;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 6314874540728894861L;
	
	private String name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("name")
	public String getName()  {
		
		return name;
	}

	
	public User setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 100);
		this.name = value;
		
		return this;
	}

	
	private java.util.Set<String> roles;

	
	@com.fasterxml.jackson.annotation.JsonProperty("roles")
	public java.util.Set<String> getRoles()  {
		
		return roles;
	}

	
	public User setRoles(final java.util.Set<String> value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"roles\" cannot be null!");
		org.revenj.Guards.checkNulls(value);
		this.roles = value;
		
		return this;
	}

	
	private byte[] password;

	
	@com.fasterxml.jackson.annotation.JsonProperty("password")
	public byte[] getPassword()  {
		
		return password;
	}

	
	public User setPassword(final byte[] value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"password\" cannot be null!");
		this.password = value;
		
		return this;
	}

	
	private boolean isAllowed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("isAllowed")
	public boolean getIsAllowed()  {
		
		return isAllowed;
	}

	
	public User setIsAllowed(final boolean value) {
		
		this.isAllowed = value;
		
		return this;
	}

	private transient User __originalValue;
	
	static {
		security.repositories.UserRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (security.User agg : aggregates) {
						 
						agg.URI = security.converters.UserConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<security.User> oldAggregates = aggregates.getKey();
					java.util.List<security.User> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						security.User oldAgg = oldAggregates.get(i);
						security.User newAgg = newAggregates.get(i);
						 
						newAgg.URI = security.converters.UserConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (security.User agg : aggregates) { 
				}
			},
			agg -> { 
				
		User _res = agg.__originalValue;
		agg.__originalValue = (User)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.User")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.User")
		public security.User[] value;

		public static final java.util.function.Function<security.User[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.User")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.User")
		public java.util.List<security.User> value;

		public static final java.util.function.Function<java.util.List<security.User>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public User(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<User>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<User> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = security.converters.UserConverter.buildURI(reader, this);
		this.__originalValue = (User)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<User>[] readers, int __index___name, int __index___roles, int __index___password, int __index___isAllowed) {
		
		readers[__index___name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___roles] = (item, reader, context) -> { { java.util.List<String> __list = org.revenj.database.postgres.converters.StringConverter.parseCollection(reader, context, false); if(__list != null) {item.roles = new java.util.LinkedHashSet<String>(__list);} else item.roles = new java.util.LinkedHashSet<String>(4); }; return item; };
		readers[__index___password] = (item, reader, context) -> { item.password = org.revenj.database.postgres.converters.ByteaConverter.parse(reader, context); return item; };
		readers[__index___isAllowed] = (item, reader, context) -> { item.isAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<User>[] readers, int __index__extended_name, int __index__extended_roles, int __index__extended_password, int __index__extended_isAllowed) {
		
		readers[__index__extended_name] = (item, reader, context) -> { item.name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_roles] = (item, reader, context) -> { { java.util.List<String> __list = org.revenj.database.postgres.converters.StringConverter.parseCollection(reader, context, false); if(__list != null) {item.roles = new java.util.LinkedHashSet<String>(__list);} else item.roles = new java.util.LinkedHashSet<String>(4); }; return item; };
		readers[__index__extended_password] = (item, reader, context) -> { item.password = org.revenj.database.postgres.converters.ByteaConverter.parse(reader, context); return item; };
		readers[__index__extended_isAllowed] = (item, reader, context) -> { item.isAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
}
