package me.matteogiovagnotti.springilmiofotoalbum.controllers;

import jakarta.validation.Valid;
import me.matteogiovagnotti.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import me.matteogiovagnotti.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import me.matteogiovagnotti.springilmiofotoalbum.models.AlertMessage;
import me.matteogiovagnotti.springilmiofotoalbum.models.Photo;
import me.matteogiovagnotti.springilmiofotoalbum.services.CategoryService;
import me.matteogiovagnotti.springilmiofotoalbum.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {

        List<Photo> photos;

        if (keyword.isEmpty()) photos = photoService.getAllPhotos();
        else {
            photos = photoService.getFilteredPhotos(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", photos);
        return "/photos/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        try {
            Photo photo = photoService.getPhotoById(id);
            model.addAttribute("photo", photo);
            return "/photos/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("photo", new Photo());
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "/photos/form";

    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

        boolean hasErrors = bindingResult.hasErrors();

        if (hasErrors) {

            model.addAttribute("categoryList", categoryService.getAllCategories());
            return "/photos/form";


        }

        photoService.createPhoto(formPhoto);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        try {
            Photo photo = photoService.getPhotoById(id);
            model.addAttribute("photo", photo);
            model.addAttribute("categoryList", categoryService.getAllCategories());
            return "/photos/form";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id = " + id + " not found.");
        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categoryList", categoryService.getAllCategories());
            return "photos/form";

        }

        Photo updatedPhoto = photoService.updatePhoto(formPhoto, id);


    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        try {
            boolean success = photoService.deleteById(id);
            if (success) {

                redirectAttributes.addFlashAttribute("message",
                        new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "Photo with id " + id + " deleted"));
            } else {

                redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Unable to delete Photo with id = " + id));

            }
        } catch (CategoryNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Photo with id = " + id + " not found."));
        }

        return "redirect:/";
    }
}

    /*
    @GetMapping("/image/{photoId}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer photoId) {
        Photo photo = photoService.getAllPhotos().get(photoId);
        MediaType mediaType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(img.getContent());
    }
    */



