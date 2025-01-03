package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommandLineArgs {
    private boolean appendToExist = false;
    private boolean shortStat = false;
    private boolean fullStat = false;
    private String prefix = "";
    private String outputPath = "";
}
