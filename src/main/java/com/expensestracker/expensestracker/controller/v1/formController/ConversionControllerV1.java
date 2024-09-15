package com.expensestracker.expensestracker.controller.v1.formController;

import com.expensestracker.expensestracker.service.Impl.ExampleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConversionControllerV1 {


    @Autowired
    ExampleServiceImpl exampleService;

    @GetMapping("/convert")
    public String showForm(Model model) {
        model.addAttribute("conversionRequest", new ConversionRequest());
        return "conversion";
    }

    @PostMapping("/convert")
    public String convert(@ModelAttribute ConversionRequest body, Model model){

        if(body.getSourceCurrency().length() != 3 || body.getTargetCurrency().length() != 3 ){
            model.addAttribute("errorResult", "Currency code must be 3 digits");
            return "conversion";
        }
        double result = 0;
        try{
            result = body.getAmount() * 1.2;
        } catch (Exception e) {
            model.addAttribute("errorResult", e.getMessage());
            return "conversion";
        }

        model.addAttribute("conversionResult", result);

        model.addAttribute("conversionCurrency", body.getTargetCurrency());
        return "conversion";
    }

}
