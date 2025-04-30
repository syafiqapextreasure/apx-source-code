/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.io.output.ByteArrayOutputStream
 *  org.apache.fop.apps.FOPException
 *  org.apache.fop.apps.FOUserAgent
 *  org.apache.fop.apps.Fop
 *  org.apache.fop.apps.FopFactory
 *  org.apache.fop.apps.FopFactoryBuilder
 */
package com.ilmu.apache.fop.FO2PDF;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;

public class FOPMaintain {
    protected static final String FO_REQUEST_PARAM = "fo";
    protected static final String XML_REQUEST_PARAM = "xml";
    protected static final String XSLT_REQUEST_PARAM = "xslt";
    protected static final String URL_REQUEST_PARAM = "url";
    protected TransformerFactory transFactory;
    protected FopFactory fopFactory;
    protected transient URIResolver uriResolver;

    protected Source convertString2Source(String param) {
        Source src;
        try {
            src = this.uriResolver.resolve(param, null);
        }
        catch (TransformerException e) {
            src = null;
        }
        if (src == null) {
            src = new StreamSource(new File(param));
        }
        return src;
    }

    private void sendPDF(byte[] content, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/pdf");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    protected void configureFopFactory(FopFactoryBuilder builder) {
    }

    protected void renderFO(String fo, HttpServletResponse response) throws FOPException, TransformerException, IOException {
        Source foSrc = this.convertString2Source(fo);
        Transformer transformer = this.transFactory.newTransformer();
        transformer.setURIResolver(this.uriResolver);
        this.render(foSrc, transformer, response);
    }

    protected void renderURL(String url, HttpServletResponse response) throws FOPException, TransformerException, IOException {
        StreamSource foSrc = new StreamSource(url);
        Transformer transformer = this.transFactory.newTransformer();
        transformer.setURIResolver(this.uriResolver);
        this.render(foSrc, transformer, response);
    }

    protected void renderXML(String xml, String xslt, HttpServletResponse response) throws FOPException, TransformerException, IOException {
        Source xmlSrc = this.convertString2Source(xml);
        Source xsltSrc = this.convertString2Source(xslt);
        Transformer transformer = this.transFactory.newTransformer(xsltSrc);
        transformer.setURIResolver(this.uriResolver);
        this.render(xmlSrc, transformer, response);
    }

    protected void render(Source src, Transformer transformer, HttpServletResponse response) throws FOPException, TransformerException, IOException {
        FOUserAgent foUserAgent = this.getFOUserAgent();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Fop fop = this.fopFactory.newFop("application/pdf", foUserAgent, (OutputStream)out);
        SAXResult res = new SAXResult(fop.getDefaultHandler());
        transformer.transform(src, res);
        this.sendPDF(out.toByteArray(), response);
    }

    protected FOUserAgent getFOUserAgent() {
        FOUserAgent userAgent = this.fopFactory.newFOUserAgent();
        return userAgent;
    }
}
