package org.app.projectpharmacy.controller;

import java.io.IOException;

public interface DataTransfer {
    void initialize() throws IOException;

    void receiveData(String data);
}
