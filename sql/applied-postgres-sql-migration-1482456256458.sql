/*MIGRATION_DESCRIPTION
--REMOVE: Security-RolePermission-IsAllowed
Property IsAllowed will be removed from object RolePermission in schema Security
--REMOVE: Security-RolePermission-RoleID
Property RoleID will be removed from object RolePermission in schema Security
--REMOVE: Security-RolePermission-Name
Property Name will be removed from object RolePermission in schema Security
--REMOVE: Security-RolePermission
Object RolePermission will be removed from schema Security
--REMOVE: Security-GlobalPermission-IsAllowed
Property IsAllowed will be removed from object GlobalPermission in schema Security
--REMOVE: Security-GlobalPermission-Name
Property Name will be removed from object GlobalPermission in schema Security
--REMOVE: Security-GlobalPermission
Object GlobalPermission will be removed from schema Security
--REMOVE: Security-InheritedRole-ParentName
Property ParentName will be removed from object InheritedRole in schema Security
--REMOVE: Security-InheritedRole-Name
Property Name will be removed from object InheritedRole in schema Security
--REMOVE: Security-InheritedRole
Object InheritedRole will be removed from schema Security
--REMOVE: Security-Role-Name
Property Name will be removed from object Role in schema Security
--REMOVE: Security-Role
Object Role will be removed from schema Security
--REMOVE: Security-User-IsAllowed
Property IsAllowed will be removed from object User in schema Security
--REMOVE: Security-User-PasswordHash
Property PasswordHash will be removed from object User in schema Security
--REMOVE: Security-User-Name
Property Name will be removed from object User in schema Security
--REMOVE: Security-User
Object User will be removed from schema Security
--REMOVE: hello-Recipe-ingredients
Property ingredients will be removed from object Recipe in schema hello
--REMOVE: hello-Recipe-name
Property name will be removed from object Recipe in schema hello
--REMOVE: hello-Recipe-ID
Property ID will be removed from object Recipe in schema hello
--REMOVE: hello-Recipe
Object Recipe will be removed from schema hello
--CREATE: security-User
New object User will be created in schema security
--CREATE: security-User-name
New property name will be created for User in security
--CREATE: security-User-roles
New property roles will be created for User in security
--CREATE: security-User-password
New property password will be created for User in security
--CREATE: security-User-isAllowed
New property isAllowed will be created for User in security
--CREATE: security-GlobalPermission
New object GlobalPermission will be created in schema security
--CREATE: security-GlobalPermission-name
New property name will be created for GlobalPermission in security
--CREATE: security-GlobalPermission-isAllowed
New property isAllowed will be created for GlobalPermission in security
--CREATE: security-RolePermission
New object RolePermission will be created in schema security
--CREATE: security-RolePermission-name
New property name will be created for RolePermission in security
--CREATE: security-RolePermission-roleID
New property roleID will be created for RolePermission in security
--CREATE: security-RolePermission-isAllowed
New property isAllowed will be created for RolePermission in security
MIGRATION_DESCRIPTION*/

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = '-DSL-' AND c.relname = 'database_setting') THEN	
		IF EXISTS(SELECT * FROM "-DSL-".Database_Setting WHERE Key ILIKE 'mode' AND NOT Value ILIKE 'unsafe') THEN
			RAISE EXCEPTION 'Database upgrade is forbidden. Change database mode to allow upgrade';
		END IF;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'fk_Role' AND n.nspname = 'Security' AND r.relname = 'RolePermission' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."RolePermission" DROP CONSTRAINT "fk_Role";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'fk_ParentRole' AND n.nspname = 'Security' AND r.relname = 'InheritedRole' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."InheritedRole" DROP CONSTRAINT "fk_ParentRole";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'fk_Role' AND n.nspname = 'Security' AND r.relname = 'InheritedRole' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."InheritedRole" DROP CONSTRAINT "fk_Role";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'fk_Role' AND n.nspname = 'Security' AND r.relname = 'User' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."User" DROP CONSTRAINT "fk_Role";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON r.oid = d.objoid AND d.objsubid = 0 WHERE c.conname = 'uq_Recipe_name' AND n.nspname = 'hello' AND r.relname = 'Recipe' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "hello"."Recipe" DROP CONSTRAINT "uq_Recipe_name";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "hello"."Recipe" ALTER COLUMN "ID" SET DEFAULT NULL;
