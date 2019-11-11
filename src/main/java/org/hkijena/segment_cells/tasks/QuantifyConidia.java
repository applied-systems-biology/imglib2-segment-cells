package org.hkijena.segment_cells.tasks;

import net.imglib2.Cursor;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import org.hkijena.segment_cells.SampleDataInterface;
import org.hkijena.segment_cells.data.GlomeruliQuantificationResult;
import org.hkijena.segment_cells.data.Glomerulus;

public class QuantifyConidia extends DAGTask {

    private double glomerulusMinRadius = 15;
    private double glomerulusMaxRadius = 65;

    public QuantifyConidia(Integer tid, SampleDataInterface sampleDataInterface) {
        super(tid, sampleDataInterface);
    }

    @Override
    public void work() {
        System.out.println("Running QuantifyConidia on " + getSampleDataInterface().toString());
    }
}
