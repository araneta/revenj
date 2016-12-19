/*MIGRATION_DESCRIPTION
--REMOVE: Cookbook-Recipe-ingredients
Property ingredients will be removed from object Recipe in schema Cookbook
--REMOVE: Cookbook-Recipe-name
Property name will be removed from object Recipe in schema Cookbook
--REMOVE: Cookbook-Recipe-ID
Property ID will be removed from object Recipe in schema Cookbook
--REMOVE: Cookbook-Recipe
Object Recipe will be removed from schema Cookbook
--CREATE: hello-Recipe
New object Recipe will be created in schema hello
--CREATE: hello-Recipe-ID
New property ID will be created for Recipe in hello
--CREATE: hello-Recipe-name
New property name will be created for Recipe in hello
--CREATE: hello-Recipe-ingredients
New property ingredients will be created for Recipe in hello
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON r.oid = d.objoid AND d.objsubid = 0 WHERE c.conname = 'uq_Recipe_name' AND n.nspname = 'Cookbook' AND r.relname = 'Recipe' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Cookbook"."Recipe" DROP CONSTRAINT "uq_Recipe_name";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Cookbook"."Recipe" ALTER COLUMN "ID" SET DEFAULT NULL;
DROP SEQUENCE IF EXISTS "Cookbook"."Recipe_ID_seq";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_Recipe' AND n.nspname = 'Cookbook' AND r.relname = 'Recipe' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Cookbook"."Recipe" DROP CONSTRAINT "pk_Recipe";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Cookbook' AND t.typname = 'Recipe_entity') THEN
		CREATE OR REPLACE FUNCTION "Cookbook"."cast_Recipe_to_type"("Cookbook"."-ngs_Recipe_type-") RETURNS "Cookbook"."Recipe_entity" AS $x$ SELECT $1::text::"Cookbook"."Recipe_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "Cookbook"."cast_Recipe_to_type"("Cookbook"."Recipe_entity") RETURNS "Cookbook"."-ngs_Recipe_type-" AS $x$ SELECT $1::text::"Cookbook"."-ngs_Recipe_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "Cookbook"."update_Recipe"("Cookbook"."Recipe_entity"[],"Cookbook"."Recipe_entity"[]);;

DROP FUNCTION IF EXISTS "Cookbook"."persist_Recipe"("Cookbook"."Recipe_entity"[], "Cookbook"."Recipe_entity"[], "Cookbook"."Recipe_entity"[], "Cookbook"."Recipe_entity"[]);
DROP FUNCTION IF EXISTS "Cookbook"."insert_Recipe"("Cookbook"."Recipe_entity"[]);;
DROP VIEW IF EXISTS "Cookbook"."Recipe_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Cookbook' AND t.typname = 'Recipe_entity') THEN
		DROP CAST IF EXISTS ("Cookbook"."-ngs_Recipe_type-" AS "Cookbook"."Recipe_entity");
		DROP CAST IF EXISTS ("Cookbook"."Recipe_entity" AS "Cookbook"."-ngs_Recipe_type-");
		DROP FUNCTION IF EXISTS "Cookbook"."cast_Recipe_to_type"("Cookbook"."-ngs_Recipe_type-");
		DROP FUNCTION IF EXISTS "Cookbook"."cast_Recipe_to_type"("Cookbook"."Recipe_entity");
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Cookbook' AND t.typname = 'Recipe_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("Cookbook"."Recipe_entity");
		DROP VIEW IF EXISTS "Cookbook"."Recipe_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ingredients' AND is_ngs_generated) THEN
		ALTER TYPE "Cookbook"."-ngs_Recipe_type-" DROP ATTRIBUTE "ingredients";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = 'Recipe' AND column_name = 'ingredients' AND is_ngs_generated) THEN
		ALTER TABLE "Cookbook"."Recipe" DROP COLUMN "ingredients";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = '-ngs_Recipe_type-' AND column_name = 'name' AND is_ngs_generated) THEN
		ALTER TYPE "Cookbook"."-ngs_Recipe_type-" DROP ATTRIBUTE "name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = 'Recipe' AND column_name = 'name' AND is_ngs_generated) THEN
		ALTER TABLE "Cookbook"."Recipe" DROP COLUMN "name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ID' AND is_ngs_generated) THEN
		ALTER TYPE "Cookbook"."-ngs_Recipe_type-" DROP ATTRIBUTE "ID";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = 'Recipe' AND column_name = 'ID' AND is_ngs_generated) THEN
		ALTER TABLE "Cookbook"."Recipe" DROP COLUMN "ID";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Cookbook' AND c.relname = 'Recipe_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "Cookbook"."Recipe_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Cookbook' AND c.relname = 'Recipe' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "Cookbook"."Recipe";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Cookbook' AND t.typname = '-ngs_Recipe_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "Cookbook"."-ngs_Recipe_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'hello' AND t.typname = '-ngs_Recipe_type-') THEN	
		CREATE TYPE "hello"."-ngs_Recipe_type-" AS ();
		COMMENT ON TYPE "hello"."-ngs_Recipe_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'hello' AND c.relname = 'Recipe') THEN	
		CREATE TABLE "hello"."Recipe" ();
		COMMENT ON TABLE "hello"."Recipe" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'hello' AND c.relname = 'Recipe_sequence') THEN
		CREATE SEQUENCE "hello"."Recipe_sequence";
		COMMENT ON SEQUENCE "hello"."Recipe_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = 'Recipe' AND column_name = 'ID') THEN
		ALTER TABLE "hello"."Recipe" ADD COLUMN "ID" INT;
		COMMENT ON COLUMN "hello"."Recipe"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ID') THEN
		ALTER TYPE "hello"."-ngs_Recipe_type-" ADD ATTRIBUTE "ID" INT;
		COMMENT ON COLUMN "hello"."-ngs_Recipe_type-"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = 'Recipe' AND column_name = 'name') THEN
		ALTER TABLE "hello"."Recipe" ADD COLUMN "name" VARCHAR;
		COMMENT ON COLUMN "hello"."Recipe"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = '-ngs_Recipe_type-' AND column_name = 'name') THEN
		ALTER TYPE "hello"."-ngs_Recipe_type-" ADD ATTRIBUTE "name" VARCHAR;
		COMMENT ON COLUMN "hello"."-ngs_Recipe_type-"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = 'Recipe' AND column_name = 'ingredients') THEN
		ALTER TABLE "hello"."Recipe" ADD COLUMN "ingredients" VARCHAR[];
		COMMENT ON COLUMN "hello"."Recipe"."ingredients" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ingredients') THEN
		ALTER TYPE "hello"."-ngs_Recipe_type-" ADD ATTRIBUTE "ingredients" VARCHAR[];
		COMMENT ON COLUMN "hello"."-ngs_Recipe_type-"."ingredients" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "hello"."Recipe_entity" AS
SELECT _entity."ID", _entity."name", _entity."ingredients"
FROM
	"hello"."Recipe" _entity
	;
COMMENT ON VIEW "hello"."Recipe_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("hello"."Recipe_entity") RETURNS TEXT AS $$
SELECT CAST($1."ID" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "hello"."cast_Recipe_to_type"("hello"."-ngs_Recipe_type-") RETURNS "hello"."Recipe_entity" AS $$ SELECT $1::text::"hello"."Recipe_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "hello"."cast_Recipe_to_type"("hello"."Recipe_entity") RETURNS "hello"."-ngs_Recipe_type-" AS $$ SELECT $1::text::"hello"."-ngs_Recipe_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'hello' AND s.typname = 'Recipe_entity' AND t.typname = '-ngs_Recipe_type-') THEN
		CREATE CAST ("hello"."-ngs_Recipe_type-" AS "hello"."Recipe_entity") WITH FUNCTION "hello"."cast_Recipe_to_type"("hello"."-ngs_Recipe_type-") AS IMPLICIT;
		CREATE CAST ("hello"."Recipe_entity" AS "hello"."-ngs_Recipe_type-") WITH FUNCTION "hello"."cast_Recipe_to_type"("hello"."Recipe_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "hello"."Recipe_unprocessed_events" AS
SELECT _aggregate."ID"
FROM
	"hello"."Recipe_entity" _aggregate
;
COMMENT ON VIEW "hello"."Recipe_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "hello"."insert_Recipe"(IN _inserted "hello"."Recipe_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "hello"."Recipe" ("ID", "name", "ingredients") VALUES(_inserted[1]."ID", _inserted[1]."name", _inserted[1]."ingredients");
	
	PERFORM pg_notify('aggregate_roots', 'hello.Recipe:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "hello"."persist_Recipe"(
IN _inserted "hello"."Recipe_entity"[], IN _updated_original "hello"."Recipe_entity"[], IN _updated_new "hello"."Recipe_entity"[], IN _deleted "hello"."Recipe_entity"[]) 
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

	

	INSERT INTO "hello"."Recipe" ("ID", "name", "ingredients")
	SELECT _i."ID", _i."name", _i."ingredients" 
	FROM unnest(_inserted) _i;

	

	UPDATE "hello"."Recipe" as _tbl SET "ID" = (_u.changed)."ID", "name" = (_u.changed)."name", "ingredients" = (_u.changed)."ingredients"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."ID" = (_u.original)."ID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "hello"."Recipe"
	WHERE ("ID") IN (SELECT _d."ID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'hello.Recipe', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'hello.Recipe', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'hello.Recipe', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."ID" != (_u.original)."ID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'hello.Recipe', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "hello"."update_Recipe"(IN _original "hello"."Recipe_entity"[], IN _updated "hello"."Recipe_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "hello"."Recipe" AS _tab SET "ID" = _updated[1]."ID", "name" = _updated[1]."name", "ingredients" = _updated[1]."ingredients" WHERE _tab."ID" = _original[1]."ID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'hello.Recipe:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."ID" != _updated[1]."ID") THEN
		PERFORM pg_notify('aggregate_roots', 'hello.Recipe:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

SELECT "-DSL-".Create_Type_Cast('"hello"."cast_Recipe_to_type"("hello"."-ngs_Recipe_type-")', 'hello', '-ngs_Recipe_type-', 'Recipe_entity');
SELECT "-DSL-".Create_Type_Cast('"hello"."cast_Recipe_to_type"("hello"."Recipe_entity")', 'hello', 'Recipe_entity', '-ngs_Recipe_type-');
UPDATE "hello"."Recipe" SET "ID" = 0 WHERE "ID" IS NULL;
UPDATE "hello"."Recipe" SET "name" = '' WHERE "name" IS NULL;
UPDATE "hello"."Recipe" SET "ingredients" = '{}' WHERE "ingredients" IS NULL;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'hello' AND c.relname = 'Recipe') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"hello"."Recipe"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('ID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table hello.Recipe. Expected primary key: ID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "hello"."Recipe" ADD CONSTRAINT "pk_Recipe" PRIMARY KEY("ID");
		COMMENT ON CONSTRAINT "pk_Recipe" ON "hello"."Recipe" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "hello"."Recipe" ALTER "ID" SET NOT NULL;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON c.relnamespace = n.oid WHERE n.nspname = 'hello' AND c.relname = 'Recipe_ID_seq' AND c.relkind = 'S') THEN
		CREATE SEQUENCE "hello"."Recipe_ID_seq";
		ALTER TABLE "hello"."Recipe"	ALTER COLUMN "ID" SET DEFAULT NEXTVAL('"hello"."Recipe_ID_seq"');
		PERFORM SETVAL('"hello"."Recipe_ID_seq"', COALESCE(MAX("ID"), 0) + 1000) FROM "hello"."Recipe";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "hello"."Recipe" ALTER "name" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_index i JOIN pg_class r ON i.indexrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE n.nspname = 'hello' AND r.relname = 'uq_Recipe_name') THEN
		CREATE UNIQUE INDEX "uq_Recipe_name" ON "hello"."Recipe" ("name");
		COMMENT ON INDEX "hello"."uq_Recipe_name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'uq_Recipe_name' AND n.nspname = 'hello' AND r.relname = 'Recipe') THEN	
		ALTER TABLE "hello"."Recipe" ADD CONSTRAINT "uq_Recipe_name" UNIQUE USING INDEX "uq_Recipe_name" DEFERRABLE;
		COMMENT ON CONSTRAINT "uq_Recipe_name" ON "hello"."Recipe" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "hello"."Recipe" ALTER "ingredients" SET NOT NULL;

SELECT "-DSL-".Persist_Concepts('"dsl/model.dsl"=>"module hello {
  aggregate World(ID) {
    int ID { sequence; }
    string message;
  }
  root Recipe
    {
        String    name { unique;}
        String[]  ingredients;
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
", "dsl/test2.dsl"=>"module Cookbook
{
    
}
"', '\x','1.7.6196.23272');
SELECT pg_notify('migration', 'removal');