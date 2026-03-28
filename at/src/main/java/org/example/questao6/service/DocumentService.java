package org.example.questao6.service;

import org.example.questao6.document.Document;

import java.util.ServiceLoader;

public class DocumentService {
    public void printAll() {
        ServiceLoader<Document> loader =
                ServiceLoader.load(Document.class);

        for (Document document : loader) {
            document.print();
        }
    }
}