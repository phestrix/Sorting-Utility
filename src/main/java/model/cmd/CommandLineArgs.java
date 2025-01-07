package model.cmd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CommandLineArgs {
    private boolean appendToExist = false;
    private boolean shortStat = false;
    private boolean fullStat = false;
    private String prefix = "";
    private String outputPath = "";
    private ArrayList<String> inputFiles = new ArrayList<>();

    public void addFileToList(String filename){
        inputFiles.add(filename);
    }
}
