package com.epam.ht.command;

import javax.servlet.http.HttpServletRequest;


public final class CommandCreator {
	private static final String ATTR_NAME_COMMAND = "command";

	private CommandCreator() {
	}

	public static Command createCommand(HttpServletRequest request) {
		CommandEnum commandEnum = getCommandEnum((String) request
				.getParameter(ATTR_NAME_COMMAND));
		switch (commandEnum) {
		case VIEW_PAGE:
			return new ViewPageCommand();
		case SET_NUMB_EMPLOYEES_PER_PAGE:
			return new SetNEmplPerPageCommand();
		default:
			return new DefaultCommand();
		}
	}

	private static CommandEnum getCommandEnum(String command) {
		if (command == null) {
			return CommandEnum.DEFAULT_COMMAND;
		} else if (isWrongCommandName(command)) {
			return CommandEnum.DEFAULT_COMMAND;
		} else {
			return CommandEnum.valueOf(command);
		}
	}

	private static boolean isWrongCommandName(String command) {
		CommandEnum[] realCommandNames = CommandEnum.values();
		for (CommandEnum realCommandName : realCommandNames) {
			if (realCommandName.toString().equals(command)) {
				return false;
			}
		}
		return true;
	}
}
