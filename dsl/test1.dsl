module Inheritance
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
			'it => customers.Contains(it.customer)'
			limit total
			order by placed asc;
		List<Order> allOtherOrdersInThatRange
			'it => !customers.Contains(it.customer)
				&& ordersByCustomers.FirstOrDefault() != null
				&& ordersByCustomers.FirstOrDefault().placed <= it.placed
				&& ordersByCustomers.LastOrDefault().placed >= it.placed'
			order by placed asc;                
	}*/


}
