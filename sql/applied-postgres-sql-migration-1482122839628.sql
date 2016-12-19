/*MIGRATION_DESCRIPTION
--CREATE: Inheritance-ServeTable
New object ServeTable will be created in schema Inheritance
--CREATE: Inheritance-ServeTable-Waiter
New property Waiter will be created for ServeTable in Inheritance
--CREATE: Inheritance-ServeTable-Table
New property Table will be created for ServeTable in Inheritance
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'ServeTable') THEN	
		CREATE TABLE "Inheritance"."ServeTable" 
		(
			_event_id BIGSERIAL PRIMARY KEY,
			_queued_at TIMESTAMPTZ NOT NULL DEFAULT(NOW()),
			_processed_at TIMESTAMPTZ
		);
		COMMENT ON TABLE "Inheritance"."ServeTable" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'ServeTable' AND column_name = 'Waiter') THEN
		ALTER TABLE "Inheritance"."ServeTable" ADD COLUMN "Waiter" "Inheritance"."-ngs_WaiterObject_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'ServeTable' AND column_name = 'Table') THEN
		ALTER TABLE "Inheritance"."ServeTable" ADD COLUMN "Table" VARCHAR;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."ServeTable_event" AS
SELECT _event._event_id AS "_event_id", _event._queued_at AS "QueuedAt", _event._processed_at AS "ProcessedAt" , _event."Waiter", _event."Table"
FROM
	"Inheritance"."ServeTable" _event
;

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."ServeTable_event") RETURNS TEXT AS $$
SELECT $1."_event_id"::text
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."mark_ServeTable"(_events BIGINT[])
	RETURNS VOID AS
$$
BEGIN
	UPDATE "Inheritance"."ServeTable" SET _processed_at = CURRENT_TIMESTAMP WHERE _event_id = ANY(_events) AND _processed_at IS NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

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
		INSERT INTO "Inheritance"."ServeTable" (_queued_at, _processed_at, "Waiter", "Table")
		SELECT i."QueuedAt", i."ProcessedAt" , i."Waiter", i."Table"
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

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_index i JOIN pg_class r ON i.indexrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE n.nspname = 'Inheritance' AND r.relname = 'ix_unprocessed_events_Inheritance_ServeTable') THEN
		CREATE INDEX "ix_unprocessed_events_Inheritance_ServeTable" ON "Inheritance"."ServeTable" (_event_id) WHERE _processed_at IS NULL;
		COMMENT ON INDEX "Inheritance"."ix_unprocessed_events_Inheritance_ServeTable" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

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
        WaiterObject Waiter;
        string Table;
    }
}
"', '\x','1.7.6196.23272');
SELECT pg_notify('migration', 'new');