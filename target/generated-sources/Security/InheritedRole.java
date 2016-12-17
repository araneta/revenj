/*
* Created by DSL Platform
* v1.7.6193.30391 
*/

package Security;



public class InheritedRole   implements java.lang.Cloneable, java.io.Serializable, org.revenj.patterns.AggregateRoot {
	
	
	
	public InheritedRole() {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
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
		if (obj == null || obj instanceof InheritedRole == false)
			return false;
		final InheritedRole other = (InheritedRole) obj;
		return URI.equals(other.URI);
	}

	public boolean deepEquals(final InheritedRole other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!URI.equals(other.URI))
			return false;
		
		if(!(this.Name.equals(other.Name)))
			return false;
		if(!(this.ParentName.equals(other.ParentName)))
			return false;
		if(!(this.RoleURI == other.RoleURI || this.RoleURI != null && this.RoleURI.equals(other.RoleURI)))
			return false;
		if(!(this.ParentRoleURI == other.ParentRoleURI || this.ParentRoleURI != null && this.ParentRoleURI.equals(other.ParentRoleURI)))
			return false;
		return true;
	}

	private InheritedRole(InheritedRole other) {
		this.URI = other.URI;
		this.__locator = other.__locator;
		this.Name = other.Name;
		this.ParentName = other.ParentName;
		this.RoleURI = other.RoleURI;
		this.ParentRoleURI = other.ParentRoleURI;
		this.__originalValue = other.__originalValue;
	}

	@Override
	public Object clone() {
		return new InheritedRole(this);
	}

	@Override
	public String toString() {
		return "InheritedRole(" + URI + ')';
	}
	
	
	public InheritedRole(
			final Security.Role Role,
			final Security.Role ParentRole) {
			
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		URI = java.lang.Integer.toString(System.identityHashCode(this));
		setRole(Role);
		setParentRole(ParentRole);
	}

	
	@com.fasterxml.jackson.annotation.JsonCreator private InheritedRole(
			@com.fasterxml.jackson.annotation.JsonProperty("URI") final String URI ,
			@com.fasterxml.jackson.annotation.JacksonInject("__locator") final org.revenj.patterns.ServiceLocator __locator,
			@com.fasterxml.jackson.annotation.JsonProperty("Name") final String Name,
			@com.fasterxml.jackson.annotation.JsonProperty("ParentName") final String ParentName,
			@com.fasterxml.jackson.annotation.JsonProperty("RoleURI") final String RoleURI,
			@com.fasterxml.jackson.annotation.JsonProperty("ParentRoleURI") final String ParentRoleURI) {
		this.URI = URI != null ? URI : new java.util.UUID(0L, 0L).toString();
		this.__locator = java.util.Optional.ofNullable(__locator);
		this.Name = Name == null ? "" : Name;
		this.ParentName = ParentName == null ? "" : ParentName;
		this.RoleURI = RoleURI;
		this.ParentRoleURI = ParentRoleURI;
	}

	
	private transient java.util.Optional<org.revenj.patterns.ServiceLocator> __locator = java.util.Optional.empty();
	private static final long serialVersionUID = 4172070198383204963L;
	
	private String Name;

	
	@com.fasterxml.jackson.annotation.JsonProperty("Name")
	public String getName()  {
		
		if (this.Role != null) this.Name = this.Role.getName();
		return Name;
	}

	
	private InheritedRole setName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"Name\" cannot be null!");
		org.revenj.Guards.checkLength(value, 100);
		this.Name = value;
		
		return this;
	}

	
	private String ParentName;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ParentName")
	public String getParentName()  {
		
		if (this.ParentRole != null) this.ParentName = this.ParentRole.getName();
		return ParentName;
	}

	
	private InheritedRole setParentName(final String value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"ParentName\" cannot be null!");
		org.revenj.Guards.checkLength(value, 100);
		this.ParentName = value;
		
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

	
	public InheritedRole setRole(final Security.Role value) {
		
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

	
	private Security.Role ParentRole;

	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Security.Role getParentRole()  {
		
		
		if (__locator.isPresent() && (ParentRole != null && !ParentRole.getURI().equals(ParentRoleURI) || ParentRole == null && ParentRoleURI != null)) {
			Security.repositories.RoleRepository repository = __locator.get().resolve(Security.repositories.RoleRepository.class);
			ParentRole = repository.find(ParentRoleURI).orElse(null);
		}
		return ParentRole;
	}

	
	public InheritedRole setParentRole(final Security.Role value) {
		
		if(value == null) throw new IllegalArgumentException("Property \"ParentRole\" cannot be null!");
		
		if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Security.Role\" for property \"ParentRole\" must be persisted before it's assigned");
		this.ParentRole = value;
		
		
		this.ParentName = value.getName();
		this.ParentRoleURI = value.getURI();
		return this;
	}

	
	private String ParentRoleURI;

	
	@com.fasterxml.jackson.annotation.JsonProperty("ParentRoleURI")
	public String getParentRoleURI()  {
		
		if (this.ParentRole != null) this.ParentRoleURI = this.ParentRole.getURI();
		return this.ParentRoleURI;
	}

	private transient InheritedRole __originalValue;
	
	static {
		Security.repositories.InheritedRoleRepository.__setupPersist(
			(aggregates, arg) -> {
				try {
					for (Security.InheritedRole agg : aggregates) {
						 
						agg.URI = Security.converters.InheritedRoleConverter.buildURI(arg.getKey(), agg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			(aggregates, arg) -> {
				try {
					java.util.List<Security.InheritedRole> oldAggregates = aggregates.getKey();
					java.util.List<Security.InheritedRole> newAggregates = aggregates.getValue();
					for (int i = 0; i < newAggregates.size(); i++) {
						Security.InheritedRole oldAgg = oldAggregates.get(i);
						Security.InheritedRole newAgg = newAggregates.get(i);
						 
						newAgg.URI = Security.converters.InheritedRoleConverter.buildURI(arg.getKey(), newAgg);
					}
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			},
			aggregates -> { 
				for (Security.InheritedRole agg : aggregates) { 
				}
			},
			agg -> { 
				
		InheritedRole _res = agg.__originalValue;
		agg.__originalValue = (InheritedRole)agg.clone();
		if (_res != null) {
			return _res;
		}
				return null;
			}
		);
	}
	
	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.InheritedRole")
	public static class _ArrayXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.InheritedRole")
		public Security.InheritedRole[] value;

		public static final java.util.function.Function<Security.InheritedRole[], _ArrayXML> convert = s -> {
			_ArrayXML xml = new _ArrayXML();
			xml.value = s;
			return xml;
		};
	}

	@javax.xml.bind.annotation.XmlRootElement(name = "ArrayOfSecurity.InheritedRole")
	public static class _ListXML {
		@javax.xml.bind.annotation.XmlElement(name = "Security.InheritedRole")
		public java.util.List<Security.InheritedRole> value;

		public static final java.util.function.Function<java.util.List<Security.InheritedRole>, _ListXML> convert = s -> {
			_ListXML xml = new _ListXML();
			xml.value = s;
			return xml;
		};
	}
	
	public InheritedRole(org.revenj.database.postgres.PostgresReader reader, int context, org.revenj.database.postgres.ObjectConverter.Reader<InheritedRole>[] readers) throws java.io.IOException {
		this.__locator = reader.getLocator();
		for (org.revenj.database.postgres.ObjectConverter.Reader<InheritedRole> rdr : readers) {
			rdr.read(this, reader, context);
		}
		URI = Security.converters.InheritedRoleConverter.buildURI(reader, this);
		this.__originalValue = (InheritedRole)this.clone();
	}

	public static void __configureConverter(org.revenj.database.postgres.ObjectConverter.Reader<InheritedRole>[] readers, int __index___Name, int __index___ParentName, int __index___RoleURI, int __index___ParentRoleURI) {
		
		readers[__index___Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___ParentName] = (item, reader, context) -> { item.ParentName = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index___RoleURI] = (item, reader, context) -> { item.RoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index___ParentRoleURI] = (item, reader, context) -> { item.ParentRoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
	}
	
	public static void __configureConverterExtended(org.revenj.database.postgres.ObjectConverter.Reader<InheritedRole>[] readers, int __index__extended_Name, int __index__extended_ParentName, int __index__extended_RoleURI, int __index__extended_ParentRoleURI) {
		
		readers[__index__extended_Name] = (item, reader, context) -> { item.Name = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_ParentName] = (item, reader, context) -> { item.ParentName = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, false); return item; };
		readers[__index__extended_RoleURI] = (item, reader, context) -> { item.RoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
		readers[__index__extended_ParentRoleURI] = (item, reader, context) -> { item.ParentRoleURI = org.revenj.database.postgres.converters.StringConverter.parse(reader, context, true); return item; };
	}
}
