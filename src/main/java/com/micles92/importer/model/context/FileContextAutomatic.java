package com.micles92.importer.model.context;

/**
 * Created by mlesniak on 2017-09-16.
 */
public class FileContextAutomatic extends AbstractFileContext<String,String> {


    private String destination;
    private String input;


    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }


}
