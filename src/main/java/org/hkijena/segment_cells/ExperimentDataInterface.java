package org.hkijena.segment_cells;

import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import org.hkijena.segment_cells.caches.TIFFImageCache;

import java.nio.file.Path;

public class ExperimentDataInterface {
    private Path inputDirectory;
    private SampleDataInterface sample;

    private TIFFImageCache<UnsignedShortType> inputImage;
    private TIFFImageCache<IntType> outputLabel;
    private TIFFImageCache<IntType> outputFilteredLabel;

    public ExperimentDataInterface(Path inputDirectory, SampleDataInterface sample) {
        this.inputDirectory = inputDirectory;
        this.sample = sample;
        this.inputImage = new TIFFImageCache<>(inputDirectory.resolve("channel1.tif"), new UnsignedShortType());
        this.outputLabel = new TIFFImageCache<>(sample.getOutputDirectory().resolve("segmented").resolve(inputDirectory.getFileName().toString() + ".tif"), new IntType(), this.inputImage);
        this.outputFilteredLabel = new TIFFImageCache<>(sample.getOutputDirectory().resolve("filtered").resolve(inputDirectory.getFileName().toString() + ".tif"), new IntType(), this.inputImage);
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

    public TIFFImageCache<IntType> getOutputLabel() {
        return outputLabel;
    }

    public TIFFImageCache<IntType> getOutputFilteredLabel() {
        return outputFilteredLabel;
    }
}
