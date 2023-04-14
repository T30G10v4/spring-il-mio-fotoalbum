package me.matteogiovagnotti.springilmiofotoalbum.controllers;

import jakarta.validation.Valid;
import me.matteogiovagnotti.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import me.matteogiovagnotti.springilmiofotoalbum.models.AlertMessage;
import me.matteogiovagnotti.springilmiofotoalbum.models.Category;
import me.matteogiovagnotti.springilmiofotoalbum.models.Photo;
import me.matteogiovagnotti.springilmiofotoalbum.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model) {

        List<Category> categories;

        categories = categoryService.getAllCategories();

        model.addAttribute("list", categories);
        model.addAttribute("newCategory", new Category());

        return "/categories/index";

    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult, Model model) {

        boolean hasErrors = bindingResult.hasErrors();

        if (hasErrors) {

            List<Category> categories;
            categories = categoryService.getAllCategories();
            model.addAttribute("list", categories);

            return "/categories/index";


        }

        categoryService.createCategory(formCategory);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        try {
            boolean success = categoryService.deleteById(id);
            if (success) {

                redirectAttributes.addFlashAttribute("message",
                        new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "Book with id " + id + " deleted"));
            } else {

                redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Unable to delete Category with id = " + id));

            }
        } catch (CategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Category with id = " + id + " not found."));
        }

        return "redirect:/categories";
    }
}



