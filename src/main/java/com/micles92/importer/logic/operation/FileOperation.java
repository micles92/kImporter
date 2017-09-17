package com.micles92.importer.logic.operation;

import com.micles92.importer.model.context.FileContext;
import com.micles92.importer.model.OperationType;

/**
 * Created by mlesniak on 2017-09-14.
 */
public interface FileOperation<C extends FileContext> {

    void process(C fileContext);

    boolean validateFileContext(C context);

}