DROP SEQUENCE IF EXISTS "hello"."Recipe_ID_seq";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_RolePermission' AND n.nspname = 'Security' AND r.relname = 'RolePermission' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."RolePermission" DROP CONSTRAINT "pk_RolePermission";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_GlobalPermission' AND n.nspname = 'Security' AND r.relname = 'GlobalPermission' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."GlobalPermission" DROP CONSTRAINT "pk_GlobalPermission";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_InheritedRole' AND n.nspname = 'Security' AND r.relname = 'InheritedRole' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."InheritedRole" DROP CONSTRAINT "pk_InheritedRole";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_Role' AND n.nspname = 'Security' AND r.relname = 'Role' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."Role" DROP CONSTRAINT "pk_Role";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_User' AND n.nspname = 'Security' AND r.relname = 'User' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "Security"."User" DROP CONSTRAINT "pk_User";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_constraint c JOIN pg_class r ON c.conrelid = r.oid JOIN pg_namespace n ON n.oid = r.relnamespace JOIN pg_description d ON c.oid = d.objoid WHERE c.conname = 'pk_Recipe' AND n.nspname = 'hello' AND r.relname = 'Recipe' AND d.description LIKE 'NGS generated%') THEN
		ALTER TABLE "hello"."Recipe" DROP CONSTRAINT "pk_Recipe";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF (1, 1) = (SELECT COUNT(*), SUM(CASE WHEN column_name = 'Name' THEN 1 ELSE 0 END) FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'Role') THEN	
		DELETE FROM "Security"."Role" WHERE "Name" = 'Administrator';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'RolePermission_entity') THEN
		CREATE OR REPLACE FUNCTION "Security"."cast_RolePermission_to_type"("Security"."-ngs_RolePermission_type-") RETURNS "Security"."RolePermission_entity" AS $x$ SELECT $1::text::"Security"."RolePermission_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "Security"."cast_RolePermission_to_type"("Security"."RolePermission_entity") RETURNS "Security"."-ngs_RolePermission_type-" AS $x$ SELECT $1::text::"Security"."-ngs_RolePermission_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'GlobalPermission_entity') THEN
		CREATE OR REPLACE FUNCTION "Security"."cast_GlobalPermission_to_type"("Security"."-ngs_GlobalPermission_type-") RETURNS "Security"."GlobalPermission_entity" AS $x$ SELECT $1::text::"Security"."GlobalPermission_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "Security"."cast_GlobalPermission_to_type"("Security"."GlobalPermission_entity") RETURNS "Security"."-ngs_GlobalPermission_type-" AS $x$ SELECT $1::text::"Security"."-ngs_GlobalPermission_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'InheritedRole_entity') THEN
		CREATE OR REPLACE FUNCTION "Security"."cast_InheritedRole_to_type"("Security"."-ngs_InheritedRole_type-") RETURNS "Security"."InheritedRole_entity" AS $x$ SELECT $1::text::"Security"."InheritedRole_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "Security"."cast_InheritedRole_to_type"("Security"."InheritedRole_entity") RETURNS "Security"."-ngs_InheritedRole_type-" AS $x$ SELECT $1::text::"Security"."-ngs_InheritedRole_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'Role_entity') THEN
		CREATE OR REPLACE FUNCTION "Security"."cast_Role_to_type"("Security"."-ngs_Role_type-") RETURNS "Security"."Role_entity" AS $x$ SELECT $1::text::"Security"."Role_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "Security"."cast_Role_to_type"("Security"."Role_entity") RETURNS "Security"."-ngs_Role_type-" AS $x$ SELECT $1::text::"Security"."-ngs_Role_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'User_entity') THEN
		CREATE OR REPLACE FUNCTION "Security"."cast_User_to_type"("Security"."-ngs_User_type-") RETURNS "Security"."User_entity" AS $x$ SELECT $1::text::"Security"."User_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "Security"."cast_User_to_type"("Security"."User_entity") RETURNS "Security"."-ngs_User_type-" AS $x$ SELECT $1::text::"Security"."-ngs_User_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'hello' AND t.typname = 'Recipe_entity') THEN
		CREATE OR REPLACE FUNCTION "hello"."cast_Recipe_to_type"("hello"."-ngs_Recipe_type-") RETURNS "hello"."Recipe_entity" AS $x$ SELECT $1::text::"hello"."Recipe_entity" $x$ IMMUTABLE LANGUAGE sql;
		CREATE OR REPLACE FUNCTION "hello"."cast_Recipe_to_type"("hello"."Recipe_entity") RETURNS "hello"."-ngs_Recipe_type-" AS $x$ SELECT $1::text::"hello"."-ngs_Recipe_type-" $x$ IMMUTABLE LANGUAGE sql;
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "Role"("Security"."RolePermission_entity");
DROP FUNCTION IF EXISTS "Security"."update_RolePermission"("Security"."RolePermission_entity"[],"Security"."RolePermission_entity"[]);;

