package controller;

import io.DefaultFileReader;
import io.DefaultFileWriter;
import model.cmd.CommandLineArgs;
import model.type.DataType;
import model.type.FloatData;
import model.type.IntegerData;
import service.FileProcessor;
import service.StatisticsService;
import util.CommandLineParser;
import util.DirectoryUtil;

import java.util.*;

public class MainController {
    private final FileProcessor fileProcessor;
    private final StatisticsService statisticsService;

    public MainController(FileProcessor fileProcessor, StatisticsService statisticsService) {
        this.fileProcessor = fileProcessor;
        this.statisticsService = statisticsService;
    }

    public MainController() {
        this.fileProcessor = new FileProcessor(new DefaultFileReader(), new DefaultFileWriter());
        this.statisticsService = new StatisticsService();
    }

    public void execute(String[] args) {
        ArrayList<String> listOfArgs = new ArrayList<>(List.of(args));
        CommandLineArgs commandLineArgs = CommandLineParser.parseArgs(listOfArgs);
        Map<String, List<DataType<?>>> dataMap = new LinkedHashMap<>();

        for (String file : commandLineArgs.getInputFiles()) {
            var data = fileProcessor.getDataFromFile(file);
            for (Map.Entry<String, DataType<?>> entry : data.entrySet()) {
                if (dataMap.containsKey(entry.getKey())) {
                    dataMap.get(entry.getKey()).add(entry.getValue());
                } else {
                    dataMap.put(entry.getKey(), new ArrayList<>(List.of(entry.getValue())));
                }
            }
        }

        checkUp(dataMap, commandLineArgs);

        writeDataToFile(dataMap, commandLineArgs);

        if (commandLineArgs.isShortStat() && !commandLineArgs.isFullStat()) {
            printShortStat(dataMap);
        } else if (commandLineArgs.isFullStat()) {
            printFullStat(dataMap);

        }
    }

    private void writeDataToFile(Map<String, List<DataType<?>>> map, CommandLineArgs commandLineArgs) {
        for (Map.Entry<String, List<DataType<?>>> entry : map.entrySet()) {
            List<DataType<?>> data = entry.getValue();
            for (var node : data) {
                if (node instanceof IntegerData) {
                    fileProcessor.writeDataToFile(
                            commandLineArgs.getOutputPath() + commandLineArgs.getPrefix() + "integers.txt",
                            node);
                } else if (node instanceof FloatData) {
                    fileProcessor.writeDataToFile(
                            commandLineArgs.getOutputPath() + commandLineArgs.getPrefix() + "floats.txt",
                            node);
                } else {
                    fileProcessor.writeDataToFile(
                            commandLineArgs.getOutputPath() + commandLineArgs.getPrefix() + "strings.txt",
                            node);
                }
            }
        }
    }

    private void printFullStat(Map<String, List<DataType<?>>> map) {
        for (var entry : map.entrySet()) {
            if (entry.getValue().get(0) instanceof FloatData) {
                var stat = statisticsService.collectFullNumericStatistic(entry.getValue());
                System.out.println("Type: Floats" + ", count - " + stat.getCount() + ", mean - " + stat.getMean() + ", min - " + stat.getMin() + ", max - " + stat.getMax());
            } else if (entry.getValue().get(0) instanceof IntegerData) {
                var stat = statisticsService.collectFullNumericStatistic(entry.getValue());
                System.out.println("Type: Integers" + ", count - " + stat.getCount() + ", mean - " + stat.getMean() + ", min - " + stat.getMin() + ", max - " + stat.getMax());
            } else {
                var stat = statisticsService.collectFullStringStatistic(entry.getValue());
                System.out.println("Type: Strings" + ", count - " + stat.getCount() + ", min length - " + stat.getMinLength() + ", max length - " + stat.getMaxLength());
            }
        }
    }

    private void printShortStat(Map<String, List<DataType<?>>> map) {
        for (var entry : map.entrySet()) {
            if (entry.getValue().get(0) instanceof FloatData) {
                var stat = statisticsService.getShortStat(entry.getValue());
                System.out.println("Type: Floats " + ", count - " + stat);
            } else if (entry.getValue().get(0) instanceof IntegerData) {
                var stat = statisticsService.getShortStat(entry.getValue());
                System.out.println("Type: Integers " + ", count - " + stat);
            } else {
                var stat = statisticsService.getShortStat(entry.getValue());
                System.out.println("Type: Strings " + ", count - " + stat);
            }
        }
    }

    private void checkUp(Map<String, List<DataType<?>>> map, CommandLineArgs commandLineArgs) {
        if(!commandLineArgs.getOutputPath().isBlank()){
            DirectoryUtil.checkAndCreateDirectory(commandLineArgs.getOutputPath());
        }
        for (Map.Entry<String, List<DataType<?>>> entry : map.entrySet()) {
            for (DataType<?> dataType : entry.getValue()) {
                String filePath = getFilePath(commandLineArgs, dataType);
                fileProcessor.getWriter().setPath(filePath);
                fileProcessor.getWriter().cleanBefore(commandLineArgs.isAppendToExist());
            }
        }
    }

    private static String getFilePath(CommandLineArgs commandLineArgs, DataType<?> dataType) {
        String filePath;
        if (dataType instanceof IntegerData) {
            filePath = commandLineArgs.getOutputPath() + commandLineArgs.getPrefix() + "integers.txt";
        } else if (dataType instanceof FloatData) {
            filePath = commandLineArgs.getOutputPath() + commandLineArgs.getPrefix() + "floats.txt";
        } else {
            filePath = commandLineArgs.getOutputPath() + commandLineArgs.getPrefix() + "strings.txt";
        }
        return filePath;
    }
}
