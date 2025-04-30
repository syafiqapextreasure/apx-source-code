/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.xmlgraphics.io.Resource
 *  org.apache.xmlgraphics.io.ResourceResolver
 */
package com.ilmu.apache.fop.FO2PDF;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;

class FopServlet$1
implements ResourceResolver {
    FopServlet$1() {
    }

    public OutputStream getOutputStream(URI uri) throws IOException {
        URL url = FopServlet.this.getServletContext().getResource(uri.toASCIIString());
        System.out.println("wwww2");
        return url.openConnection().getOutputStream();
    }

    public Resource getResource(URI uri) throws IOException {
        System.out.println("wwww3");
        return new Resource(FopServlet.this.getServletContext().getResourceAsStream(uri.toASCIIString()));
    }
}
