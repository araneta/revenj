/*
* Created by DSL Platform
* v1.7.6207.41740 
*/

package security;



public final class UserRegistered   implements java.io.Serializable, org.revenj.patterns.DomainEvent {
	
	
	
	public UserRegistered(
			 final String username) {
			
		setUsername(username);
	}

	
	
	public UserRegistered() {
			
		this.username = "";
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
		final UserRegistered other = (UserRegistered) obj;

		return URI != null && URI.equals(other.URI);
	}

	@Override
	public String toString() {
		return URI != null ? "UserRegistered(" + URI + ')' : "new UserRegistered(" + super.hashCode() + ')';
	}
	private static final long serialVersionUID = -4056751550267598563L;
	
	private String username;

	
	@com.fasterxml.jackson.annotation.JsonProperty("username")
	public String getUsername()  {
		
		return username;
	}

	
	public UserRegistered setUsername(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"username\" cannot be null!");
		this.username = value;
		
		return this;
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private UserRegistered(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JsonProperty("ProcessedAt") final java.time.OffsetDateTime ProcessedAt,
			@com.fasterxml.jackson.annotation.JsonProperty("QueuedAt") final java.time.OffsetDateTime QueuedAt,
			@com.fasterxml.jackson.annotation.JsonProperty("username") final String username) {
		this.URI = URI != null ? URI : "new " + new java.util.UUID(0L, 0L).toString();
		this.ProcessedAt = ProcessedAt == null ? null : ProcessedAt;
		this.QueuedAt = QueuedAt == null ? null : QueuedAt;
		this.username = username == null ? "" : username;
	}

	
	public UserRegistered(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<UserRegistered>[] readers) throws java.io.IOException {
		for (org.revenj.database.postgres.ObjectConverter.Reader<UserRegistered> rdr : readers) {
			rdr.read(this, reader, context);
		}
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<UserRegistered>[] readers, int __index____event_id, int __index___QueuedAt, int __index___ProcessedAt, int __index___username) {
		
		readers[__index____event_id] = (item, reader, context) -> { item.URI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___QueuedAt] = (item, reader, context) -> { item.QueuedAt = org.revenj.database.postgres.converters.TimestampConverter.parseOffset(reader, context, false, false); return item; };
		readers[__index___ProcessedAt] = (item, reader, context) -> { item.ProcessedAt = org.revenj.database.postgres.converters.TimestampConverter.parseOffset(reader, context, true, false); return item; };
		readers[__index___username] = (item, reader, context) -> { item.username = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.UserRegistered")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.UserRegistered")
		public security.UserRegistered[] value;

		public static final java.util.function.Function<security.UserRegistered[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfsecurity.UserRegistered")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "security.UserRegistered")
		public java.util.List<security.UserRegistered> value;

		public static final java.util.function.Function<java.util.List<security.UserRegistered>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	static {
		security.repositories.UserRegisteredRepository.__configure(
			events -> {
				java.time.OffsetDateTime now = java.time.OffsetDateTime.now();
				for (security.UserRegistered eve : events) {
					eve.URI = null;
					eve.QueuedAt = now;eve.ProcessedAt = now;
				}
			},
			(events, uris) -> {
				int _i = 0;
				for (security.UserRegistered eve : events) {
					eve.URI = uris[_i++];
				}
			}
		);
	}
}
