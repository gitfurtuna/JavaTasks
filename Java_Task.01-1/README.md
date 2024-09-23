# Day 01 – Java 
### OOP/Collections

### Exercise 00 — Models


Your first task is to develop basic domain models — namely, user and transaction classes.

It is quite likely that different users in the system will have the same name. This problem should be solved by adding a special field for a user's unique ID. This ID can be any integer. The specific logic for creating the ID is described in the next exercise.

Thus, the following set of states (fields) is typical for the User class:

- Identifier,
- User name,
- Balance.

The Transaction class describes a money transfer between two users. A unique identifier should also be defined here. Since the number of such transactions can be very large, we define the identifier as a UUID string. Thus, the following set of states (fields) is typical for the Transaction class:
- Identifier,
- Recipient (user type),
- Sender (user type),
- Transfer category (debit, credit),
- Transfer amount.

It is necessary to check the user's initial balance (it cannot be negative), as well as the balance for outgoing (only negative amounts) and incoming (only positive amounts) transactions (use of get/set methods).

An example of the use of such classes should be included in the Program file (creation, initialization, printing the object contents to a console). All data for class fields must be hard-coded in the Program.


### Exercise 01 – ID Generator


Make sure that each user ID is unique. To do this, create the UserIdsGenerator class. The behavior of the object of this class defines the functionality for generating user IDs.

Modern database management systems support auto-increment principle, where each new ID is the value of the previously generated ID +1.
Thus, the UserIdsGenerator class contains the last generated ID as its state. The behavior of UserIdsGenerator is defined by the int generateId() method, which returns a newly generated ID each time it is called.

An example of using such classes is contained in the program file (creation, initialization, printing object contents to a console).

**Notes**:

- Make sure that only one UserIdsGenerator object exists (see the Singleton pattern). It is required because the existence of multiple objects of this class cannot guarantee that all user identifiers are unique.

- The user identifier must be read-only because it is initialized only once (when the object is created) and cannot be changed later during program execution.

- Temporary logic for identifier initialization should be added to the User class constructor:
```java
public User(...) {
	this.id = UserIdsGenerator.getInstance().generateId();
}
```


### Exercise 02 – List of Users


Now we need to implement some functionality for saving users while the program is running. 

Currently, your application does not have any persistent storage (such as a file system or database). However, we want to avoid making your logic dependent on the user storage implementation method. To provide more flexibility, we define a UsersList interface that describes the following behavior

- Add a user;
- Get a user by ID;
- Get a user by index;
- Get number of users.

This interface will allow you to develop the business logic of your application so that a specific storage implementation does not affect other system components.

We will also implement a UsersArrayList class that implements the UsersList interface.

This class will use an array to store user data. The default size of the array is 10. When the array is full, its size is increased by half. The user-add method puts an object of type User into the first empty cell of the array.

If an attempt is made to retrieve a user with a non-existent ID, a non-checked UserNotFoundException must be thrown.

An example of using such classes is included in the program file (creating, initializing, printing object contents to a console).

**Note**:<br>
Nested `ArrayList<T>` Java class has the same structure. By modeling behavior of this class on your own, you will learn how to use mechanisms of this standard library class. 


### Exercise 03 — List of Transactions


Unlike users, a list of transactions requires a special implementation approach. Since the number of transaction creation operations can be very large, we need a storage method to avoid a costly array size extension. 

In this task, we offer you to create TransactionsList interface describing the following behavior:
- Add a transaction;
- Remove a transaction by ID (in this case, UUID string identifier is used);
- Transform into array (ex. Transaction[] toArray()).

A list of transactions shall be implemented as a linked list (LinkedList) in TransactionsLinkedList class. Therefore, each transaction shall contain a field with a link to the next transaction object.
If an attempt is made to remove a transaction with non-existent ID, TransactionNotFoundException runtime exception must be thrown.
An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console).

**Note**:<br>
- We need to add transactions field of TransactionsList type to User class so that each user can store the list of their transactions.
- A transaction must be added with a SINGLE operation (O(1)).
- `LinkedList<T>` nested Java class has the same structure, a bidirectional linked list.


### Exercise 04 – Business Logic


The business logic level of the application is located in service classes. Such classes contain basic system algorithms, automated processes, etc. These classes are usually designed according to the facade pattern, which can encapsulate the behavior of several classes.

In this case, the TransactionsService class must contain a UserList type field for user interaction and provide the following functionality
- Add a user;
- Get a user's balance.
- Perform a transfer transaction (user IDs and transfer amount are specified). In this case, two transactions of type DEBIT/CREDIT are created and added to the receiver and sender. The IDs of both transactions must be the same;
- Get transfers of a specific user (an ARRAY of transfers is returned). Remove a transaction by ID for a specific user (transaction ID and user ID are specified);
- Validate transactions (returns an ARRAY of unpaired transactions).

In case of an attempt to transfer the amount exceeding the user's remaining balance, the IllegalTransactionException runtime exception must be thrown.

An example of using such classes is included in the program file (creation, initialization, printing object contents to a console).


### Exercise 05 — Menu


As a result, you should have a working application with a console menu. The menu functionality must be implemented in the appropriate class with a link field to TransactionsService.

Each menu item must be accompanied by the number of the command that a user enters to invoke an action.

The application must support two startup modes — production (default mode) and dev (where transfer information for a specific user can be removed by user ID and a function that checks the validity of all transfers can be executed). 

If an exception is thrown, a message containing information about the error is displayed and the user is provided with an opportunity to enter valid data.

The application operation scenario is as follows (the program must carefully follow this output example):

```
$ java Program --profile=dev

1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Jonh 777
User with id = 1 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Mike 100
User with id = 2 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 100
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 150
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 50
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 2
Enter a user ID
-> 2
Mike - 400
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 4
Enter a user ID
-> 1
To Mike(id = 2) -100 with id = cc128842-2e5c-4cca-a44c-7829f53fc31f
To Mike(id = 2) -150 with id = 1fc852e7-914f-4bfd-913d-0313aab1ed99
TO Mike(id = 2) -50 with id = ce183f49-5be9-4513-bd05-8bd82214eaba
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 5
Enter a user ID and a transfer ID
-> 1 1fc852e7-914f-4bfd-913d-0313aab1ed99
Transfer To Mike(id = 2) 150 removed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 6
Check results:
Mike(id = 2) has an unacknowledged transfer id = 1fc852e7-914f-4bfd-913d-0313aab1ed99 from John(id = 1) for 150
```
