package com.micles92.importer.logic.parser;

import com.micles92.importer.model.context.FileContext;
import com.micles92.importer.model.context.FileContextManually;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mlesniak on 2017-09-15.
 */
public class ManuallyArgumentParser implements ArgumentParser<FileContextManually> {

    @Override
    public void parse(String[] args , FileContextManually context) {

        if(emptyArgumentPassed(args)){
            throw new NoArgumentPassed();
        }

        String arguments = String.join("", args).trim();

        String regularExpression = "\\[[^\\[]*\\]";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher m = pattern.matcher(arguments);

        final List<String> matches = new LinkedList<>();
        while (m.find()) {
            matches.add(m.group(0).replaceAll("\\[|\\]", ""));
        }
        //walidacja że matches.size powinno być równe 2

        if(matches.size() != 2){
            throw new InvalidArgumentPassed();
        }

        context.setInput(Arrays.asList(matches.get(0).split(";")));
        context.setDestination(matches.get(1));
    }

    private boolean emptyArgumentPassed(String[] args) {
        return args == null || args.length ==0;
    }


    public static class NoArgumentPassed extends RuntimeException{

        public NoArgumentPassed(){
            super("Nie podano argument�w");
        }

    }

    public static class InvalidArgumentPassed extends RuntimeException{

        public InvalidArgumentPassed(){
            super("Nieprawid�owe argumenty przekazane do aplikacji");
        }

    }



}
