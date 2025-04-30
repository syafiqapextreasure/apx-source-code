/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.servlet.ModelAndView
 *  org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
 */
package com.kmlink.ilmu.colorSpineManagement.controller;

import com.kmlink.ilmu.colorSpineManagement.config.WebMvcConfig;
import com.kmlink.ilmu.colorSpineManagement.dao.ListDao;
import com.kmlink.ilmu.colorSpineManagement.model.Records;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Controller
public class ControllerSpineColorManagement
extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Autowired
    private ListDao listDao;

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @RequestMapping(value={"/colorSpineManagementHome"}, method={RequestMethod.GET})
    public String colorSpineManagementHome(ModelAndView model) {
        model.setViewName("/module/colorSpineManagement/main");
        return "model";
    }

    @RequestMapping(value={"/colourSequence"})
    public ModelAndView readColourSequence(ModelAndView model) throws IOException {
        List<Records> colourSequence = this.listDao.colourSequence();
        for (Records records : colourSequence) {
        }
        model.addObject("colourSequence", colourSequence);
        model.setViewName("/module/colorSpineManagement/colourSequence");
        return model;
    }

    @RequestMapping(value={"/location"})
    public ModelAndView readLocation(ModelAndView model) throws IOException {
        List<Records> listLocation = this.listDao.locationList();
        model.addObject("listLocation", listLocation);
        model.setViewName("/module/colorSpineManagement/location");
        return model;
    }

    @RequestMapping(value={"/itemclass"})
    public ModelAndView readItemClass(ModelAndView model) throws IOException {
        List<Records> listItemClass = this.listDao.itemClassList();
        for (Records records : listItemClass) {
        }
        model.addObject("listItemClass", listItemClass);
        model.setViewName("/module/colorSpineManagement/itemclass");
        return model;
    }

    @RequestMapping(value={"/read"})
    public ModelAndView readRecord(ModelAndView model) throws IOException {
        List<Records> listLocation = this.listDao.locationList();
        model.addObject("listLocation", listLocation);
        model.setViewName("/module/colorSpineManagement/read");
        return model;
    }

    @RequestMapping(value={"/update/{colourSequence}"})
    public ModelAndView findRecordById(ModelAndView model, @PathVariable(value="colourSequence") int colourSequence) throws IOException {
        List<Records> listColour = this.listDao.findRecordById(colourSequence);
        model.addObject("listColour", listColour);
        model.setViewName("/module/colorSpineManagement/update");
        return model;
    }

    @RequestMapping(value={"/update"}, method={RequestMethod.POST})
    public ModelAndView updateRecord(@RequestParam(value="id") int id, @RequestParam(value="newColourCode") String newColourCode, ModelAndView mv) {
        Records colour = new Records();
        colour.setColourSequence(id);
        colour.setColourCode(newColourCode);
        int counter = this.listDao.update(colour);
        if (counter > 0) {
            mv.addObject("msg", (Object)("Records updated for Colour Sequence: " + colour.getColourSequence()));
        } else {
            mv.addObject("msg", (Object)"Sorry, an error occured. Please try again");
        }
        mv.setViewName("/module/colorSpineManagement/update");
        return mv;
    }

    @RequestMapping(value={"/delete/{colourSequence}"})
    public ModelAndView deleteRecordById(ModelAndView mv, @PathVariable(value="colourSequence") int colourSequence) throws IOException {
        int counter = this.listDao.delete(colourSequence);
        if (counter > 0) {
            mv.addObject("msg", (Object)("Records deleted for colour sequence: " + colourSequence));
        } else {
            mv.addObject("msg", (Object)"Sorry, an error occured. Please try again");
        }
        mv.setViewName("/module/colorSpineManagement/delete");
        return mv;
    }

    @RequestMapping(value={"/create"})
    public ModelAndView createRecord(ModelAndView mv) {
        mv.setViewName("/module/colorSpineManagement/create");
        return mv;
    }

    @RequestMapping(value={"/addColor"})
    public ModelAndView createRecord(@RequestParam(value="colseq") int colseq, @RequestParam(value="colcode") String colcode, ModelAndView mv) {
        Records colour = new Records();
        colour.setColourSequence(colseq);
        colour.setColourCode(colcode);
        int counter = this.listDao.create(colour);
        if (counter > 0) {
            mv.addObject("msg", (Object)("New colour successfully added: " + colour.getColourSequence()));
        } else {
            mv.addObject("msg", (Object)"Sorry, an error occured. Please try again");
        }
        mv.setViewName("/module/colorSpineManagement/create");
        return mv;
    }
}
