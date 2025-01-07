package util;


import model.cmd.CommandLineArgs;

import java.util.ArrayList;

public class CommandLineParser {

    public static CommandLineArgs parseArgs(ArrayList<String> args) {
        CommandLineArgs result = new CommandLineArgs();
        for (var arg : args) {
            switch (arg) {
                case "-f":
                    result.setFullStat(true);
                    break;
                case "-s":
                    result.setShortStat(true);
                    break;
                case "-p": {
                    result.setPrefix(args.get(args.indexOf(arg) + 1));
                    break;
                }
                case "-o": {
                    result.setOutputPath(args.get(args.indexOf(arg) + 1));
                    break;
                }
                case "-a": {
                    result.setAppendToExist(true);
                    break;
                }
                default:
                    if (arg.contains(".txt")) {
                        result.addFileToList(arg);
                        break;
                    }
            }

        }
        return result;

    }
}