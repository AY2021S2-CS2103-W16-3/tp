package seedu.partyplanet.logic.autocomplete;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.ArgumentMultimap;
import seedu.partyplanet.logic.parser.ArgumentTokenizer;
import seedu.partyplanet.logic.parser.ParserUtil;
import seedu.partyplanet.logic.parser.Prefix;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

public class EditAutocompleteUtil {

    private static final String INDEX_NOT_SPECIFIED_OR_INVALID_MESSAGE = "Index not specified or invalid!";

    /**
     * Used to convert Set of {@code Tag}s into a String with Tag Prefixes.
     */
    private static String getTagsAsAutocompletedString(Set<Tag> tags) {
        return tags
            .stream()
            .map(t -> t.tagName)
            .sorted()
            .map(t -> "-t " + t)
            .collect(Collectors.joining(" "));
    }

    /**
     * Parses an edit command to autocomplete remark.
     * @param arguments User's input command.
     * @param model Model instance containing address book.
     * @return String of new autocompleted command.
     * @throws ParseException If the input command does not follow requirements.
     * @throws CommandException If the input command is out of bounds.
     */
    public String parseCommand(String arguments, Model model) throws ParseException, CommandException {
        requireNonNull(arguments);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_BIRTHDAY, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble().split(" ")[0]);
        } catch (ParseException pe) {
            throw new ParseException(INDEX_NOT_SPECIFIED_OR_INVALID_MESSAGE);
        }

        ObservableList<Person> filteredPersonList = model.getFilteredPersonList();
        if (index.getZeroBased() >= filteredPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = filteredPersonList.get(index.getZeroBased());

        // Create a Map of Prefix to the relevant getter method
        Map<Prefix, String> prefixMethodMap = Map.of(
            PREFIX_ADDRESS, person.getAddress().value,
            PREFIX_BIRTHDAY, person.getBirthday().value,
            PREFIX_EMAIL, person.getEmail().value,
            PREFIX_NAME, person.getName().fullName,
            PREFIX_PHONE, person.getPhone().value,
            PREFIX_REMARK, person.getRemark().value,
            PREFIX_TAG, ""
        );

        String output = "edit " + argMultimap.getPreamble();

        // Here we can assume Prefixes are sorted in the order they are entered.
        for (Prefix prefix: argMultimap.getPrefixPositionOrders()) {
            List<String> values = argMultimap.getAllValues(prefix);

            // Remove Preamble
            if (prefix.getPrefix().equals("")) {
                continue;
            }

            // If Prefix is not a relevant/correct Prefix, ignore.
            if (!prefixMethodMap.keySet().contains(prefix)) {
                output += " " + prefix;
                continue;
            }

            if (prefix.equals(PREFIX_TAG)) {
                Set<Tag> tags = new HashSet<>(person.getTags());
                Set<Tag> inputTags = new HashSet<>();

                boolean hasExtraPrefix = false;

                for (String value: values) {
                    if (value.equals("")) {
                        hasExtraPrefix = true;
                        continue;
                    }

                    try {
                        Tag tag = new Tag(value);
                        if (tags.contains(tag)) {
                            inputTags.add(tag);
                        }
                    } catch (IllegalArgumentException e) {
                        // If tag input is invalid, dont need to check if tags set contains it.
                    }

                    output += " -t " + value;
                }

                // Get tags that aren't input by User
                tags.removeAll(inputTags);

                // Only add space if there are tags to add in
                // Else add prefix only
                String tagsString = getTagsAsAutocompletedString(tags);
                if (!tagsString.equals("")) {
                    output += " " + getTagsAsAutocompletedString(tags);
                } else if (hasExtraPrefix) {
                    output += " -t";
                }
                continue;
            }

            boolean hasOutput = false;
            if (values.size() > 0) {
                for (String value: values) {
                    if (value.length() > 0) {
                        output += " " + prefix + " " + value;
                        hasOutput = true;
                    }
                }
            }

            if (!hasOutput) {
                output += " " + prefix + " " + prefixMethodMap.get(prefix);
            }

        }

        return output;
    }

}
