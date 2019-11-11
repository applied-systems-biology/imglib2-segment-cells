package org.hkijena.segment_cells.tasks;

import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.algorithm.gauss3.Gauss3;
import net.imglib2.algorithm.labeling.ConnectedComponents;
import net.imglib2.algorithm.morphology.Dilation;
import net.imglib2.algorithm.morphology.Erosion;
import net.imglib2.algorithm.neighborhood.HyperSphereShape;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.interpolation.randomaccess.NLinearInterpolatorFactory;
import net.imglib2.interpolation.randomaccess.NearestNeighborInterpolatorFactory;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.view.Views;
import org.hkijena.segment_cells.ExperimentDataInterface;
import org.hkijena.segment_cells.SampleDataInterface;
import org.hkijena.segment_cells.Filters;

import java.util.*;

public class SegmentConidia extends DAGTask {

    private ExperimentDataInterface experimentDataInterface;

    public SegmentConidia(Integer tid, ExperimentDataInterface experimentDataInterface) {
        super(tid, experimentDataInterface.getSampleDataInterface());
        this.experimentDataInterface = experimentDataInterface;
    }

    @Override
    public void work() {
        System.out.println("Running SegmentConidia on " + experimentDataInterface.toString());
//        final Img<UnsignedByteType> importedImage = getSampleDataInterface().getInputData().getOrCreatePlane(planeZIndex);
//
//        Img<FloatType> img = ImageJFunctions.convertFloat(ImageJFunctions.wrap(importedImage, "img"));
    }
}
