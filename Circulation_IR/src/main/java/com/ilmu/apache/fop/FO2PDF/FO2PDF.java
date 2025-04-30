/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.fop.apps.FOPException
 *  org.apache.fop.apps.FOUserAgent
 *  org.apache.fop.apps.Fop
 *  org.apache.fop.apps.FopFactory
 *  org.apache.fop.apps.FormattingResults
 *  org.apache.fop.apps.PageSequenceResults
 */
package com.ilmu.apache.fop.FO2PDF;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FormattingResults;
import org.apache.fop.apps.PageSequenceResults;

public class FO2PDF {
    private final FopFactory fopFactory = FopFactory.newInstance((URI)new File(".").toURI());

    private void convertFO2PDF(File fo, File pdf) throws IOException, FOPException {
        try (OutputStream out = null;){
            try {
                FOUserAgent foUserAgent = this.fopFactory.newFOUserAgent();
                System.out.println(pdf + " =testing what is this");
                out = new FileOutputStream(pdf);
                System.out.println(out + " =testing what is this out");
                out = new BufferedOutputStream(out);
                Fop fop = this.fopFactory.newFop("application/pdf", foUserAgent, out);
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer();
                StreamSource src = new StreamSource(fo);
                SAXResult res = new SAXResult(fop.getDefaultHandler());
                transformer.transform(src, res);
                FormattingResults foResults = fop.getResults();
                List pageSequences = foResults.getPageSequences();
                for (PageSequenceResults pageSequenceResults : pageSequences) {
                    System.out.println("PageSequence " + (String.valueOf(pageSequenceResults.getID()).length() > 0 ? pageSequenceResults.getID() : "<no id>") + " generated " + pageSequenceResults.getPageCount() + " pages.");
                }
                System.out.println("Generated " + foResults.getPageCount() + " pages in total.");
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
                out.close();
            }
        }
    }

    public static Boolean printPDF(String in, String out) {
        try {
            System.out.println("FOP FO2PDF\n");
            System.out.println("Preparing...");
            File fofile = new File(in);
            File pdffile = new File(out);
            System.out.println("Input: XSL-FO (" + fofile + ")");
            System.out.println("Output: PDF (" + pdffile + ")");
            System.out.println();
            System.out.println("Transforming...");
            FO2PDF app = new FO2PDF();
            app.convertFO2PDF(fofile, pdffile);
            System.out.println("Success!");
        }
        catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace(System.err);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println("FOP ExampleFO2PDF\n");
            System.out.println("Preparing...");
            File baseDir = new File(".");
            File outDir = new File(baseDir, "out");
            outDir.mkdirs();
            File fofile = new File(baseDir, "helloworld.fo");
            File pdffile = new File(outDir, "ResultFO2PDF.pdf");
            System.out.println("Input: XSL-FO (" + fofile + ")");
            System.out.println("Output: PDF (" + pdffile + ")");
            System.out.println();
            System.out.println("Transforming...");
            FO2PDF app = new FO2PDF();
            app.convertFO2PDF(fofile, pdffile);
            System.out.println("Success!");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
}
