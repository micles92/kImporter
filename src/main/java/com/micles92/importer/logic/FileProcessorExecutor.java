package com.micles92.importer.logic;

import com.micles92.importer.exception.ImporterException;
import com.micles92.importer.logic.operation.FileOperation;
import com.micles92.importer.logic.operation.FileOperationFactory;
import com.micles92.importer.logic.parser.ArgumentParser;
import com.micles92.importer.logic.parser.ArgumentParserFactory;
import com.micles92.importer.model.OperationMode;
import com.micles92.importer.model.context.FileContext;
import com.micles92.importer.model.FileProcessingResult;
import com.micles92.importer.model.context.FileContextFactory;
import com.micles92.importer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by mlesniak on 2017-09-14.
 */
@Service
public final class FileProcessorExecutor {


    @Autowired
    private EmailService emailService;



    public FileProcessorExecutor() {


    }

    public FileProcessingResult execute(OperationMode mode, String[] arguments) {
        try {

            FileContext fileContext = FileContextFactory.getContext(mode);
            ArgumentParser parser = ArgumentParserFactory.getParser(mode);
            parser.parse(arguments,fileContext);

            FileOperation operation = FileOperationFactory.getFileOperation(mode);
            operation.validateFileContext(fileContext);
            operation.process(fileContext);

            emailService.sendMessageWithAttachment(fileContext);
            return FileProcessingResult.ofValid();
        } catch (ImporterException e) {
            return FileProcessingResult.ofInvalid(e.getMessage(), e);
        } catch (Exception e) {
            return FileProcessingResult.ofInvalid("Exception occured - contact with administrator", e);
        }


    }

}
