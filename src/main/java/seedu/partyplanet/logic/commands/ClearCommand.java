package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.Model;

/**
 * Clears PartyPlanet.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "PartyPlanet has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears PartyPlanet. ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.addState();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
