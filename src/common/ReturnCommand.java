package common;

import java.io.Serializable;

/**
 * @AUTHOR ONEILL PANKER
 *
 */
@SuppressWarnings("serial")
public class ReturnCommand implements Serializable {
	private final String command;
	private final Object returnValue;
	
	/**
	 * @param command
	 * @param returnValue
	 */
	public ReturnCommand(String command, Object returnValue) {
		this.command = command;
		this.returnValue = returnValue;
	}
	
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @return the returnValue
	 */
	public Object getReturnValue() {
		return returnValue;
	}
	
	
}
