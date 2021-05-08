package com.application.customer.controller;

import com.application.customer.entity.ValidationEntity;
import com.application.customer.service.IValidationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/validation")
public class ValidationController {

    private final IValidationService customerService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/add")
    public String getForm(Model model){
        model.addAttribute("validationEntity", new ValidationEntity());
        return "validationFormPage";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("validationEntity") ValidationEntity validationEntity, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("hasError", bindingResult.hasErrors());
            return "validationFormPage";
        } else {
            customerService.createCustomer(validationEntity);
            return "validationDetailsPage";
        }
    }

}
