/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Security;



public class User   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public User() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		this.PasswordHash = org.revenj.Utils.EMPTY_BINARY;
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
		
		if(!(this.Name.equals(other.Name)))
			return false;
		if(!(this.RoleURI == other.RoleURI || this.RoleURI != null && this.RoleURI.equals(other.RoleURI)))
			return false;
		if(!(java.util.Arrays.equals(this.PasswordHash, other.PasswordHash)))
			return false;
		if(!(this.IsAllowed == other.IsAllowed))
			return false;
		return true;
	}

	private User(User other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.Name = other.Name;
		this.RoleURI = other.RoleURI;
		this.PasswordHash = other.PasswordHash != null ? java.util.Arrays.copyOf(other.PasswordHash, other.PasswordHash.length) : null;
		this.IsAllowed = other.IsAllowed;
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
			final Security.Role Role,
			final byte[] PasswordHash,
			final boolean IsAllowed) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setRole(Role);
		setPasswordHash(PasswordHash);
		setIsAllowed(IsAllowed);
		this.URI = this.Name;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private User(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("Name") final String Name,
			@com.fasterxml.jackson.annotation.JsonProperty("RoleURI") final String RoleURI,
			@com.fasterxml.jackson.annotation.JsonProperty("PasswordHash") final byte[] PasswordHash,
			@com.fasterxml.jackson.annotation.JsonProperty("IsAllowed") final boolean IsAllowed) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.Name = Name == null ? "" : Name;
		this.RoleURI = RoleURI;
		this.PasswordHash = PasswordHash == null ? org.revenj.Utils.EMPTY_BINARY : PasswordHash;
		this.IsAllowed = IsAllowed;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 7200787253913374398L;
	
	private String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		if (this.Role != null) this.Name = this.Role.getName();
		return Name;
	}

	
	private User setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 100);
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

	
	public User setRole(final Security.Role value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Role\" cannot be null!");
		
		if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Security.Role\" for property \"Role\" must be persisted before it's assigned");
		this.Role = value;
		
		
		this.Name = value.getName();
		this.RoleURI = value.getURI();
		return this;
	}

	
	private String RoleURI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("RoleURI")
	public String getRoleURI()  {
		
		if (this.Role != null) this.RoleURI = this.Role.getURI();
		return this.RoleURI;
	}

	
	private byte[] PasswordHash;

	
	@com.fasterxml.jackson.annotation.JsonProperty("PasswordHash")
	public byte[] getPasswordHash()  {
		
		return PasswordHash;
	}

	
	public User setPasswordHash(final byte[] value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"PasswordHash\" cannot be null!");
		this.PasswordHash = value;
		
		return this;
	}

	
	private boolean IsAllowed;

	
	@com.fasterxml.jackson.annotation.JsonProperty("IsAllowed")
	public boolean getIsAllowed()  {
		
		return IsAllowed;
	}

	
	public User setIsAllowed(final boolean value) {
		
		this.IsAllowed = value;
		
		return this;
	}

	private transient User __originalValue;
	
	static {
		Security.repositories.UserRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Security.User agg : aggregates) {
						 
						agg.URI = Security.converters.UserConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Security.User> oldAggregates = aggregates.getKey();
					java.util.List<Security.User> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Security.User oldAgg = oldAggregates.get(i);
						Security.User newAgg = newAggregates.get(i);
						 
						newAgg.URI = Security.converters.UserConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Security.User agg : aggregates) { 
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
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.User")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.User")
		public Security.User[] value;

		public static final java.util.function.Function<Security.User[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.User")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.User")
		public java.util.List<Security.User> value;

		public static final java.util.function.Function<java.util.List<Security.User>, _ListXML> convert = s -> {
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
		URI = Security.converters.UserConverter.buildURI(reader, this);
		this.__originalValue = (User)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<User>[] readers, int __index___Name, int __index___RoleURI, int __index___PasswordHash, int __index___IsAllowed) {
		
		readers[__index___Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___RoleURI] = (item, reader, context) -> { item.RoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index___PasswordHash] = (item, reader, context) -> { item.PasswordHash = org.revenj.database.postgres.converters.ByteaConverter.parse(reader, context); return item; };
		readers[__index___IsAllowed] = (item, reader, context) -> { item.IsAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<User>[] readers, int __index__extended_Name, int __index__extended_RoleURI, int __index__extended_PasswordHash, int __index__extended_IsAllowed) {
		
		readers[__index__extended_Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_RoleURI] = (item, reader, context) -> { item.RoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index__extended_PasswordHash] = (item, reader, context) -> { item.PasswordHash = org.revenj.database.postgres.converters.ByteaConverter.parse(reader, context); return item; };
		readers[__index__extended_IsAllowed] = (item, reader, context) -> { item.IsAllowed = org.revenj.database.postgres.converters.BoolConverter.parse(reader); return item; };
	}
}
