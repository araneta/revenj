/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package security;



public final class RegisterUser   implements java.io.Serializable, org.revenj.patterns.DomainEvent {
	
	
	
	public RegisterUser(
			 final String username,
			 final byte[] password) {
			
		setUsername(username);
		setPassword(password);
	}

	
	
	public RegisterUser() {
			
		this.username = "";
		this.password = org.revenj.Utils.EMPTY_BINARY;
	}

	
	private String URI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("URI")
	@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
	public String getURI()  {
		
		return this.URI;
	}

	
	private java.time.OffsetDateTime ProcessedAt;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ProcessedAt")
	@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
	public java.time.OffsetDateTime getProcessedAt()  {
		
		return this.ProcessedAt;
	}

	
	private java.time.OffsetDateTime QueuedAt;

	
	@com.fasterxml.jackson.annotation.JsonProperty("QueuedAt")
	@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
	public java.time.OffsetDateTime getQueuedAt()  {
		
		return this.QueuedAt;
	}

	
	@Override
	public int hashCode() {
		return URI != null ? URI.hashCode() : super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;
		final RegisterUser other = (RegisterUser) obj;

		return URI != null && URI.equals(other.URI);
	}

	@Override
	public String toString() {
		return URI != null ? "RegisterUser(" + URI + ')' : "new RegisterUser(" + super.hashCode() + ')';
	}
	private static final long serialVersionUID = 6582512315854676703L;
	
	private String username;

	
	@com.fasterxml.jackson.annotation.JsonProperty("username")
	public String getUsername()  {
		
		return username;
	}

	
	public RegisterUser setUsername(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"username\" cannot be null!");
		this.username = value;
		
		return this;
	}

	
	private byte[] password;

	
	@com.fasterxml.jackson.annotation.JsonProperty("password")
	public byte[] getPassword()  {
		
		return password;
	}

	
	public RegisterUser setPassword(final byte[] value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"password\" cannot be null!");
		this.password = value;
		
		return this;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private RegisterUser(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JsonProperty("ProcessedAt") final java.time.OffsetDateTime ProcessedAt,
			@com.fasterxml.jackson.annotation.JsonProperty("QueuedAt") final java.time.OffsetDateTime QueuedAt,
			@com.fasterxml.jackson.annotation.JsonProperty("username") final String username,
			@com.fasterxml.jackson.annotation.JsonProperty("password") final byte[] password) {
		this.URI = URI != null ? URI : "new " + new java.util.UUID(0L, 0L).toString();
		this.ProcessedAt = ProcessedAt == null ? null : ProcessedAt;
		this.QueuedAt = QueuedAt == null ? null : QueuedAt;
		this.username = username == null ? "" : username;
		this.password = password == null ? org.revenj.Utils.EMPTY_BINARY : password;
	}

	
	public RegisterUser(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<RegisterUser>[] readers) throws java.io.IOException {
		for (org.revenj.database.postgres.ObjectConverter.Reader<RegisterUser> rdr : readers) {
			rdr.read(this, reader, context);
		}
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<RegisterUser>[] readers, int __index____event_id, int __index___QueuedAt, int __index___ProcessedAt, int __index___username, int __index___password) {
		
		readers[__index____event_id] = (item, reader, context) -> { item.URI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___QueuedAt] = (item, reader, context) -> { item.QueuedAt = org.revenj.database.postgres.converters.TimestampConverter.parseOffset(reader, context, false, false); return item; };
		readers[__index___ProcessedAt] = (item, reader, context) -> { item.ProcessedAt = org.revenj.database.postgres.converters.TimestampConverter.parseOffset(reader, context, true, false); return item; };
		readers[__index___username] = (item, reader, context) -> { item.username = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___password] = (item, reader, context) -> { item.password = org.revenj.database.postgres.converters.ByteaConverter.parse(reader, context); return item; };
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.RegisterUser")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.RegisterUser")
		public security.RegisterUser[] value;

		public static final java.util.function.Function<security.RegisterUser[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.RegisterUser")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.RegisterUser")
		public java.util.List<security.RegisterUser> value;

		public static final java.util.function.Function<java.util.List<security.RegisterUser>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	static {
		security.repositories.RegisterUserRepository.__configure(
			events -> {
				java.time.OffsetDateTime now = java.time.OffsetDateTime.now();
				for (security.RegisterUser eve : events) {
					eve.URI = null;
					eve.QueuedAt = now;eve.ProcessedAt = now;
				}
			},
			(events, uris) -> {
				int _i = 0;
				for (security.RegisterUser eve : events) {
					eve.URI = uris[_i++];
				}
			}
		);
	}
}
