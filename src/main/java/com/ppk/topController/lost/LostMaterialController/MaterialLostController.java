package com.ppk.topController.lost.LostMaterialController;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ppk.lost.material.model.CirBahanRosakHilangtblPinjaman;
import com.ppk.topController.lost.LostMaterial.service.CirBahanRosakHilangService;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MaterialLostController {

    @Autowired
    private CirBahanRosakHilangService service;
    
    @GetMapping("/GetLoadtblPinjaman")
    public ResponseEntity<List<CirBahanRosakHilangtblPinjaman>> getLoadTblPinjaman(@RequestParam String idlogin) throws SQLException, InterruptedException {
    	System.out.println("idlogin is"+idlogin);  
    	List<CirBahanRosakHilangtblPinjaman> list = service.getBahanRosakHilangByPatronId(idlogin);
       System.out.println("list is final response "+new Gson().toJson(list));
    	return ResponseEntity.ok(list);   
    }
}