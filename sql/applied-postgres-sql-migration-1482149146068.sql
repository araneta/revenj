/*MIGRATION_DESCRIPTION
--CREATE: Cookbook-Recipe
New object Recipe will be created in schema Cookbook
--CREATE: Cookbook-Recipe-ID
New property ID will be created for Recipe in Cookbook
--CREATE: Cookbook-Recipe-name
New property name will be created for Recipe in Cookbook
--CREATE: Cookbook-Recipe-ingredients
New property ingredients will be created for Recipe in Cookbook
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_namespace WHERE nspname = 'Cookbook') THEN
		CREATE SCHEMA "Cookbook";
		COMMENT ON SCHEMA "Cookbook" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Cookbook' AND t.typname = '-ngs_Recipe_type-') THEN	
		CREATE TYPE "Cookbook"."-ngs_Recipe_type-" AS ();
		COMMENT ON TYPE "Cookbook"."-ngs_Recipe_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Cookbook' AND c.relname = 'Recipe') THEN	
		CREATE TABLE "Cookbook"."Recipe" ();
		COMMENT ON TABLE "Cookbook"."Recipe" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Cookbook' AND c.relname = 'Recipe_sequence') THEN
		CREATE SEQUENCE "Cookbook"."Recipe_sequence";
		COMMENT ON SEQUENCE "Cookbook"."Recipe_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = 'Recipe' AND column_name = 'ID') THEN
		ALTER TABLE "Cookbook"."Recipe" ADD COLUMN "ID" INT;
		COMMENT ON COLUMN "Cookbook"."Recipe"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ID') THEN
		ALTER TYPE "Cookbook"."-ngs_Recipe_type-" ADD ATTRIBUTE "ID" INT;
		COMMENT ON COLUMN "Cookbook"."-ngs_Recipe_type-"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = 'Recipe' AND column_name = 'name') THEN
		ALTER TABLE "Cookbook"."Recipe" ADD COLUMN "name" VARCHAR;
		COMMENT ON COLUMN "Cookbook"."Recipe"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = '-ngs_Recipe_type-' AND column_name = 'name') THEN
		ALTER TYPE "Cookbook"."-ngs_Recipe_type-" ADD ATTRIBUTE "name" VARCHAR;
		COMMENT ON COLUMN "Cookbook"."-ngs_Recipe_type-"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = 'Recipe' AND column_name = 'ingredients') THEN
		ALTER TABLE "Cookbook"."Recipe" ADD COLUMN "ingredients" VARCHAR[];
		COMMENT ON COLUMN "Cookbook"."Recipe"."ingredients" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Cookbook' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ingredients') THEN
		ALTER TYPE "Cookbook"."-ngs_Recipe_type-" ADD ATTRIBUTE "ingredients" VARCHAR[];
		COMMENT ON COLUMN "Cookbook"."-ngs_Recipe_type-"."ingredients" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Cookbook"."Recipe_entity" AS
SELECT _entity."ID", _entity."name", _entity."ingredients"
FROM
	"Cookbook"."Recipe" _entity
	;
COMMENT ON VIEW "Cookbook"."Recipe_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Cookbook"."Recipe_entity") RETURNS TEXT AS $$
SELECT CAST($1."ID" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Cookbook"."cast_Recipe_to_type"("Cookbook"."-ngs_Recipe_type-") RETURNS "Cookbook"."Recipe_entity" AS $$ SELECT $1::text::"Cookbook"."Recipe_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Cookbook"."cast_Recipe_to_type"("Cookbook"."Recipe_entity") RETURNS "Cookbook"."-ngs_Recipe_type-" AS $$ SELECT $1::text::"Cookbook"."-ngs_Recipe_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Cookbook' AND s.typname = 'Recipe_entity' AND t.typname = '-ngs_Recipe_type-') THEN
		CREATE CAST ("Cookbook"."-ngs_Recipe_type-" AS "Cookbook"."Recipe_entity") WITH FUNCTION "Cookbook"."cast_Recipe_to_type"("Cookbook"."-ngs_Recipe_type-") AS IMPLICIT;
		CREATE CAST ("Cookbook"."Recipe_entity" AS "Cookbook"."-ngs_Recipe_type-") WITH FUNCTION "Cookbook"."cast_Recipe_to_type"("Cookbook"."Recipe_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Cookbook"."Recipe_unprocessed_events" AS
SELECT _aggregate."ID"
FROM
	"Cookbook"."Recipe_entity" _aggregate
;
COMMENT ON VIEW "Cookbook"."Recipe_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Cookbook"."insert_Recipe"(IN _inserted "Cookbook"."Recipe_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Cookbook"."Recipe" ("ID", "name", "ingredients") VALUES(_inserted[1]."ID", _inserted[1]."name", _inserted[1]."ingredients");
	
	PERFORM pg_notify('aggregate_roots', 'Cookbook.Recipe:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Cookbook"."persist_Recipe"(
IN _inserted "Cookbook"."Recipe_entity"[], IN _updated_original "Cookbook"."Recipe_entity"[], IN _updated_new "Cookbook"."Recipe_entity"[], IN _deleted "Cookbook"."Recipe_entity"[]) 
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

	

	INSERT INTO "Cookbook"."Recipe" ("ID", "name", "ingredients")
	SELECT _i."ID", _i."name", _i."ingredients" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Cookbook"."Recipe" as _tbl SET "ID" = (_u.changed)."ID", "name" = (_u.changed)."name", "ingredients" = (_u.changed)."ingredients"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."ID" = (_u.original)."ID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Cookbook"."Recipe"
	WHERE ("ID") IN (SELECT _d."ID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Cookbook.Recipe', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Cookbook.Recipe', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Cookbook.Recipe', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."ID" != (_u.original)."ID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Cookbook.Recipe', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Cookbook"."update_Recipe"(IN _original "Cookbook"."Recipe_entity"[], IN _updated "Cookbook"."Recipe_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Cookbook"."Recipe" AS _tab SET "ID" = _updated[1]."ID", "name" = _updated[1]."name", "ingredients" = _updated[1]."ingredients" WHERE _tab."ID" = _original[1]."ID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Cookbook.Recipe:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."ID" != _updated[1]."ID") THEN
		PERFORM pg_notify('aggregate_roots', 'Cookbook.Recipe:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

SELECT "-DSL-".Create_Type_Cast('"Cookbook"."cast_Recipe_to_type"("Cookbook"."-ngs_Recipe_type-")', 'Cookbook', '-ngs_Recipe_type-', 'Recipe_entity');
SELECT "-DSL-".Create_Type_Cast('"Cookbook"."cast_Recipe_to_type"("Cookbook"."Recipe_entity")', 'Cookbook', 'Recipe_entity', '-ngs_Recipe_type-');
UPDATE "Cookbook"."Recipe" SET "ID" = 0 WHERE "ID" IS NULL;
UPDATE "Cookbook"."Recipe" SET "name" = '' WHERE "name" IS NULL;
UPDATE "Cookbook"."Recipe" SET "ingredients" = '{}' WHERE "ingredients" IS NULL;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Cookbook' AND c.relname = 'Recipe') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Cookbook"."Recipe"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('ID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Cookbook.Recipe. Expected primary key: ID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Cookbook"."Recipe" ADD CONSTRAINT "pk_Recipe" PRIMARY KEY("ID");
		COMMENT ON CONSTRAINT "pk_Recipe" ON "Cookbook"."Recipe" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Cookbook"."Recipe" ALTER "ID" SET NOT NULL;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON c.relnamespace = n.oid WHERE n.nspname = 'Cookbook' AND c.relname = 'Recipe_ID_seq' AND c.relkind = 'S') THEN
		CREATE SEQUENCE "Cookbook"."Recipe_ID_seq";
		ALTER TABLE "Cookbook"."Recipe"	ALTER COLUMN "ID" SET DEFAULT NEXTVAL('"Cookbook"."Recipe_ID_seq"');
		PERFORM SETVAL('"Cookbook"."Recipe_ID_seq"', COALESCE(MAX("ID"), 0) + 1000) FROM "Cookbook"."Recipe";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Cookbook"."Recipe" ALTER "name" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_index i JOIN pg_class r ON i.indexrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE n.nspname = 'Cookbook' AND r.relname = 'uq_Recipe_name') THEN
		CREATE UNIQUE INDEX "uq_Recipe_name" ON "Cookbook"."Recipe" ("name");
		COMMENT ON INDEX "Cookbook"."uq_Recipe_name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'uq_Recipe_name' AND n.nspname = 'Cookbook' AND r.relname = 'Recipe') THEN	
		ALTER TABLE "Cookbook"."Recipe" ADD CONSTRAINT "uq_Recipe_name" UNIQUE USING INDEX "uq_Recipe_name" DEFERRABLE;
		COMMENT ON CONSTRAINT "uq_Recipe_name" ON "Cookbook"."Recipe" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Cookbook"."Recipe" ALTER "ingredients" SET NOT NULL;

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
", "dsl/test2.dsl"=>"module Cookbook
{
    root Recipe
    {
        String    name { unique;}
        String[]  ingredients;
    }
}
"', '\x','1.7.6196.23272');
SELECT pg_notify('migration', 'new');