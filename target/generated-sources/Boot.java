/*
* Created by DSL Platform
* v1.7.6207.41740 
*/


public class Boot implements org.revenj.extensibility.SystemAspect {

	public static org.revenj.patterns.ServiceLocator configure(String jdbcUrl) throws java.io.IOException {
		java.util.Properties properties = new java.util.Properties();
		java.io.File revProps = new java.io.File("revenj.properties");
		if (revProps.exists() && revProps.isFile()) {
			properties.load(new java.io.FileReader(revProps));
		}
		return configure(jdbcUrl, properties);
	}

	private static void setNamespace(java.util.Properties properties) {
		String ns = properties.getProperty("revenj.namespace");
		properties.setProperty("revenj.namespace", (ns == null ? "" : ",") + "");
	}

	public static org.revenj.patterns.ServiceLocator configure(String jdbcUrl, java.util.Properties properties) throws java.io.IOException {
		setNamespace(properties);
		if (jdbcUrl == null || !jdbcUrl.startsWith("jdbc:postgresql:")) {
			throw new java.io.IOException("Invalid revenj.jdbcUrl provided. Expecting: 'jdbc:postgresql:...'. Found: '" + jdbcUrl + "'.\n" +
					"If you wish to use custom jdbc driver provide custom data source instead of using Postgres builtin data source.");
		}
		org.postgresql.ds.PGPoolingDataSource dataSource = new org.postgresql.ds.PGPoolingDataSource();
		dataSource.setUrl(jdbcUrl);
		properties.setProperty("revenj.jdbcUrl", jdbcUrl);
		String user = properties.getProperty("user");
		String revUser = properties.getProperty("revenj.user");
		if (revUser != null && revUser.length() > 0) {
			dataSource.setUser(revUser);
		} else if (user != null && user.length() > 0) {
			dataSource.setUser(user);
		}
		String password = properties.getProperty("password");
		String revPassword = properties.getProperty("revenj.password");
		if (revPassword != null && revPassword.length() > 0) {
			dataSource.setPassword(revPassword);
		} else if (password != null && password.length() > 0) {
			dataSource.setPassword(password);
		}
		return org.revenj.Revenj.setup(dataSource, properties, java.util.Optional.<ClassLoader>empty(), java.util.Collections.singletonList((org.revenj.extensibility.SystemAspect) new Boot()).iterator());
	}

