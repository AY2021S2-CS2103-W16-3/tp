### [Feature] Adding Persons
The Persons stored inside PartyPlanet should not have any compulsory fields except for name. This is to allow
for addition of contacts where the user is unable to, or does not need to fill up all fields.

One example of such case is a vendor's contact. The user does not need to store information on a vendor's birthday.

Additionally, the user should also be able to store remarks for that contact.

#### Implementation
* The remark is a new class that stores a String containing the specific remark
* Each `Person` class contains fields `Name`, `Address`, `Phone`, `Birthday`, `Email` , `Tag` and `Remark`
    * To allow for optional fields `Address`, `Phone`, `Birthday`, `Email`, `Tag` and `Remark`, each class has an attribute
      `isEmpty` that indicates whether the field in the person is empty.
    * The empty fields will then be stored as an empty string `""` in the `addressbook.json` folder and be read as an
      empty field accordingly.
* Syntax for adding Person: `add -n NAME [-a ADDRESS] [-p PHONE] [-b BIRTHDAY] [-e EMAIL] [-t TAG]... [-r REMARK]`

Given below is an example usage scenario and how the `add` mechanism behaves at each step.

1. The user executes `add -n James -r Loves sweets` command to add a person with name `James` and remark `Loves
   sweets`, represented by `execute("add -n James -r Loves sweets"")`. Note that fields `Address`, `Phone`,
   `Birthday`, `Tag` and `Email` are not specified and hence are empty fields.
2. `LogicManager` uses the `AddressBookParser` class to parse the user command, represented by `parseCommand("add -n
   James -r Loves sweets")`

Below is the partial sequence diagram for steps 1 and 2.

![Interactions Inside the Logic Component for the `add -n James -r Loves sweets` Command p1](images/AddSequenceDiagram1.png)

3. `AddressBookParser` creates an `AddCommandParser` which is used to parse the arguments provided by the user. This
   is represented by `parse("-n James -r Loves sweets")`.
4. `AddCommandParser` calls the constructor of a `Person` with the given arguments as input and creates a `Person`
   This is represented by `Person("James", "", "", "", "", "Loves sweets", [])`. Note empty
   string `""` and `[]` represent empty fields.
5. The `AddCommandParser` then passes this newly created `Person` as input to create an `AddCommand` which will be
   returned to the `LogicManager`. This is represented by `AddCommand(p)`

Below is the partial sequence diagram for steps 3, 4 and 5.

![Interactions Inside the Logic Component for the `add -n James -r Loves sweets` Command p2](images/AddSequenceDiagram2.png)



6. The `LogicManager` executes the `AddCommand` by calling `AddCommand#execute()` and passes the `CommandResult`
   back to the `UI`.

![Interactions Inside the Logic Component for the `add -n James -r Loves sweets` Command p3](images/AddSequenceDiagram3.png)

Given below is the full Sequence Diagram for interactions within the `Logic` component for the `execute("add -n
James -r Loves sweets")` API call.

![Interactions Inside the Logic Component for the `add -n James -r Loves sweets` Command](images/AddSequenceDiagram.png)
