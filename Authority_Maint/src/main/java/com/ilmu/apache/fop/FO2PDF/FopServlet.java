/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  net.sf.saxon.TransformerFactoryImpl
 *  org.apache.commons.io.output.ByteArrayOutputStream
 *  org.apache.fop.apps.FOPException
 *  org.apache.fop.apps.FOUserAgent
 *  org.apache.fop.apps.Fop
 *  org.apache.fop.apps.FopFactory
 *  org.apache.fop.apps.FopFactoryBuilder
 *  org.apache.fop.servlet.ServletContextURIResolver
 *  org.apache.xmlgraphics.io.Resource
 *  org.apache.xmlgraphics.io.ResourceResolver
 */
package com.ilmu.apache.fop.FO2PDF;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.fop.servlet.ServletContextURIResolver;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;

@WebServlet(value={"/print"})
public class FopServlet
extends HttpServlet {
    private static final long serialVersionUID = -908918093488215264L;
    public static final String FO_REQUEST_PARAM = "fo";
    public static final String XML_REQUEST_PARAM = "xml";
    public static final String XSLT_REQUEST_PARAM = "xslt";
    public static final String URL_REQUEST_PARAM = "url";
    public TransformerFactory transFactory;
    public FopFactory fopFactory;
    public transient URIResolver uriResolver;

    public void init() throws ServletException {
        System.out.println("wwww");
        this.uriResolver = new ServletContextURIResolver(this.getServletContext());
        TransformerFactoryImpl fact = new TransformerFactoryImpl();
        this.transFactory = TransformerFactory.newInstance();
        this.transFactory.setURIResolver(this.uriResolver);
        System.out.println("wwww1");
        ResourceResolver resolver = new ResourceResolver(){

            public OutputStream getOutputStream(URI uri) throws IOException {
                URL url = FopServlet.this.getServletContext().getResource(uri.toASCIIString());
                System.out.println("wwww2");
                return url.openConnection().getOutputStream();
            }

            public Resource getResource(URI uri) throws IOException {
                System.out.println("wwww3");
                return new Resource(FopServlet.this.getServletContext().getResourceAsStream(uri.toASCIIString()));
            }
        };
        System.out.println("wwww4");
        FopFactoryBuilder builder = new FopFactoryBuilder(new File(".").toURI(), resolver);
        this.configureFopFactory(builder);
        this.fopFactory = builder.build();
        System.out.println("wwww5");
    }

    protected void configureFopFactory(FopFactoryBuilder builder) {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String foParam = request.getParameter(FO_REQUEST_PARAM);
            String xmlParam = request.getParameter(XML_REQUEST_PARAM);
            String xsltParam = request.getParameter(XSLT_REQUEST_PARAM);
            String urlParam = request.getParameter(URL_REQUEST_PARAM);
            if (foParam != null) {
                this.renderFO(foParam, response);
            } else if (xmlParam != null && xsltParam != null) {
                this.renderXML(xmlParam, xsltParam, response);
            } else if (urlParam != null) {
                if (urlParam.contains("{}")) {
                    urlParam = urlParam.replace("{}", "&");
                }
                System.out.println(String.valueOf(urlParam) + response);
                this.renderURL(urlParam, response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Error</title></head>\n<body><h1>FopServlet Error</h1><h3>No 'fo' request param given.</body></html>");
            }
        }
        catch (Exception ex) {
            throw new ServletException((Throwable)ex);
        }
    }

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
