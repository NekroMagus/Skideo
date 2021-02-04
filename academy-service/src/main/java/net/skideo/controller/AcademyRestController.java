package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.model.Academy;
import net.skideo.model.Video;
import net.skideo.repository.AcademyRepository;
import net.skideo.repository.VideoRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/academy")
@RequiredArgsConstructor
public class AcademyRestController {

    private final AcademyService academyService;
    private final AcademyRepository repository;
    private final VideoRepository videoRepository;

    @GetMapping("/profile")
    public AcademyProfileDto getProfile() {
        return academyService.getProfile();
    }

    @PutMapping("/auth/data")
    public void updateLoginAndPassword(@Valid @RequestBody AuthDto authDto) {
        academyService.updateLoginAndPassword(authDto);
    }

    @PutMapping("/profile")
    public void updateProfile(@Valid @RequestBody AcademyProfileDto profileDto) {
        academyService.updateProfile(profileDto);
    }

    /* ------------- для тестов ------------------ */

    @GetMapping("/all")
    public List<Academy> all() {
        return repository.findAll();
    }

    @GetMapping("/allV")
    public List<Video> allV() {
        return videoRepository.findAll();
    }

    @GetMapping("/me")
    public Academy me() {
        return academyService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /* ------------------------------------- */

}
