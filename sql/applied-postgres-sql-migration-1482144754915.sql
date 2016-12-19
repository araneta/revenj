
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
SELECT pg_notify('migration', 'nothing');