package com.jkprojects.jwtuauth.controller;
import com.jkprojects.jwtuauth.model.Game;
import com.jkprojects.jwtuauth.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class MainController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping(path = "/private/allGames")
    public @ResponseBody Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

//    @GetMapping(path = "/private/googleaccount/info")
//    public @ResponseBody String googleAccountInfo() {
//
//    }

    @GetMapping(path="/handshake")
    public @ResponseBody String handshake(@RequestParam(value = "name", defaultValue = "to my first spring App") String name) {
        return String.format("Hello %s", name);
    }

}
