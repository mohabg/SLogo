package slogo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import commands.CommandNode;
import gui.CommandWindow;


public class SaveSettings {

    private List<CommandNode> saveInfo;
    private CommandWindow console;

    public SaveSettings (CommandWindow console) {
    }

    public void saveInfo (List<CommandNode> history) {
        saveInfo = history;
        StringBuilder saveText = new StringBuilder();
        for (CommandNode command : history) {
            String name = command.toString();
            int value = (int) command.getValue();
            if (!name.equals("Constant")) {
                saveText.append(name + " ");
                System.out.println("command");
            }
            else {
                saveText.append(value + " ");
                System.out.println("constant value");
            }
        }
        writeFile(saveText.toString());
        System.out.println("Save text: " + saveText.toString());

    }

    public void writeFile (String text) {
        File file = new File("SavedFiles/test.txt");

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
