package com.example.neo4jback.controller;

import com.example.neo4jback.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvImportController {

    private final CsvImportService csvImportService;

    @Autowired
    public CsvImportController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @PostMapping("/import-csv")
    public void importCsv(@RequestParam("file") String filePath) {
        csvImportService.importCsvToNeo4j(filePath);
    }
}
