package seedu.partyplanet.logic.commands;

import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
