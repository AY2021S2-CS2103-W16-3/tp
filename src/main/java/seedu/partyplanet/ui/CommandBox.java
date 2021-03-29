package seedu.partyplanet.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.partyplanet.commons.util.InputHistory;
import seedu.partyplanet.logic.commands.CommandResult;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final AutoCompleter autoCompleter;

    private final KeyCombination undo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    private final KeyCombination redo1 = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
    private final KeyCombination redo2 =
            new KeyCodeCombination(KeyCode.Z, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);

    @FXML
    private TextField commandTextField;

    private InputHistory history;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, AutoCompleter autoCompleter) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.autoCompleter = autoCompleter;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.setOnKeyPressed(e -> handleUserKey(e.getCode()));
        commandTextField.addEventHandler(KeyEvent.KEY_RELEASED, e -> UndoRedoHandler(e));
        history = new InputHistory();
    }

    /**
     * Handles key combination releases.
     * Executes undo command if CTRL + Z shortcut is used
     * Executes redo command if CTRL + SHIFT + Z or CTRL + Y shortcut is used
     */
    private void UndoRedoHandler(KeyEvent event) {
        if (undo.match(event)) {
            try {
                commandExecutor.execute("undo");
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
        } else if (redo1.match(event) || redo2.match(event)) {
            try {
                commandExecutor.execute("redo");
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
        }
    }


    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        history.add(commandText);

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    private void handleUserKey(KeyCode key) {
        switch(key) {
        case UP:
            commandTextField.setText(history.getPrevious());
            commandTextField.end();
            break;
        case DOWN:
            commandTextField.setText(history.getNext());
            commandTextField.end();
            break;
        case ESCAPE:
            commandTextField.clear();
            break;
        case TAB:
            String command = commandTextField.getText();
            try {
                String output = autoCompleter.autoComplete(command);
                commandTextField.setText(output);
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
            commandTextField.requestFocus();
            commandTextField.end();
            break;
        default:
            break;
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.partyplanet.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents a function that can parse input and return autocompletes.
     */
    @FunctionalInterface
    public interface AutoCompleter {
        /**
         * Parses the command and returns the autocompleted result.
         *
         * @see seedu.partyplanet.logic.Logic#execute(String)
         */
        String autoComplete(String commandText) throws CommandException, ParseException;
    }

}
