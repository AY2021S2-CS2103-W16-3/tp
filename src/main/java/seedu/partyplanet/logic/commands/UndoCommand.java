package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;

/**
 * Undoes the previous command that changes the AddressBook
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the last command that changes the AddressBook.";

    public static final String MESSAGE_USAGE_CONCISE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Completed undo for the change: ";

    public static final String MESSAGE_INVALID_UNDO = "There's nothing left to undo!";


    /**
     * Creates an UndoCommand to undo the last command that changes the AddressBook
     */
    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String command;
        try {
            command = model.undo();
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_UNDO);
        }
        return new CommandResult(MESSAGE_SUCCESS + command);
    }

}

