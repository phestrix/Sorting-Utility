package util;


import exception.InvalidInputException;
import model.CommandLineArgs;

import java.util.ArrayList;

public class CommandLineParser {

    public static CommandLineArgs parseArgs(ArrayList<String> args) {
        CommandLineArgs result = new CommandLineArgs();
        for (var arg : args) {
            switch (arg) {
                case "-f":
                    result.setFullStat(true);
                case "-s":
                    result.setShortStat(true);
                case "-p": {
                    result.setPrefix(args.get(args.indexOf(arg) + 1));
                }
                case "-o": {
                    result.setOutputPath(args.get(args.indexOf(arg) + 1));
                }
                case "-a": {
                    result.setAppendToExist(true);
                }
                default:
                    throw new InvalidInputException("unexpected argument");
            }

        }
        return result;

    }
}