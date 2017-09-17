package com.micles92.importer.logic.parser;

import com.micles92.importer.model.OperationMode;

/**
 * Created by mlesniak on 2017-09-15.
 */
public class ArgumentParserFactory {

    public static ArgumentParser getParser(OperationMode mode){

        switch (mode){

            case MANUALLY:
                return new ManuallyArgumentParser();
            case AUTOMATICALLY:
                return new AutomaticArgumentParser();
        }

        throw new RuntimeException("Nie rozpoznowalny tryb operacji");

    }

}
