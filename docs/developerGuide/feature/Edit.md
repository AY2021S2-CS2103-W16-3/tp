### [Feature] Editing Persons

Information about a person can change overtime, and user can edit contacts without having to delete and add a new replacement.

Edit allows modification of any target field and thus requires just one input parameter to work.
The updated contact is then displayed in-place of the old one.

Coupled with flag `--remove`, edit can remove all specified tags from all contacts in displayed list.

#### Implementation

* Syntax for editing individual Persons:
  `edit INDEX [-n NAME] [-a ADDRESS] [-p PHONE] [-b BIRTHDAY] [-e EMAIL] [-t TAG]... [-r REMARK]`

* Syntax for removing tags for all Persons in displayed list:
  `edit --remove -t TAG [-t TAG]...`

Given below is an example usage scenario and how the `edit` mechanism behaves.

1. The user executes `edit --remove -t friends -t pilot` command to edit all persons with `friends` and/ or `pilot` tags by removing it from their list of tags.

2. `LogicManager` calls `parseCommand("edit --remove -t friends -t pilot")` of `AddressBookParser` to parse this user command.

3. `AddressBookParser` recognises the command word `edit` and creates an `EditCommandParser`.

4. `AddressBookParser` calls `parse(--remove -t friends -t pilot)` of `EditCommandParser`.

5. `EditCommandParser` detects flag `--remove` and calls `parseTags(argMultimap.getAllValues(PREFIX_TAG)` of `ParserUtil`
   to processes the input tags into a `Set<Tag>`.

6. `EditCommandParser` then passes this `Set<Tag>` as input to create an `EditToRemoveTagCommand` which is returned to the `LogicManager`.

7. `LogicManager` executes the `EditToRemoveTagCommand` by calling `execute(model)`.

8. `EditToRemoveTagCommand` loops through the set of tags and persons in displayed list to remove the `friends` and `pilot` tags from each person in the displayed list.

9. `EditToRemoveTagCommand` creates a `CommandResult` with the success output message and returns it to `LogicManager`.
