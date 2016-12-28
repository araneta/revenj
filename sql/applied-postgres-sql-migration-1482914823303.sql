/*MIGRATION_DESCRIPTION
--CREATE: security-RegisterUser
New object RegisterUser will be created in schema security
--CREATE: security-RegisterUser-username
New property username will be created for RegisterUser in security
--CREATE: security-RegisterUser-password
New property password will be created for RegisterUser in security
--CREATE: security-UserRegistered
New object UserRegistered will be created in schema security
--CREATE: security-UserRegistered-username
New property username will be created for UserRegistered in security
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'RegisterUser') THEN	
		CREATE TABLE "security"."RegisterUser" 
		(
			_event_id BIGSERIAL PRIMARY KEY,
			_queued_at TIMESTAMPTZ NOT NULL DEFAULT(NOW()),
			_processed_at TIMESTAMPTZ
		);
		COMMENT ON TABLE "security"."RegisterUser" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'UserRegistered') THEN	
		CREATE TABLE "security"."UserRegistered" 
		(
			_event_id BIGSERIAL PRIMARY KEY,
			_queued_at TIMESTAMPTZ NOT NULL DEFAULT(NOW()),
			_processed_at TIMESTAMPTZ
		);
		COMMENT ON TABLE "security"."UserRegistered" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'RegisterUser' AND column_name = 'username') THEN
		ALTER TABLE "security"."RegisterUser" ADD COLUMN "username" VARCHAR;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'RegisterUser' AND column_name = 'password') THEN
		ALTER TABLE "security"."RegisterUser" ADD COLUMN "password" BYTEA;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'UserRegistered' AND column_name = 'username') THEN
		ALTER TABLE "security"."UserRegistered" ADD COLUMN "username" VARCHAR;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "security"."RegisterUser_event" AS
SELECT _event._event_id AS "_event_id", _event._queued_at AS "QueuedAt", _event._processed_at AS "ProcessedAt" , _event."username", _event."password"
FROM
	"security"."RegisterUser" _event
;

CREATE OR REPLACE FUNCTION "URI"("security"."RegisterUser_event") RETURNS TEXT AS $$
SELECT $1."_event_id"::text
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "security"."UserRegistered_event" AS
SELECT _event._event_id AS "_event_id", _event._queued_at AS "QueuedAt", _event._processed_at AS "ProcessedAt" , _event."username"
FROM
	"security"."UserRegistered" _event
;

CREATE OR REPLACE FUNCTION "URI"("security"."UserRegistered_event") RETURNS TEXT AS $$
SELECT $1."_event_id"::text
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."mark_RegisterUser"(_events BIGINT[])
	RETURNS VOID AS
$$
BEGIN
	UPDATE "security"."RegisterUser" SET _processed_at = CURRENT_TIMESTAMP WHERE _event_id = ANY(_events) AND _processed_at IS NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."mark_UserRegistered"(_events BIGINT[])
	RETURNS VOID AS
$$
BEGIN
	UPDATE "security"."UserRegistered" SET _processed_at = CURRENT_TIMESTAMP WHERE _event_id = ANY(_events) AND _processed_at IS NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."submit_RegisterUser"(IN events "security"."RegisterUser_event"[], OUT "URI" VARCHAR) 
	RETURNS SETOF VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE newUris VARCHAR[];
BEGIN

	

	FOR uri IN 
		INSERT INTO "security"."RegisterUser" (_queued_at, _processed_at, "username", "password")
		SELECT i."QueuedAt", i."ProcessedAt" , i."username", i."password"
		FROM unnest(events) i
		RETURNING _event_id::text
	LOOP
		"URI" = uri;
		newUris = array_append(newUris, uri);
		RETURN NEXT;
	END LOOP;

	PERFORM "-DSL-".Safe_Notify('events', 'security.RegisterUser', 'Insert', newUris);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."cast_RegisterUser_to_type"(int8) RETURNS "security"."RegisterUser_event" AS $$ SELECT _e FROM "security"."RegisterUser_event" _e WHERE _e."_event_id" = $1 $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "security"."cast_RegisterUser_to_type"("security"."RegisterUser_event") RETURNS int8 AS $$ SELECT $1."_event_id" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'security' AND s.typname = 'RegisterUser_event' AND t.typname = 'int8') THEN
		CREATE CAST (int8 AS "security"."RegisterUser_event") WITH FUNCTION "security"."cast_RegisterUser_to_type"(int8) AS IMPLICIT;
		CREATE CAST ("security"."RegisterUser_event" AS int8) WITH FUNCTION "security"."cast_RegisterUser_to_type"("security"."RegisterUser_event") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "security"."submit_UserRegistered"(IN events "security"."UserRegistered_event"[], OUT "URI" VARCHAR) 
	RETURNS SETOF VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE newUris VARCHAR[];
BEGIN

	

	FOR uri IN 
		INSERT INTO "security"."UserRegistered" (_queued_at, _processed_at, "username")
		SELECT i."QueuedAt", i."ProcessedAt" , i."username"
		FROM unnest(events) i
		RETURNING _event_id::text
	LOOP
		"URI" = uri;
		newUris = array_append(newUris, uri);
		RETURN NEXT;
	END LOOP;

	PERFORM "-DSL-".Safe_Notify('events', 'security.UserRegistered', 'Insert', newUris);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."cast_UserRegistered_to_type"(int8) RETURNS "security"."UserRegistered_event" AS $$ SELECT _e FROM "security"."UserRegistered_event" _e WHERE _e."_event_id" = $1 $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "security"."cast_UserRegistered_to_type"("security"."UserRegistered_event") RETURNS int8 AS $$ SELECT $1."_event_id" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'security' AND s.typname = 'UserRegistered_event' AND t.typname = 'int8') THEN
		CREATE CAST (int8 AS "security"."UserRegistered_event") WITH FUNCTION "security"."cast_UserRegistered_to_type"(int8) AS IMPLICIT;
		CREATE CAST ("security"."UserRegistered_event" AS int8) WITH FUNCTION "security"."cast_UserRegistered_to_type"("security"."UserRegistered_event") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;
COMMENT ON VIEW "security"."RegisterUser_event" IS 'NGS volatile';
COMMENT ON VIEW "security"."UserRegistered_event" IS 'NGS volatile';

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_index i JOIN pg_class r ON i.indexrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE n.nspname = 'security' AND r.relname = 'ix_unprocessed_events_security_RegisterUser') THEN
		CREATE INDEX "ix_unprocessed_events_security_RegisterUser" ON "security"."RegisterUser" (_event_id) WHERE _processed_at IS NULL;
		COMMENT ON INDEX "security"."ix_unprocessed_events_security_RegisterUser" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_index i JOIN pg_class r ON i.indexrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE n.nspname = 'security' AND r.relname = 'ix_unprocessed_events_security_UserRegistered') THEN
		CREATE INDEX "ix_unprocessed_events_security_UserRegistered" ON "security"."UserRegistered" (_event_id) WHERE _processed_at IS NULL;
		COMMENT ON INDEX "security"."ix_unprocessed_events_security_UserRegistered" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

SELECT "-DSL-".Persist_Concepts('"dsl/security.dsl"=>"module security {

	root User(name)	{
		string(100) name;
		//Revenj supports Postgres arrays and this will be mapped to varchar[] in the database
		Set<string> roles;
		binary password;
		bool isAllowed;
	}

	root GlobalPermission(name)	{
		string(200) name;
		bool isAllowed;
		//Implements will attach specified signature to GlobalPermission aggregate root in generated code.
		//Since builtin RevenjPermissionManager uses this specific signature to load up security metadata
		//this way we can use our \"custom\" object with builtin simple permission infrastructure
		implements Java ''org.revenj.security.GlobalPermission'';
		//Compiler will create SQL function with the matching implementation which Revenj will use
		//when search by specification is called on repository/data context
		specification WithPrefix ''it => it.name == prefix || it.name.StartsWith(prefix + \".\")'' {
			string prefix;
		}
	}

	root RolePermission(name, roleID) {
		string(200) name;
		//the variable is named this way to match the RolePermission interface signature
		string roleID;
		bool isAllowed;
		implements Java ''org.revenj.security.RolePermission'';
		specification ForRole ''it => it.roleID == role'' {
			string role;
		}
	}
	
	//use this event as a command
	event RegisterUser {
		string username;
		binary password;
	}
	
	//use this event as Domain event
	event UserRegistered {
		string username;
	}
}", "dsl/model.dsl"=>"//module will be mapped to part of the namespace in Java
//and to the schema name in Postgres
module hello {
	//Matching table will be created in the Postgres (although with this DSL there is one extra implied field)
	aggregate World {
		string message;
	}
}", "dsl/test1.dsl"=>"module Inheritance
{
    root Person
    {
        string Name;
    }
    root Employee
    {
        date StartedWorking;
        Person *Person;
    }
    root Waiter
    {
        Employee *Employee;
    }
    snowflake WaiterObject from Waiter
    {
        Employee.Person.Name;
        Employee.StartedWorking;
    }
    event ServeTable
    {        
        string Table;
    }
    aggregate Customer
	{
	  string name;
	}

	aggregate Order
	{
	  date placed;
	  date? deadline;
	  Customer? *customer;

	  List<LineItem> items;
	}

	entity LineItem
	{
	  string productName;
	  decimal quantity;
	}


	snowflake OrderInfo from Order
	{
	  placed;
	  deadline;
	  customer.name customerName;

	  order by deadline desc;
	}
	/*
	report OrderReport
	{
		int? total;
		Set<string> customers;
		List<Order> ordersByCustomers 
			''it => customers.Contains(it.customer)''
			limit total
			order by placed asc;
		List<Order> allOtherOrdersInThatRange
			''it => !customers.Contains(it.customer)
				&& ordersByCustomers.FirstOrDefault() != null
				&& ordersByCustomers.FirstOrDefault().placed <= it.placed
				&& ordersByCustomers.LastOrDefault().placed >= it.placed''
			order by placed asc;                
	}*/


}
", "dsl/test2.dsl"=>"module Cookbook
{
    
}
"', '\x','1.7.6200.20202');
SELECT pg_notify('migration', 'new');