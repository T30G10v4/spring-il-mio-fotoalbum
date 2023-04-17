package me.matteogiovagnotti.springilmiofotoalbum.api;

import me.matteogiovagnotti.springilmiofotoalbum.models.Photo;
import me.matteogiovagnotti.springilmiofotoalbum.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotosRestController {

    @Autowired
    PhotoService photoService;


    @GetMapping
    public List<Photo> list() {

        return photoService.getAllPhotos();
    }

}
