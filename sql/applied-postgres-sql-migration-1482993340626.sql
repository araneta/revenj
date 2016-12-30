
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
	
	//use this event as a command
	event RegisterUser {
		string username;
		binary password;
	}
	
	//use this event as Domain event
	event UserRegistered {
		string username;
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
SELECT pg_notify('migration', 'nothing');