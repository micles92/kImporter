package com.micles92.importer.logic.parser;

import com.micles92.importer.model.context.FileContext;

/**
 * Created by mlesniak on 2017-09-15.
 */
public interface ArgumentParser< C extends FileContext> {

    void parse(String[] arguments,C context);

}
