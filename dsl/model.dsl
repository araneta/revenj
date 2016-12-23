//module will be mapped to part of the namespace in Java
//and to the schema name in Postgres
module hello {
	//Matching table will be created in the Postgres (although with this DSL there is one extra implied field)
	aggregate World {
		string message;
	}
}