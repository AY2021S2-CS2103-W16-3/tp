package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;

/**
 * Deletes a person or persons identified using it's displayed index from PartyPlanet.
 */
public class DeleteContactCommand extends DeleteCommand {

    private final List<Index> targetIndexes;

    /**
     * Creates an DeleteContactCommand to delete the {@code Person} at specified indexes.
     */
    public DeleteContactCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> deletedPersons = new ArrayList<>();

        for (Index idx : targetIndexes) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(idx.getZeroBased());
            deletedPersons.add(personToDelete);
        }

        for (Person personToDelete : deletedPersons) {
            model.deletePerson(personToDelete);
        }
        model.addState(String.format(MESSAGE_DELETE_PERSON_SUCCESS, displayPersons(deletedPersons)));
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, displayPersons(deletedPersons)));
    }

    /**
     * Returns list of persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> deletedPersons) {
        return deletedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .get();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteContactCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteContactCommand) other).targetIndexes)); // state check
    }
}
