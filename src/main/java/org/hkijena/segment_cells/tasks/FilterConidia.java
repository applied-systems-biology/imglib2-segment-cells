package org.hkijena.segment_cells.tasks;

import net.imglib2.Cursor;
import net.imglib2.algorithm.gauss3.Gauss3;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.type.logic.NativeBoolType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.view.Views;
import org.hkijena.segment_cells.ExperimentDataInterface;
import org.hkijena.segment_cells.Filters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FilterConidia extends DAGTask {

    private ExperimentDataInterface experimentDataInterface;
    private double minRadius = 5;
    private double maxRadius = 12;

    public FilterConidia(Integer tid, ExperimentDataInterface experimentDataInterface) {
        super(tid, experimentDataInterface.getSampleDataInterface());
        this.experimentDataInterface = experimentDataInterface;
    }

    Map<Integer, Long> getHistogram(Img<IntType> img) {
        Map<Integer, Long> histogram = new HashMap<>();
        Cursor<IntType> cursor = img.cursor();

        while(cursor.hasNext()) {
            cursor.fwd();
            histogram.put(cursor.get().getInteger(), histogram.getOrDefault(cursor.get().getInteger(), 0L) + 1);
        }

        return histogram;
    }

    @Override
    public void work() {
        System.out.println("Running FilterConidia on " + experimentDataInterface.toString());

        Img<IntType> labels = experimentDataInterface.getOutputLabel().getOrCreate();
        Map<Integer, Long> histogram = getHistogram(labels);
        Set<Integer> invalid = new HashSet<>();
        final double minArea = Math.PI * minRadius * minRadius;
        final double maxArea = Math.PI * maxRadius * maxRadius;

        for(Map.Entry<Integer, Long> kv : histogram.entrySet()) {
            if(kv.getValue() < minArea || kv.getValue() > maxArea)
                invalid.add(kv.getKey());
        }

        // Apply changes
        {
            Cursor<IntType> cursor = labels.cursor();

            while(cursor.hasNext()) {
                cursor.fwd();
                if(invalid.contains(cursor.get().get()))
                    cursor.get().set(0);
            }
        }

        experimentDataInterface.getOutputFilteredLabel().set(labels);
    }
}
