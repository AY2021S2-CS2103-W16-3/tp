package seedu.partyplanet.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n", "--name");
    public static final Prefix PREFIX_PHONE = new Prefix("-p", "--phone");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e", "--email");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("-b", "--birthday");
    public static final Prefix PREFIX_ADDRESS = new Prefix("-a", "--address");
    public static final Prefix PREFIX_TAG = new Prefix("-t", "--tag");
    public static final Prefix PREFIX_FIND = new Prefix("-f", "--find");
    public static final Prefix PREFIX_SORT = new Prefix("-s", "--sort");

    /* Flag definitions */
    public static final Prefix FLAG_ANY = new Prefix("--any");
    public static final Prefix FLAG_APPEND = new Prefix("--append");
    public static final Prefix FLAG_DELETE = new Prefix("--delete");
    public static final Prefix FLAG_PARTIAL = new Prefix("--partial");

}
