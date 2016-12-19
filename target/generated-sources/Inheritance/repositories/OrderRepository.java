/*
* Created by DSL Platform
* v1.7.6196.23272 
*/

package Inheritance.repositories;



@org.springframework.stereotype.Repository
public class OrderRepository   implements java.io.Closeable, org.revenj.patterns.SearchableRepository<Inheritance.Order>, org.revenj.database.postgres.BulkRepository<Inheritance.Order>, org.revenj.patterns.PersistableRepository<Inheritance.Order> {
	
	
	
	public OrderRepository(
			 final java.util.Optional<java.sql.Connection> transactionContext,
			 final javax.sql.DataSource dataSource,
			 final org.revenj.database.postgres.QueryProvider queryProvider,
			 final Inheritance.converters.OrderConverter converter,
			 final org.revenj.patterns.ServiceLocator locator) {
			
		this.transactionContext = transactionContext;
		this.dataSource = dataSource;
		this.queryProvider = queryProvider;
		this.transactionConnection = transactionContext.orElse(null);
		this.converter = converter;
		this.locator = locator;
	}

	private final java.util.Optional<java.sql.Connection> transactionContext;
	private final javax.sql.DataSource dataSource;
	private final org.revenj.database.postgres.QueryProvider queryProvider;
	private final java.sql.Connection transactionConnection;
	private final Inheritance.converters.OrderConverter converter;
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

	public OrderRepository(org.revenj.patterns.ServiceLocator locator) {
		this(locator.tryResolve(java.sql.Connection.class), locator.resolve(javax.sql.DataSource.class), locator.resolve(org.revenj.database.postgres.QueryProvider.class), locator.resolve(Inheritance.converters.OrderConverter.class), locator);
	}
	

	public static org.revenj.patterns.Specification<Inheritance.Order> rewriteSpecificationToLambda(org.revenj.patterns.Specification<Inheritance.Order> filter) {
		
		return filter;
	}

	private static final boolean hasCustomSecurity = false;

	@Override
	public org.revenj.patterns.Query<Inheritance.Order> query(org.revenj.patterns.Specification<Inheritance.Order> filter) {
		org.revenj.patterns.Query<Inheritance.Order> query = queryProvider.query(transactionConnection, locator, Inheritance.Order.class);
		if (filter != null) {
			query = query.filter(rewriteSpecificationToLambda(filter));
		}
		
		return query;
	}