DROP FUNCTION IF EXISTS "Security"."persist_RolePermission"("Security"."RolePermission_entity"[], "Security"."RolePermission_entity"[], "Security"."RolePermission_entity"[], "Security"."RolePermission_entity"[]);
DROP FUNCTION IF EXISTS "Security"."insert_RolePermission"("Security"."RolePermission_entity"[]);;
DROP VIEW IF EXISTS "Security"."RolePermission_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'RolePermission_entity') THEN
		DROP CAST IF EXISTS ("Security"."-ngs_RolePermission_type-" AS "Security"."RolePermission_entity");
		DROP CAST IF EXISTS ("Security"."RolePermission_entity" AS "Security"."-ngs_RolePermission_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_RolePermission_to_type"("Security"."-ngs_RolePermission_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_RolePermission_to_type"("Security"."RolePermission_entity");
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "Security"."update_GlobalPermission"("Security"."GlobalPermission_entity"[],"Security"."GlobalPermission_entity"[]);;

DROP FUNCTION IF EXISTS "Security"."persist_GlobalPermission"("Security"."GlobalPermission_entity"[], "Security"."GlobalPermission_entity"[], "Security"."GlobalPermission_entity"[], "Security"."GlobalPermission_entity"[]);
DROP FUNCTION IF EXISTS "Security"."insert_GlobalPermission"("Security"."GlobalPermission_entity"[]);;
DROP VIEW IF EXISTS "Security"."GlobalPermission_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'GlobalPermission_entity') THEN
		DROP CAST IF EXISTS ("Security"."-ngs_GlobalPermission_type-" AS "Security"."GlobalPermission_entity");
		DROP CAST IF EXISTS ("Security"."GlobalPermission_entity" AS "Security"."-ngs_GlobalPermission_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_GlobalPermission_to_type"("Security"."-ngs_GlobalPermission_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_GlobalPermission_to_type"("Security"."GlobalPermission_entity");
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "ParentRole"("Security"."InheritedRole_entity");
DROP FUNCTION IF EXISTS "Role"("Security"."InheritedRole_entity");
DROP FUNCTION IF EXISTS "Security"."update_InheritedRole"("Security"."InheritedRole_entity"[],"Security"."InheritedRole_entity"[]);;

DROP FUNCTION IF EXISTS "Security"."persist_InheritedRole"("Security"."InheritedRole_entity"[], "Security"."InheritedRole_entity"[], "Security"."InheritedRole_entity"[], "Security"."InheritedRole_entity"[]);
DROP FUNCTION IF EXISTS "Security"."insert_InheritedRole"("Security"."InheritedRole_entity"[]);;
DROP VIEW IF EXISTS "Security"."InheritedRole_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'InheritedRole_entity') THEN
		DROP CAST IF EXISTS ("Security"."-ngs_InheritedRole_type-" AS "Security"."InheritedRole_entity");
		DROP CAST IF EXISTS ("Security"."InheritedRole_entity" AS "Security"."-ngs_InheritedRole_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_InheritedRole_to_type"("Security"."-ngs_InheritedRole_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_InheritedRole_to_type"("Security"."InheritedRole_entity");
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "Security"."update_Role"("Security"."Role_entity"[],"Security"."Role_entity"[]);;

DROP FUNCTION IF EXISTS "Security"."persist_Role"("Security"."Role_entity"[], "Security"."Role_entity"[], "Security"."Role_entity"[], "Security"."Role_entity"[]);
DROP FUNCTION IF EXISTS "Security"."insert_Role"("Security"."Role_entity"[]);;
DROP VIEW IF EXISTS "Security"."Role_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'Role_entity') THEN
		DROP CAST IF EXISTS ("Security"."-ngs_Role_type-" AS "Security"."Role_entity");
		DROP CAST IF EXISTS ("Security"."Role_entity" AS "Security"."-ngs_Role_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_Role_to_type"("Security"."-ngs_Role_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_Role_to_type"("Security"."Role_entity");
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "Role"("Security"."User_entity");
DROP FUNCTION IF EXISTS "Security"."update_User"("Security"."User_entity"[],"Security"."User_entity"[]);;

DROP FUNCTION IF EXISTS "Security"."persist_User"("Security"."User_entity"[], "Security"."User_entity"[], "Security"."User_entity"[], "Security"."User_entity"[]);
DROP FUNCTION IF EXISTS "Security"."insert_User"("Security"."User_entity"[]);;
DROP VIEW IF EXISTS "Security"."User_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'User_entity') THEN
		DROP CAST IF EXISTS ("Security"."-ngs_User_type-" AS "Security"."User_entity");
		DROP CAST IF EXISTS ("Security"."User_entity" AS "Security"."-ngs_User_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_User_to_type"("Security"."-ngs_User_type-");
		DROP FUNCTION IF EXISTS "Security"."cast_User_to_type"("Security"."User_entity");
	END IF;
END $$ LANGUAGE plpgsql;
DROP FUNCTION IF EXISTS "hello"."update_Recipe"("hello"."Recipe_entity"[],"hello"."Recipe_entity"[]);;

DROP FUNCTION IF EXISTS "hello"."persist_Recipe"("hello"."Recipe_entity"[], "hello"."Recipe_entity"[], "hello"."Recipe_entity"[], "hello"."Recipe_entity"[]);
DROP FUNCTION IF EXISTS "hello"."insert_Recipe"("hello"."Recipe_entity"[]);;
DROP VIEW IF EXISTS "hello"."Recipe_unprocessed_events";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'hello' AND t.typname = 'Recipe_entity') THEN
		DROP CAST IF EXISTS ("hello"."-ngs_Recipe_type-" AS "hello"."Recipe_entity");
		DROP CAST IF EXISTS ("hello"."Recipe_entity" AS "hello"."-ngs_Recipe_type-");
		DROP FUNCTION IF EXISTS "hello"."cast_Recipe_to_type"("hello"."-ngs_Recipe_type-");
		DROP FUNCTION IF EXISTS "hello"."cast_Recipe_to_type"("hello"."Recipe_entity");
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'RolePermission_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("Security"."RolePermission_entity");
		DROP VIEW IF EXISTS "Security"."RolePermission_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'GlobalPermission_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("Security"."GlobalPermission_entity");
		DROP VIEW IF EXISTS "Security"."GlobalPermission_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'InheritedRole_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("Security"."InheritedRole_entity");
		DROP VIEW IF EXISTS "Security"."InheritedRole_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'Role_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("Security"."Role_entity");
		DROP VIEW IF EXISTS "Security"."Role_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'Security' AND t.typname = 'User_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("Security"."User_entity");
		DROP VIEW IF EXISTS "Security"."User_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'hello' AND t.typname = 'Recipe_entity') THEN
		DROP FUNCTION IF EXISTS "URI"("hello"."Recipe_entity");
		DROP VIEW IF EXISTS "hello"."Recipe_entity";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'IsAllowed' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" DROP ATTRIBUTE "IsAllowed";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'RolePermission' AND column_name = 'IsAllowed' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."RolePermission" DROP COLUMN "IsAllowed";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'RoleID' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" DROP ATTRIBUTE "RoleID";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'RolePermission' AND column_name = 'RoleID' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."RolePermission" DROP COLUMN "RoleID";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TYPE "Security"."-ngs_RolePermission_type-" DROP ATTRIBUTE IF EXISTS "RoleURI";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_RolePermission_type-" DROP ATTRIBUTE "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'RolePermission' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."RolePermission" DROP COLUMN "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_GlobalPermission_type-' AND column_name = 'IsAllowed' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_GlobalPermission_type-" DROP ATTRIBUTE "IsAllowed";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'GlobalPermission' AND column_name = 'IsAllowed' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."GlobalPermission" DROP COLUMN "IsAllowed";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_GlobalPermission_type-' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_GlobalPermission_type-" DROP ATTRIBUTE "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'GlobalPermission' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."GlobalPermission" DROP COLUMN "Name";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TYPE "Security"."-ngs_InheritedRole_type-" DROP ATTRIBUTE IF EXISTS "ParentRoleURI";
ALTER TYPE "Security"."-ngs_InheritedRole_type-" DROP ATTRIBUTE IF EXISTS "RoleURI";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_InheritedRole_type-' AND column_name = 'ParentName' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_InheritedRole_type-" DROP ATTRIBUTE "ParentName";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'InheritedRole' AND column_name = 'ParentName' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."InheritedRole" DROP COLUMN "ParentName";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_InheritedRole_type-' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_InheritedRole_type-" DROP ATTRIBUTE "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'InheritedRole' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."InheritedRole" DROP COLUMN "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_Role_type-' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_Role_type-" DROP ATTRIBUTE "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'Role' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."Role" DROP COLUMN "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'IsAllowed' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_User_type-" DROP ATTRIBUTE "IsAllowed";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'User' AND column_name = 'IsAllowed' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."User" DROP COLUMN "IsAllowed";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'PasswordHash' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_User_type-" DROP ATTRIBUTE "PasswordHash";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'User' AND column_name = 'PasswordHash' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."User" DROP COLUMN "PasswordHash";
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TYPE "Security"."-ngs_User_type-" DROP ATTRIBUTE IF EXISTS "RoleURI";

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = '-ngs_User_type-' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TYPE "Security"."-ngs_User_type-" DROP ATTRIBUTE "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'Security' AND type_name = 'User' AND column_name = 'Name' AND is_ngs_generated) THEN
		ALTER TABLE "Security"."User" DROP COLUMN "Name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ingredients' AND is_ngs_generated) THEN
		ALTER TYPE "hello"."-ngs_Recipe_type-" DROP ATTRIBUTE "ingredients";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = 'Recipe' AND column_name = 'ingredients' AND is_ngs_generated) THEN
		ALTER TABLE "hello"."Recipe" DROP COLUMN "ingredients";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = '-ngs_Recipe_type-' AND column_name = 'name' AND is_ngs_generated) THEN
		ALTER TYPE "hello"."-ngs_Recipe_type-" DROP ATTRIBUTE "name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = 'Recipe' AND column_name = 'name' AND is_ngs_generated) THEN
		ALTER TABLE "hello"."Recipe" DROP COLUMN "name";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = '-ngs_Recipe_type-' AND column_name = 'ID' AND is_ngs_generated) THEN
		ALTER TYPE "hello"."-ngs_Recipe_type-" DROP ATTRIBUTE "ID";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'hello' AND type_name = 'Recipe' AND column_name = 'ID' AND is_ngs_generated) THEN
		ALTER TABLE "hello"."Recipe" DROP COLUMN "ID";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'RolePermission_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "Security"."RolePermission_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'RolePermission' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "Security"."RolePermission";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND t.typname = '-ngs_RolePermission_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "Security"."-ngs_RolePermission_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'GlobalPermission_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "Security"."GlobalPermission_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'GlobalPermission' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "Security"."GlobalPermission";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND t.typname = '-ngs_GlobalPermission_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "Security"."-ngs_GlobalPermission_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'InheritedRole_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "Security"."InheritedRole_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'InheritedRole' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "Security"."InheritedRole";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND t.typname = '-ngs_InheritedRole_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "Security"."-ngs_InheritedRole_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'Role_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "Security"."Role_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'Role' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "Security"."Role";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND t.typname = '-ngs_Role_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "Security"."-ngs_Role_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'User_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "Security"."User_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND c.relname = 'User' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "Security"."User";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'Security' AND t.typname = '-ngs_User_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "Security"."-ngs_User_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'hello' AND c.relname = 'Recipe_sequence' AND d.description LIKE 'NGS generated%') THEN
		DROP SEQUENCE "hello"."Recipe_sequence";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace JOIN pg_description d ON c.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'hello' AND c.relname = 'Recipe' AND d.description LIKE 'NGS generated%') THEN
		DROP TABLE "hello"."Recipe";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace JOIN pg_description d ON t.oid = d.objoid AND d.objsubid = 0 WHERE n.nspname = 'hello' AND t.typname = '-ngs_Recipe_type-' AND d.description LIKE 'NGS generated%') THEN
		DROP TYPE "hello"."-ngs_Recipe_type-";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF EXISTS(SELECT * FROM pg_namespace n JOIN pg_description d ON d.objoid = n.oid WHERE n.nspname = 'Security' AND d.description LIKE 'NGS generated%') THEN
		DROP SCHEMA "Security";
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_namespace WHERE nspname = 'security') THEN
		CREATE SCHEMA "security";
		COMMENT ON SCHEMA "security" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'security' AND t.typname = '-ngs_User_type-') THEN	
		CREATE TYPE "security"."-ngs_User_type-" AS ();
		COMMENT ON TYPE "security"."-ngs_User_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'User') THEN	
		CREATE TABLE "security"."User" ();
		COMMENT ON TABLE "security"."User" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'User_sequence') THEN
		CREATE SEQUENCE "security"."User_sequence";
		COMMENT ON SEQUENCE "security"."User_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'security' AND t.typname = '-ngs_GlobalPermission_type-') THEN	
		CREATE TYPE "security"."-ngs_GlobalPermission_type-" AS ();
		COMMENT ON TYPE "security"."-ngs_GlobalPermission_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'GlobalPermission') THEN	
		CREATE TABLE "security"."GlobalPermission" ();
		COMMENT ON TABLE "security"."GlobalPermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'GlobalPermission_sequence') THEN
		CREATE SEQUENCE "security"."GlobalPermission_sequence";
		COMMENT ON SEQUENCE "security"."GlobalPermission_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_type t JOIN pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = 'security' AND t.typname = '-ngs_RolePermission_type-') THEN	
		CREATE TYPE "security"."-ngs_RolePermission_type-" AS ();
		COMMENT ON TYPE "security"."-ngs_RolePermission_type-" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'RolePermission') THEN	
		CREATE TABLE "security"."RolePermission" ();
		COMMENT ON TABLE "security"."RolePermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = 'security' AND c.relname = 'RolePermission_sequence') THEN
		CREATE SEQUENCE "security"."RolePermission_sequence";
		COMMENT ON SEQUENCE "security"."RolePermission_sequence" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'User' AND column_name = 'name') THEN
		ALTER TABLE "security"."User" ADD COLUMN "name" VARCHAR(100);
		COMMENT ON COLUMN "security"."User"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_User_type-' AND column_name = 'name') THEN
		ALTER TYPE "security"."-ngs_User_type-" ADD ATTRIBUTE "name" VARCHAR(100);
		COMMENT ON COLUMN "security"."-ngs_User_type-"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'User' AND column_name = 'roles') THEN
		ALTER TABLE "security"."User" ADD COLUMN "roles" VARCHAR[];
		COMMENT ON COLUMN "security"."User"."roles" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_User_type-' AND column_name = 'roles') THEN
		ALTER TYPE "security"."-ngs_User_type-" ADD ATTRIBUTE "roles" VARCHAR[];
		COMMENT ON COLUMN "security"."-ngs_User_type-"."roles" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'User' AND column_name = 'password') THEN
		ALTER TABLE "security"."User" ADD COLUMN "password" BYTEA;
		COMMENT ON COLUMN "security"."User"."password" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_User_type-' AND column_name = 'password') THEN
		ALTER TYPE "security"."-ngs_User_type-" ADD ATTRIBUTE "password" BYTEA;
		COMMENT ON COLUMN "security"."-ngs_User_type-"."password" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'User' AND column_name = 'isAllowed') THEN
		ALTER TABLE "security"."User" ADD COLUMN "isAllowed" BOOL;
		COMMENT ON COLUMN "security"."User"."isAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_User_type-' AND column_name = 'isAllowed') THEN
		ALTER TYPE "security"."-ngs_User_type-" ADD ATTRIBUTE "isAllowed" BOOL;
		COMMENT ON COLUMN "security"."-ngs_User_type-"."isAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'GlobalPermission' AND column_name = 'name') THEN
		ALTER TABLE "security"."GlobalPermission" ADD COLUMN "name" VARCHAR(200);
		COMMENT ON COLUMN "security"."GlobalPermission"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_GlobalPermission_type-' AND column_name = 'name') THEN
		ALTER TYPE "security"."-ngs_GlobalPermission_type-" ADD ATTRIBUTE "name" VARCHAR(200);
		COMMENT ON COLUMN "security"."-ngs_GlobalPermission_type-"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'GlobalPermission' AND column_name = 'isAllowed') THEN
		ALTER TABLE "security"."GlobalPermission" ADD COLUMN "isAllowed" BOOL;
		COMMENT ON COLUMN "security"."GlobalPermission"."isAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_GlobalPermission_type-' AND column_name = 'isAllowed') THEN
		ALTER TYPE "security"."-ngs_GlobalPermission_type-" ADD ATTRIBUTE "isAllowed" BOOL;
		COMMENT ON COLUMN "security"."-ngs_GlobalPermission_type-"."isAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'RolePermission' AND column_name = 'name') THEN
		ALTER TABLE "security"."RolePermission" ADD COLUMN "name" VARCHAR(200);
		COMMENT ON COLUMN "security"."RolePermission"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'name') THEN
		ALTER TYPE "security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "name" VARCHAR(200);
		COMMENT ON COLUMN "security"."-ngs_RolePermission_type-"."name" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'RolePermission' AND column_name = 'roleID') THEN
		ALTER TABLE "security"."RolePermission" ADD COLUMN "roleID" VARCHAR;
		COMMENT ON COLUMN "security"."RolePermission"."roleID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'roleID') THEN
		ALTER TYPE "security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "roleID" VARCHAR;
		COMMENT ON COLUMN "security"."-ngs_RolePermission_type-"."roleID" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = 'RolePermission' AND column_name = 'isAllowed') THEN
		ALTER TABLE "security"."RolePermission" ADD COLUMN "isAllowed" BOOL;
		COMMENT ON COLUMN "security"."RolePermission"."isAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM "-DSL-".Load_Type_Info() WHERE type_schema = 'security' AND type_name = '-ngs_RolePermission_type-' AND column_name = 'isAllowed') THEN
		ALTER TYPE "security"."-ngs_RolePermission_type-" ADD ATTRIBUTE "isAllowed" BOOL;
		COMMENT ON COLUMN "security"."-ngs_RolePermission_type-"."isAllowed" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "security"."User_entity" AS
