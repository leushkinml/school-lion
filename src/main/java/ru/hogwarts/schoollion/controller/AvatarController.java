package ru.hogwarts.schoollion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoollion.model.Avatar;
import ru.hogwarts.schoollion.model.Student;
import ru.hogwarts.schoollion.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }



    @GetMapping("list-of-avatar-by-page")
   public ResponseEntity<List<Avatar>> getListOfAvatarsByPage(@RequestParam("page") Integer pageNumber,
                                                              @RequestParam("size") Integer pageSize)
            throws IOException {
        List<Avatar> avatars = avatarService.getAllAvatarsByPage(pageNumber, pageSize);

        return ResponseEntity.ok(avatars);
     }


    @GetMapping(value = "/get-all-avatars")  // Работа с ФАЙЛАМИ
    public void downloadAllAvatars(@RequestParam("page") Integer pageNumber,
                                   @RequestParam("size") Integer pageSize,
                                   HttpServletResponse response) throws IOException {
        List<Avatar> avatars = avatarService.getAllAvatarsByPage(pageNumber, pageSize);

        Path path = Path.of(avatars.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()){
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(Math.toIntExact(avatar.getFileSize()));
            is.transferTo(os);
        }
    }



    @GetMapping(value = "/{studentId}/avatar")  // Работа с ФАЙЛАМИ
    public void downloadAvatar(@PathVariable Long studentId, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(studentId);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()){
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(Math.toIntExact(avatar.getFileSize()));
            is.transferTo(os);
        }
    }
}
