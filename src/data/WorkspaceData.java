package data;

import java.util.Map;
import java.util.Set;


public interface WorkspaceData {

    public Map<String, String> getUserVariables ();

    public Set<String> getUserFunctions ();

}
