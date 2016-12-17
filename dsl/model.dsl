module hello {
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
		implements server 'Revenj.Security.IUser';
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
		implements server 'Revenj.Security.IUserRoles';
	}

	role Administrator;

	root GlobalPermission(Name)
	{
		string(200) Name;
		bool IsAllowed;
		implements server 'Revenj.Security.IGlobalPermission';
	}

	root RolePermission(Name, RoleID)
	{
		string(200) Name;
		Role *Role;
		bool IsAllowed;
		implements server 'Revenj.Security.IRolePermission';
	}
}
