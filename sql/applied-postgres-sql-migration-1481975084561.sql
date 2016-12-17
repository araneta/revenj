/*MIGRATION_DESCRIPTION
--CREATE: Security-User
New object User will be created in schema Security
--CREATE: Security-User-Name
New property Name will be created for User in Security
--CREATE: Security-User-PasswordHash
New property PasswordHash will be created for User in Security
--CREATE: Security-User-IsAllowed
New property IsAllowed will be created for User in Security
--CREATE: Security-Role
New object Role will be created in schema Security
--CREATE: Security-Role-Name
New property Name will be created for Role in Security
--CREATE: Security-InheritedRole
New object InheritedRole will be created in schema Security
--CREATE: Security-InheritedRole-Name
New property Name will be created for InheritedRole in Security
--CREATE: Security-InheritedRole-ParentName
New property ParentName will be created for InheritedRole in Security
--CREATE: Security-GlobalPermission
New object GlobalPermission will be created in schema Security
--CREATE: Security-GlobalPermission-Name
New property Name will be created for GlobalPermission in Security
--CREATE: Security-GlobalPermission-IsAllowed
New property IsAllowed will be created for GlobalPermission in Security
--CREATE: Security-RolePermission
New object RolePermission will be created in schema Security
--CREATE: Security-RolePermission-Name
New property Name will be created for RolePermission in Security
--CREATE: Security-RolePermission-RoleID
New property RoleID will be created for RolePermission in Security
--CREATE: Security-RolePermission-IsAllowed
New property IsAllowed will be created for RolePermission in Security
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_namespace WHERE nspname = 'Security') THEN
		CREATE SCHEMA "Security";
		COMMENT ON SCHEMA "Security" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = '-ngs_User_type-') THEN	
		CREATE TYPE "Security"."-ngs_User_type-" AS ();
		COMMENT ON TYPE "Security"."-ngs_User_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'User') THEN	
		CREATE TABLE "Security"."User" ();
		COMMENT ON TABLE "Security"."User" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'User_sequence') THEN
		CREATE SEQUENCE "Security"."User_sequence";
		COMMENT ON SEQUENCE "Security"."User_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = '-ngs_Role_type-') THEN	
		CREATE TYPE "Security"."-ngs_Role_type-" AS ();
		COMMENT ON TYPE "Security"."-ngs_Role_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'Role') THEN	
		CREATE TABLE "Security"."Role" ();
		COMMENT ON TABLE "Security"."Role" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'Role_sequence') THEN
		CREATE SEQUENCE "Security"."Role_sequence";
		COMMENT ON SEQUENCE "Security"."Role_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = '-ngs_InheritedRole_type-') THEN	
		CREATE TYPE "Security"."-ngs_InheritedRole_type-" AS ();
		COMMENT ON TYPE "Security"."-ngs_InheritedRole_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'InheritedRole') THEN	
		CREATE TABLE "Security"."InheritedRole" ();
		COMMENT ON TABLE "Security"."InheritedRole" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'InheritedRole_sequence') THEN
		CREATE SEQUENCE "Security"."InheritedRole_sequence";
		COMMENT ON SEQUENCE "Security"."InheritedRole_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = '-ngs_GlobalPermission_type-') THEN	
		CREATE TYPE "Security"."-ngs_GlobalPermission_type-" AS ();
		COMMENT ON TYPE "Security"."-ngs_GlobalPermission_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'GlobalPermission') THEN	
		CREATE TABLE "Security"."GlobalPermission" ();
		COMMENT ON TABLE "Security"."GlobalPermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'GlobalPermission_sequence') THEN
		CREATE SEQUENCE "Security"."GlobalPermission_sequence";
		COMMENT ON SEQUENCE "Security"."GlobalPermission_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = '-ngs_RolePermission_type-') THEN	
		CREATE TYPE "Security"."-ngs_RolePermission_type-" AS ();
		COMMENT ON TYPE "Security"."-ngs_RolePermission_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'RolePermission') THEN	
		CREATE TABLE "Security"."RolePermission" ();
		COMMENT ON TABLE "Security"."RolePermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'Security' AND c.relname = 'RolePermission_sequence') THEN
		CREATE SEQUENCE "Security"."RolePermission_sequence";
		COMMENT ON SEQUENCE "Security"."RolePermission_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'User' AND column_name = 'Name') THEN
		ALTER TABLE "Security"."User" ADD COLUMN "Name" VARCHAR(100);
		COMMENT ON COLUMN "Security"."User"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'Name') THEN
		ALTER TYPE "Security"."-ngs_User_type-" ADD ATTRIBUTE "Name" VARCHAR(100);
		COMMENT ON COLUMN "Security"."-ngs_User_type-"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'RoleURI') THEN
		ALTER TYPE "Security"."-ngs_User_type-" ADD ATTRIBUTE "RoleURI" TEXT;
		COMMENT ON COLUMN "Security"."-ngs_User_type-"."RoleURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'User' AND column_name = 'PasswordHash') THEN
		ALTER TABLE "Security"."User" ADD COLUMN "PasswordHash" BYTEA;
		COMMENT ON COLUMN "Security"."User"."PasswordHash" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'PasswordHash') THEN
		ALTER TYPE "Security"."-ngs_User_type-" ADD ATTRIBUTE "PasswordHash" BYTEA;
		COMMENT ON COLUMN "Security"."-ngs_User_type-"."PasswordHash" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'User' AND column_name = 'IsAllowed') THEN
		ALTER TABLE "Security"."User" ADD COLUMN "IsAllowed" BOOL;
		COMMENT ON COLUMN "Security"."User"."IsAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'IsAllowed') THEN
		ALTER TYPE "Security"."-ngs_User_type-" ADD ATTRIBUTE "IsAllowed" BOOL;
		COMMENT ON COLUMN "Security"."-ngs_User_type-"."IsAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'Role' AND column_name = 'Name') THEN
		ALTER TABLE "Security"."Role" ADD COLUMN "Name" VARCHAR(100);
		COMMENT ON COLUMN "Security"."Role"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_Role_type-' AND column_name = 'Name') THEN
		ALTER TYPE "Security"."-ngs_Role_type-" ADD ATTRIBUTE "Name" VARCHAR(100);
		COMMENT ON COLUMN "Security"."-ngs_Role_type-"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'InheritedRole' AND column_name = 'Name') THEN
		ALTER TABLE "Security"."InheritedRole" ADD COLUMN "Name" VARCHAR(100);
		COMMENT ON COLUMN "Security"."InheritedRole"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_InheritedRole_type-' AND column_name = 'Name') THEN
		ALTER TYPE "Security"."-ngs_InheritedRole_type-" ADD ATTRIBUTE "Name" VARCHAR(100);
		COMMENT ON COLUMN "Security"."-ngs_InheritedRole_type-"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'InheritedRole' AND column_name = 'ParentName') THEN
		ALTER TABLE "Security"."InheritedRole" ADD COLUMN "ParentName" VARCHAR(100);
		COMMENT ON COLUMN "Security"."InheritedRole"."ParentName" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_InheritedRole_type-' AND column_name = 'ParentName') THEN
		ALTER TYPE "Security"."-ngs_InheritedRole_type-" ADD ATTRIBUTE "ParentName" VARCHAR(100);
		COMMENT ON COLUMN "Security"."-ngs_InheritedRole_type-"."ParentName" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_InheritedRole_type-' AND column_name = 'RoleURI') THEN
		ALTER TYPE "Security"."-ngs_InheritedRole_type-" ADD ATTRIBUTE "RoleURI" TEXT;
		COMMENT ON COLUMN "Security"."-ngs_InheritedRole_type-"."RoleURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_InheritedRole_type-' AND column_name = 'ParentRoleURI') THEN
		ALTER TYPE "Security"."-ngs_InheritedRole_type-" ADD ATTRIBUTE "ParentRoleURI" TEXT;
		COMMENT ON COLUMN "Security"."-ngs_InheritedRole_type-"."ParentRoleURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'GlobalPermission' AND column_name = 'Name') THEN
		ALTER TABLE "Security"."GlobalPermission" ADD COLUMN "Name" VARCHAR(200);
		COMMENT ON COLUMN "Security"."GlobalPermission"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_GlobalPermission_type-' AND column_name = 'Name') THEN
		ALTER TYPE "Security"."-ngs_GlobalPermission_type-" ADD ATTRIBUTE "Name" VARCHAR(200);
		COMMENT ON COLUMN "Security"."-ngs_GlobalPermission_type-"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'GlobalPermission' AND column_name = 'IsAllowed') THEN
		ALTER TABLE "Security"."GlobalPermission" ADD COLUMN "IsAllowed" BOOL;
		COMMENT ON COLUMN "Security"."GlobalPermission"."IsAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_GlobalPermission_type-' AND column_name = 'IsAllowed') THEN
		ALTER TYPE "Security"."-ngs_GlobalPermission_type-" ADD ATTRIBUTE "IsAllowed" BOOL;
		COMMENT ON COLUMN "Security"."-ngs_GlobalPermission_type-"."IsAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'RolePermission' AND column_name = 'Name') THEN
		ALTER TABLE "Security"."RolePermission" ADD COLUMN "Name" VARCHAR(200);
		COMMENT ON COLUMN "Security"."RolePermission"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'Name') THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "Name" VARCHAR(200);
		COMMENT ON COLUMN "Security"."-ngs_RolePermission_type-"."Name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'RoleURI') THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "RoleURI" TEXT;
		COMMENT ON COLUMN "Security"."-ngs_RolePermission_type-"."RoleURI" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'RolePermission' AND column_name = 'RoleID') THEN
		ALTER TABLE "Security"."RolePermission" ADD COLUMN "RoleID" VARCHAR(100);
		COMMENT ON COLUMN "Security"."RolePermission"."RoleID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'RoleID') THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "RoleID" VARCHAR(100);
		COMMENT ON COLUMN "Security"."-ngs_RolePermission_type-"."RoleID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'RolePermission' AND column_name = 'IsAllowed') THEN
		ALTER TABLE "Security"."RolePermission" ADD COLUMN "IsAllowed" BOOL;
		COMMENT ON COLUMN "Security"."RolePermission"."IsAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'IsAllowed') THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "IsAllowed" BOOL;
		COMMENT ON COLUMN "Security"."-ngs_RolePermission_type-"."IsAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Security"."User_entity" AS
SELECT _entity."Name", CAST(_entity."Name" as TEXT) AS "RoleURI", _entity."PasswordHash", _entity."IsAllowed"
FROM
	"Security"."User" _entity
	;
COMMENT ON VIEW "Security"."User_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Security"."User_entity") RETURNS TEXT AS $$
SELECT CAST($1."Name" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Security"."Role_entity" AS
SELECT _entity."Name"
FROM
	"Security"."Role" _entity
	;
COMMENT ON VIEW "Security"."Role_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Security"."Role_entity") RETURNS TEXT AS $$
SELECT CAST($1."Name" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Security"."InheritedRole_entity" AS
SELECT _entity."Name", _entity."ParentName", CAST(_entity."Name" as TEXT) AS "RoleURI", CAST(_entity."ParentName" as TEXT) AS "ParentRoleURI"
FROM
	"Security"."InheritedRole" _entity
	;
COMMENT ON VIEW "Security"."InheritedRole_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Security"."InheritedRole_entity") RETURNS TEXT AS $$
SELECT "-DSL-".Generate_Uri2(CAST($1."Name" as TEXT), CAST($1."ParentName" as TEXT))
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Security"."GlobalPermission_entity" AS
SELECT _entity."Name", _entity."IsAllowed"
FROM
	"Security"."GlobalPermission" _entity
	;
COMMENT ON VIEW "Security"."GlobalPermission_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Security"."GlobalPermission_entity") RETURNS TEXT AS $$
SELECT CAST($1."Name" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "Security"."RolePermission_entity" AS
SELECT _entity."Name", CAST(_entity."RoleID" as TEXT) AS "RoleURI", _entity."RoleID", _entity."IsAllowed"
FROM
	"Security"."RolePermission" _entity
	;
COMMENT ON VIEW "Security"."RolePermission_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("Security"."RolePermission_entity") RETURNS TEXT AS $$
SELECT "-DSL-".Generate_Uri2(CAST($1."Name" as TEXT), CAST($1."RoleID" as TEXT))
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Security"."cast_User_to_type"("Security"."-ngs_User_type-") RETURNS "Security"."User_entity" AS $$ SELECT $1::text::"Security"."User_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Security"."cast_User_to_type"("Security"."User_entity") RETURNS "Security"."-ngs_User_type-" AS $$ SELECT $1::text::"Security"."-ngs_User_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Security' AND s.typname = 'User_entity' AND t.typname = '-ngs_User_type-') THEN
		CREATE CAST ("Security"."-ngs_User_type-" AS "Security"."User_entity") WITH FUNCTION "Security"."cast_User_to_type"("Security"."-ngs_User_type-") AS IMPLICIT;
		CREATE CAST ("Security"."User_entity" AS "Security"."-ngs_User_type-") WITH FUNCTION "Security"."cast_User_to_type"("Security"."User_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Security"."User_unprocessed_events" AS
SELECT _aggregate."Name"
FROM
	"Security"."User_entity" _aggregate
;
COMMENT ON VIEW "Security"."User_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Security"."insert_User"(IN _inserted "Security"."User_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Security"."User" ("Name", "PasswordHash", "IsAllowed") VALUES(_inserted[1]."Name", _inserted[1]."PasswordHash", _inserted[1]."IsAllowed");
	
	PERFORM pg_notify('aggregate_roots', 'Security.User:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."persist_User"(
IN _inserted "Security"."User_entity"[], IN _updated_original "Security"."User_entity"[], IN _updated_new "Security"."User_entity"[], IN _deleted "Security"."User_entity"[]) 
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

	

	INSERT INTO "Security"."User" ("Name", "PasswordHash", "IsAllowed")
	SELECT _i."Name", _i."PasswordHash", _i."IsAllowed" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Security"."User" as _tbl SET "Name" = (_u.changed)."Name", "PasswordHash" = (_u.changed)."PasswordHash", "IsAllowed" = (_u.changed)."IsAllowed"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."Name" = (_u.original)."Name";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Security"."User"
	WHERE ("Name") IN (SELECT _d."Name" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.User', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.User', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.User', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."Name" != (_u.original)."Name"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.User', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Security"."update_User"(IN _original "Security"."User_entity"[], IN _updated "Security"."User_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Security"."User" AS _tab SET "Name" = _updated[1]."Name", "PasswordHash" = _updated[1]."PasswordHash", "IsAllowed" = _updated[1]."IsAllowed" WHERE _tab."Name" = _original[1]."Name";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Security.User:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."Name" != _updated[1]."Name") THEN
		PERFORM pg_notify('aggregate_roots', 'Security.User:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Role"("Security"."User_entity") RETURNS "Security"."Role_entity" AS $$ 
SELECT _r FROM "Security"."Role_entity" _r WHERE _r."Name" = $1."Name"
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION "Security"."cast_Role_to_type"("Security"."-ngs_Role_type-") RETURNS "Security"."Role_entity" AS $$ SELECT $1::text::"Security"."Role_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Security"."cast_Role_to_type"("Security"."Role_entity") RETURNS "Security"."-ngs_Role_type-" AS $$ SELECT $1::text::"Security"."-ngs_Role_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Security' AND s.typname = 'Role_entity' AND t.typname = '-ngs_Role_type-') THEN
		CREATE CAST ("Security"."-ngs_Role_type-" AS "Security"."Role_entity") WITH FUNCTION "Security"."cast_Role_to_type"("Security"."-ngs_Role_type-") AS IMPLICIT;
		CREATE CAST ("Security"."Role_entity" AS "Security"."-ngs_Role_type-") WITH FUNCTION "Security"."cast_Role_to_type"("Security"."Role_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Security"."Role_unprocessed_events" AS
SELECT _aggregate."Name"
FROM
	"Security"."Role_entity" _aggregate
;
COMMENT ON VIEW "Security"."Role_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Security"."insert_Role"(IN _inserted "Security"."Role_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Security"."Role" ("Name") VALUES(_inserted[1]."Name");
	
	PERFORM pg_notify('aggregate_roots', 'Security.Role:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."persist_Role"(
IN _inserted "Security"."Role_entity"[], IN _updated_original "Security"."Role_entity"[], IN _updated_new "Security"."Role_entity"[], IN _deleted "Security"."Role_entity"[]) 
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

	

	INSERT INTO "Security"."Role" ("Name")
	SELECT _i."Name" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Security"."Role" as _tbl SET "Name" = (_u.changed)."Name"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."Name" = (_u.original)."Name";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Security"."Role"
	WHERE ("Name") IN (SELECT _d."Name" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.Role', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.Role', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.Role', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."Name" != (_u.original)."Name"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.Role', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Security"."update_Role"(IN _original "Security"."Role_entity"[], IN _updated "Security"."Role_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Security"."Role" AS _tab SET "Name" = _updated[1]."Name" WHERE _tab."Name" = _original[1]."Name";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Security.Role:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."Name" != _updated[1]."Name") THEN
		PERFORM pg_notify('aggregate_roots', 'Security.Role:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."cast_InheritedRole_to_type"("Security"."-ngs_InheritedRole_type-") RETURNS "Security"."InheritedRole_entity" AS $$ SELECT $1::text::"Security"."InheritedRole_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Security"."cast_InheritedRole_to_type"("Security"."InheritedRole_entity") RETURNS "Security"."-ngs_InheritedRole_type-" AS $$ SELECT $1::text::"Security"."-ngs_InheritedRole_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Security' AND s.typname = 'InheritedRole_entity' AND t.typname = '-ngs_InheritedRole_type-') THEN
		CREATE CAST ("Security"."-ngs_InheritedRole_type-" AS "Security"."InheritedRole_entity") WITH FUNCTION "Security"."cast_InheritedRole_to_type"("Security"."-ngs_InheritedRole_type-") AS IMPLICIT;
		CREATE CAST ("Security"."InheritedRole_entity" AS "Security"."-ngs_InheritedRole_type-") WITH FUNCTION "Security"."cast_InheritedRole_to_type"("Security"."InheritedRole_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Security"."InheritedRole_unprocessed_events" AS
SELECT _aggregate."Name", _aggregate."ParentName"
FROM
	"Security"."InheritedRole_entity" _aggregate
;
COMMENT ON VIEW "Security"."InheritedRole_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Security"."insert_InheritedRole"(IN _inserted "Security"."InheritedRole_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Security"."InheritedRole" ("Name", "ParentName") VALUES(_inserted[1]."Name", _inserted[1]."ParentName");
	
	PERFORM pg_notify('aggregate_roots', 'Security.InheritedRole:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."persist_InheritedRole"(
IN _inserted "Security"."InheritedRole_entity"[], IN _updated_original "Security"."InheritedRole_entity"[], IN _updated_new "Security"."InheritedRole_entity"[], IN _deleted "Security"."InheritedRole_entity"[]) 
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

	

	INSERT INTO "Security"."InheritedRole" ("Name", "ParentName")
	SELECT _i."Name", _i."ParentName" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Security"."InheritedRole" as _tbl SET "Name" = (_u.changed)."Name", "ParentName" = (_u.changed)."ParentName"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."Name" = (_u.original)."Name" AND _tbl."ParentName" = (_u.original)."ParentName";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Security"."InheritedRole"
	WHERE ("Name", "ParentName") IN (SELECT _d."Name", _d."ParentName" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.InheritedRole', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.InheritedRole', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.InheritedRole', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."Name" != (_u.original)."Name" OR (_u.changed)."ParentName" != (_u.original)."ParentName"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.InheritedRole', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Security"."update_InheritedRole"(IN _original "Security"."InheritedRole_entity"[], IN _updated "Security"."InheritedRole_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Security"."InheritedRole" AS _tab SET "Name" = _updated[1]."Name", "ParentName" = _updated[1]."ParentName" WHERE _tab."Name" = _original[1]."Name" AND _tab."ParentName" = _original[1]."ParentName";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Security.InheritedRole:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."Name" != _updated[1]."Name" OR _original[1]."ParentName" != _updated[1]."ParentName") THEN
		PERFORM pg_notify('aggregate_roots', 'Security.InheritedRole:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Role"("Security"."InheritedRole_entity") RETURNS "Security"."Role_entity" AS $$ 
SELECT _r FROM "Security"."Role_entity" _r WHERE _r."Name" = $1."Name"
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION "ParentRole"("Security"."InheritedRole_entity") RETURNS "Security"."Role_entity" AS $$ 
SELECT _r FROM "Security"."Role_entity" _r WHERE _r."Name" = $1."ParentName"
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION "Security"."cast_GlobalPermission_to_type"("Security"."-ngs_GlobalPermission_type-") RETURNS "Security"."GlobalPermission_entity" AS $$ SELECT $1::text::"Security"."GlobalPermission_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Security"."cast_GlobalPermission_to_type"("Security"."GlobalPermission_entity") RETURNS "Security"."-ngs_GlobalPermission_type-" AS $$ SELECT $1::text::"Security"."-ngs_GlobalPermission_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Security' AND s.typname = 'GlobalPermission_entity' AND t.typname = '-ngs_GlobalPermission_type-') THEN
		CREATE CAST ("Security"."-ngs_GlobalPermission_type-" AS "Security"."GlobalPermission_entity") WITH FUNCTION "Security"."cast_GlobalPermission_to_type"("Security"."-ngs_GlobalPermission_type-") AS IMPLICIT;
		CREATE CAST ("Security"."GlobalPermission_entity" AS "Security"."-ngs_GlobalPermission_type-") WITH FUNCTION "Security"."cast_GlobalPermission_to_type"("Security"."GlobalPermission_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Security"."GlobalPermission_unprocessed_events" AS
SELECT _aggregate."Name"
FROM
	"Security"."GlobalPermission_entity" _aggregate
;
COMMENT ON VIEW "Security"."GlobalPermission_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Security"."insert_GlobalPermission"(IN _inserted "Security"."GlobalPermission_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Security"."GlobalPermission" ("Name", "IsAllowed") VALUES(_inserted[1]."Name", _inserted[1]."IsAllowed");
	
	PERFORM pg_notify('aggregate_roots', 'Security.GlobalPermission:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."persist_GlobalPermission"(
IN _inserted "Security"."GlobalPermission_entity"[], IN _updated_original "Security"."GlobalPermission_entity"[], IN _updated_new "Security"."GlobalPermission_entity"[], IN _deleted "Security"."GlobalPermission_entity"[]) 
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

	

	INSERT INTO "Security"."GlobalPermission" ("Name", "IsAllowed")
	SELECT _i."Name", _i."IsAllowed" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Security"."GlobalPermission" as _tbl SET "Name" = (_u.changed)."Name", "IsAllowed" = (_u.changed)."IsAllowed"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."Name" = (_u.original)."Name";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Security"."GlobalPermission"
	WHERE ("Name") IN (SELECT _d."Name" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.GlobalPermission', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.GlobalPermission', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.GlobalPermission', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."Name" != (_u.original)."Name"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.GlobalPermission', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Security"."update_GlobalPermission"(IN _original "Security"."GlobalPermission_entity"[], IN _updated "Security"."GlobalPermission_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Security"."GlobalPermission" AS _tab SET "Name" = _updated[1]."Name", "IsAllowed" = _updated[1]."IsAllowed" WHERE _tab."Name" = _original[1]."Name";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Security.GlobalPermission:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."Name" != _updated[1]."Name") THEN
		PERFORM pg_notify('aggregate_roots', 'Security.GlobalPermission:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."cast_RolePermission_to_type"("Security"."-ngs_RolePermission_type-") RETURNS "Security"."RolePermission_entity" AS $$ SELECT $1::text::"Security"."RolePermission_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "Security"."cast_RolePermission_to_type"("Security"."RolePermission_entity") RETURNS "Security"."-ngs_RolePermission_type-" AS $$ SELECT $1::text::"Security"."-ngs_RolePermission_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'Security' AND s.typname = 'RolePermission_entity' AND t.typname = '-ngs_RolePermission_type-') THEN
		CREATE CAST ("Security"."-ngs_RolePermission_type-" AS "Security"."RolePermission_entity") WITH FUNCTION "Security"."cast_RolePermission_to_type"("Security"."-ngs_RolePermission_type-") AS IMPLICIT;
		CREATE CAST ("Security"."RolePermission_entity" AS "Security"."-ngs_RolePermission_type-") WITH FUNCTION "Security"."cast_RolePermission_to_type"("Security"."RolePermission_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "Security"."RolePermission_unprocessed_events" AS
SELECT _aggregate."Name", _aggregate."RoleID"
FROM
	"Security"."RolePermission_entity" _aggregate
;
COMMENT ON VIEW "Security"."RolePermission_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "Security"."insert_RolePermission"(IN _inserted "Security"."RolePermission_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "Security"."RolePermission" ("Name", "RoleID", "IsAllowed") VALUES(_inserted[1]."Name", _inserted[1]."RoleID", _inserted[1]."IsAllowed");
	
	PERFORM pg_notify('aggregate_roots', 'Security.RolePermission:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Security"."persist_RolePermission"(
IN _inserted "Security"."RolePermission_entity"[], IN _updated_original "Security"."RolePermission_entity"[], IN _updated_new "Security"."RolePermission_entity"[], IN _deleted "Security"."RolePermission_entity"[]) 
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

	

	INSERT INTO "Security"."RolePermission" ("Name", "RoleID", "IsAllowed")
	SELECT _i."Name", _i."RoleID", _i."IsAllowed" 
	FROM unnest(_inserted) _i;

	

	UPDATE "Security"."RolePermission" as _tbl SET "Name" = (_u.changed)."Name", "RoleID" = (_u.changed)."RoleID", "IsAllowed" = (_u.changed)."IsAllowed"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."Name" = (_u.original)."Name" AND _tbl."RoleID" = (_u.original)."RoleID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "Security"."RolePermission"
	WHERE ("Name", "RoleID") IN (SELECT _d."Name", _d."RoleID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.RolePermission', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.RolePermission', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.RolePermission', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."Name" != (_u.original)."Name" OR (_u.changed)."RoleID" != (_u.original)."RoleID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'Security.RolePermission', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "Security"."update_RolePermission"(IN _original "Security"."RolePermission_entity"[], IN _updated "Security"."RolePermission_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "Security"."RolePermission" AS _tab SET "Name" = _updated[1]."Name", "RoleID" = _updated[1]."RoleID", "IsAllowed" = _updated[1]."IsAllowed" WHERE _tab."Name" = _original[1]."Name" AND _tab."RoleID" = _original[1]."RoleID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'Security.RolePermission:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."Name" != _updated[1]."Name" OR _original[1]."RoleID" != _updated[1]."RoleID") THEN
		PERFORM pg_notify('aggregate_roots', 'Security.RolePermission:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "Role"("Security"."RolePermission_entity") RETURNS "Security"."Role_entity" AS $$ 
SELECT _r FROM "Security"."Role_entity" _r WHERE _r."Name" = $1."RoleID"
$$ LANGUAGE SQL;

SELECT "-DSL-".Create_Type_Cast('"Security"."cast_User_to_type"("Security"."-ngs_User_type-")', 'Security', '-ngs_User_type-', 'User_entity');
SELECT "-DSL-".Create_Type_Cast('"Security"."cast_User_to_type"("Security"."User_entity")', 'Security', 'User_entity', '-ngs_User_type-');

SELECT "-DSL-".Create_Type_Cast('"Security"."cast_Role_to_type"("Security"."-ngs_Role_type-")', 'Security', '-ngs_Role_type-', 'Role_entity');
SELECT "-DSL-".Create_Type_Cast('"Security"."cast_Role_to_type"("Security"."Role_entity")', 'Security', 'Role_entity', '-ngs_Role_type-');

SELECT "-DSL-".Create_Type_Cast('"Security"."cast_InheritedRole_to_type"("Security"."-ngs_InheritedRole_type-")', 'Security', '-ngs_InheritedRole_type-', 'InheritedRole_entity');
SELECT "-DSL-".Create_Type_Cast('"Security"."cast_InheritedRole_to_type"("Security"."InheritedRole_entity")', 'Security', 'InheritedRole_entity', '-ngs_InheritedRole_type-');

SELECT "-DSL-".Create_Type_Cast('"Security"."cast_GlobalPermission_to_type"("Security"."-ngs_GlobalPermission_type-")', 'Security', '-ngs_GlobalPermission_type-', 'GlobalPermission_entity');
SELECT "-DSL-".Create_Type_Cast('"Security"."cast_GlobalPermission_to_type"("Security"."GlobalPermission_entity")', 'Security', 'GlobalPermission_entity', '-ngs_GlobalPermission_type-');

SELECT "-DSL-".Create_Type_Cast('"Security"."cast_RolePermission_to_type"("Security"."-ngs_RolePermission_type-")', 'Security', '-ngs_RolePermission_type-', 'RolePermission_entity');
SELECT "-DSL-".Create_Type_Cast('"Security"."cast_RolePermission_to_type"("Security"."RolePermission_entity")', 'Security', 'RolePermission_entity', '-ngs_RolePermission_type-');
UPDATE "Security"."User" SET "Name" = '' WHERE "Name" IS NULL;
UPDATE "Security"."User" SET "PasswordHash" = '' WHERE "PasswordHash" IS NULL;
UPDATE "Security"."User" SET "IsAllowed" = false WHERE "IsAllowed" IS NULL;
UPDATE "Security"."Role" SET "Name" = '' WHERE "Name" IS NULL;
UPDATE "Security"."InheritedRole" SET "Name" = '' WHERE "Name" IS NULL;
UPDATE "Security"."InheritedRole" SET "ParentName" = '' WHERE "ParentName" IS NULL;

DO $$ BEGIN
	IF (1, 1) = (SELECT COUNT(*), SUM(CASE WHEN column_name = 'Name' THEN 1 ELSE 0 END) FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'Role') THEN	
		INSERT INTO "Security"."Role"("Name") 
		SELECT 'Administrator'
		WHERE NOT EXISTS(SELECT * FROM "Security"."Role" WHERE "Name" = 'Administrator');
	END IF;
END $$ LANGUAGE plpgsql;
UPDATE "Security"."GlobalPermission" SET "Name" = '' WHERE "Name" IS NULL;
UPDATE "Security"."GlobalPermission" SET "IsAllowed" = false WHERE "IsAllowed" IS NULL;
UPDATE "Security"."RolePermission" SET "Name" = '' WHERE "Name" IS NULL;
UPDATE "Security"."RolePermission" SET "RoleID" = '' WHERE "RoleID" IS NULL;
UPDATE "Security"."RolePermission" SET "IsAllowed" = false WHERE "IsAllowed" IS NULL;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Security' AND c.relname = 'User') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Security"."User"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('Name' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Security.User. Expected primary key: Name. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Security"."User" ADD CONSTRAINT "pk_User" PRIMARY KEY("Name");
		COMMENT ON CONSTRAINT "pk_User" ON "Security"."User" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Security' AND c.relname = 'Role') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Security"."Role"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('Name' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Security.Role. Expected primary key: Name. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Security"."Role" ADD CONSTRAINT "pk_Role" PRIMARY KEY("Name");
		COMMENT ON CONSTRAINT "pk_Role" ON "Security"."Role" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Security' AND c.relname = 'InheritedRole') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Security"."InheritedRole"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('Name, ParentName' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Security.InheritedRole. Expected primary key: Name, ParentName. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Security"."InheritedRole" ADD CONSTRAINT "pk_InheritedRole" PRIMARY KEY("Name","ParentName");
		COMMENT ON CONSTRAINT "pk_InheritedRole" ON "Security"."InheritedRole" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Security' AND c.relname = 'GlobalPermission') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Security"."GlobalPermission"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('Name' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Security.GlobalPermission. Expected primary key: Name. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Security"."GlobalPermission" ADD CONSTRAINT "pk_GlobalPermission" PRIMARY KEY("Name");
		COMMENT ON CONSTRAINT "pk_GlobalPermission" ON "Security"."GlobalPermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'Security' AND c.relname = 'RolePermission') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"Security"."RolePermission"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('Name, RoleID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table Security.RolePermission. Expected primary key: Name, RoleID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "Security"."RolePermission" ADD CONSTRAINT "pk_RolePermission" PRIMARY KEY("Name","RoleID");
		COMMENT ON CONSTRAINT "pk_RolePermission" ON "Security"."RolePermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Security"."User" ALTER "Name" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_Role' AND n.nspname = 'Security' AND r.relname = 'User') THEN	
		ALTER TABLE "Security"."User" 
			ADD CONSTRAINT "fk_Role"
				FOREIGN KEY ("Name") REFERENCES "Security"."Role" ("Name")
				ON UPDATE CASCADE ;
		COMMENT ON CONSTRAINT "fk_Role" ON "Security"."User" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Security"."User" ALTER "PasswordHash" SET NOT NULL;
ALTER TABLE "Security"."User" ALTER "IsAllowed" SET NOT NULL;
ALTER TABLE "Security"."Role" ALTER "Name" SET NOT NULL;
ALTER TABLE "Security"."InheritedRole" ALTER "Name" SET NOT NULL;
ALTER TABLE "Security"."InheritedRole" ALTER "ParentName" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_Role' AND n.nspname = 'Security' AND r.relname = 'InheritedRole') THEN	
		ALTER TABLE "Security"."InheritedRole" 
			ADD CONSTRAINT "fk_Role"
				FOREIGN KEY ("Name") REFERENCES "Security"."Role" ("Name")
				ON UPDATE CASCADE ;
		COMMENT ON CONSTRAINT "fk_Role" ON "Security"."InheritedRole" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_ParentRole' AND n.nspname = 'Security' AND r.relname = 'InheritedRole') THEN	
		ALTER TABLE "Security"."InheritedRole" 
			ADD CONSTRAINT "fk_ParentRole"
				FOREIGN KEY ("ParentName") REFERENCES "Security"."Role" ("Name")
				ON UPDATE CASCADE ;
		COMMENT ON CONSTRAINT "fk_ParentRole" ON "Security"."InheritedRole" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Security"."GlobalPermission" ALTER "Name" SET NOT NULL;
ALTER TABLE "Security"."GlobalPermission" ALTER "IsAllowed" SET NOT NULL;
ALTER TABLE "Security"."RolePermission" ALTER "Name" SET NOT NULL;
ALTER TABLE "Security"."RolePermission" ALTER "RoleID" SET NOT NULL;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace WHERE c.conname = 'fk_Role' AND n.nspname = 'Security' AND r.relname = 'RolePermission') THEN	
		ALTER TABLE "Security"."RolePermission" 
			ADD CONSTRAINT "fk_Role"
				FOREIGN KEY ("RoleID") REFERENCES "Security"."Role" ("Name")
				ON UPDATE CASCADE ;
		COMMENT ON CONSTRAINT "fk_Role" ON "Security"."RolePermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "Security"."RolePermission" ALTER "IsAllowed" SET NOT NULL;

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
"', '\x','1.7.6193.30391');
SELECT pg_notify('migration', 'new');