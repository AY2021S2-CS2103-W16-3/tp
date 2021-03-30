package seedu.partyplanet.commons.util;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.partyplanet.commons.core.LogsCenter;


/**
 * StateHistory records and retrieves the AddressBook state history
 */
public class StateHistory {
    private ArrayList<State> states;
    private int currentStatePointer;

    private final Logger logger = LogsCenter.getLogger(StateHistory.class);

    /**
     * Initialise StateHistory with the saved AddressBook
     */
    public StateHistory(State savedState) {
        this.states = new ArrayList<State>();
        this.states.add(savedState);
        this.currentStatePointer = 0;
        logger.info("Initialised stateHistory." + " Current number of states is: " + states.size()
            + ". Currently on state: " + currentStatePointer);
    }

    /**
     * Adds a new State to the history
     * @param newState the state to be added
     */
    public void addState(State newState) {
        if (currentStatePointer != states.size() - 1) {
            clearStatesAfterCurrent();
        }
        states.add(newState);
        currentStatePointer++;
        logger.info("Added state to stateHistory." + " Current number of states is: " + states.size()
                + ". Currently on state: " + currentStatePointer);
    }

    /**
     * Retrieves the previous state
     * @return the previous state
     * @throws IndexOutOfBoundsException when there is no valid previous state
     */
    public State previousState() throws IndexOutOfBoundsException {
        currentStatePointer--;
        State previousState;
        try {
            previousState = states.get(currentStatePointer);
        } catch (IndexOutOfBoundsException e) {
            currentStatePointer++;
            throw e;
        }
        logger.info("Went to previous state." + " Current number of states is: " + states.size()
                + ". Currently on state: " + currentStatePointer);
        return previousState;
    }

    /**
     * Retrieves the current state
     * @return the current state
     */
    public State currentState() {
        return states.get(currentStatePointer);
    }

    /**
     * Retrieves the next state
     * @return the next state
     * @throws IndexOutOfBoundsException when there is no valid next state
     */
    public State nextState() {
        currentStatePointer++;
        State nextState;
        try {
            nextState = states.get(currentStatePointer);
        } catch (IndexOutOfBoundsException e) {
            currentStatePointer--;
            throw e;
        }
        logger.info("Went to previous state." + " Current number of states is: " + states.size()
                + ". Currently on state: " + currentStatePointer);
        return nextState;
    }

    private void clearStatesAfterCurrent() {
        logger.info("Clearing subsequent states");
        states.subList(currentStatePointer + 1, states.size()).clear();
    }
}
