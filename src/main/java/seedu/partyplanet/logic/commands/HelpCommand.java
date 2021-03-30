package seedu.partyplanet.logic.commands;

import seedu.partyplanet.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD + " [COMMAND]";

    public static final String SHOWING_HELP_MESSAGE = "Here is a list of commands available, "
            + "for more information refer to https://ay2021s2-cs2103-w16-3.github.io/tp/UserGuide.html\n"
            + "Showing help: " + HelpCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "*****ADDRESSBOOK*****\n"
            + "Adding contacts: " + AddCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Deleting contacts: " + DeleteCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Editing contacts: " + EditCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Listing contacts: " + ListCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "*****EVENTBOOK*****\n"
            + "Adding events: " + EAddCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Deleting events: " + EDeleteCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Editing events: " + EEditCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Marking events as done: " + EDoneCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Listing events: " + EListCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "*****GENERAL*****\n"
            + "Undoing mistakes: " + UndoCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Redoing commands: " + RedoCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Toggle theme: " + ToggleThemeCommand.MESSAGE_USAGE_CONCISE + "\n"
            + "Leaving app: " + ExitCommand.MESSAGE_USAGE_CONCISE;

    private final String commandWord;

    /**
     * Creates a HelpCommand to give syntax details for all possible commands
     */
    public HelpCommand() {
        this.commandWord = "";
    }

    /**
     * Creates a HelpCommand to give syntax details for a specified {@code commandWord}
     */
    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult execute(Model model) {

        switch (commandWord) {

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(HelpCommand.MESSAGE_USAGE, false);

        // AddressBook Commands

        case AddCommand.COMMAND_WORD:
            return new CommandResult(AddCommand.MESSAGE_USAGE, false);

        case DeleteCommand.COMMAND_WORD:
            return new CommandResult(DeleteCommand.MESSAGE_USAGE, false);

        case EditCommand.COMMAND_WORD:
            return new CommandResult(EditCommand.MESSAGE_USAGE, false);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE, false);


        // EventBook Commands

        case EAddCommand.COMMAND_WORD:
            return new CommandResult(EAddCommand.MESSAGE_USAGE, false);

        case EDeleteCommand.COMMAND_WORD:
            return new CommandResult(EDeleteCommand.MESSAGE_USAGE, false);

        case EEditCommand.COMMAND_WORD:
            return new CommandResult(EEditCommand.MESSAGE_USAGE, false);

        case EDoneCommand.COMMAND_WORD:
            return new CommandResult(EDoneCommand.MESSAGE_USAGE, false);

        case EListCommand.COMMAND_WORD:
            return new CommandResult(EListCommand.MESSAGE_USAGE, false);

        // General Commands

        case UndoCommand.COMMAND_WORD:
            return new CommandResult(UndoCommand.MESSAGE_USAGE, false);

        case RedoCommand.COMMAND_WORD:
            return new CommandResult(RedoCommand.MESSAGE_USAGE, false);

        case ToggleThemeCommand.COMMAND_WORD:
            return new CommandResult(ToggleThemeCommand.MESSAGE_USAGE, false);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE, false);

        default:
            return new CommandResult(SHOWING_HELP_MESSAGE, false);
        }
    }


}
