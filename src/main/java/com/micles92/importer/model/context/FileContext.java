package com.micles92.importer.model.context;

import java.util.List;

/**
 * Created by mlesniak on 2017-09-14.
 *
 */
public interface FileContext<Input,Output> {


    Output getDestination();

    Input getInput();

    void setInput(Input input);

    void setDestination(Output output);




    void addSuccessesReplacedFile(String fileName);

    void addSuccessedReplacedFileAll(List<String> filesName);

    void addFailedReplaced(String fileName);

    void addFailedReplacedAll(List<String> filesName);

    List<String> getSuccessFilesReplaced();

    List<String> getFailedReplaced();

}