	private java.util.List<Inheritance.Order> readFromDb(java.sql.PreparedStatement statement, java.util.List<Inheritance.Order> result) throws java.sql.SQLException, java.io.IOException {
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
	public java.util.List<Inheritance.Order> search(org.revenj.patterns.Specification<Inheritance.Order> specification, Integer limit, Integer offset) {
		final String selectType = "SELECT it";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.database.postgres.PostgresWriter pgWriter = org.revenj.database.postgres.PostgresWriter.create()) {
			String sql;
			if (specification == null) {
				sql = "SELECT r FROM \"Inheritance\".\"Order_entity\" r";
			} 
			else {
				org.revenj.patterns.Query<Inheritance.Order> query = query(specification);
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

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.List<Inheritance.Order>> search(org.revenj.database.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<Inheritance.Order> specification, Integer limit, Integer offset) {
		String selectType = "SELECT array_agg(_r) FROM (SELECT it as _r";
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("SELECT array_agg(_r) FROM (SELECT _r FROM \"Inheritance\".\"Order_entity\" _r");
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
				java.util.List<Inheritance.Order> result = org.revenj.database.postgres.converters.ArrayTuple.parse(rdr, 0, converter::from); 
				
				return result;
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	@Override
	public long count(org.revenj.patterns.Specification<Inheritance.Order> specification) {
		final String selectType = "SELECT COUNT(*)";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.database.postgres.PostgresWriter pgWriter = org.revenj.database.postgres.PostgresWriter.create()) {
			String sql;
			if (specification == null) {
				sql = "SELECT COUNT(*) FROM \"Inheritance\".\"Order_entity\" r";
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

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, Long> count(org.revenj.database.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<Inheritance.Order> specification) {
		String selectType = "SELECT count(*)";
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("SELECT count(*) FROM \"Inheritance\".\"Order_entity\" r");
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
	public boolean exists(org.revenj.patterns.Specification<Inheritance.Order> specification) {
		final String selectType = "SELECT exists(SELECT *";
		java.util.function.Consumer<java.sql.PreparedStatement> applyFilters = ps -> {};
		java.sql.Connection connection = getConnection();
		try (org.revenj.database.postgres.PostgresWriter pgWriter = org.revenj.database.postgres.PostgresWriter.create()) {
			String sql = null;
			if (specification == null) {
				sql = "SELECT exists(SELECT * FROM \"Inheritance\".\"Order_entity\" r";
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

	public java.util.function.BiFunction<java.sql.ResultSet, Integer, Boolean> exists(org.revenj.database.postgres.BulkReaderQuery query, org.revenj.patterns.Specification<Inheritance.Order> specification) {
		String selectType = "exists(SELECT *";
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter pgWriter = query.getWriter();
		int index = query.getArgumentIndex();
		StringBuilder sb = query.getBuilder();
		if (specification == null) {
			sb.append("exists(SELECT * FROM \"Inheritance\".\"Order_entity\" r");
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

	
	@Override
	public java.util.List<Inheritance.Order> find(String[] uris) {
		if (uris == null || uris.length == 0) return java.util.Collections.emptyList();
		final int[] ids = new int[uris.length];
		for (int i = 0; i < uris.length; i++) {
			try {
				ids[i] = Integer.parseInt(uris[i]);
			} catch (java.lang.Exception e) {
				throw new java.lang.IllegalArgumentException("Invalid URI value found: " + uris[i], e);
			}
		}
		java.sql.Connection connection = getConnection();
		try {
			return find(ids, connection);
		} finally {
			releaseConnection(connection);
		}
	}

	@Override
	public java.util.Optional<Inheritance.Order> find(String uri) {
		if (uri == null) java.util.Optional.empty();
		final int id;
		try {
			id = Integer.parseInt(uri);
		} catch (java.lang.Exception e) {
			throw new java.lang.IllegalArgumentException("Invalid URI value found: " + uri, e);
		}
		java.sql.Connection connection = getConnection();
		try {
			return find(id, connection);
		} finally {
			releaseConnection(connection);
		}
	}

	public java.util.List<Inheritance.Order> find(int[] ids, java.sql.Connection connection) {
		try (java.sql.PreparedStatement statement = connection.prepareStatement("SELECT _r FROM \"Inheritance\".\"Order_entity\" _r WHERE _r.\"ID\" = ANY(?)");
			org.revenj.database.postgres.PostgresReader reader = org.revenj.database.postgres.PostgresReader.create(locator)) {
			org.postgresql.util.PGobject arr = new org.postgresql.util.PGobject();
			arr.setType("int4[]");
			arr.setValue(org.revenj.database.postgres.converters.ArrayTuple.create(ids, org.revenj.database.postgres.converters.IntConverter::toTuple).buildTuple(false));
			statement.setObject(1, arr);
			java.util.List<Inheritance.Order> result = new java.util.ArrayList<>(ids.length);
			try (java.sql.ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					reader.process(rs.getString(1));
					result.add(converter.from(reader));
				}
			}
			
			return result;
		} catch (java.sql.SQLException | java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}

	public java.util.Optional<Inheritance.Order> find(int id, java.sql.Connection connection) {
		try (java.sql.PreparedStatement statement = connection.prepareStatement("SELECT _r FROM \"Inheritance\".\"Order_entity\" _r WHERE _r.\"ID\" = ?");
			org.revenj.database.postgres.PostgresReader reader = org.revenj.database.postgres.PostgresReader.create(locator)) {
			statement.setInt(1, id);
			Inheritance.Order instance;
			try (java.sql.ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					reader.process(rs.getString(1));
					instance = converter.from(reader);
				} else {
					return java.util.Optional.empty();
				}
			}
			if (!hasCustomSecurity) return java.util.Optional.of(instance);
			java.util.List<Inheritance.Order> result = new java.util.ArrayList<>(1);
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
	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.Optional<Inheritance.Order>> find(org.revenj.database.postgres.BulkReaderQuery query, String uri) {
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		StringBuilder sb = query.getBuilder();
		int index = query.getArgumentIndex();
		if (uri == null) {
			sb.append("SELECT 0");
			return (rs, ind) -> java.util.Optional.empty();
		}
		final int id;
		try {
			id = Integer.parseInt(uri);
		} catch (java.lang.Exception e) {
			throw new java.lang.IllegalArgumentException("Invalid URI value found: " + uri, e);
		}
		sb.append("SELECT _r FROM \"Inheritance\".\"Order_entity\" _r WHERE _r.\"ID\" = ?");
		query.addArgument(ps -> {
			try {
				ps.setInt(index, id);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
		return (rs, ind) -> {
			try {
				String res = rs.getString(ind);
				if (res == null) {
					return java.util.Optional.empty();
				}
				rdr.process(res);
				Inheritance.Order instance = converter.from(rdr);
				if (!hasCustomSecurity) return java.util.Optional.of(instance);
				java.util.List<Inheritance.Order> result = new java.util.ArrayList<>(1);
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
	public java.util.function.BiFunction<java.sql.ResultSet, Integer, java.util.List<Inheritance.Order>> find(org.revenj.database.postgres.BulkReaderQuery query, String[] uris) {
		final org.revenj.database.postgres.PostgresReader rdr = query.getReader();
		final org.revenj.database.postgres.PostgresWriter writer = query.getWriter();
		StringBuilder sb = query.getBuilder();
		int index = query.getArgumentIndex();
		if (uris == null || uris.length == 0) {
			sb.append("SELECT 0");
			return (rs, ind) -> java.util.Collections.emptyList();
		}
		sb.append("SELECT array_agg(_r) FROM \"Inheritance\".\"Order_entity\" _r WHERE _r.\"ID\" = ANY(?)");
		final int[] ids = new int[uris.length];
		for (int i = 0; i < uris.length; i++) {
			try {
				ids[i] = Integer.parseInt(uris[i]);
			} catch (java.lang.Exception e) {
				throw new java.lang.IllegalArgumentException("Invalid URI value found: " + uris[i], e);
			}
		}
		query.addArgument(ps -> {
			try {
				org.postgresql.util.PGobject arr = new org.postgresql.util.PGobject();
				arr.setType("int4[]");
				writer.reset();
				org.revenj.database.postgres.converters.PostgresTuple tuple = org.revenj.database.postgres.converters.ArrayTuple.create(ids, org.revenj.database.postgres.converters.IntConverter::toTuple);
				tuple.buildTuple(writer, false);
				arr.setValue(writer.toString());
				ps.setObject(index, arr);
			} catch (java.sql.SQLException e) {
				throw new RuntimeException(e);
			}
		});
		return (rs, ind) -> {
			try {
				String res = rs.getString(ind);
				if (res == null || res.length() == 0 || res.length() == 2) {
					return new java.util.ArrayList<>(0);
				}
				rdr.process(res);
				java.util.List<Inheritance.Order> result = org.revenj.database.postgres.converters.ArrayTuple.parse(rdr, 0, converter::from); 
				
				return result;
			} catch (java.sql.SQLException | java.io.IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	
	public static void __setupPersist(
			java.util.function.BiConsumer<java.util.Collection<Inheritance.Order>, java.util.Map.Entry<org.revenj.database.postgres.PostgresWriter, org.revenj.patterns.ServiceLocator>> insert, 
			java.util.function.BiConsumer<java.util.Map.Entry<java.util.List<Inheritance.Order>, java.util.List<Inheritance.Order>>, java.util.Map.Entry<org.revenj.database.postgres.PostgresWriter, org.revenj.patterns.ServiceLocator>> update,
			java.util.function.Consumer<java.util.Collection<Inheritance.Order>> delete,
			java.util.function.Function<Inheritance.Order, Inheritance.Order> track) {
		insertLoop = insert;
		updateLoop = update;
		deleteLoop = delete;
		trackChanges = track;
	}

	private static java.util.function.BiConsumer<java.util.Collection<Inheritance.Order>, java.util.Map.Entry<org.revenj.database.postgres.PostgresWriter, org.revenj.patterns.ServiceLocator>> insertLoop;
	private static java.util.function.BiConsumer<java.util.Map.Entry<java.util.List<Inheritance.Order>, java.util.List<Inheritance.Order>>, java.util.Map.Entry<org.revenj.database.postgres.PostgresWriter, org.revenj.patterns.ServiceLocator>> updateLoop;
	private static java.util.function.Consumer<java.util.Collection<Inheritance.Order>> deleteLoop;
	private static java.util.function.Function<Inheritance.Order, Inheritance.Order> trackChanges;

	private static final String[] EMPTY_URI = new String[0];

	@Override
	public String[] persist(
			java.util.Collection<Inheritance.Order> insert,
			java.util.Collection<java.util.Map.Entry<Inheritance.Order, Inheritance.Order>> update,
			java.util.Collection<Inheritance.Order> delete) throws java.io.IOException {
		java.sql.Connection connection = getConnection();
		try (java.sql.PreparedStatement statement = connection.prepareStatement("/*NO LOAD BALANCE*/SELECT \"Inheritance\".\"persist_Order\"(?, ?, ?, ?)");
			org.revenj.database.postgres.PostgresWriter sw = org.revenj.database.postgres.PostgresWriter.create()) {
			String[] result;
			if (insert != null && !insert.isEmpty()) {
				assignSequenceID.accept(insert, connection);
				insertLoop.accept(insert, new java.util.AbstractMap.SimpleEntry<>(sw, locator));
				sw.reset();
				org.revenj.database.postgres.converters.PostgresTuple tuple = org.revenj.database.postgres.converters.ArrayTuple.create(insert, converter::to);
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"Inheritance\".\"Order_entity\"[]");
				sw.reset();
				tuple.buildTuple(sw, false);
				pgo.setValue(sw.toString());
				sw.reset();
				statement.setObject(1, pgo);
				result = new String[insert.size()];
				int i = 0;
				for (Inheritance.Order it : insert) {
					result[i++] = it.getURI();
					trackChanges.apply(it);
				}
			} else {
				statement.setArray(1, null);
				result = EMPTY_URI;
			}
			if (update != null && !update.isEmpty()) {
				java.util.List<Inheritance.Order> oldUpdate = new java.util.ArrayList<>(update.size());
				java.util.List<Inheritance.Order> newUpdate = new java.util.ArrayList<>(update.size());
				java.util.Map<String, Integer> missing = new java.util.HashMap<>();
				int cnt = 0;
				for (java.util.Map.Entry<Inheritance.Order, Inheritance.Order> it : update) {
					Inheritance.Order oldValue = trackChanges.apply(it.getValue());
					if (it.getKey() != null) {
						oldValue = it.getKey();
					}
					oldUpdate.add(oldValue);
					if (oldValue == null) {
						missing.put(it.getValue().getURI(), cnt);
					}
					newUpdate.add(it.getValue());
					cnt++;
				}
				if (!missing.isEmpty()) {
					java.util.List<Inheritance.Order> found = find(missing.keySet().toArray(new String[missing.size()]));
					for (Inheritance.Order it : found) {
						oldUpdate.set(missing.get(it.getURI()), it);
					}
				}
				updateLoop.accept(new java.util.AbstractMap.SimpleEntry<>(oldUpdate, newUpdate), new java.util.AbstractMap.SimpleEntry<>(sw, locator));
				org.revenj.database.postgres.converters.PostgresTuple tupleOld = org.revenj.database.postgres.converters.ArrayTuple.create(oldUpdate, converter::to);
				org.revenj.database.postgres.converters.PostgresTuple tupleNew = org.revenj.database.postgres.converters.ArrayTuple.create(newUpdate, converter::to);
				org.postgresql.util.PGobject pgOld = new org.postgresql.util.PGobject();
				org.postgresql.util.PGobject pgNew = new org.postgresql.util.PGobject();
				pgOld.setType("\"Inheritance\".\"Order_entity\"[]");
				pgNew.setType("\"Inheritance\".\"Order_entity\"[]");
				tupleOld.buildTuple(sw, false);
				pgOld.setValue(sw.toString());
				sw.reset();
				tupleNew.buildTuple(sw, false);
				pgNew.setValue(sw.toString());
				sw.reset();
				statement.setObject(2, pgOld);
				statement.setObject(3, pgNew);
			} else {
				statement.setArray(2, null);
				statement.setArray(3, null);
			}
			if (delete != null && !delete.isEmpty()) {
				sw.reset();
				deleteLoop.accept(delete);
				org.revenj.database.postgres.converters.PostgresTuple tuple = org.revenj.database.postgres.converters.ArrayTuple.create(delete, converter::to);
				org.postgresql.util.PGobject pgo = new org.postgresql.util.PGobject();
				pgo.setType("\"Inheritance\".\"Order_entity\"[]");
				tuple.buildTuple(sw, false);
				pgo.setValue(sw.toString());
				statement.setObject(4, pgo);
			} else {
				statement.setArray(4, null);
			}
			try (java.sql.ResultSet rs = statement.executeQuery()) {
				rs.next();
				String message = rs.getString(1);
				if (message != null) throw new java.io.IOException(message);
			}
			return result;
		} catch (java.sql.SQLException e) {
			throw new java.io.IOException(e);
		} finally { 
			releaseConnection(connection); 
		}
	}

	
	public static void __setupSequenceID(java.util.function.BiConsumer<java.util.Collection<Inheritance.Order>, java.sql.Connection> sequence) {
		assignSequenceID = sequence;
	}

	private static java.util.function.BiConsumer<java.util.Collection<Inheritance.Order>, java.sql.Connection> assignSequenceID;
	
	@org.springframework.beans.factory.annotation.Autowired
	public OrderRepository(javax.sql.DataSource dataSource, org.revenj.patterns.ServiceLocator locator) {
		this(java.util.Optional.empty(), dataSource, locator.resolve(org.revenj.database.postgres.QueryProvider.class), locator.resolve(Inheritance.converters.OrderConverter.class), locator);
	}

}
