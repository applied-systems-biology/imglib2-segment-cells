package org.hkijena.segment_cells;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleDataInterface {

    private Path outputDirectory;
    private Map<String, ExperimentDataInterface> experiments = new HashMap<>();

    public SampleDataInterface(Path inputDirectory, Path outputDirectory) {
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

    }

    @Override
    public String toString() {
        return outputDirectory.toString();
    }

    public Path getOutputDirectory() {
        return outputDirectory;
    }
}
