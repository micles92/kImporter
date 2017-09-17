package com.micles92.importer.logic.operation;

import com.micles92.importer.logic.operation.automatic.AutomaticOperation;
import com.micles92.importer.logic.operation.manually.ManuallyOperation;
import com.micles92.importer.model.OperationMode;

/**
 * Created by mlesniak on 2017-09-14.
 */
public class FileOperationFactory {

    public static FileOperation getFileOperation(OperationMode mode) {

        switch (mode) {

            case MANUALLY:
                return new ManuallyOperation();
            case AUTOMATICALLY:
                return new AutomaticOperation();
        }

        throw new RuntimeException("Nie rozpoznano operacji");
    }


}
