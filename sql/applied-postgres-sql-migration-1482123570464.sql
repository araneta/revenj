/*MIGRATION_DESCRIPTION
--CREATE: Inheritance-Order
New object Order will be created in schema Inheritance
--CREATE: Inheritance-Order-ID
New property ID will be created for Order in Inheritance
--CREATE: Inheritance-Order-placed
New property placed will be created for Order in Inheritance
--CREATE: Inheritance-Order-deadline
New property deadline will be created for Order in Inheritance
--CREATE: Inheritance-Order-customerName
New property customerName will be created for Order in Inheritance
--CREATE: Inheritance-LineItem
New object LineItem will be created in schema Inheritance
--CREATE: Inheritance-LineItem-productName
New property productName will be created for LineItem in Inheritance
--CREATE: Inheritance-LineItem-quantity
New property quantity will be created for LineItem in Inheritance
--CREATE: Inheritance-LineItem-OrderID
New property OrderID will be created for LineItem in Inheritance
--CREATE: Inheritance-LineItem-Index
New property Index will be created for LineItem in Inheritance
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = '-ngs_Order_type-') THEN	
		CREATE TYPE "Inheritance"."-ngs_Order_type-" AS ();
		COMMENT ON TYPE "Inheritance"."-ngs_Order_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Order') THEN	
		CREATE TABLE "Inheritance"."Order" ();
		COMMENT ON TABLE "Inheritance"."Order" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'Order_sequence') THEN
		CREATE SEQUENCE "Inheritance"."Order_sequence";
		COMMENT ON SEQUENCE "Inheritance"."Order_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Inheritance' AND t.typname = '-ngs_LineItem_type-') THEN	
		CREATE TYPE "Inheritance"."-ngs_LineItem_type-" AS ();
		COMMENT ON TYPE "Inheritance"."-ngs_LineItem_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = 'LineItem') THEN	
		CREATE TABLE "Inheritance"."LineItem" ();
		COMMENT ON TABLE "Inheritance"."LineItem" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Order' AND column_name = 'ID') THEN
		ALTER TABLE "Inheritance"."Order" ADD COLUMN "ID" INT;
		COMMENT ON COLUMN "Inheritance"."Order"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Order_type-' AND column_name = 'ID') THEN
		ALTER TYPE "Inheritance"."-ngs_Order_type-" ADD ATTRIBUTE "ID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_Order_type-"."ID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Order' AND column_name = 'placed') THEN
		ALTER TABLE "Inheritance"."Order" ADD COLUMN "placed" DATE;
		COMMENT ON COLUMN "Inheritance"."Order"."placed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Order_type-' AND column_name = 'placed') THEN
		ALTER TYPE "Inheritance"."-ngs_Order_type-" ADD ATTRIBUTE "placed" DATE;
		COMMENT ON COLUMN "Inheritance"."-ngs_Order_type-"."placed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Order' AND column_name = 'deadline') THEN
		ALTER TABLE "Inheritance"."Order" ADD COLUMN "deadline" DATE;
		COMMENT ON COLUMN "Inheritance"."Order"."deadline" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Order_type-' AND column_name = 'deadline') THEN
		ALTER TYPE "Inheritance"."-ngs_Order_type-" ADD ATTRIBUTE "deadline" DATE;
		COMMENT ON COLUMN "Inheritance"."-ngs_Order_type-"."deadline" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'Order' AND column_name = 'customerName') THEN
		ALTER TABLE "Inheritance"."Order" ADD COLUMN "customerName" VARCHAR;
		COMMENT ON COLUMN "Inheritance"."Order"."customerName" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Order_type-' AND column_name = 'customerName') THEN
		ALTER TYPE "Inheritance"."-ngs_Order_type-" ADD ATTRIBUTE "customerName" VARCHAR;
		COMMENT ON COLUMN "Inheritance"."-ngs_Order_type-"."customerName" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Order_type-' AND column_name = 'itemsURI') THEN
		ALTER TYPE "Inheritance"."-ngs_Order_type-" ADD ATTRIBUTE "itemsURI" TEXT[];
		COMMENT ON COLUMN "Inheritance"."-ngs_Order_type-"."itemsURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'LineItem' AND column_name = 'productName') THEN
		ALTER TABLE "Inheritance"."LineItem" ADD COLUMN "productName" VARCHAR;
		COMMENT ON COLUMN "Inheritance"."LineItem"."productName" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_LineItem_type-' AND column_name = 'productName') THEN
		ALTER TYPE "Inheritance"."-ngs_LineItem_type-" ADD ATTRIBUTE "productName" VARCHAR;
		COMMENT ON COLUMN "Inheritance"."-ngs_LineItem_type-"."productName" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'LineItem' AND column_name = 'quantity') THEN
		ALTER TABLE "Inheritance"."LineItem" ADD COLUMN "quantity" NUMERIC;
		COMMENT ON COLUMN "Inheritance"."LineItem"."quantity" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_LineItem_type-' AND column_name = 'quantity') THEN
		ALTER TYPE "Inheritance"."-ngs_LineItem_type-" ADD ATTRIBUTE "quantity" NUMERIC;
		COMMENT ON COLUMN "Inheritance"."-ngs_LineItem_type-"."quantity" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'LineItem' AND column_name = 'OrderID') THEN
		ALTER TABLE "Inheritance"."LineItem" ADD COLUMN "OrderID" INT;
		COMMENT ON COLUMN "Inheritance"."LineItem"."OrderID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_LineItem_type-' AND column_name = 'OrderID') THEN
		ALTER TYPE "Inheritance"."-ngs_LineItem_type-" ADD ATTRIBUTE "OrderID" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_LineItem_type-"."OrderID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = 'LineItem' AND column_name = 'Index') THEN
		ALTER TABLE "Inheritance"."LineItem" ADD COLUMN "Index" INT;
		COMMENT ON COLUMN "Inheritance"."LineItem"."Index" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_LineItem_type-' AND column_name = 'Index') THEN
		ALTER TYPE "Inheritance"."-ngs_LineItem_type-" ADD ATTRIBUTE "Index" INT;
		COMMENT ON COLUMN "Inheritance"."-ngs_LineItem_type-"."Index" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '-ngs_Order_type-' AND column_name = 'items') THEN
		ALTER TYPE "Inheritance"."-ngs_Order_type-" ADD ATTRIBUTE "items" "Inheritance"."-ngs_LineItem_type-"[];
		COMMENT ON COLUMN "Inheritance"."-ngs_Order_type-"."items" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."LineItem_entity" AS
SELECT _entity."productName", _entity."quantity", _entity."OrderID", _entity."Index"
FROM
	"Inheritance"."LineItem" _entity
	;
COMMENT ON VIEW "Inheritance"."LineItem_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."LineItem_entity") RETURNS TEXT AS $$
SELECT "-DSL-".Generate_Uri2(CAST($1."OrderID" as TEXT), CAST($1."Index" as TEXT))
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Inheritance"."Order_entity" AS
SELECT _entity."ID", _entity."placed", _entity."deadline", _entity."customerName", COALESCE((SELECT array_agg(sq ORDER BY sq."OrderID", sq."Index") FROM "Inheritance"."LineItem_entity" sq WHERE sq."OrderID" = _entity."ID"), '{}') AS "items"
FROM
	"Inheritance"."Order" _entity
	;
COMMENT ON VIEW "Inheritance"."Order_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Inheritance"."Order_entity") RETURNS TEXT AS $$
SELECT CAST($1."ID" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."cast_LineItem_to_type"("Inheritance"."-ngs_LineItem_type-") RETURNS "Inheritance"."LineItem_entity" AS $$ SELECT $1::text::"Inheritance"."LineItem_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Inheritance"."cast_LineItem_to_type"("Inheritance"."LineItem_entity") RETURNS "Inheritance"."-ngs_LineItem_type-" AS $$ SELECT $1::text::"Inheritance"."-ngs_LineItem_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Inheritance' AND s.typname = 'LineItem_entity' AND t.typname = '-ngs_LineItem_type-') THEN
		CREATE CAST ("Inheritance"."-ngs_LineItem_type-" AS "Inheritance"."LineItem_entity") WITH FUNCTION "Inheritance"."cast_LineItem_to_type"("Inheritance"."-ngs_LineItem_type-") AS IMPLICIT;
		CREATE CAST ("Inheritance"."LineItem_entity" AS "Inheritance"."-ngs_LineItem_type-") WITH FUNCTION "Inheritance"."cast_LineItem_to_type"("Inheritance"."LineItem_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "Inheritance"."cast_Order_to_type"("Inheritance"."-ngs_Order_type-") RETURNS "Inheritance"."Order_entity" AS $$ SELECT $1::text::"Inheritance"."Order_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Inheritance"."cast_Order_to_type"("Inheritance"."Order_entity") RETURNS "Inheritance"."-ngs_Order_type-" AS $$ SELECT $1::text::"Inheritance"."-ngs_Order_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Inheritance' AND s.typname = 'Order_entity' AND t.typname = '-ngs_Order_type-') THEN
		CREATE CAST ("Inheritance"."-ngs_Order_type-" AS "Inheritance"."Order_entity") WITH FUNCTION "Inheritance"."cast_Order_to_type"("Inheritance"."-ngs_Order_type-") AS IMPLICIT;
		CREATE CAST ("Inheritance"."Order_entity" AS "Inheritance"."-ngs_Order_type-") WITH FUNCTION "Inheritance"."cast_Order_to_type"("Inheritance"."Order_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Inheritance"."Order_unprocessed_events" AS
SELECT _aggregate."ID"
FROM
	"Inheritance"."Order_entity" _aggregate
;
COMMENT ON VIEW "Inheritance"."Order_unprocessed_events" IS 'NGS volatile';

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '>tmp-Order-insert<' AND column_name = 'tuple') THEN
		DROP TABLE IF EXISTS "Inheritance".">tmp-Order-insert<";
	END IF;
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '>tmp-Order-update<' AND column_name = 'old') THEN
		DROP TABLE IF EXISTS "Inheritance".">tmp-Order-update<";
	END IF;
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '>tmp-Order-delete<' AND column_name = 'tuple') THEN
		DROP TABLE IF EXISTS "Inheritance".">tmp-Order-delete<";
	END IF;
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = '>tmp-Order-insert<') THEN
		CREATE UNLOGGED TABLE "Inheritance".">tmp-Order-insert<" AS SELECT 0::int as i, _t as tuple FROM "Inheritance"."Order_entity" _t LIMIT 0;
	END IF;
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = '>tmp-Order-update<') THEN
		CREATE UNLOGGED TABLE "Inheritance".">tmp-Order-update<" AS SELECT 0::int as i, _t as old, _t as new FROM "Inheritance"."Order_entity" _t LIMIT 0;
	END IF;
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = '>tmp-Order-delete<') THEN
		CREATE UNLOGGED TABLE "Inheritance".">tmp-Order-delete<" AS SELECT 0::int as i, _t as tuple FROM "Inheritance"."Order_entity" _t LIMIT 0;
	END IF;

	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '>tmp-Order-insert758996489<' AND column_name = 'tuple') THEN
		DROP TABLE IF EXISTS "Inheritance".">tmp-Order-insert758996489<";
	END IF;
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '>tmp-Order-update758996489<' AND column_name = 'old') THEN
		DROP TABLE IF EXISTS "Inheritance".">tmp-Order-update758996489<";
	END IF;
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Inheritance' AND type_name = '>tmp-Order-delete758996489<' AND column_name = 'tuple') THEN
		DROP TABLE IF EXISTS "Inheritance".">tmp-Order-delete758996489<";
	END IF;
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = '>tmp-Order-insert758996489<') THEN
		CREATE UNLOGGED TABLE "Inheritance".">tmp-Order-insert758996489<" AS SELECT 0::int as i, 0::int as index, _t as tuple FROM "Inheritance"."LineItem_entity" _t LIMIT 0;
	END IF;
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = '>tmp-Order-update758996489<') THEN
		CREATE UNLOGGED TABLE "Inheritance".">tmp-Order-update758996489<" AS SELECT 0::int as i, 0::int as index, _t as old, _t as changed, _t as new, true as is_new FROM "Inheritance"."LineItem_entity" _t LIMIT 0;
	END IF;
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Inheritance' AND c.relname = '>tmp-Order-delete758996489<') THEN
		CREATE UNLOGGED TABLE "Inheritance".">tmp-Order-delete758996489<" AS SELECT 0::int as i, 0::int as index, _t as tuple FROM "Inheritance"."LineItem_entity" _t LIMIT 0;
	END IF;
END $$ LANGUAGE plpgsql;

--TODO: temp fix for rename
DROP FUNCTION IF EXISTS "Inheritance"."persist_Order_internal"(int, int);

CREATE OR REPLACE FUNCTION "Inheritance"."persist_Order_cleanup"() RETURNS VOID AS
$$
BEGIN
	
	DELETE FROM "Inheritance".">tmp-Order-insert758996489<";
	DELETE FROM "Inheritance".">tmp-Order-update758996489<";
	DELETE FROM "Inheritance".">tmp-Order-delete758996489<";
	DELETE FROM "Inheritance".">tmp-Order-insert<";
	DELETE FROM "Inheritance".">tmp-Order-update<";
	DELETE FROM "Inheritance".">tmp-Order-delete<";
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "Inheritance"."persist_Order_internal"(_update_count int, _delete_count int) 
	RETURNS VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE "_var_Inheritance.LineItem" "Inheritance"."LineItem_entity"[];
BEGIN

	SET CONSTRAINTS ALL DEFERRED;

	

	INSERT INTO "Inheritance"."Order" ("ID", "placed", "deadline", "customerName")
	SELECT (tuple)."ID", (tuple)."placed", (tuple)."deadline", (tuple)."customerName" 
	FROM "Inheritance".">tmp-Order-insert<" i;

	
	INSERT INTO "Inheritance"."LineItem" ("OrderID", "Index", "productName", "quantity")
	SELECT (tuple)."OrderID", (tuple)."Index", (tuple)."productName", (tuple)."quantity" 
	FROM "Inheritance".">tmp-Order-insert758996489<" t;

		
	UPDATE "Inheritance"."Order" as tbl SET 
		"ID" = (new)."ID", "placed" = (new)."placed", "deadline" = (new)."deadline", "customerName" = (new)."customerName"
	FROM "Inheritance".">tmp-Order-update<" u
	WHERE
		tbl."ID" = (old)."ID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		PERFORM "Inheritance"."persist_Order_cleanup"();
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	
	DELETE FROM "Inheritance"."LineItem" AS tbl
	WHERE 
		("OrderID", "Index") IN (SELECT (u.old)."OrderID", (u.old)."Index" FROM "Inheritance".">tmp-Order-update758996489<" u WHERE NOT u.old IS NULL AND u.changed IS NULL);

	UPDATE "Inheritance"."LineItem" AS tbl SET
		"OrderID" = (u.changed)."OrderID", "Index" = (u.changed)."Index", "productName" = (u.changed)."productName", "quantity" = (u.changed)."quantity"
	FROM "Inheritance".">tmp-Order-update758996489<" u
	WHERE
		NOT u.changed IS NULL
		AND NOT u.old IS NULL
		AND u.old != u.changed
		AND tbl."OrderID" = (u.old)."OrderID" AND tbl."Index" = (u.old)."Index" ;

	INSERT INTO "Inheritance"."LineItem" ("OrderID", "Index", "productName", "quantity")
	SELECT (new)."OrderID", (new)."Index", (new)."productName", (new)."quantity"
	FROM 
		"Inheritance".">tmp-Order-update758996489<" u
	WHERE u.is_new;
	DELETE FROM "Inheritance"."LineItem"	WHERE ("OrderID", "Index") IN (SELECT (tuple)."OrderID", (tuple)."Index" FROM "Inheritance".">tmp-Order-delete758996489<" d);

	DELETE FROM "Inheritance"."Order"
	WHERE ("ID") IN (SELECT (tuple)."ID" FROM "Inheritance".">tmp-Order-delete<" d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		PERFORM "Inheritance"."persist_Order_cleanup"();
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Order', 'Insert', (SELECT array_agg((tuple)."URI") FROM "Inheritance".">tmp-Order-insert<"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Order', 'Update', (SELECT array_agg((old)."URI") FROM "Inheritance".">tmp-Order-update<"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Order', 'Change', (SELECT array_agg((new)."URI") FROM "Inheritance".">tmp-Order-update<" WHERE (old)."ID" != (new)."ID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Inheritance.Order', 'Delete', (SELECT array_agg((tuple)."URI") FROM "Inheritance".">tmp-Order-delete<"));

	SET CONSTRAINTS ALL IMMEDIATE;

	
	DELETE FROM "Inheritance".">tmp-Order-insert758996489<";
	DELETE FROM "Inheritance".">tmp-Order-update758996489<";
	DELETE FROM "Inheritance".">tmp-Order-delete758996489<";
	DELETE FROM "Inheritance".">tmp-Order-insert<";
	DELETE FROM "Inheritance".">tmp-Order-update<";
	DELETE FROM "Inheritance".">tmp-Order-delete<";

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Inheritance"."persist_Order"(
IN _inserted "Inheritance"."Order_entity"[], IN _updated_original "Inheritance"."Order_entity"[], IN _updated_new "Inheritance"."Order_entity"[], IN _deleted "Inheritance"."Order_entity"[]) 
	RETURNS VARCHAR AS
$$
DECLARE cnt int;
DECLARE uri VARCHAR;
DECLARE tmp record;
DECLARE msg TEXT;
DECLARE "_var_Inheritance.LineItem" "Inheritance"."LineItem_entity"[];
BEGIN

	INSERT INTO "Inheritance".">tmp-Order-insert<"
	SELECT row_number() over (), _i
	FROM unnest(_inserted) _i;

	INSERT INTO "Inheritance".">tmp-Order-update<"
	SELECT row_number() over (), _sq.old, _sq.new
	FROM (SELECT unnest(_updated_original) AS old, unnest(_updated_new) AS new) _sq;

	INSERT INTO "Inheritance".">tmp-Order-delete<"
	SELECT row_number() over (), _d
	FROM unnest(_deleted) _d;

	
	FOR cnt, "_var_Inheritance.LineItem" IN SELECT t.i, (t.tuple)."items" AS children FROM "Inheritance".">tmp-Order-insert<" t LOOP
		INSERT INTO "Inheritance".">tmp-Order-insert758996489<"
		SELECT cnt, row_number() over (), _t
		FROM unnest("_var_Inheritance.LineItem") _t;
	END LOOP;

	WITH source AS (
		SELECT 
			i,
			row_number() over () as ord,
			(t.old)."items" AS old,
			(t.new)."items" AS new,
			coalesce(array_upper((t.old)."items", 1), 0) AS old_count,
			coalesce(array_upper((t.new)."items", 1), 0) AS new_count,
			(SELECT array_agg(row(_r."OrderID", _r."Index")) FROM unnest((t.old)."items") _r) as old_pks,
			(SELECT array_agg(row(_r."OrderID", _r."Index")) FROM unnest((t.new)."items") _r) as new_pks,
			(SELECT array_agg(i) FROM generate_series(1, CASE WHEN coalesce(array_upper((t.old)."items", 1), 0) > coalesce(array_upper((t.new)."items", 1), 0) THEN array_upper((t.old)."items", 1) ELSE array_upper((t.new)."items", 1) END) i) as indexes
		FROM "Inheritance".">tmp-Order-update<" t
		WHERE 
			NOT (t.old)."items" IS NULL AND (t.new)."items" IS NULL
			OR (t.old)."items" IS NULL AND NOT (t.new)."items" IS NULL
			OR NOT (t.old)."items" IS NULL AND NOT (t.new)."items" IS NULL AND (t.old)."items" != (t.new)."items"
	)
	INSERT INTO "Inheritance".">tmp-Order-update758996489<"
	SELECT i, index, old[index] AS old, 
		CASE 
			WHEN old_pks[index] = new_pks[index] THEN new[index] 
			WHEN NOT COALESCE(old_pks[index] = ANY (new_pks), false) THEN null
			ELSE (select n from unnest(new) n where n."OrderID" = old[index]."OrderID" AND n."Index" = old[index]."Index")
		END AS changed,
		new[index] AS new,
		CASE WHEN old_pks[index] = new_pks[index] THEN false ELSE NOT new_pks[index] IS NULL AND NOT COALESCE(new_pks[index] = ANY (old_pks), false) END AS is_new
	FROM
	(
		SELECT i as _i, ord as _ord, unnest((SELECT array_agg(i) FROM generate_series(1, CASE WHEN old_count > new_count THEN old_count ELSE new_count END) i)) as index
		FROM source s
	) ix
	INNER JOIN source sq ON ix._i = sq.i AND ix._ord = sq.ord;

	FOR cnt, "_var_Inheritance.LineItem" IN SELECT t.i, (t.tuple)."items" AS children FROM "Inheritance".">tmp-Order-delete<" t LOOP
		INSERT INTO "Inheritance".">tmp-Order-delete758996489<"
		SELECT cnt, row_number() over (), _t
		FROM unnest("_var_Inheritance.LineItem") _t;
	END LOOP;

	RETURN "Inheritance"."persist_Order_internal"(array_upper(_updated_original, 1), array_upper(_deleted, 1));
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_LineItem_to_type"("Inheritance"."-ngs_LineItem_type-")', 'Inheritance', '-ngs_LineItem_type-', 'LineItem_entity');
SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_LineItem_to_type"("Inheritance"."LineItem_entity")', 'Inheritance', 'LineItem_entity', '-ngs_LineItem_type-');

SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Order_to_type"("Inheritance"."-ngs_Order_type-")', 'Inheritance', '-ngs_Order_type-', 'Order_entity');
SELECT "-DSL-".Create_Type_Cast('"Inheritance"."cast_Order_to_type"("Inheritance"."Order_entity")', 'Inheritance', 'Order_entity', '-ngs_Order_type-');
UPDATE "Inheritance"."Order" SET "ID" = 0 WHERE "ID" IS NULL;
UPDATE "Inheritance"."Order" SET "placed" = CURRENT_DATE WHERE "placed" IS NULL;
UPDATE "Inheritance"."LineItem" SET "productName" = '' WHERE "productName" IS NULL;
UPDATE "Inheritance"."LineItem" SET "quantity" = 0 WHERE "quantity" IS NULL;
UPDATE "Inheritance"."LineItem" SET "OrderID" = 0 WHERE "OrderID" IS NULL;
UPDATE "Inheritance"."LineItem" SET "Index" = 0 WHERE "Index" IS NULL;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Inheritance' AND c.relname = 'Order') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Inheritance"."Order"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('ID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Inheritance.Order. Expected primary key: ID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Inheritance"."Order" ADD CONSTRAINT "pk_Order" PRIMARY KEY("ID");
		COMMENT ON CONSTRAINT "pk_Order" ON "Inheritance"."Order" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Inheritance' AND c.relname = 'LineItem') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Inheritance"."LineItem"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('OrderID, Index' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Inheritance.LineItem. Expected primary key: OrderID, Index. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Inheritance"."LineItem" ADD CONSTRAINT "pk_LineItem" PRIMARY KEY("OrderID","Index");
		COMMENT ON CONSTRAINT "pk_LineItem" ON "Inheritance"."LineItem" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Order" ALTER "ID" SET NOT NULL;

DO $$ 
BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON c.relnamespace = n.oid WHERE n.nspname = 'Inheritance' AND c.relname = 'Order_ID_seq' AND c.relkind = 'S') THEN
		CREATE SEQUENCE "Inheritance"."Order_ID_seq";
		ALTER TABLE "Inheritance"."Order"	ALTER COLUMN "ID" SET DEFAULT NEXTVAL('"Inheritance"."Order_ID_seq"');
		PERFORM SETVAL('"Inheritance"."Order_ID_seq"', COALESCE(MAX("ID"), 0) + 1000) FROM "Inheritance"."Order";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Inheritance"."Order" ALTER "placed" SET NOT NULL;
ALTER TABLE "Inheritance"."LineItem" ALTER "productName" SET NOT NULL;
ALTER TABLE "Inheritance"."LineItem" ALTER "quantity" SET NOT NULL;
ALTER TABLE "Inheritance"."LineItem" ALTER "OrderID" SET NOT NULL;
ALTER TABLE "Inheritance"."LineItem" ALTER "Index" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_items' AND n.nspname = 'Inheritance' AND r.relname = 'LineItem') THEN	
		ALTER TABLE "Inheritance"."LineItem" 
			ADD CONSTRAINT "fk_items"
				FOREIGN KEY ("OrderID") REFERENCES "Inheritance"."Order" ("ID")
				ON UPDATE CASCADE ON DELETE CASCADE;
		COMMENT ON CONSTRAINT "fk_items" ON "Inheritance"."LineItem" IS 'NGS generated';
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
    aggregate Order
	{
	  date placed;
	  date? deadline;
	  string? customerName;
	  List<LineItem> items;
	}

	entity LineItem
	{
	  string productName;
	  decimal quantity;
	}

}
"', '\x','1.7.6196.23272');
SELECT pg_notify('migration', 'new');