# Backoffice Booking AWS

### Simple API to show CRUD operations through HTTP Verbs POST, GET, DELETE and PUT

This README aims to explain some of the code as well as how to consume the API :computer: <br>

### Check availability

With that you should select the appropriate method `GET` in your software (Google Chrome, POSTMan and alike) to make the request.

URL:  http://ec2-3-87-208-55.compute-1.amazonaws.com/aws/public/v1/booking

Method : GET
JSON format as well as an HTTP Status 200.
You should get a JSON like the following if the room it's unavailable (For Example):

```
{
	"data": 
	{
		"message": "The room is unavailable until 2021-11-17",
		"is_occupied": true
	}
}
```

You should get a JSON like the following if the room it's available:

```
{
	"data": 
	{
		"message": "Room available",
		"is_occupied": true
	}
}

```

In case of success, a status code "OK" 200 is returned

### Placing an specific reservation

<b>You Must Need</b> pass a <b>valid id_customer</b>.

You can create a valid customer in the section :
<b>" Creating a new customer "</b>

With that you should select the appropriate method `POST` in your software (Google Chrome, POSTMan and alike) to make the request.

URL:  http://ec2-3-87-208-55.compute-1.amazonaws.com/aws/public/v1/booking

Method : POST

```
{
	"start_date":"2021-10-20",
	"finish_date":"2021-10-22",
	"customer":
	{
		"id_customer":"1"
	}
}
```

JSON format as well as an HTTP Status 201.
You should get a JSON like the following  (For Example):

```
{
	"data": 
	{
		"customer": 
		{
			"email": "felipemota@teste.com",
			"first_name": "Felipe",
			"last_name": "Mota",
			"cellphone": "+5513981325132",
			"date_of_birth": "1991-03-11",
			"id_customer": 1
		},
		"start_date": "2021-10-20",
		"finish_date": "2021-10-22",
		"is_occupied": true,
		"id_book": 1
	}
}
```

In case of success, a status code "CREATED" 201 is returned

### Updating a reservation

The process is similar to "Placing an specific reservation", but in this case we'll update or resource on the server using PUT Verb

With that you should select the appropriate method `PUT` in your software (Google Chrome, POSTMan and alike) to make the request.

URL:  http://ec2-3-87-208-55.compute-1.amazonaws.com/aws/public/v1/booking

Method : `PUT`

```
{
	"start_date":"2021-10-20",
	"finish_date":"2021-10-22",
	"customer":
	{
		"id_customer":"1"
	}
}
```

JSON format as well as an HTTP Status 201.
You should get a JSON like the following  (For Example):

```
{
	"data": 
	{
		"customer": 
		{
			"email": "felipemota@teste.com",
			"first_name": "Felipe",
			"last_name": "Mota",
			"cellphone": "+5513981325132",
			"date_of_birth": "1991-03-11",
			"id_customer": 1
		},
		"start_date": "2021-10-20",
		"finish_date": "2021-10-22",
		"is_occupied": true,
		"id_book": 1
	}
}
```

In case of success, a status code "CREATED" 201 is returned

## Cancelling a reservation

With that you should select the appropriate method `DELETE` in your software (Google Chrome, POSTMan and alike) to make the request.

URL:  http://ec2-3-87-208-55.compute-1.amazonaws.com/aws/public/v1/booking

You will need to pass is the id_customer with the HTTP Status code `DELETE`

```
{
	"customer":
	{
		"id_customer":"1"
	}
}
```

In case you typed the correct id_customer your reservation should be canceled and you should receive an HTTP Status Code 204 "NO CONTENT"

## Possible error scenarios

Family HTTP 4XX

```
Response: 404
{
	"message": " The room is unavailable until 2021-11-17. 	Please if you are the owner of this check in disregard this 	message.",
	"date": "2021-10-15T05:30:13.391+00:00",
	"http_status_code": 404
}

------------------------------------------------------------
  
Response: 400
{
	"message": "START_DATE_MUST_BE_GREATER_THAN_TODAY",
	"date": "2021-10-15T05:31:27.135+00:00",
	"http_status_code": 400
}

------------------------------------------------------------

Response: 400
{
	"message": "THE_STAY_CANNOT_BE_MORE_THAN_3_DAYS",
	"date": "2021-10-15T05:33:01.859+00:00",
	"http_status_code": 400
}

------------------------------------------------------------
  
Response: 400
{
	"message": "DATE_MORE_THAN_30_DAYS_IN_ADVANCE",
	"date": "2021-10-15T05:33:46.552+00:00",
	"http_status_code": 400
}
```

### Creating a new customer

With that you should select the appropriate method `POST` in your software (Google Chrome, POSTMan and alike) to make the request.

Method `POST`

URL:  http://ec2-3-87-208-55.compute-1.amazonaws.com/aws/public/v1/booking

```
{
	"first_name"  :  "Felipe",
	"last_name"  :  "Mota",
	"email"  :  "felipemota@test.com",
	"password":"test@123#",
	"cellphone"  :  "+5513981325132",
	"date_of_birth"  :  "1991-03-11"
}
```

JSON format as well as an HTTP Status 201.
You should get a JSON like the following  (For Example):

```
{
	"data":  
	{
		"email":  "felipemota@gmail.com",
		"first_name":  "Felipe",
		"last_name":  "Mota",
		"cellphone":  "+5513981325132",
		"date_of_birth":  "1991-03-11T00:00:00.000+00:00",
		"id_customer":  1
	}
}
```

In case of success, a status code "CREATED" 201 is returned

### Get all customers by lazy mode

With that you should select the appropriate method `GET` in your software (Google Chrome, POSTMan and alike) to make the request.

http://ec2-3-87-208-55.compute-1.amazonaws.com/aws/public/v1/customers/

```
{
	"totalElements":  1,
	"pageSize":  1,
	"totalPages":  1,
	"elements":  
	[
		{
			"email":  "felipemota@gmail.com",
			"first_name":  "Felipe",
			"last_name":  "Mota",
			"cellphone":  "+5513981325132",
			"date_of_birth":  "1991-03-11",
			"id_customer":  1
		}
	]
} 
```

In case of success, a status code "OK" 200 is returned
