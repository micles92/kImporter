package com.micles92.importer.model.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mlesniak on 2017-09-16.
 */
public class FileContextManually extends AbstractFileContext<List<String>,String> {


    private String destination;
    private List<String> filePathsToReplace = new ArrayList<>();

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getInput() {
        if (Objects.isNull(filePathsToReplace)) {
            return new ArrayList<>();
        }
        return filePathsToReplace;
    }

    public void setInput(List<String> filesPathToReplace) {
        this.filePathsToReplace = filesPathToReplace;
    }




}
