/*
* Created by DSL Platform
* v1.7.6200.20202 
*/

package Inheritance.repositories;



@org.springframework.stereotype.Repository
public class ServeTableRepository   implements java.io.Closeable, org.revenj.patterns.SearchableRepository<Inheritance.ServeTable>, org.revenj.patterns.DomainEventStore<Inheritance.ServeTable>, org.revenj.database.postgres.BulkRepository<Inheritance.ServeTable> {
	
	
	
	public ServeTableRepository(
			 final java.util.Optional<java.sql.Connection> transactionContext,
			 final javax.sql.DataSource dataSource,
			 final org.revenj.database.postgres.QueryProvider queryProvider,
			 final Inheritance.converters.ServeTableConverter converter,
			 final org.revenj.patterns.ServiceLocator locator,
			 final org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable>[] singleHandlers,
			 final org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable[]>[] collectionHandlers,
			 final org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable>>[] lazyHandlers,
			 final org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable[]>>[] collectionLazyHandlers) {
			
		this.transactionContext = transactionContext;
		this.dataSource = dataSource;
		this.queryProvider = queryProvider;
		this.transactionConnection = transactionContext.orElse(null);
		this.converter = converter;
		this.locator = locator;
		this.singleHandlers = singleHandlers;
		this.collectionHandlers = collectionHandlers;
		this.lazyHandlers = lazyHandlers;
		this.collectionLazyHandlers = collectionLazyHandlers;
	}

	private final java.util.Optional<java.sql.Connection> transactionContext;
	private final javax.sql.DataSource dataSource;
	private final org.revenj.database.postgres.QueryProvider queryProvider;
	private final java.sql.Connection transactionConnection;
	private final Inheritance.converters.ServeTableConverter converter;
	private final org.revenj.patterns.ServiceLocator locator;
	
