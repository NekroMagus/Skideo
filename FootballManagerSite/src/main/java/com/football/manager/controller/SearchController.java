package com.football.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for search page.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping
    public String getSearch() {
        return "search";
    }
}