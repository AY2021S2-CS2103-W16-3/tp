package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;

/**
 * Redoes the previous command that changes the AddressBook
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes the last command that changes the AddressBook.";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Completed redo for the change: ";

    public static final String MESSAGE_INVALID_REDO = "There's nothing left to redo!";


    /**
     * Creates a RedoCommand to redo the last command that changes the AddressBook
     */
    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String command;
        try {
            command = model.redo();
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_REDO);
        }
        return new CommandResult(MESSAGE_SUCCESS + command);
    }

}

