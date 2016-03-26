// This entire file is part of my masterpiece.
// Patrick Grady

package data;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WorkspaceData {

	Map<String, String> getUserVariables();

	Set<String> getUserFunctions();

	List<TurtleData> getTurtles();

}
