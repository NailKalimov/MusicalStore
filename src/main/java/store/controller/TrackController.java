package store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import store.dao.TrackDAO;
import store.entity.Track;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
public class TrackController {
    private TrackDAO trackDAO;

    public TrackController(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @GetMapping(path = "/tracks/{id}")
    public Track getById(@PathVariable(name = "id")Long id){
        return trackDAO.getEntityById(id);
    }

    @GetMapping(path = "/tracks/all")
    public List<Track> getAll(){
        return trackDAO.getAll();
    }

    @GetMapping(path = "/tracks/delete/{id}")
    public boolean deleteById(@PathVariable(name = "id")Long id){
        return trackDAO.deleteById(id);
    }

    @GetMapping(path = "/tracks/update/{id}_{trackName}")
    public void updateById(@PathVariable(name = "id")Long id, @PathVariable(name = "trackName") String trackName){
        Track track = trackDAO.getEntityById(id);
        track.setTrackName(trackName);
        trackDAO.update(track);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoResultException.class)
    @ResponseBody
    public String handleNumberFormatException(Throwable ex) {
        System.out.println("------------ex: " + ex);
        return "Error. NoResultException: No entity found for query";
    }
}
