/*MIGRATION_DESCRIPTION
--CREATE: Inheritance-Person
New object Person will be created in schema Inheritance
--CREATE: Inheritance-Person-ID
New property ID will be created for Person in Inheritance
--CREATE: Inheritance-Person-Name
New property Name will be created for Person in Inheritance
--CREATE: Inheritance-Employee
New object Employee will be created in schema Inheritance
--CREATE: Inheritance-Employee-ID
New property ID will be created for Employee in Inheritance
--CREATE: Inheritance-Employee-StartedWorking
New property StartedWorking will be created for Employee in Inheritance
--CREATE: Inheritance-Employee-PersonID
New property PersonID will be created for Employee in Inheritance
--CREATE: Inheritance-Waiter
New object Waiter will be created in schema Inheritance
--CREATE: Inheritance-Waiter-ID
New property ID will be created for Waiter in Inheritance
--CREATE: Inheritance-Waiter-EmployeeID
New property EmployeeID will be created for Waiter in Inheritance
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_namespace WHERE nspname = 'Inheritance') THEN
		CREATE SCHEMA "Inheritance";
		COMMENT ON SCHEMA "Inheritance" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = '-ngs_Person_type-') THEN	
		CREATE TYPE "Inheritance"."-ngs_Person_type-" AS ();
		COMMENT ON TYPE "Inheritance"."-ngs_Person_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Person') THEN	
		CREATE TABLE "Inheritance"."Person" ();
		COMMENT ON TABLE "Inheritance"."Person" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Person_sequence') THEN
		CREATE SEQUENCE "Inheritance"."Person_sequence";
		COMMENT ON SEQUENCE "Inheritance"."Person_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = '-ngs_Employee_type-') THEN	
		CREATE TYPE "Inheritance"."-ngs_Employee_type-" AS ();
		COMMENT ON TYPE "Inheritance"."-ngs_Employee_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Employee') THEN	
		CREATE TABLE "Inheritance"."Employee" ();
		COMMENT ON TABLE "Inheritance"."Employee" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Employee_sequence') THEN
		CREATE SEQUENCE "Inheritance"."Employee_sequence";
		COMMENT ON SEQUENCE "Inheritance"."Employee_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = '-ngs_Waiter_type-') THEN	
		CREATE TYPE "Inheritance"."-ngs_Waiter_type-" AS ();
		COMMENT ON TYPE "Inheritance"."-ngs_Waiter_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Waiter') THEN	
		CREATE TABLE "Inheritance"."Waiter" ();
		COMMENT ON TABLE "Inheritance"."Waiter" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Waiter_sequence') THEN
		CREATE SEQUENCE "Inheritance"."Waiter_sequence";
		COMMENT ON SEQUENCE "Inheritance"."Waiter_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Person' AND column_name = 'ID') THEN
		ALTER TABLE "Inheritance"."Person" ADD COLUMN "ID" INT;
		COMMENT ON COLUMN "Inheritance"."Person"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Person_type-' AND column_name = 'ID') THEN
		ALTER TYPE "Inheritance"."-ngs_Person_type-" ADD ATTRIBUTE "ID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Person_type-"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Person' AND column_name = 'Name') THEN
		ALTER TABLE "Inheritance"."Person" ADD COLUMN "Name" VARCHAR;
		COMMENT ON COLUMN "Inheritance"."Person"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Person_type-' AND column_name = 'Name') THEN
		ALTER TYPE "Inheritance"."-ngs_Person_type-" ADD ATTRIBUTE "Name" VARCHAR;
		COMMENT ON COLUMN "Inheritance"."-ngs_Person_type-"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Employee' AND column_name = 'ID') THEN
		ALTER TABLE "Inheritance"."Employee" ADD COLUMN "ID" INT;
		COMMENT ON COLUMN "Inheritance"."Employee"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Employee_type-' AND column_name = 'ID') THEN
		ALTER TYPE "Inheritance"."-ngs_Employee_type-" ADD ATTRIBUTE "ID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Employee_type-"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Employee' AND column_name = 'StartedWorking') THEN
		ALTER TABLE "Inheritance"."Employee" ADD COLUMN "StartedWorking" DATE;
		COMMENT ON COLUMN "Inheritance"."Employee"."StartedWorking" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Employee_type-' AND column_name = 'StartedWorking') THEN
		ALTER TYPE "Inheritance"."-ngs_Employee_type-" ADD ATTRIBUTE "StartedWorking" DATE;
		COMMENT ON COLUMN "Inheritance"."-ngs_Employee_type-"."StartedWorking" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Employee_type-' AND column_name = 'PersonURI') THEN
		ALTER TYPE "Inheritance"."-ngs_Employee_type-" ADD ATTRIBUTE "PersonURI" TEXT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Employee_type-"."PersonURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Employee' AND column_name = 'PersonID') THEN
		ALTER TABLE "Inheritance"."Employee" ADD COLUMN "PersonID" INT;
		COMMENT ON COLUMN "Inheritance"."Employee"."PersonID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Employee_type-' AND column_name = 'PersonID') THEN
		ALTER TYPE "Inheritance"."-ngs_Employee_type-" ADD ATTRIBUTE "PersonID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Employee_type-"."PersonID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Waiter' AND column_name = 'ID') THEN
		ALTER TABLE "Inheritance"."Waiter" ADD COLUMN "ID" INT;
		COMMENT ON COLUMN "Inheritance"."Waiter"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Waiter_type-' AND column_name = 'ID') THEN
		ALTER TYPE "Inheritance"."-ngs_Waiter_type-" ADD ATTRIBUTE "ID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Waiter_type-"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Waiter_type-' AND column_name = 'EmployeeURI') THEN
		ALTER TYPE "Inheritance"."-ngs_Waiter_type-" ADD ATTRIBUTE "EmployeeURI" TEXT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Waiter_type-"."EmployeeURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Waiter' AND column_name = 'EmployeeID') THEN
		ALTER TABLE "Inheritance"."Waiter" ADD COLUMN "EmployeeID" INT;
		COMMENT ON COLUMN "Inheritance"."Waiter"."EmployeeID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Waiter_type-' AND column_name = 'EmployeeID') THEN
		ALTER TYPE "Inheritance"."-ngs_Waiter_type-" ADD ATTRIBUTE "EmployeeID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Waiter_type-"."EmployeeID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."Person_entity" AS
SELECT _entity."ID", _entity."Name"
FROM
	"Inheritance"."Person" _entity
	;
COMMENT ON VIEW "Inheritance"."Person_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."Person_entity") RETURNS TEXT AS $$
SELECT CAST($1."ID" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Inheritance"."Employee_entity" AS
SELECT _entity."ID", _entity."StartedWorking", CAST(_entity."PersonID" as TEXT) AS "PersonURI", _entity."PersonID"
FROM
	"Inheritance"."Employee" _entity
	;
COMMENT ON VIEW "Inheritance"."Employee_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."Employee_entity") RETURNS TEXT AS $$
SELECT CAST($1."ID" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Inheritance"."Waiter_entity" AS
SELECT _entity."ID", CAST(_entity."EmployeeID" as TEXT) AS "EmployeeURI", _entity."EmployeeID"
FROM
	"Inheritance"."Waiter" _entity
	;
COMMENT ON VIEW "Inheritance"."Waiter_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."Waiter_entity") RETURNS TEXT AS $$
SELECT CAST($1."ID" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."cast_Person_to_type"("Inheritance"."-ngs_Person_type-") RETURNS "Inheritance"."Person_entity" AS $$ SELECT $1::text::"Inheritance"."Person_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Inheritance"."cast_Person_to_type"("Inheritance"."Person_entity") RETURNS "Inheritance"."-ngs_Person_type-" AS $$ SELECT $1::text::"Inheritance"."-ngs_Person_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Inheritance' AND s.typname = 'Person_entity' AND t.typname = '-ngs_Person_type-') THEN
		CREATE CAST ("Inheritance"."-ngs_Person_type-" AS "Inheritance"."Person_entity") WITH FUNCTION "Inheritance"."cast_Person_to_type"("Inheritance"."-ngs_Person_type-") AS IMPLICIT;
		CREATE CAST ("Inheritance"."Person_entity" AS "Inheritance"."-ngs_Person_type-") WITH FUNCTION "Inheritance"."cast_Person_to_type"("Inheritance"."Person_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."Person_unprocessed_events" AS
SELECT _aggregate."ID"
FROM
	"Inheritance"."Person_entity" _aggregate
;
COMMENT ON VIEW "Inheritance"."Person_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Inheritance"."insert_Person"(IN _inserted "Inheritance"."Person_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Inheritance"."Person" ("ID", "Name") VALUES(_inserted[1]."ID", _inserted[1]."Name");
	
	PERFORM pg_notify('aggregate_roots', 'Inheritance.Person:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Inheritance"."persist_Person"(
IN _inserted "Inheritance"."Person_entity"[], IN _updated_original "Inheritance"."Person_entity"[], IN _updated_new "Inheritance"."Person_entity"[], IN _deleted "Inheritance"."Person_entity"[]) 
	RETURNS VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE _update_count int = array_upper(_updated_original, 1);
DECLARE _delete_count int = array_upper(_deleted, 1);

BEGIN

	SET CONSTRAINTS ALL DEFERRED;

	

	INSERT INTO "Inheritance"."Person" ("ID", "Name")
	SELECT _i."ID", _i."Name" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Inheritance"."Person" as _tbl SET "ID" = (_u.changed)."ID", "Name" = (_u.changed)."Name"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."ID" = (_u.original)."ID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Inheritance"."Person"
	WHERE ("ID") IN (SELECT _d."ID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Person', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Person', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Person', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."ID" != (_u.original)."ID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Person', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."update_Person"(IN _original "Inheritance"."Person_entity"[], IN _updated "Inheritance"."Person_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Inheritance"."Person" AS _tab SET "ID" = _updated[1]."ID", "Name" = _updated[1]."Name" WHERE _tab."ID" = _original[1]."ID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Inheritance.Person:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."ID" != _updated[1]."ID") THEN
		PERFORM pg_notify('aggregate_roots', 'Inheritance.Person:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Inheritance"."cast_Employee_to_type"("Inheritance"."-ngs_Employee_type-") RETURNS "Inheritance"."Employee_entity" AS $$ SELECT $1::text::"Inheritance"."Employee_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Inheritance"."cast_Employee_to_type"("Inheritance"."Employee_entity") RETURNS "Inheritance"."-ngs_Employee_type-" AS $$ SELECT $1::text::"Inheritance"."-ngs_Employee_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Inheritance' AND s.typname = 'Employee_entity' AND t.typname = '-ngs_Employee_type-') THEN
		CREATE CAST ("Inheritance"."-ngs_Employee_type-" AS "Inheritance"."Employee_entity") WITH FUNCTION "Inheritance"."cast_Employee_to_type"("Inheritance"."-ngs_Employee_type-") AS IMPLICIT;
		CREATE CAST ("Inheritance"."Employee_entity" AS "Inheritance"."-ngs_Employee_type-") WITH FUNCTION "Inheritance"."cast_Employee_to_type"("Inheritance"."Employee_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."Employee_unprocessed_events" AS
SELECT _aggregate."ID"
FROM
	"Inheritance"."Employee_entity" _aggregate
;
COMMENT ON VIEW "Inheritance"."Employee_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Inheritance"."insert_Employee"(IN _inserted "Inheritance"."Employee_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Inheritance"."Employee" ("ID", "StartedWorking", "PersonID") VALUES(_inserted[1]."ID", _inserted[1]."StartedWorking", _inserted[1]."PersonID");
	
	PERFORM pg_notify('aggregate_roots', 'Inheritance.Employee:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Inheritance"."persist_Employee"(
IN _inserted "Inheritance"."Employee_entity"[], IN _updated_original "Inheritance"."Employee_entity"[], IN _updated_new "Inheritance"."Employee_entity"[], IN _deleted "Inheritance"."Employee_entity"[]) 
	RETURNS VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE _update_count int = array_upper(_updated_original, 1);
DECLARE _delete_count int = array_upper(_deleted, 1);

BEGIN

	SET CONSTRAINTS ALL DEFERRED;

	

	INSERT INTO "Inheritance"."Employee" ("ID", "StartedWorking", "PersonID")
	SELECT _i."ID", _i."StartedWorking", _i."PersonID" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Inheritance"."Employee" as _tbl SET "ID" = (_u.changed)."ID", "StartedWorking" = (_u.changed)."StartedWorking", "PersonID" = (_u.changed)."PersonID"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."ID" = (_u.original)."ID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Inheritance"."Employee"
	WHERE ("ID") IN (SELECT _d."ID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Employee', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Employee', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Employee', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."ID" != (_u.original)."ID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Employee', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."update_Employee"(IN _original "Inheritance"."Employee_entity"[], IN _updated "Inheritance"."Employee_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Inheritance"."Employee" AS _tab SET "ID" = _updated[1]."ID", "StartedWorking" = _updated[1]."StartedWorking", "PersonID" = _updated[1]."PersonID" WHERE _tab."ID" = _original[1]."ID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Inheritance.Employee:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."ID" != _updated[1]."ID") THEN
		PERFORM pg_notify('aggregate_roots', 'Inheritance.Employee:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Person"("Inheritance"."Employee_entity") RETURNS "Inheritance"."Person_entity" AS $$ 
SELECT _r FROM "Inheritance"."Person_entity" _r WHERE _r."ID" = $1."PersonID"
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION "Inheritance"."cast_Waiter_to_type"("Inheritance"."-ngs_Waiter_type-") RETURNS "Inheritance"."Waiter_entity" AS $$ SELECT $1::text::"Inheritance"."Waiter_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Inheritance"."cast_Waiter_to_type"("Inheritance"."Waiter_entity") RETURNS "Inheritance"."-ngs_Waiter_type-" AS $$ SELECT $1::text::"Inheritance"."-ngs_Waiter_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Inheritance' AND s.typname = 'Waiter_entity' AND t.typname = '-ngs_Waiter_type-') THEN
		CREATE CAST ("Inheritance"."-ngs_Waiter_type-" AS "Inheritance"."Waiter_entity") WITH FUNCTION "Inheritance"."cast_Waiter_to_type"("Inheritance"."-ngs_Waiter_type-") AS IMPLICIT;
		CREATE CAST ("Inheritance"."Waiter_entity" AS "Inheritance"."-ngs_Waiter_type-") WITH FUNCTION "Inheritance"."cast_Waiter_to_type"("Inheritance"."Waiter_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."Waiter_unprocessed_events" AS
SELECT _aggregate."ID"
FROM
	"Inheritance"."Waiter_entity" _aggregate
;
COMMENT ON VIEW "Inheritance"."Waiter_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Inheritance"."insert_Waiter"(IN _inserted "Inheritance"."Waiter_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Inheritance"."Waiter" ("ID", "EmployeeID") VALUES(_inserted[1]."ID", _inserted[1]."EmployeeID");
	
	PERFORM pg_notify('aggregate_roots', 'Inheritance.Waiter:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Inheritance"."persist_Waiter"(
IN _inserted "Inheritance"."Waiter_entity"[], IN _updated_original "Inheritance"."Waiter_entity"[], IN _updated_new "Inheritance"."Waiter_entity"[], IN _deleted "Inheritance"."Waiter_entity"[]) 
	RETURNS VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE _update_count int = array_upper(_updated_original, 1);
DECLARE _delete_count int = array_upper(_deleted, 1);

BEGIN

	SET CONSTRAINTS ALL DEFERRED;

	

	INSERT INTO "Inheritance"."Waiter" ("ID", "EmployeeID")
	SELECT _i."ID", _i."EmployeeID" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Inheritance"."Waiter" as _tbl SET "ID" = (_u.changed)."ID", "EmployeeID" = (_u.changed)."EmployeeID"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."ID" = (_u.original)."ID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Inheritance"."Waiter"
	WHERE ("ID") IN (SELECT _d."ID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Waiter', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Waiter', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Waiter', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."ID" != (_u.original)."ID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Waiter', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."update_Waiter"(IN _original "Inheritance"."Waiter_entity"[], IN _updated "Inheritance"."Waiter_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Inheritance"."Waiter" AS _tab SET "ID" = _updated[1]."ID", "EmployeeID" = _updated[1]."EmployeeID" WHERE _tab."ID" = _original[1]."ID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Inheritance.Waiter:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."ID" != _updated[1]."ID") THEN
		PERFORM pg_notify('aggregate_roots', 'Inheritance.Waiter:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Employee"("Inheritance"."Waiter_entity") RETURNS "Inheritance"."Employee_entity" AS $$ 
SELECT _r FROM "Inheritance"."Employee_entity" _r WHERE _r."ID" = $1."EmployeeID"
$$ LANGUAGE SQL;

SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Person_to_type"("Inheritance"."-ngs_Person_type-")', 'Inheritance', '-ngs_Person_type-', 'Person_entity');
SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Person_to_type"("Inheritance"."Person_entity")', 'Inheritance', 'Person_entity', '-ngs_Person_type-');

SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Employee_to_type"("Inheritance"."-ngs_Employee_type-")', 'Inheritance', '-ngs_Employee_type-', 'Employee_entity');
SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Employee_to_type"("Inheritance"."Employee_entity")', 'Inheritance', 'Employee_entity', '-ngs_Employee_type-');

SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Waiter_to_type"("Inheritance"."-ngs_Waiter_type-")', 'Inheritance', '-ngs_Waiter_type-', 'Waiter_entity');
SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Waiter_to_type"("Inheritance"."Waiter_entity")', 'Inheritance', 'Waiter_entity', '-ngs_Waiter_type-');
UPDATE "Inheritance"."Person" SET "ID" = 0 WHERE "ID" IS NULL;
UPDATE "Inheritance"."Person" SET "Name" = '' WHERE "Name" IS NULL;
UPDATE "Inheritance"."Employee" SET "ID" = 0 WHERE "ID" IS NULL;
UPDATE "Inheritance"."Employee" SET "StartedWorking" = CURRENT_DATE WHERE "StartedWorking" IS NULL;
UPDATE "Inheritance"."Employee" SET "PersonID" = 0 WHERE "PersonID" IS NULL;
UPDATE "Inheritance"."Waiter" SET "ID" = 0 WHERE "ID" IS NULL;
UPDATE "Inheritance"."Waiter" SET "EmployeeID" = 0 WHERE "EmployeeID" IS NULL;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Inheritance' AND c.relname = 'Person') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Inheritance"."Person"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('ID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Inheritance.Person. Expected primary key: ID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Inheritance"."Person" ADD CONSTRAINT "pk_Person" PRIMARY KEY("ID");
		COMMENT ON CONSTRAINT "pk_Person" ON "Inheritance"."Person" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Inheritance' AND c.relname = 'Employee') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Inheritance"."Employee"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('ID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Inheritance.Employee. Expected primary key: ID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Inheritance"."Employee" ADD CONSTRAINT "pk_Employee" PRIMARY KEY("ID");
		COMMENT ON CONSTRAINT "pk_Employee" ON "Inheritance"."Employee" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Inheritance' AND c.relname = 'Waiter') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Inheritance"."Waiter"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('ID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Inheritance.Waiter. Expected primary key: ID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Inheritance"."Waiter" ADD CONSTRAINT "pk_Waiter" PRIMARY KEY("ID");
		COMMENT ON CONSTRAINT "pk_Waiter" ON "Inheritance"."Waiter" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Person" ALTER "ID" SET NOT NULL;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON c.relnamespace = n.oid WHERE n.nspname = 'Inheritance' AND c.relname = 'Person_ID_seq' AND c.relkind = 'S') THEN
		CREATE SEQUENCE "Inheritance"."Person_ID_seq";
		ALTER TABLE "Inheritance"."Person"	ALTER COLUMN "ID" SET DEFAULT NEXTVAL('"Inheritance"."Person_ID_seq"');
		PERFORM SETVAL('"Inheritance"."Person_ID_seq"', COALESCE(MAX("ID"), 0) + 1000) FROM "Inheritance"."Person";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Person" ALTER "Name" SET NOT NULL;
ALTER TABLE "Inheritance"."Employee" ALTER "ID" SET NOT NULL;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON c.relnamespace = n.oid WHERE n.nspname = 'Inheritance' AND c.relname = 'Employee_ID_seq' AND c.relkind = 'S') THEN
		CREATE SEQUENCE "Inheritance"."Employee_ID_seq";
		ALTER TABLE "Inheritance"."Employee"	ALTER COLUMN "ID" SET DEFAULT NEXTVAL('"Inheritance"."Employee_ID_seq"');
		PERFORM SETVAL('"Inheritance"."Employee_ID_seq"', COALESCE(MAX("ID"), 0) + 1000) FROM "Inheritance"."Employee";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Employee" ALTER "StartedWorking" SET NOT NULL;
ALTER TABLE "Inheritance"."Employee" ALTER "PersonID" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_Person' AND n.nspname = 'Inheritance' AND r.relname = 'Employee') THEN	
		ALTER TABLE "Inheritance"."Employee" 
			ADD CONSTRAINT "fk_Person"
				FOREIGN KEY ("PersonID") REFERENCES "Inheritance"."Person" ("ID")
				ON UPDATE CASCADE ;
		COMMENT ON CONSTRAINT "fk_Person" ON "Inheritance"."Employee" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Waiter" ALTER "ID" SET NOT NULL;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON c.relnamespace = n.oid WHERE n.nspname = 'Inheritance' AND c.relname = 'Waiter_ID_seq' AND c.relkind = 'S') THEN
		CREATE SEQUENCE "Inheritance"."Waiter_ID_seq";
		ALTER TABLE "Inheritance"."Waiter"	ALTER COLUMN "ID" SET DEFAULT NEXTVAL('"Inheritance"."Waiter_ID_seq"');
		PERFORM SETVAL('"Inheritance"."Waiter_ID_seq"', COALESCE(MAX("ID"), 0) + 1000) FROM "Inheritance"."Waiter";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Waiter" ALTER "EmployeeID" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_Employee' AND n.nspname = 'Inheritance' AND r.relname = 'Waiter') THEN	
		ALTER TABLE "Inheritance"."Waiter" 
			ADD CONSTRAINT "fk_Employee"
				FOREIGN KEY ("EmployeeID") REFERENCES "Inheritance"."Employee" ("ID")
				ON UPDATE CASCADE ;
		COMMENT ON CONSTRAINT "fk_Employee" ON "Inheritance"."Waiter" IS 'NGS generated';
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
}
"', '\x','1.7.6196.23272');
SELECT pg_notify('migration', 'new');