package me.matteogiovagnotti.springilmiofotoalbum.services;

import me.matteogiovagnotti.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import me.matteogiovagnotti.springilmiofotoalbum.models.Photo;
import me.matteogiovagnotti.springilmiofotoalbum.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll(Sort.by("title"));
    }

    public Photo getPhotoById(Integer photoId) throws PhotoNotFoundException {

        Optional<Photo> result = photoRepository.findById(photoId);
        if (result.isPresent()) return result.get();
        else throw new PhotoNotFoundException(Integer.toString(photoId));
    }

    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
