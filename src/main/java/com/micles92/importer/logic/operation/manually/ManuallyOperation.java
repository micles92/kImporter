package com.micles92.importer.logic.operation.manually;

import com.micles92.importer.exception.ImporterException;
import com.micles92.importer.logic.operation.FileOperation;
import com.micles92.importer.model.context.FileContextManually;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by mlesniak on 2017-09-14.
 */
public class ManuallyOperation implements FileOperation<FileContextManually> {

    @Override
    public void process(FileContextManually fileContext) {
        File destination = new File(fileContext.getDestination());

        for (String fileToReplace : fileContext.getInput()) {
            try {

                File source = new File(fileToReplace);
                FileUtils.moveFileToDirectory(source, destination, false);
                fileContext.addSuccessesReplacedFile(fileToReplace);

            } catch (FileExistsException e) {
            } catch (IOException e) {
                e.printStackTrace();
                fileContext.addFailedReplaced(fileToReplace);
                System.out.println("Wyst�pi� b��d podczas przenoszenia pliku " + fileToReplace);
                continue;

            }


        }

    }

    @Override
    public boolean validateFileContext(FileContextManually fileContext) {
        ///Pobieramy wszystkie pliki z wiersza polecen do przeniesienia
        for (String path : fileContext.getInput()) {
            File file = new File(path);

            if(!file.exists()){
                throw new ImporterException("Plik nie istnieje");
            }

            if(!file.isFile()){
                throw new ImporterException("Podana scie�ka nie jest plikiem");
            }

        }




        File destination = new File(fileContext.getDestination());


        if (fileContext.getInput().isEmpty()) {
            throw new FileToExportNotFound();
        }

        if (!destination.exists()) {
            throw new DestinationNotFound();
        }

        if (!destination.isDirectory()) {
            throw new DestinationIsNotDirectory();
        }


        return true;
    }

    public static class FileToExportNotFound extends RuntimeException {
        FileToExportNotFound() {
            super("Brak plik�w do przeniesienia");
        }
    }


    public static class DestinationNotFound extends RuntimeException {
        DestinationNotFound() {
            super("Nie odnaleziono katalogu docelowego");
        }
    }

    public static class DestinationIsNotDirectory extends RuntimeException {
        DestinationIsNotDirectory() {
            super("Katalog docelowy jest plikiem");
        }
    }

}
