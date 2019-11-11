package org.hkijena.segment_cells;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import org.apache.commons.cli.Option;
import org.hkijena.segment_cells.caches.TIFFPlanesImageCache;
import org.hkijena.segment_cells.data.GlomeruliQuantificationResult;
import org.hkijena.segment_cells.data.TissueQuantificationResult;

import java.io.FileWriter;
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
}
