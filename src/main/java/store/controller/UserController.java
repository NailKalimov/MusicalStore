//package store.controller;
//
//import store.dao.AlbumDAO;
//import store.entity.Album;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
////@RequestMapping(path = "/users")
//public class UserController {
//    private AlbumDAO albumDAO;
//
//    public UserController(AlbumDAO albumDAO) {
//        this.albumDAO = albumDAO;
//    }
//
////    @GetMapping(path = "/users")
////    public List<User> getUsers() {
////        return Collections.emptyList();
////    }
//
////    @GetMapping(path = "/users/{id}")
////    public User getUserById(@PathVariable(name = "id") Integer id) {
////        return userDao.getUserById(id);
////    }
//
//    @GetMapping(path = "/users/{id}")
//    public void getTrackById(@PathVariable(name = "id") Long id){
//        Album a = albumDAO.getEntityById(id);
//    }
//}
