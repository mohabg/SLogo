package observers;

import java.util.Map;
import java.util.Set;


public interface WorkspaceData extends Data {

    public Map<String, String> getUserVariables ();

    public Set<String> getUserFunctions ();

}
