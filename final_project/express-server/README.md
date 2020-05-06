## Setup
* Run the queries below in a local MySQL editor: 
```
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
	"John Appleseed",
    4,
    20,
    43.522298,
    -116.052267,
    "http://10.0.2.2:3000/drivers/marcus.jpg"
);
insert into drivers(name, stars, rate, lat, `long`, imageUrl) values(
	"Winston Churchill",
    2,
    22,
    43.522298,
    -116.052267,
    "http://10.0.2.2:3000/drivers/marcus.jpg"
);
```
* Node and NPM must be installed on your machine
* You MUST adjust the address, port, username, and password to match your own database in main.js.
* run "npm install" to install necessary dependencies for the express server
* Once the above are complete, you can run the express server with the command "node main.js" in this directory. 
