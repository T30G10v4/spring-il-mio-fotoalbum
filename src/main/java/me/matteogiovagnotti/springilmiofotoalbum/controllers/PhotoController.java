package me.matteogiovagnotti.springilmiofotoalbum.controllers;

import me.matteogiovagnotti.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import me.matteogiovagnotti.springilmiofotoalbum.models.Photo;
import me.matteogiovagnotti.springilmiofotoalbum.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PhotoController {

    @Autowired
    PhotoService photoService;

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
        return "/photos/form";

    }

    /*
    @GetMapping("/image/{photoId}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer photoId) {
        Photo photo = photoService.getAllPhotos().get(photoId);
        MediaType mediaType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(img.getContent());
    }
    */


}