	private static java.util.List<org.revenj.database.postgres.ObjectConverter.ColumnInfo> loadColumnsInfo(
			org.revenj.extensibility.Container container,
			String query) throws java.sql.SQLException {
		java.util.List<org.revenj.database.postgres.ObjectConverter.ColumnInfo> columns = new java.util.ArrayList<>();
		try (java.sql.Connection connection = container.resolve(javax.sql.DataSource.class).getConnection();
				java.sql.Statement statement = connection.createStatement();
				java.sql.ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				columns.add(
						new org.revenj.database.postgres.ObjectConverter.ColumnInfo(
								rs.getString("type_schema"),
								rs.getString("type_name"),
								rs.getString("column_name"),
								rs.getString("column_schema"),
								rs.getString("column_type"),
								rs.getShort("column_index"),
								rs.getBoolean("is_not_null"),
								rs.getBoolean("is_ngs_generated")
						)
				);
			}
		}
		return columns;
	}

	public void configure(org.revenj.extensibility.Container container) throws java.io.IOException {
		java.util.Properties properties = container.resolve(java.util.Properties.class);
		setNamespace(properties);
		java.util.List<org.revenj.database.postgres.ObjectConverter.ColumnInfo> columns;
		try {
			columns = loadColumnsInfo(container, "SELECT * FROM \"-DSL-\".load_type_info()");
		} catch (java.sql.SQLException ignore) {
			try {
				columns = loadColumnsInfo(container, "SELECT " +
"	ns.nspname::varchar as type_schema, " +
"	cl.relname::varchar as type_name, " +
"	atr.attname::varchar as column_name, " +
"	ns_ref.nspname::varchar as column_schema, " +
"	typ.typname::varchar as column_type, " +
"	(SELECT COUNT(*) + 1 " +
"	FROM pg_attribute atr_ord " +
"	WHERE " +
"		atr.attrelid = atr_ord.attrelid " +
"		AND atr_ord.attisdropped = false " +
"		AND atr_ord.attnum > 0 " +
"		AND atr_ord.attnum < atr.attnum)::smallint as column_index, " +
"	atr.attnotnull as is_not_null, " +
"	coalesce(d.description LIKE 'NGS generated%', false) as is_ngs_generated " +
"FROM " +
"	pg_attribute atr " +
"	INNER JOIN pg_class cl ON atr.attrelid = cl.oid " +
"	INNER JOIN pg_namespace ns ON cl.relnamespace = ns.oid " +
"	INNER JOIN pg_type typ ON atr.atttypid = typ.oid " +
"	INNER JOIN pg_namespace ns_ref ON typ.typnamespace = ns_ref.oid " +
"	LEFT JOIN pg_description d ON d.objoid = cl.oid " +
"								AND d.objsubid = atr.attnum " +
"WHERE " +
"	(cl.relkind = 'r' OR cl.relkind = 'v' OR cl.relkind = 'c') " +
"	AND ns.nspname NOT LIKE 'pg_%' " +
"	AND ns.nspname != 'information_schema' " +
"	AND atr.attnum > 0 " +
"	AND atr.attisdropped = FALSE " +
"ORDER BY 1, 2, 6");
			} catch (java.sql.SQLException e) {
				throw new java.io.IOException(e);
			}
		}
		org.revenj.database.postgres.jinq.JinqMetaModel metamodel = org.revenj.database.postgres.jinq.JinqMetaModel.configure(container);
		org.revenj.extensibility.PluginLoader plugins = container.resolve(org.revenj.extensibility.PluginLoader.class);
		
		security.converters.UserConverter security$converter$UserConverter = new security.converters.UserConverter(columns, container);
		security.converters.GlobalPermissionConverter security$converter$GlobalPermissionConverter = new security.converters.GlobalPermissionConverter(columns, container);
		security.converters.RolePermissionConverter security$converter$RolePermissionConverter = new security.converters.RolePermissionConverter(columns, container);
		security.converters.RegisterUserConverter security$converter$RegisterUserConverter = new security.converters.RegisterUserConverter(columns, container);
		security.converters.UserRegisteredConverter security$converter$UserRegisteredConverter = new security.converters.UserRegisteredConverter(columns, container);
		hello.converters.WorldConverter hello$converter$WorldConverter = new hello.converters.WorldConverter(columns, container);
		Inheritance.converters.PersonConverter Inheritance$converter$PersonConverter = new Inheritance.converters.PersonConverter(columns, container);
		Inheritance.converters.EmployeeConverter Inheritance$converter$EmployeeConverter = new Inheritance.converters.EmployeeConverter(columns, container);
		Inheritance.converters.WaiterConverter Inheritance$converter$WaiterConverter = new Inheritance.converters.WaiterConverter(columns, container);
		Inheritance.converters.WaiterObjectConverter Inheritance$converter$WaiterObjectConverter = new Inheritance.converters.WaiterObjectConverter(columns, container);
		Inheritance.converters.ServeTableConverter Inheritance$converter$ServeTableConverter = new Inheritance.converters.ServeTableConverter(columns, container);
		Inheritance.converters.CustomerConverter Inheritance$converter$CustomerConverter = new Inheritance.converters.CustomerConverter(columns, container);
		Inheritance.converters.OrderConverter Inheritance$converter$OrderConverter = new Inheritance.converters.OrderConverter(columns, container);
		Inheritance.converters.LineItemConverter Inheritance$converter$LineItemConverter = new Inheritance.converters.LineItemConverter(columns, container);
		Inheritance.converters.OrderInfoConverter Inheritance$converter$OrderInfoConverter = new Inheritance.converters.OrderInfoConverter(columns, container);
		security$converter$UserConverter.__configure(container, plugins, metamodel);
		security$converter$GlobalPermissionConverter.__configure(container, plugins, metamodel);
		metamodel.registerSpecification(security.GlobalPermission.WithPrefix.class, security.GlobalPermission.WithPrefix::rewriteLambda);
		metamodel.register(new _security_GlobalPermission_WithPrefix_());
		security$converter$RolePermissionConverter.__configure(container, plugins, metamodel);
		metamodel.registerSpecification(security.RolePermission.ForRole.class, security.RolePermission.ForRole::rewriteLambda);
		metamodel.register(new _security_RolePermission_ForRole_());
		security$converter$RegisterUserConverter.__configure(container, plugins, metamodel);
		security$converter$UserRegisteredConverter.__configure(container, plugins, metamodel);
		hello$converter$WorldConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$PersonConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$EmployeeConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$WaiterConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$WaiterObjectConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$ServeTableConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$CustomerConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$OrderConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$LineItemConverter.__configure(container, plugins, metamodel);
		Inheritance$converter$OrderInfoConverter.__configure(container, plugins, metamodel);
	}
	
	private static class _security_GlobalPermission_WithPrefix_ implements org.revenj.database.postgres.jinq.transform.MethodHandlerVirtual {
		@Override
		public java.util.List<ch.epfl.labos.iu.orm.queryll2.symbolic.MethodSignature> getSupportedSignatures() {
			return java.util.Collections.singletonList(new ch.epfl.labos.iu.orm.queryll2.symbolic.MethodSignature("security/GlobalPermission$WithPrefix", "test", "(Lsecurity/GlobalPermission;)Z"));
		}

		
		private final static java.util.function.Function<security.GlobalPermission.WithPrefix, String> propprefix = security.GlobalPermission.WithPrefix::getPrefix;

		private final static java.util.List<org.revenj.database.postgres.jinq.jpqlquery.GetterExpression> arguments = java.util.Arrays.asList(
				new org.revenj.database.postgres.jinq.jpqlquery.GetterExpression(propprefix, "String", "varchar")
		);

		@Override
		public org.revenj.database.postgres.jinq.jpqlquery.ColumnExpressions<?> handle(
				ch.epfl.labos.iu.orm.queryll2.symbolic.MethodCallValue.VirtualMethodCallValue val,
				org.revenj.database.postgres.jinq.transform.SymbExPassDown in,
				org.revenj.database.postgres.jinq.transform.SymbExToColumns columns) throws ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValueVisitorException {
			return org.revenj.database.postgres.jinq.jpqlquery.ColumnExpressions.singleColumn(
					org.revenj.database.postgres.jinq.jpqlquery.SimpleRowReader.READER,
					new org.revenj.database.postgres.jinq.jpqlquery.SqlPredicateExpression("\"security\".\"GlobalPermission.WithPrefix\"", columns.lambdaIndex, arguments));
		}
	}
	
	private static class _security_RolePermission_ForRole_ implements org.revenj.database.postgres.jinq.transform.MethodHandlerVirtual {
		@Override
		public java.util.List<ch.epfl.labos.iu.orm.queryll2.symbolic.MethodSignature> getSupportedSignatures() {
			return java.util.Collections.singletonList(new ch.epfl.labos.iu.orm.queryll2.symbolic.MethodSignature("security/RolePermission$ForRole", "test", "(Lsecurity/RolePermission;)Z"));
		}

		
		private final static java.util.function.Function<security.RolePermission.ForRole, String> proprole = security.RolePermission.ForRole::getRole;

		private final static java.util.List<org.revenj.database.postgres.jinq.jpqlquery.GetterExpression> arguments = java.util.Arrays.asList(
				new org.revenj.database.postgres.jinq.jpqlquery.GetterExpression(proprole, "String", "varchar")
		);

		@Override
		public org.revenj.database.postgres.jinq.jpqlquery.ColumnExpressions<?> handle(
				ch.epfl.labos.iu.orm.queryll2.symbolic.MethodCallValue.VirtualMethodCallValue val,
				org.revenj.database.postgres.jinq.transform.SymbExPassDown in,
				org.revenj.database.postgres.jinq.transform.SymbExToColumns columns) throws ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValueVisitorException {
			return org.revenj.database.postgres.jinq.jpqlquery.ColumnExpressions.singleColumn(
					org.revenj.database.postgres.jinq.jpqlquery.SimpleRowReader.READER,
					new org.revenj.database.postgres.jinq.jpqlquery.SqlPredicateExpression("\"security\".\"RolePermission.ForRole\"", columns.lambdaIndex, arguments));
		}
	}
}
