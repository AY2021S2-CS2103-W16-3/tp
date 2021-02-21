---
layout: page
title: User Guide
---

HippoCampus is a **desktop app for managing contacts and tasks, optimised for use via a Command Line Interface** (CLI) while still having the benefits of Graphical User Interface (GUI). If you can type fast, HippoCampus can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Double-click the file to start the app.<br>
   ![Ui](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n John Doe`.

* Items in square brackets are optional.<br>
  e.g `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[-t TAG]…​` can be used as ` ` (i.e. 0 times), `-t friend`, `-t friend -t family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -p PHONE_NUMBER`, the alternative `-p PHONE_NUMBER -n NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Adding contacts : `add`

Adds a person to the address book.

Format: `add NAME [-p PHONE_NUM] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]​`<br>
* The birthday must be in a valid date format, e.g. 13 Jan

Examples:
* `add James Ho -p 22224444 -e jamesho@example.com -a 123, Clementi Rd, 1234665 -t friend -t colleague -b 1 Jan` Adds a new person James Ho with specified details.

### Clearing all data : `clear`

Removes all contacts from the address book.

Format: `clear`

### Deleting contacts : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX [INDEX…]`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list (without sorting).
* The index must be a positive integer valid in the list.

Examples:
* `delete 3` deletes contact at 3rd index.
* `delete 3 4 5` deletes contacts at 3rd, 4th and 5th index.

### Editing contacts : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG…​ [-b BIRTHDAY]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list (not sorted). The index must be a positive integer that is a valid number in the list.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `-t` without specifying any tags after it.

Examples:
*  `edit 2 -n James Lee -e jameslee@example.com` edits the contact name to be “James Lee” and email address to be “jameslee@example.com”.
*  `edit 2 -n Betsy Crower -t` Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.

### Finding contacts : `find`

Finds persons whose names contain the given keywords and/or is associated with the given tag.

Format: `find [-n NAME] [-t TAG]`

* The search is case-insensitive. e.g `hans` will match `Hans`.
* Partial matches to names are returned, e.g. `lliam` will match `williams`.
* Only full tags will be matched.


Examples:
* `find -n Bob -t cs2103` Finds contacts where the name contains Bob and the contact contains the tag cs2103.

### Listing contacts : `list`

Shows a list of all persons in the address book.

Format: `list [-s SORT_ORDER]`
* List out all contacts by default; does not alter the index position of each contact.
* `-s` list out all contacts sorted according to `SORT_ORDER`.
* Possible values of `SORT_ORDER`:
  * `asc`: ascending lexicographical order
  * `desc`: descending lexicographical order
  * `bday`: in ascending order from Jan-01 to Dec-31

Examples:
* `list` Lists out all the contacts in HippoCampus.
* `list -s asc` Lists out all the contacts in ascending lexicographical order.

### Finding tags : `tags`

Finds all tags or tags that contain the given keywords.

Format: `tags [-f KEYWORD]`

Examples:
* `tags` lists out all tags available.
* `tags -f cs2103` lists out all tags that contain `cs2103`.

### Show help : `help`

Shows a messgae explaining a list of available commands.

Format: `help [COMMAND]`
* List all available commands.
* `[COMMAND]` a single parameter requesting help for a specific command's syntax.

Examples:
* `help` lists all available commands.
* `help list` shows the syntax and description for the `list` command.

### Leaving app : `exit`

Exits the app.

Format: `exit`

### Archiving data files `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: **What is the name of your application?**<br>
**A**: HippoCampus

**Q**: **How much does it cost?**<br>
**A**: Free!

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add NAME [-p PHONE_NUM] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]` <br> e.g., `add James Ho -p 96280000 -t friend -t colleague`
**Clear** | `clear`
**Delete** | `delete INDEX [INDEX…​]`<br> e.g., `delete 3 4 5`
**Edit** | `edit INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]`<br> e.g.,`edit 2 -n James Lee -e jameslee@example.com`<br> e.g., `edit 2 -n Betsy Crower -t`
**Find** | `find [-n NAME] [-t TAG]`<br> e.g., `find -n Bob -t cs2103`
**List** | `list [-s SORT_ORDER]`<br> e.g., `list`<br> e.g., `list -s asc`
**Find tags** | `tags [-f KEYWORD]`<br> e.g.,`tags`<br> e.g., `tags -f cs2103`
**Help** | `help [COMMAND]`<br> e.g., `help`<br> e.g.,`help list`
**Exit** | `exit`
