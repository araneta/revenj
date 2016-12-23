module security {

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
		//this way we can use our "custom" object with builtin simple permission infrastructure
		implements Java 'org.revenj.security.GlobalPermission';
		//Compiler will create SQL function with the matching implementation which Revenj will use
		//when search by specification is called on repository/data context
		specification WithPrefix 'it => it.name == prefix || it.name.StartsWith(prefix + ".")' {
			string prefix;
		}
	}

	root RolePermission(name, roleID) {
		string(200) name;
		//the variable is named this way to match the RolePermission interface signature
		string roleID;
		bool isAllowed;
		implements Java 'org.revenj.security.RolePermission';
		specification ForRole 'it => it.roleID == role' {
			string role;
		}
	}
}