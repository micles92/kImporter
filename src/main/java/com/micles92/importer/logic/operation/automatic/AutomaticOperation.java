package com.micles92.importer.logic.operation.automatic;

import com.micles92.importer.logic.operation.FileOperation;
import com.micles92.importer.model.context.FileContextAutomatic;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.ExportException;

/**
 * Created by mlesniak on 2017-09-15.
 */
public class AutomaticOperation implements FileOperation<FileContextAutomatic> {


    @Override
    public void process(FileContextAutomatic fileContext) {


        String input = fileContext.getInput();
        File folder = new File(input);
        File destination = new File(fileContext.getDestination());


        for (final File fileEntry : folder.listFiles()) {

            try {

                if (fileEntry.isFile()) {
                    FileUtils.moveFileToDirectory(fileEntry, destination, false);


                } else {
                    //Katalogu nie kopiujemy, tylko wy?wietlamy jego nazwe
                    System.out.println("Katalog nie zostanie skopoiowany: " + fileEntry.getName());
                }
                fileContext.addSuccessesReplacedFile(fileEntry.getName());

            }catch(Exception ex){
                ex.printStackTrace();
                fileContext.addFailedReplaced(fileEntry.getName());
                System.out.println("Wyst?pi? b??d podczas przenoszenia pliku " + fileEntry.getName());
                continue;

            }


        }


    }

    @Override
    public boolean validateFileContext(FileContextAutomatic context) {
        String input = context.getInput();
        File inputDirectory = new File(input);

        if (!inputDirectory.exists()) {
            throw new SourceNotFound();
        }

        if (!inputDirectory.isDirectory()) {
            throw new SourceNotDirectory();
        }

        if(inputDirectory.listFiles().length == 0){
            throw new EmptySourceFolder();
        }


        String destination = input.replaceFirst("\\\\[^/]*", "\\\\output");
        Path path = Paths.get(destination);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
                throw new CannotCreateDestinationDirectory();
            }
        }


        context.setDestination(destination);

        return true;
    }




    public static class EmptySourceFolder extends RuntimeException {

        EmptySourceFolder() {
            super("Katalog zr?d?owy jest pusty");
        }

    }


    public static class CannotCreateDestinationDirectory extends RuntimeException {

        CannotCreateDestinationDirectory() {
            super("Nie mozna utowrzy? katalogu docelowego");
        }

    }


    public static class SourceNotFound extends RuntimeException {

        SourceNotFound() {
            super("Katalog nie istnieje");
        }

    }

    public static class SourceNotDirectory extends RuntimeException {

        SourceNotDirectory() {
            super("Scie?ka wej?ciowa nie jest katalogiem");
        }

    }


}
