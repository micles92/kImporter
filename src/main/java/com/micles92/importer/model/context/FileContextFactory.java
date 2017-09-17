package com.micles92.importer.model.context;

import com.micles92.importer.model.OperationMode;

/**
 * Created by mlesniak on 2017-09-14.
 */
public class FileContextFactory {


    public static FileContext getContext(OperationMode mode) {
        switch (mode) {

            case MANUALLY:
                return new FileContextManually();
            case AUTOMATICALLY:
                return new FileContextAutomatic();
        }

        throw new RuntimeException("Nie rozpoznawalny tryb");

    }

}

