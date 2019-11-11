package org.hkijena.segment_cells;

import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import org.hkijena.segment_cells.caches.TIFFImageCache;

import java.nio.file.Path;

public class ExperimentDataInterface {
    private Path inputDirectory;
    private SampleDataInterface sample;

    private TIFFImageCache<UnsignedShortType> inputImage;
    private TIFFImageCache<UnsignedIntType> outputMask;

    public ExperimentDataInterface(Path inputDirectory, SampleDataInterface sample) {
        this.inputDirectory = inputDirectory;
        this.sample = sample;
        this.inputImage = new TIFFImageCache<>(inputDirectory.resolve("channel1.tif"), new UnsignedShortType());
        this.outputMask = new TIFFImageCache<>(sample.getOutputDirectory().resolve(inputDirectory.getFileName().toString() + ".tif"), new UnsignedIntType());
    }

    public SampleDataInterface getSampleDataInterface() {
        return sample;
    }

    @Override
    public String toString() {
        return inputDirectory.getFileName().toString();
    }

    public TIFFImageCache<UnsignedShortType> getInputImage() {
        return inputImage;
    }

    public TIFFImageCache<UnsignedIntType> getOutputMask() {
        return outputMask;
    }
}
