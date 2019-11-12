package org.hkijena.segment_cells;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SampleDataInterface {

    private Path outputDirectory;
    private Map<String, ExperimentDataInterface> experiments = new HashMap<>();
    private Map<String, Integer> quantificationResults = new HashMap<>();

    public SampleDataInterface(Path inputDirectory, Path outputDirectory) {
        this.outputDirectory = outputDirectory;
        try {
            for(Path experiment : Files.list(inputDirectory).collect(Collectors.toList())) {
                if(Files.isDirectory(experiment)) {
                    ExperimentDataInterface dataInterface = new ExperimentDataInterface(experiment, this);
                    experiments.put(experiment.getFileName().toString(), dataInterface);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, ExperimentDataInterface> getExperiments() {
        return experiments;
    }

    public void saveQuantificationResults() {
        try(FileWriter writer = new FileWriter(outputDirectory.resolve("conidia.json").toFile())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(quantificationResults);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return outputDirectory.toString();
    }

    public Path getOutputDirectory() {
        return outputDirectory;
    }

    public Map<String, Integer> getQuantificationResults() {
        return quantificationResults;
    }
}
