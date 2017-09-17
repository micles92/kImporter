package com.micles92.importer.logic.parser;

import com.micles92.importer.model.context.FileContextAutomatic;

/**
 * Created by mlesniak on 2017-09-15.
 */
public class AutomaticArgumentParser implements ArgumentParser<FileContextAutomatic> {


    @Override
    public void parse(String[] args, FileContextAutomatic context) {
        if(emptyArgumentPassed(args)){
            throw new NoArgumentPassed();
        }

        String arguments = args[0];
        context.setInput(arguments);
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
