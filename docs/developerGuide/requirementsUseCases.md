### Use cases

(For all use cases below, the **System** is the `PartyPlanet` application while the **Actor** is the `User`,
unless specified otherwise)

    Use case: UC1 - Add a contact
    MSS:
      1. User requests to add a new contact.
      2. PartyPlanet displays the new list of contacts with the added contact.
      Use case ends.
    Extensions:
      1a. PartyPlanet detects an error in the entered data.
        1a1. PartyPlanet shows the user an example of the correct format.
        1a2. PartyPlanet requests for the correct data.
        Use case ends.


    Use case: UC2 - Clear
    MSS:
      1. User requests to clear all data.
      2. PartyPlanet clears all existing data.
      Use case ends.


    Use case: UC3 - Delete a contact
    MSS:
      1. User requests for a contact to be deleted.
      2. PartyPlanet displays the list of contacts without the deleted contact.
      Use case ends.
    Extensions:
      1a. The contact is invalid.
        1a1. PartyPlanet shows an error message.
        Use case ends.
      1b. PartyPlanet detects invalid format.
        1b1. PartyPlanet shows the user an example of the correct format.
        1b2. PartyPlanet requests for the correct data.
        Use case ends.


    Use case: UC4 - Edit a contact
    MSS:
      1. User request for a contact to be edited.
      2. PartyPlanet displays the updated details.
      Use case ends.
    Extensions:
      1a. PartyPlanet detects invalid format.
        1a1. PartyPlanet shows the user an example of the correct format.
        1a2. PartyPlanet requests for the correct data.
        Use case ends.
      1b. The contact is invalid.
        1b1. PartyPlanet requests for the correct data.
        Use case ends.


    Use case: UC5 - Find a contact
    MSS:
      1. User requests to search for relevant contacts.
      2. PartyPlanet displays a list of contacts that match the input.
      Use case ends.
    Extensions:
      1a. PartyPlanet detects invalid format.
        1a1. PartyPlanet shows the user an example of the correct format.
        1a2. PartyPlanet requests for the correct data.
        Use case ends.


    Use case: UC6 - List contacts
    MSS:
      1. User requests to list out all contacts.
      2. PartyPlanet displays a list of all contacts.
      Use case ends.
    Extensions:
      1a. User chooses a sort order from a list of possible sort orders.
        1a1. PartyPlanet displays the list of all contacts in the given sort order.
        Use case ends.
      1b. User enters an invalid / missing sort order.
        1b1. PartyPlanet displays an error.
        Use case ends.


    Use case: UC7 - Find tags
    MSS:
      1. User requests a search for relevant tags.
      2. PartyPlanet displays all relevant tags.
      Use case ends.
    Extensions:
      1a. PartyPlanet detects wrong parameters.
        1a1. PartyPlanet displays an error message.
        1a2. PartyPlanet prompts users to re-enter input.
        Use case ends.
      1b. PartyPlanet detects specific tags supplied.
        1b1. PartyPlanet displays all tags matching the name of the specified tag.
        Use case ends.


    Use case: UC8 - Get Help
    MSS:
      1. User requests for help.
      2. PartyPlanet displays all available commands.
      Use case ends.
    Extensions:
      1a. User supplied a specific command as a parameter.
        1a1. PartyPlanet displays help for the specific command supplied.
        Use case ends.
      1b. User supplied an unrecognised command as a parameter.
        1b1. PartyPlanet displays an error.
        Use case ends.
      1c. PartyPlanet detects an error in the entered data.
        1c1. PartyPlanet shows the user an example of the correct format.
        1c2. PartyPlanet requests for the correct data.
        Use case ends.


    Use case: UC9 - Exit PartyPlanet
    MSS:
      1. User requests to exit.
      2. PartyPlanet exits and closes the window.
      Use case ends.
