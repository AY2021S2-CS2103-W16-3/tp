---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](developer/SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

{% include_relative developerGuide/architecture.md %}

The sections below give more details of each component.

{% include_relative developerGuide/architectureUi.md %}

{% include_relative developerGuide/architectureLogic.md %}

{% include_relative developerGuide/architectureModel.md %}

{% include_relative developerGuide/architectureStorage.md %}


### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

{% include_relative developerGuide/featureHelp.md %}

{% include_relative developerGuide/featureUndoRedo.md %}

{% include_relative developerGuide/featureAdd.md %}

{% include_relative developerGuide/featureEdit.md %}

{% include_relative developerGuide/featureEventDone.md %}

{% include_relative developerGuide/featureAutocomplete.md %}

{% include_relative developerGuide/featureDataArchiving.md %}

{% include_relative developerGuide/featureSortByDate.md %}

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](developer/Documentation.md)
* [Testing guide](developer/Testing.md)
* [Logging guide](developer/Logging.md)
* [Configuration guide](developer/Configuration.md)
* [DevOps guide](developer/DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

{% include_relative developerGuide/requirementsProductScope.md %}

{% include_relative developerGuide/requirementsUserStories.md %}

{% include_relative developerGuide/requirementsUseCases.md %}

{% include_relative developerGuide/requirementsNonFunctional.md %}

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

{% include_relative developerGuide/testingManual.md %}
