package com.micles92.importer.model;

/**
 * Created by mlesniak on 2017-09-15.
 */
public enum OperationMode {

    MANUALLY(1), AUTOMATICALLY(2);

    private int mode;

    OperationMode(int pMode) {
        mode = pMode;
    }

    public int getMode() {
        return mode;
    }


    public static OperationMode getOperationMode(int pMode) {
        for (OperationMode mode : OperationMode.values()) {

            if (mode.getMode() == pMode) {
                return mode;
            }

        }
        throw new RuntimeException("Nieropoznawalny tryb operacji");

    }


}
