package seedu.partyplanet.model;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.partyplanet.commons.core.GuiSettings;
import seedu.partyplanet.commons.core.LogsCenter;
import seedu.partyplanet.commons.util.State;
import seedu.partyplanet.commons.util.StateHistory;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.person.Person;

/**
 * Represents the in-memory model of PartyPlanet data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final AddressBook addressBook;
    private final EventBook eventBook;
    private final StateHistory stateHistory;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given addressBook, eventBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyEventBook eventBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + ", event book: " + eventBook
            + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.eventBook = new EventBook(eventBook);
        State savedState = new State("Loading from saved data", addressBook, eventBook);
        this.stateHistory = new StateHistory(savedState);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredEvents = new FilteredList<>(this.eventBook.getEventList());
    }

    public ModelManager() {
        this(new AddressBook(), new EventBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getEventBookFilePath() {
        return userPrefs.getEventBookFilePath();
    }

    @Override
    public void setEventBookFilePath(Path eventBookFilePath) {
        requireNonNull(eventBookFilePath);
        userPrefs.setEventBookFilePath(eventBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return this.addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addState(String command) {
        stateHistory.addState(new State(command, addressBook, eventBook));
    }

    @Override
    public String undo() throws IndexOutOfBoundsException {
        State previousState;
        String command = stateHistory.currentState().getCommand();
        try {
            previousState = stateHistory.previousState();
            setAddressBook(previousState.getAddressBook());
            setEventBook(previousState.getEventBook());
            return command;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public String redo() throws IndexOutOfBoundsException {
        State nextState;
        String command;
        try {
            nextState = stateHistory.nextState();
            command = nextState.getCommand();
            setAddressBook(nextState.getAddressBook());
            setEventBook(nextState.getEventBook());
            return command;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }



    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void sortPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        addressBook.sort(comparator);
    }

    @Override
    public List<Person> getPersonListCopy() {
        return new ArrayList<>(this.addressBook.getPersonList());
    }




    //=========== EventBook ================================================================================

    @Override
    public void setEventBook(ReadOnlyEventBook eventBook) {
        this.eventBook.resetData(eventBook);
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return eventBook;
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return eventBook.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        eventBook.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        eventBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        eventBook.setEvent(target, editedEvent);
    }


    //=========== Filtered Event List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedEventBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public void sortEventList(Comparator<Event> comparator) {
        requireNonNull(comparator);
        eventBook.sort(comparator);
    }

    @Override
    public List<Event> getEventListCopy() {
        return new ArrayList<>(this.eventBook.getEventList());
    }


    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && eventBook.equals(other.eventBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredEvents.equals(other.filteredEvents);
    }

}