SELECT _entity."name", _entity."roles", _entity."password", _entity."isAllowed"
FROM
	"security"."User" _entity
	;
COMMENT ON VIEW "security"."User_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("security"."User_entity") RETURNS TEXT AS $$
SELECT CAST($1."name" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "security"."GlobalPermission_entity" AS
SELECT _entity."name", _entity."isAllowed"
FROM
	"security"."GlobalPermission" _entity
	;
COMMENT ON VIEW "security"."GlobalPermission_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("security"."GlobalPermission_entity") RETURNS TEXT AS $$
SELECT CAST($1."name" as TEXT)
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE VIEW "security"."RolePermission_entity" AS
SELECT _entity."name", _entity."roleID", _entity."isAllowed"
FROM
	"security"."RolePermission" _entity
	;
COMMENT ON VIEW "security"."RolePermission_entity" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "URI"("security"."RolePermission_entity") RETURNS TEXT AS $$
SELECT "-DSL-".Generate_Uri2(CAST($1."name" as TEXT), CAST($1."roleID" as TEXT))
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."cast_User_to_type"("security"."-ngs_User_type-") RETURNS "security"."User_entity" AS $$ SELECT $1::text::"security"."User_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "security"."cast_User_to_type"("security"."User_entity") RETURNS "security"."-ngs_User_type-" AS $$ SELECT $1::text::"security"."-ngs_User_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'security' AND s.typname = 'User_entity' AND t.typname = '-ngs_User_type-') THEN
		CREATE CAST ("security"."-ngs_User_type-" AS "security"."User_entity") WITH FUNCTION "security"."cast_User_to_type"("security"."-ngs_User_type-") AS IMPLICIT;
		CREATE CAST ("security"."User_entity" AS "security"."-ngs_User_type-") WITH FUNCTION "security"."cast_User_to_type"("security"."User_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "security"."User_unprocessed_events" AS
SELECT _aggregate."name"
FROM
	"security"."User_entity" _aggregate
;
COMMENT ON VIEW "security"."User_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "security"."insert_User"(IN _inserted "security"."User_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "security"."User" ("name", "roles", "password", "isAllowed") VALUES(_inserted[1]."name", _inserted[1]."roles", _inserted[1]."password", _inserted[1]."isAllowed");
	
	PERFORM pg_notify('aggregate_roots', 'security.User:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "security"."persist_User"(
IN _inserted "security"."User_entity"[], IN _updated_original "security"."User_entity"[], IN _updated_new "security"."User_entity"[], IN _deleted "security"."User_entity"[]) 
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

	

	INSERT INTO "security"."User" ("name", "roles", "password", "isAllowed")
	SELECT _i."name", _i."roles", _i."password", _i."isAllowed" 
	FROM unnest(_inserted) _i;

	

	UPDATE "security"."User" as _tbl SET "name" = (_u.changed)."name", "roles" = (_u.changed)."roles", "password" = (_u.changed)."password", "isAllowed" = (_u.changed)."isAllowed"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."name" = (_u.original)."name";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "security"."User"
	WHERE ("name") IN (SELECT _d."name" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.User', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.User', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.User', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."name" != (_u.original)."name"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.User', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."update_User"(IN _original "security"."User_entity"[], IN _updated "security"."User_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "security"."User" AS _tab SET "name" = _updated[1]."name", "roles" = _updated[1]."roles", "password" = _updated[1]."password", "isAllowed" = _updated[1]."isAllowed" WHERE _tab."name" = _original[1]."name";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'security.User:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."name" != _updated[1]."name") THEN
		PERFORM pg_notify('aggregate_roots', 'security.User:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "security"."cast_GlobalPermission_to_type"("security"."-ngs_GlobalPermission_type-") RETURNS "security"."GlobalPermission_entity" AS $$ SELECT $1::text::"security"."GlobalPermission_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "security"."cast_GlobalPermission_to_type"("security"."GlobalPermission_entity") RETURNS "security"."-ngs_GlobalPermission_type-" AS $$ SELECT $1::text::"security"."-ngs_GlobalPermission_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'security' AND s.typname = 'GlobalPermission_entity' AND t.typname = '-ngs_GlobalPermission_type-') THEN
		CREATE CAST ("security"."-ngs_GlobalPermission_type-" AS "security"."GlobalPermission_entity") WITH FUNCTION "security"."cast_GlobalPermission_to_type"("security"."-ngs_GlobalPermission_type-") AS IMPLICIT;
		CREATE CAST ("security"."GlobalPermission_entity" AS "security"."-ngs_GlobalPermission_type-") WITH FUNCTION "security"."cast_GlobalPermission_to_type"("security"."GlobalPermission_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "security"."GlobalPermission_unprocessed_events" AS
SELECT _aggregate."name"
FROM
	"security"."GlobalPermission_entity" _aggregate
;
COMMENT ON VIEW "security"."GlobalPermission_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "security"."insert_GlobalPermission"(IN _inserted "security"."GlobalPermission_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "security"."GlobalPermission" ("name", "isAllowed") VALUES(_inserted[1]."name", _inserted[1]."isAllowed");
	
	PERFORM pg_notify('aggregate_roots', 'security.GlobalPermission:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "security"."persist_GlobalPermission"(
IN _inserted "security"."GlobalPermission_entity"[], IN _updated_original "security"."GlobalPermission_entity"[], IN _updated_new "security"."GlobalPermission_entity"[], IN _deleted "security"."GlobalPermission_entity"[]) 
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

	

	INSERT INTO "security"."GlobalPermission" ("name", "isAllowed")
	SELECT _i."name", _i."isAllowed" 
	FROM unnest(_inserted) _i;

	

	UPDATE "security"."GlobalPermission" as _tbl SET "name" = (_u.changed)."name", "isAllowed" = (_u.changed)."isAllowed"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."name" = (_u.original)."name";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "security"."GlobalPermission"
	WHERE ("name") IN (SELECT _d."name" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.GlobalPermission', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.GlobalPermission', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.GlobalPermission', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."name" != (_u.original)."name"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.GlobalPermission', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."update_GlobalPermission"(IN _original "security"."GlobalPermission_entity"[], IN _updated "security"."GlobalPermission_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "security"."GlobalPermission" AS _tab SET "name" = _updated[1]."name", "isAllowed" = _updated[1]."isAllowed" WHERE _tab."name" = _original[1]."name";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'security.GlobalPermission:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."name" != _updated[1]."name") THEN
		PERFORM pg_notify('aggregate_roots', 'security.GlobalPermission:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "security"."cast_RolePermission_to_type"("security"."-ngs_RolePermission_type-") RETURNS "security"."RolePermission_entity" AS $$ SELECT $1::text::"security"."RolePermission_entity" $$ IMMUTABLE LANGUAGE sql;
CREATE OR REPLACE FUNCTION "security"."cast_RolePermission_to_type"("security"."RolePermission_entity") RETURNS "security"."-ngs_RolePermission_type-" AS $$ SELECT $1::text::"security"."-ngs_RolePermission_type-" $$ IMMUTABLE LANGUAGE sql;

DO $$ BEGIN
	IF NOT EXISTS(SELECT * FROM pg_cast c JOIN pg_type s ON c.castsource = s.oid JOIN pg_type t ON c.casttarget = t.oid JOIN pg_namespace n ON n.oid = s.typnamespace AND n.oid = t.typnamespace
					WHERE n.nspname = 'security' AND s.typname = 'RolePermission_entity' AND t.typname = '-ngs_RolePermission_type-') THEN
		CREATE CAST ("security"."-ngs_RolePermission_type-" AS "security"."RolePermission_entity") WITH FUNCTION "security"."cast_RolePermission_to_type"("security"."-ngs_RolePermission_type-") AS IMPLICIT;
		CREATE CAST ("security"."RolePermission_entity" AS "security"."-ngs_RolePermission_type-") WITH FUNCTION "security"."cast_RolePermission_to_type"("security"."RolePermission_entity") AS IMPLICIT;
	END IF;
END $$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW "security"."RolePermission_unprocessed_events" AS
SELECT _aggregate."name", _aggregate."roleID"
FROM
	"security"."RolePermission_entity" _aggregate
;
COMMENT ON VIEW "security"."RolePermission_unprocessed_events" IS 'NGS volatile';

CREATE OR REPLACE FUNCTION "security"."insert_RolePermission"(IN _inserted "security"."RolePermission_entity"[]) RETURNS VOID AS
$$
BEGIN
	INSERT INTO "security"."RolePermission" ("name", "roleID", "isAllowed") VALUES(_inserted[1]."name", _inserted[1]."roleID", _inserted[1]."isAllowed");
	
	PERFORM pg_notify('aggregate_roots', 'security.RolePermission:Insert:' || array["URI"(_inserted[1])]::TEXT);
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

CREATE OR REPLACE FUNCTION "security"."persist_RolePermission"(
IN _inserted "security"."RolePermission_entity"[], IN _updated_original "security"."RolePermission_entity"[], IN _updated_new "security"."RolePermission_entity"[], IN _deleted "security"."RolePermission_entity"[]) 
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

	

	INSERT INTO "security"."RolePermission" ("name", "roleID", "isAllowed")
	SELECT _i."name", _i."roleID", _i."isAllowed" 
	FROM unnest(_inserted) _i;

	

	UPDATE "security"."RolePermission" as _tbl SET "name" = (_u.changed)."name", "roleID" = (_u.changed)."roleID", "isAllowed" = (_u.changed)."isAllowed"
	FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u
	WHERE _tbl."name" = (_u.original)."name" AND _tbl."roleID" = (_u.original)."roleID";

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _update_count THEN 
		RETURN 'Updated ' || cnt || ' row(s). Expected to update ' || _update_count || ' row(s).';
	END IF;

	

	DELETE FROM "security"."RolePermission"
	WHERE ("name", "roleID") IN (SELECT _d."name", _d."roleID" FROM unnest(_deleted) _d);

	GET DIAGNOSTICS cnt = ROW_COUNT;
	IF cnt != _delete_count THEN 
		RETURN 'Deleted ' || cnt || ' row(s). Expected to delete ' || _delete_count || ' row(s).';
	END IF;

	
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.RolePermission', 'Insert', (SELECT array_agg(_i."URI") FROM unnest(_inserted) _i));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.RolePermission', 'Update', (SELECT array_agg(_u."URI") FROM unnest(_updated_original) _u));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.RolePermission', 'Change', (SELECT array_agg((_u.changed)."URI") FROM (SELECT unnest(_updated_original) as original, unnest(_updated_new) as changed) _u WHERE (_u.changed)."name" != (_u.original)."name" OR (_u.changed)."roleID" != (_u.original)."roleID"));
	PERFORM "-DSL-".Safe_Notify('aggregate_roots', 'security.RolePermission', 'Delete', (SELECT array_agg(_d."URI") FROM unnest(_deleted) _d));

	SET CONSTRAINTS ALL IMMEDIATE;

	RETURN NULL;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE FUNCTION "security"."update_RolePermission"(IN _original "security"."RolePermission_entity"[], IN _updated "security"."RolePermission_entity"[]) RETURNS VARCHAR AS
$$
DECLARE cnt int;
BEGIN
	
	UPDATE "security"."RolePermission" AS _tab SET "name" = _updated[1]."name", "roleID" = _updated[1]."roleID", "isAllowed" = _updated[1]."isAllowed" WHERE _tab."name" = _original[1]."name" AND _tab."roleID" = _original[1]."roleID";
	GET DIAGNOSTICS cnt = ROW_COUNT;
	
	PERFORM pg_notify('aggregate_roots', 'security.RolePermission:Update:' || array["URI"(_original[1])]::TEXT);
	IF (_original[1]."name" != _updated[1]."name" OR _original[1]."roleID" != _updated[1]."roleID") THEN
		PERFORM pg_notify('aggregate_roots', 'security.RolePermission:Change:' || array["URI"(_updated[1])]::TEXT);
	END IF;
	RETURN CASE WHEN cnt = 0 THEN 'No rows updated' ELSE NULL END;
END
$$
LANGUAGE plpgsql SECURITY DEFINER;;

SELECT "-DSL-".Create_Type_Cast('"security"."cast_User_to_type"("security"."-ngs_User_type-")', 'security', '-ngs_User_type-', 'User_entity');
SELECT "-DSL-".Create_Type_Cast('"security"."cast_User_to_type"("security"."User_entity")', 'security', 'User_entity', '-ngs_User_type-');

SELECT "-DSL-".Create_Type_Cast('"security"."cast_GlobalPermission_to_type"("security"."-ngs_GlobalPermission_type-")', 'security', '-ngs_GlobalPermission_type-', 'GlobalPermission_entity');
SELECT "-DSL-".Create_Type_Cast('"security"."cast_GlobalPermission_to_type"("security"."GlobalPermission_entity")', 'security', 'GlobalPermission_entity', '-ngs_GlobalPermission_type-');
CREATE OR REPLACE FUNCTION "security"."GlobalPermission.WithPrefix"("it" "security"."GlobalPermission_entity", "prefix" VARCHAR) RETURNS BOOL AS 
$$
	SELECT 	 ( ((("it"))."name" = "GlobalPermission.WithPrefix"."prefix") OR ((("it"))."name" LIKE  REPLACE(REPLACE(REPLACE( ("GlobalPermission.WithPrefix"."prefix" || '.'), '\','\\'), '_','\_'), '%','\%')  || '%')) 
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;
CREATE OR REPLACE FUNCTION "security"."GlobalPermission.WithPrefix"("prefix" VARCHAR) RETURNS SETOF "security"."GlobalPermission_entity" AS 
$$SELECT * FROM "security"."GlobalPermission_entity" "it"  WHERE 	 ( ((("it"))."name" = "GlobalPermission.WithPrefix"."prefix") OR ((("it"))."name" LIKE  REPLACE(REPLACE(REPLACE( ("GlobalPermission.WithPrefix"."prefix" || '.'), '\','\\'), '_','\_'), '%','\%')  || '%')) 
$$ LANGUAGE SQL STABLE SECURITY DEFINER;

SELECT "-DSL-".Create_Type_Cast('"security"."cast_RolePermission_to_type"("security"."-ngs_RolePermission_type-")', 'security', '-ngs_RolePermission_type-', 'RolePermission_entity');
SELECT "-DSL-".Create_Type_Cast('"security"."cast_RolePermission_to_type"("security"."RolePermission_entity")', 'security', 'RolePermission_entity', '-ngs_RolePermission_type-');
CREATE OR REPLACE FUNCTION "security"."RolePermission.ForRole"("it" "security"."RolePermission_entity", "role" VARCHAR) RETURNS BOOL AS 
$$
	SELECT 	 ((("it"))."roleID" = "RolePermission.ForRole"."role") 
$$ LANGUAGE SQL IMMUTABLE SECURITY DEFINER;
CREATE OR REPLACE FUNCTION "security"."RolePermission.ForRole"("role" VARCHAR) RETURNS SETOF "security"."RolePermission_entity" AS 
$$SELECT * FROM "security"."RolePermission_entity" "it"  WHERE 	 ((("it"))."roleID" = "RolePermission.ForRole"."role") 
$$ LANGUAGE SQL STABLE SECURITY DEFINER;
UPDATE "security"."User" SET "name" = '' WHERE "name" IS NULL;
UPDATE "security"."User" SET "roles" = '{}' WHERE "roles" IS NULL;
UPDATE "security"."User" SET "password" = '' WHERE "password" IS NULL;
UPDATE "security"."User" SET "isAllowed" = false WHERE "isAllowed" IS NULL;
UPDATE "security"."GlobalPermission" SET "name" = '' WHERE "name" IS NULL;
UPDATE "security"."GlobalPermission" SET "isAllowed" = false WHERE "isAllowed" IS NULL;
UPDATE "security"."RolePermission" SET "name" = '' WHERE "name" IS NULL;
UPDATE "security"."RolePermission" SET "roleID" = '' WHERE "roleID" IS NULL;
UPDATE "security"."RolePermission" SET "isAllowed" = false WHERE "isAllowed" IS NULL;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'security' AND c.relname = 'User') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"security"."User"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('name' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table security.User. Expected primary key: name. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "security"."User" ADD CONSTRAINT "pk_User" PRIMARY KEY("name");
		COMMENT ON CONSTRAINT "pk_User" ON "security"."User" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'security' AND c.relname = 'GlobalPermission') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"security"."GlobalPermission"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('name' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table security.GlobalPermission. Expected primary key: name. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "security"."GlobalPermission" ADD CONSTRAINT "pk_GlobalPermission" PRIMARY KEY("name");
		COMMENT ON CONSTRAINT "pk_GlobalPermission" ON "security"."GlobalPermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;

DO $$ 
DECLARE _pk VARCHAR;
BEGIN
	IF EXISTS(SELECT * FROM pg_index i JOIN pg_class c ON i.indrelid = c.oid JOIN pg_namespace n ON c.relnamespace = n.oid WHERE i.indisprimary AND n.nspname = 'security' AND c.relname = 'RolePermission') THEN
		SELECT array_to_string(array_agg(sq.attname), ', ') INTO _pk
		FROM
		(
			SELECT atr.attname
			FROM pg_index i
			JOIN pg_class c ON i.indrelid = c.oid 
			JOIN pg_attribute atr ON atr.attrelid = c.oid 
			WHERE 
				c.oid = '"security"."RolePermission"'::regclass
				AND atr.attnum = any(i.indkey)
				AND indisprimary
			ORDER BY (SELECT i FROM generate_subscripts(i.indkey,1) g(i) WHERE i.indkey[i] = atr.attnum LIMIT 1)
		) sq;
		IF ('name, roleID' != _pk) THEN
			RAISE EXCEPTION 'Different primary key defined for table security.RolePermission. Expected primary key: name, roleID. Found: %', _pk;
		END IF;
	ELSE
		ALTER TABLE "security"."RolePermission" ADD CONSTRAINT "pk_RolePermission" PRIMARY KEY("name","roleID");
		COMMENT ON CONSTRAINT "pk_RolePermission" ON "security"."RolePermission" IS 'NGS generated';
	END IF;
END $$ LANGUAGE plpgsql;
ALTER TABLE "security"."User" ALTER "name" SET NOT NULL;
ALTER TABLE "security"."User" ALTER "roles" SET NOT NULL;
ALTER TABLE "security"."User" ALTER "password" SET NOT NULL;
ALTER TABLE "security"."User" ALTER "isAllowed" SET NOT NULL;
ALTER TABLE "security"."GlobalPermission" ALTER "name" SET NOT NULL;
ALTER TABLE "security"."GlobalPermission" ALTER "isAllowed" SET NOT NULL;
ALTER TABLE "security"."RolePermission" ALTER "name" SET NOT NULL;
ALTER TABLE "security"."RolePermission" ALTER "roleID" SET NOT NULL;
ALTER TABLE "security"."RolePermission" ALTER "isAllowed" SET NOT NULL;

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
"', '\x','1.7.6200.20202');
SELECT pg_notify('migration', 'removal');