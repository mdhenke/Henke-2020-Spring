## Setup
* Run the queries below in a MySQL editor: 
```
drop schema skime;
create schema skime;
use skime;
create table drivers (
	id bigint auto_increment NOT NULL,
    name varchar(50) NOT NULL,
    stars integer,
    rate integer,
    lat double,
    `long` double,
    imageUrl varchar(200),
    primary key(id)
);

insert into drivers(name, stars, rate, lat, `long`, imageUrl) values(
	"Bill Gates",
    5,
    15,
    43.522298,
    -116.052267,
    "http://10.0.2.2:3000/drivers/bill_gates.jpg"
);
```

* You MUST adjust the address, port, username, and password to match your own database in main.js.

Once the above are complete, you can run the express server with the command "main.js". 