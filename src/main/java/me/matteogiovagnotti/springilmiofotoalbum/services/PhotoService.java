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

    public Photo createPhoto(Photo formPhoto) {

        Photo photoToPersist = new Photo();
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setImage(formPhoto.getImage());
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setCategories(formPhoto.getCategories());
        photoToPersist.setVisible(formPhoto.getVisible());
        photoToPersist.setUrl(formPhoto.getUrl());

        return photoRepository.save(photoToPersist);
    }

    public Photo updatePhoto(Photo formPhoto, Integer id) throws PhotoNotFoundException {

        Photo photoToUpdate = getPhotoById(id);
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setImage(formPhoto.getImage());
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setCategories(formPhoto.getCategories());
        photoToUpdate.setVisible(formPhoto.getVisible());
        photoToUpdate.setUrl(formPhoto.getUrl());

        return photoRepository.save(photoToUpdate);
    }

    public boolean deleteById(Integer id) throws PhotoNotFoundException {
        photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(Integer.toString(id)));
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
