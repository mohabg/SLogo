package slogo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import commands.CommandNode;
import gui.CommandWindow;


public class SaveSettings {

    private List<CommandNode> saveInfo;
    private CommandWindow console;

    public SaveSettings (CommandWindow console) {
    }

    public void saveInfo (List<String[]> history, String filename) {
        StringBuilder saveText = new StringBuilder();
        for (String[] command : history) {
            saveText.append(command[0]);
            saveText.append(command[1]);
        }
        writeFile(saveText.toString(), filename);
        System.out.println("Save text: " + saveText.toString());

    }

    public void writeFile (String text, String filename) {
        File file = new File("SavedFiles/" + filename + ".txt");

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = text.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        }
        catch (Exception e) {
            console.printError(e.getMessage());
        }
    }

}
