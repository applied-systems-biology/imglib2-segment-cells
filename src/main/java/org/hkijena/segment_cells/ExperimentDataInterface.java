package org.hkijena.segment_cells;

import java.nio.file.Path;

public class ExperimentDataInterface {
    private Path inputDirectory;
    private SampleDataInterface sample;

    public ExperimentDataInterface(Path inputDirectory, SampleDataInterface sample) {
        this.inputDirectory = inputDirectory;
        this.sample = sample;
    }
}
