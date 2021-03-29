### [Feature] Autocompleting `Edit` Command (Remark)

Since `Remark`s are intended to be capable of containing long sentences or paragraphs,
it brings an unintended chore of a User having to retype an entire `Remark` in order to edit it.

The Autocomplete feature allows the user to autocomplete a current `Person`'s remark into the
command box once the correct `Person` id and remark prefix has been keyed.

#### Implementation
* Syntax for EditAutocomplete: `edit INDEX -r` + `TAB`
* The user is expected to keypress the TAB key after typing the command in order to activate the autocomplete feature.
* The feature is implemented with the help of a new `EditAutocompleteUtil` class that handles parsing and retrieving the
  relavant remark from the `Model`.

Given below is an example usage scenario and how `EditAutocomplete` will work.

1. The user executes `edit 1 -r` + `TAB` command to autocomplete `Person` 1's Remark.

2. `UI` calls `autocomplete("edit 1 -r")` of `LogicManager` to handle the input.

3. `LogicManager` calls `parseEditCommand("edit 1 -r", model)` of `EditAutocompleteUtil` to parse the input.

4. `EditAutocompleteUtil` processes the input and retrieves the relevant `Person`'s `Remark` from the `Model`.

5. `EditAutocompleteUtil` creates the autocompleted output String and returns it to `LogicManager`.

7. `LogicManger` returns the autocompleted output String to `UI`.

8. `UI` updates `CommandBox` to reflect the autocompleted command input.

Given below is the full Sequence Diagram for interactions for the `edit 1 -r` + `TAB` API call.

![Sequence Diagram for Autocomplete Edit Remark](images/AutocompleteSequenceDiagram.png)