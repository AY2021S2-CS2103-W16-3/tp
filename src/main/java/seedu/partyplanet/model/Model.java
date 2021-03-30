package seedu.partyplanet.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.GuiSettings;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in PartyPlanet.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in PartyPlanet.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in PartyPlanet.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in PartyPlanet.
     * The person identity of {@code editedPerson} must not be the same as another existing person in PartyPlanet.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns a copy of the person list */
    List<Person> getPersonListCopy();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Sorts the filtered person list to be sorted by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortPersonList(Comparator<Person> comparator);

    /**
     * Returns the user prefs' event book file path.
     */
    Path getEventBookFilePath();

    /**
     * Sets the user prefs' event book file path.
     */
    void setEventBookFilePath(Path eventBookFilePath);

    /**
     * Replaces event book data with the data in {@code eventbook}.
     */
    void setEventBook(ReadOnlyEventBook eventBook);

    /** Returns the EventBook */
    ReadOnlyEventBook getEventBook();

    /**
     * Returns true if a event with the same identity as {@code event} exists in PartyPlanet.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in PartyPlanet.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in PartyPlanet.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in PartyPlanet.
     * The event name of {@code editedEvent} must not be the same as another existing event in PartyPlanet.
     */
    void setEvent(Event target, Event editedEvent);


    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);

    /**
     * Sorts the filtered event list to be sorted by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortEventList(Comparator<Event> comparator);

    /** Returns a copy of the person list */
    List<Event> getEventListCopy();

    /** Adds the current state to the StateHistory
     * @param command the command leading to this state.
     */
    void addState(String command);

    /**
     * Undoes the last change made to the address or event book.
     * @return information about the command being undone.
     */
    String undo();

    /**
     * Redoes the last undid change made to the address or event book.
     * @return information about the command being redone.
     */
    String redo();
}