	private java.sql.Connection getConnection() {
		if (transactionConnection != null) return transactionConnection;
		try {
		if (true) return org.springframework.jdbc.datasource.DataSourceUtils.getConnection(dataSource);
			return dataSource.getConnection();
		} catch (java.sql.SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void releaseConnection(java.sql.Connection connection) {
		if (this.transactionConnection != null) return;
		try {
		org.springframework.jdbc.datasource.DataSourceUtils.releaseConnection(connection, dataSource); if(true) return;
			connection.close();
		} catch (java.sql.SQLException ignore) {
		}		
	}

	public ServeTableRepository(org.revenj.patterns.ServiceLocator locator) {
		this(locator.tryResolve(java.sql.Connection.class), locator.resolve(javax.sql.DataSource.class), locator.resolve(org.revenj.database.postgres.QueryProvider.class), locator.resolve(Inheritance.converters.ServeTableConverter.class), locator);
	}
	

	public static org.revenj.patterns.Specification<Inheritance.ServeTable> rewriteSpecificationToLambda(org.revenj.patterns.Specification<Inheritance.ServeTable> filter) {
		
		return filter;
	}

	private static final boolean hasCustomSecurity = false;

	@Override
	public org.revenj.patterns.Query<Inheritance.ServeTable> query(org.revenj.patterns.Specification<Inheritance.ServeTable> filter) {
		org.revenj.patterns.Query<Inheritance.ServeTable> query = queryProvider.query(transactionConnection, locator, Inheritance.ServeTable.class);
		if (filter != null) {
			query = query.filter(rewriteSpecificationToLambda(filter));
		}
		
		return query;
	}

	private java.util.List<Inheritance.ServeTable> readFromDb(java.sql.PreparedStatement statement, java.util.List<Inheritance.ServeTable> result) throws java.sql.SQLException, java.io.IOException {
		try (java.sql.ResultSet rs = statement.executeQuery();
			org.revenj.database.postgres.PostgresReader reader = org.revenj.database.postgres.PostgresReader.create(locator)) {
			while (rs.next()) {
				reader.process(rs.getString(1));
				result.add(converter.from(reader));
			}
		}
		
		return result;
	}

	@Override
	public java.util.List<Inheritance.ServeTable> search(org.revenj.patterns.Specification<Inheritance.ServeTable> specification, Integer limit, Integer offset) {
		final String selectType = "SELECT it";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.database.postgres.PostgresWriter pgWriter = org.revenj.database.postgres.PostgresWriter.create()) {
			String sql;
			if (specification == null) {
				sql = "SELECT r FROM \"Inheritance\".\"ServeTable_event\" r";
			} 
			else {
				org.revenj.patterns.Query<Inheritance.ServeTable> query = query(specification);
				if (offset != null) {
					query = query.skip(offset);
				}
				if (limit != null) {
					query = query.limit(limit);
				}
				try {
					return query.list();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			}
			if (limit != null) {
				sql += " LIMIT " + Integer.toString(limit);
			}
			if (offset != null) {
				sql += " OFFSET " + Integer.toString(offset);
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
				applyFilters.accept(statement);
				return readFromDb(statement, new java.util.ArrayList<>());
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		} finally {
			releaseConnection(connection);
		}
	}

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.List<Inheritance.ServeTable>> search(org.revenj.database.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<Inheritance.ServeTable> specification, Integer limit, Integer offset) {
		String selectType = "SELECT array_agg(_r) FROM (SELECT it as _r";
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("SELECT array_agg(_r) FROM (SELECT _r FROM \"Inheritance\".\"ServeTable_event\" _r");
		}
		
		else {
			sb.append("SELECT 0");
			return (rs, ind) -> search(specification, limit, offset);
		}
		if (limit != null && limit >= 0) {
			sb.append(" LIMIT ");
			sb.append(Integer.toString(limit));
		}
		if (offset != null && offset >= 0) {
			sb.append(" OFFSET ");
			sb.append(Integer.toString(offset));
		}
		sb.append(") _sq");
		return (rs, ind) -> {
			try {
				String res = rs.getString(ind);
				if (res == null || res.length() == 0 || res.length() == 2) {
					return new java.util.ArrayList<>(0);
				}
				rdr.process(res);
				java.util.List<Inheritance.ServeTable> result = org.revenj.database.postgres.converters.ArrayTuple.parse(rdr, 0, converter::from); 
				
				return result;
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public long count(org.revenj.patterns.Specification<Inheritance.ServeTable> specification) {
		final String selectType = "SELECT COUNT(*)";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.database.postgres.PostgresWriter pgWriter = org.revenj.database.postgres.PostgresWriter.create()) {
			String sql;
			if (specification == null) {
				sql = "SELECT COUNT(*) FROM \"Inheritance\".\"ServeTable_event\" r";
			} 
			else {
				try {
					return query(specification).count();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {
				applyFilters.accept(statement);
				try (java.sql.ResultSet rs = statement.executeQuery()) {
					rs.next();
					return rs.getLong(1);
				}
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		} finally { 
			releaseConnection(connection); 
		}
	}

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, Long> count(org.revenj.database.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<Inheritance.ServeTable> specification) {
		String selectType = "SELECT count(*)";
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("SELECT count(*) FROM \"Inheritance\".\"ServeTable_event\" r");
		}
		
		else {
			sb.append("SELECT 0");
			return (rs, ind) -> {
				try {
					return query(specification).count();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			};
		}
		return (rs, ind) -> {
			try {
				return rs.getLong(ind);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public boolean exists(org.revenj.patterns.Specification<Inheritance.ServeTable> specification) {
		final String selectType = "SELECT exists(SELECT *";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.database.postgres.PostgresWriter pgWriter = org.revenj.database.postgres.PostgresWriter.create()) {
			String sql = null;
			if (specification == null) {
				sql = "SELECT exists(SELECT * FROM \"Inheritance\".\"ServeTable_event\" r";
			} 
			else {
				try {
					return query(specification).any();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			}
			try (java.sql.PreparedStatement statement = connection.prepareStatement(sql + ")")) {
				applyFilters.accept(statement);
				try (java.sql.ResultSet rs = statement.executeQuery()) {
					rs.next();
					return rs.getBoolean(1);
				}
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		} finally { 
			releaseConnection(connection); 
		}
	}

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, Boolean> exists(org.revenj.database.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<Inheritance.ServeTable> specification) {
		String selectType = "exists(SELECT *";
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("exists(SELECT * FROM \"Inheritance\".\"ServeTable_event\" r");
		}
		
		else {
			sb.append("SELECT 0");
			return (rs, ind) -> {
				try {
					return query(specification).any();
				} catch (java.io.IOException e) {
					throw new RuntimeException(e);
				}
			};
		}
		return (rs, ind) -> {
			try {
				return rs.getBoolean(ind);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public void close() throws java.io.IOException { 
	}

	private final org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable>[] singleHandlers;
	private final org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable[]>[] collectionHandlers;
	private final org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable>>[] lazyHandlers;
	private final org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable[]>>[] collectionLazyHandlers;
	
	public ServeTableRepository(
			final java.util.Optional<java.sql.Connection> transactionContext,
			final javax.sql.DataSource dataSource,
			final org.revenj.database.postgres.QueryProvider queryProvider,
			final Inheritance.converters.ServeTableConverter converter,
			final org.revenj.patterns.ServiceLocator locator) {
		this(transactionContext,
				dataSource,
				queryProvider,
				converter,
				locator,
				transactionContext.isPresent() ? new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable>[]>() {}.resolve(locator) : null,
				transactionContext.isPresent() ? new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable[]>[]>() {}.resolve(locator) : null,
				transactionContext.isPresent() ? new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable>>[]>() {}.resolve(locator) : null,
				transactionContext.isPresent() ? new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable[]>>[]>() {}.resolve(locator) : null
		);
	}

	@Override
	public java.util.List<Inheritance.ServeTable> find(String[] uris) {
		if (uris == null || uris.length == 0) return java.util.Collections.emptyList();
		long[] ids = new long[uris.length];
		for(int i = 0; i < uris.length; i++) {
			ids[i] = Long.parseLong(uris[i]);
		}
		java.sql.Connection connection = getConnection();
		try {
			return find(ids, connection);
		} finally {
			releaseConnection(connection);
		}
	}


	@Override
	public java.util.Optional<Inheritance.ServeTable> find(String uri) {
		long id;
		try {
			id = Long.parseLong(uri);
		} catch (Exception ignore) {
			return java.util.Optional.empty();
		}
		java.sql.Connection connection = getConnection();
		try {
			return find(id, connection);
		} finally {
			releaseConnection(connection);
		}
	}

	public java.util.List<Inheritance.ServeTable> find(long[] ids, java.sql.Connection connection) {
		try (java.sql.PreparedStatement statement = connection.prepareStatement("SELECT r FROM \"Inheritance\".\"ServeTable_event\" r WHERE r._event_id = ANY(?)")) {
			Object[] arg = new Object[ids.length];
			for(int i = 0; i < ids.length; i++) {
				arg[i] = ids[i];
			}
			statement.setArray(1, connection.createArrayOf("int8", arg));
			return readFromDb(statement, new java.util.ArrayList<>(ids.length));			
		} catch (java.sql.SQLException | java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}

	public java.util.Optional<Inheritance.ServeTable> find(long id, java.sql.Connection connection) {
		try (java.sql.PreparedStatement statement = connection.prepareStatement("SELECT r FROM \"Inheritance\".\"ServeTable_event\" r WHERE r._event_id = ?");
			org.revenj.database.postgres.PostgresReader reader = org.revenj.database.postgres.PostgresReader.create(locator)) {
			statement.setLong(1, id);
			Inheritance.ServeTable instance;
			try (java.sql.ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					reader.process(rs.getString(1));
					instance = converter.from(reader);
				} else {
					return java.util.Optional.empty();
				}
			}
			if (!hasCustomSecurity) return java.util.Optional.of(instance);
			java.util.List<Inheritance.ServeTable> result = new java.util.ArrayList<>(1);
			result.add(instance);
			
			if (result.size() == 1) {
				java.util.Optional.of(instance);
			}
			return java.util.Optional.empty();
		} catch (java.sql.SQLException | java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.Optional<Inheritance.ServeTable>> find(org.revenj.database.postgres.BulkReaderQuery query, String uri) {
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		StringBuilder sb = query.getBuilder();
		int index = query.getArgumentIndex();
		if (uri == null) {
			sb.append("SELECT 0");
			return (rs, ind) -> java.util.Optional.empty();
		}
		final long id;
		try {
			id = Long.parseLong(uri);
		} catch (java.lang.Exception e) {
			sb.append("SELECT 0");
			return (rs, ind) -> java.util.Optional.empty();
		}
		sb.append("SELECT _r FROM \"Inheritance\".\"ServeTable_event\" _r WHERE _r._event_id = ?");
		query.addArgument(ps -> {
			try {
				ps.setLong(index, id);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
		return (rs, ind) -> 
		{
			try {
				String res = rs.getString(ind);
				if (res == null) {
					return java.util.Optional.empty();
				}
				rdr.process(res);
				Inheritance.ServeTable instance = converter.from(rdr);
				if (!hasCustomSecurity) return java.util.Optional.of(instance);
				java.util.List<Inheritance.ServeTable> result = new java.util.ArrayList<>(1);
				result.add(instance);
				
				if (result.size() == 1) {
					java.util.Optional.of(instance);
				}
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
			return java.util.Optional.empty();
		};
	}

	@Override
	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.List<Inheritance.ServeTable>> find(org.revenj.database.postgres.BulkReaderQuery query, String[] uris) {
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter writer = query.getWriter();
		StringBuilder sb = query.getBuilder();
		int index = query.getArgumentIndex();
		if (uris == null || uris.length == 0) {
			sb.append("SELECT 0");
			return (rs, ind) -> java.util.Collections.emptyList();
		}
		sb.append("SELECT array_agg(_r) FROM \"Inheritance\".\"ServeTable_event\" _r WHERE _r._event_id = ANY(?)");
		final long[] ids = new long[uris.length];
		for (int i = 0; i < uris.length; i++) {
			try {
				ids[i] = Long.parseLong(uris[i]);
			} catch (java.lang.Exception e) {
				throw new java.lang.IllegalArgumentException("Invalid URI value found: " + uris[i], e);
			}
		}
		query.addArgument(ps -> {
			try {
				org.postgresql.util.PGobject arr = new org.postgresql.util.PGobject();
				arr.setType("int8[]");
				writer.reset();
				org.revenj.database.postgres.converters.PostgresTuple tuple = org.revenj.database.postgres.converters.ArrayTuple.create(ids, org.revenj.database.postgres.converters.LongConverter::toTuple);
				tuple.buildTuple(writer, false);
				arr.setValue(writer.toString());
				ps.setObject(index, arr);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
		return (rs, ind) -> 
		{
			try {
				String res = rs.getString(ind);
				if (res == null || res.length() == 0 || res.length() == 2) {
					return new java.util.ArrayList<>(0);
				}
				rdr.process(res);
				java.util.List<Inheritance.ServeTable> result = org.revenj.database.postgres.converters.ArrayTuple.parse(rdr, 0, converter::from); 
				
				return result;
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	private org.revenj.extensibility.Container executeBefore(java.sql.Connection connection, Inheritance.ServeTable[] events) {
		final org.revenj.extensibility.Container context;
		final org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable>[] sh;
		final org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable[]>[] ch;
		if (transactionContext.isPresent()) {
			context = null;
			sh = singleHandlers;
			ch = collectionHandlers;
		} else {
			context = locator.resolve(org.revenj.extensibility.Container.class);
			context.registerInstance(java.sql.Connection.class, connection, false);
			sh = new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable>[]>(){}.resolve(context);
			ch = new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable[]>[]>(){}.resolve(context);
		}
		if (sh != null) {
			for (org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable> s : sh) {
				for (Inheritance.ServeTable c : events) {
					s.handle(c);
				}
			}
		}
		if (ch != null) {
			for (org.revenj.patterns.DomainEventHandler<Inheritance.ServeTable[]> s : ch) {
				s.handle(events);
			}
		}
		return context;
	}

	private void executeAfter(org.revenj.patterns.ServiceLocator context, Inheritance.ServeTable[] events) {
		final org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable>>[] sh;
		final org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable[]>>[] ch;
		if (context == null) {
			sh = lazyHandlers;
			ch = collectionLazyHandlers;
		} else {
			sh = new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable>>[]>(){}.resolve(context);
			ch = new org.revenj.patterns.Generic<org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable[]>>[]>(){}.resolve(context);
		}
		if (sh != null) {
			for (org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable>> s : sh) {
				for (Inheritance.ServeTable c : events) {
					s.handle(() -> c);
				}
			}
		}
		if (ch != null) {
			for (org.revenj.patterns.DomainEventHandler<java.util.concurrent.Callable<Inheritance.ServeTable[]>> s : ch) {
				s.handle(() -> events);
			}
		}
	}

	@Override
	public String[] submit(java.util.Collection<Inheritance.ServeTable> domainEvents) {
		java.sql.Connection connection = getConnection();
		Inheritance.ServeTable[] events = domainEvents.toArray(new Inheritance.ServeTable[domainEvents.size()]);
		org.revenj.extensibility.Container context = executeBefore(connection, events);
		try (java.sql.PreparedStatement statement = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT \"URI\" FROM \"Inheritance\".\"submit_ServeTable\"(?)");
			org.revenj.database.postgres.PostgresWriter sw = org.revenj.database.postgres.PostgresWriter.create()) {
			if (prepareEvents != null) prepareEvents.accept(domainEvents);
			String[] result = new String[events.length];
			org.revenj.database.postgres.converters.PostgresTuple tuple = org.revenj.database.postgres.converters.ArrayTuple.create(events, converter::to);
			org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
			pgo.setType("\"Inheritance\".\"ServeTable_event\"[]");
			tuple.buildTuple(sw, false);
			pgo.setValue(sw.toString());
			statement.setObject(1, pgo);
			try (java.sql.ResultSet rs = statement.executeQuery()) {
				for (int i = 0; i < result.length; i++) {
					rs.next();
					result[i] = rs.getString(1);
				}
			}
			if (assignUris != null) assignUris.accept(domainEvents, result);
			executeAfter(context, events);
			return result;
		} catch (java.sql.SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (context != null) {
				try { context.close(); }
				catch (Exception e) { e.printStackTrace(); }
			}
			releaseConnection(connection);
		}
	}

	public static void __configure(
			java.util.function.Consumer<java.util.Collection<Inheritance.ServeTable>> prepare,
			java.util.function.BiConsumer<java.util.Collection<Inheritance.ServeTable>, String[]> assign) {
		prepareEvents = prepare;
		assignUris = assign;
	}

	private static java.util.function.Consumer<java.util.Collection<Inheritance.ServeTable>> prepareEvents;
	private static java.util.function.BiConsumer<java.util.Collection<Inheritance.ServeTable>, String[]> assignUris;

	@Override
	public void mark(String[] uris) {
		java.sql.Connection connection = getConnection();
		try (java.sql.PreparedStatement statement = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT \"Inheritance\".\"mark_ServeTable\"(?)")) {
			Object[] ids = new Object[uris.length];
			for(int i = 0; i < uris.length; i++) {
				ids[i] = Long.parseLong(uris[i]);
			}
			statement.setArray(1, connection.createArrayOf("int8", ids));
			statement.executeUpdate();
		} catch (java.sql.SQLException e) {
			throw new RuntimeException(e);
		} finally {
			releaseConnection(connection);
		}
	}

	
	@org.springframework.beans.factory.annotation.Autowired
	public ServeTableRepository(javax.sql.DataSource dataSource, org.revenj.patterns.ServiceLocator locator) {
		this(java.util.Optional.empty(), dataSource, locator.resolve(org.revenj.database.postgres.QueryProvider.class), locator.resolve(Inheritance.converters.ServeTableConverter.class), locator);
	}

}
