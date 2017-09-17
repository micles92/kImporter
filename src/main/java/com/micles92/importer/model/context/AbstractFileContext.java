package com.micles92.importer.model.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlesniak on 2017-09-14.
 *
 */
public abstract class AbstractFileContext<Input,Output> implements FileContext<Input,Output> {


    private List<String> successFilesReplaced = new ArrayList<>();
    private List<String> failedReplaced = new ArrayList<>();


    public List<String> getSuccessFilesReplaced() {
        return successFilesReplaced;
    }

    public List<String> getFailedReplaced() {
        return failedReplaced;
    }

    //Lista plikow ktore uda�o si� przenie��
    public void addSuccessesReplacedFile(String fileName){
        successFilesReplaced.add(fileName);
    }


    public void addSuccessedReplacedFileAll(List<String> filesName){
        successFilesReplaced.addAll(filesName);
    }

    //Lista plikow ktorych nie uda�o si� przenie��
    public void addFailedReplaced(String fileName){
        failedReplaced.add(fileName);
    }

    public void addFailedReplacedAll(List<String> filesName){
        failedReplaced.addAll(filesName);
    }



}
