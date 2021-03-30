### Help feature

#### Current Implementation

When called as `help`, the user will be given the concise command-line syntax of all implemented commands and their arguments following the conventions listed in https://developers.google.com/style/code-syntax

This is facilitated by `MESSAGE_USAGE_CONCISE` in each `Command` that the user is able to use.

When called as `help [COMMAND]`, the user will be given the detailed description of the usage of the specified `COMMAND`.

This is facilitated by `MESSAGE_USAGE` in each `Command`.

Given below is an example usage scenario and how the `HelpCommand` mechanism behaves at each step.

Step 1. The user launches the application for the first time. The user is unsure of the syntax and attempts to type
in the CLI a command that is unlikely to fit the syntax of implemented commands. `AddressBookParser#ParseCommand()` throws a `ParseException` and the user receives a prompt "Unknown command, try the command: help".

Step 2. The user executes `help`. `AddressBookParser#ParseCommand()` instantiates a `HelpCommandParser` to parse the arguments for `help`. Since there are no arguments, the default constructor for `HelpCommand` is called, and the user receives a concise description of the complete set of implemented commands.

Step 3. The user executes `help add`. `AddressBookParser#ParseCommand()` instantiates a `HelpCommandParser` to parse the arguments for `help add`. The constructor taking in a `commandWord` is called, and when `HelpCommand#execute` is run, the `MESSAGE_USAGE` of the `Command` matching the `commandWord` is shown to the user.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the subsequent arguments are not successfully parsed, `help` is called instead. If multiple arguments are found, only the first one is parsed.

</div>

#### Design consideration:

##### Aspect: How HelpCommand executes

* **Alternative 1 (current choice):** Entire help message is composed of `MESSAGE_USAGE_CONCISE` of the various commands in `SHOWING_HELP_MESSAGE`, which is printed.
    * Pros: Each `Command` takes care of its own syntax, only needs to be updated at one place for changes to take effect.
    * Cons: The list of commands is still hard coded into `SHOWING_HELP_MESSAGE`, and needs to be manually updated every time a new `Command` is implemented.

* **Alternative 2:** Maintain a list of Commands, which `HelpCommand` will iterate over to print the concise syntax for each command when printing the help message.
    * Pros: Need not hard code the possible commands, only have to update the list of commands
    * Cons: Possible reduced performance, especially later if a large number of commands is added.

_{more aspects and alternatives to be added}_
