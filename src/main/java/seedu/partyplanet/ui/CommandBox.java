package seedu.partyplanet.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
        commandTextField.setOnKeyPressed(e -> handleUserKey(e));
        history = new InputHistory();
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


    private void handleUserKey(KeyEvent event) {

        switch(event.getCode()) {
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
        case Z:
            try {
                if (event.isShiftDown() && event.isShortcutDown()) {
                    commandTextField.clear();
                    commandExecutor.execute("redo");
                } else if (event.isShortcutDown()) {
                    commandTextField.clear();
                    commandExecutor.execute("undo");
                }
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
            break;
        case Y:
            try {
                if (event.isShortcutDown()) {
                    commandTextField.clear();
                    commandExecutor.execute("redo");
                }
            } catch (CommandException | ParseException e) {
                setStyleToIndicateCommandFailure();
            }
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
