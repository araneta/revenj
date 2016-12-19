/*MIGRATION_DESCRIPTION
--REMOVE: Inheritance-ServeTable-Waiter
Property Waiter will be removed from object ServeTable in schema Inheritance
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = 'ServeTable_event') THEN
		DROP CAST IF EXISTS ("Inheritance"."ServeTable_event" AS int8);
		DROP CAST IF EXISTS (int8 AS "Inheritance"."ServeTable_event");
		DROP FUNCTION IF EXISTS "Inheritance"."cast_ServeTable_to_type"("Inheritance"."ServeTable_event");
		DROP FUNCTION IF EXISTS "Inheritance"."cast_ServeTable_to_type"(int8);
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = 'ServeTable_event') THEN
		DROP FUNCTION IF EXISTS "Inheritance"."submit_ServeTable"("Inheritance"."ServeTable_event"[]);
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = 'ServeTable_event') THEN
		DROP FUNCTION IF EXISTS "URI"("Inheritance"."ServeTable_event");
		DROP VIEW IF EXISTS "Inheritance"."ServeTable_event";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."ServeTable" DROP COLUMN IF EXISTS "Waiter";

CREATE OR REPLACE VIEW "Inheritance"."ServeTable_event" AS
SELECT _event._event_id AS "_event_id", _event._queued_at AS "QueuedAt", _event._processed_at AS "ProcessedAt" , _event."Table"
FROM
	"Inheritance"."ServeTable" _event
;

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."ServeTable_event") RETURNS TEXT AS $$
SELECT $1."_event_id"::text
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."submit_ServeTable"(IN events "Inheritance"."ServeTable_event"[], OUT "URI" VARCHAR) 
	RETURNS SETOF VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE newUris VARCHAR[];
BEGIN

	

	FOR uri IN 
		INSERT INTO "Inheritance"."ServeTable" (_queued_at, _processed_at, "Table")
		SELECT i."QueuedAt", i."ProcessedAt" , i."Table"
		FROM unnest(events) i
		RETURNING _event_id::text
	LOOP
		"URI" = uri;
		newUris = array_append(newUris, uri);
		RETURN NEXT;
	END LOOP;

	PERFORM "-DSL-".Safe_Notify('events', 'Inheritance.ServeTable', 'Insert', newUris);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."cast_ServeTable_to_type"(int8) RETURNS "Inheritance"."ServeTable_event" AS $$ SELECT _e FROM "Inheritance"."ServeTable_event" _e WHERE _e."_event_id" = $1 $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Inheritance"."cast_ServeTable_to_type"("Inheritance"."ServeTable_event") RETURNS int8 AS $$ SELECT $1."_event_id" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Inheritance' AND s.typname = 'ServeTable_event' AND t.typname = 'int8') THEN
		CREATE CAST (int8 AS "Inheritance"."ServeTable_event") WITH FUNCTION "Inheritance"."cast_ServeTable_to_type"(int8) AS IMPLICIT;
		CREATE CAST ("Inheritance"."ServeTable_event" AS int8) WITH FUNCTION "Inheritance"."cast_ServeTable_to_type"("Inheritance"."ServeTable_event") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;
COMMENT ON VIEW "Inheritance"."ServeTable_event" IS 'NGS volatile';

SELECT "-DSL-".Persist_Concepts('"dsl/model.dsl"=>"module hello {
  aggregate World(ID) {
    int ID { sequence; }
    string message;
  }
}
module Security
{
	root User(Name)
	{
		string(100) Name;
		Role(Name) *Role;
		binary PasswordHash;
		bool IsAllowed;
		implements server ''Revenj.Security.IUser'';
	}

	root Role(Name)
	{
		string(100) Name;
	}

	root InheritedRole(Name, ParentName)
	{
		string(100) Name;
		string(100) ParentName;
		Role(Name) *Role;
		Role(ParentName) *ParentRole;
		implements server ''Revenj.Security.IUserRoles'';
	}

	role Administrator;

	root GlobalPermission(Name)
	{
		string(200) Name;
		bool IsAllowed;
		implements server ''Revenj.Security.IGlobalPermission'';
	}

	root RolePermission(Name, RoleID)
	{
		string(200) Name;
		Role *Role;
		bool IsAllowed;
		implements server ''Revenj.Security.IRolePermission'';
	}
}
", "dsl/test1.dsl"=>"module Inheritance
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
        //WaiterObject Waiter;
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
", "dsl/test2.dsl"=>""', '\x','1.7.6196.23272');
SELECT pg_notify('migration', 'removal');