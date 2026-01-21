package com.library.library_system;
import com.library.library_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to the library system";
    }
}
