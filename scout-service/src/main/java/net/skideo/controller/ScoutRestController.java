package net.skideo.controller;


import net.skideo.dto.ProfileDto;
import net.skideo.dto.SearchDto;
import net.skideo.dto.UpdateProfileDto;
import net.skideo.dto.projections.ScoutProfileProjection;
import net.skideo.model.Scout;
import net.skideo.repository.ScoutRepository;
import net.skideo.service.scout.ScoutService;
import lombok.RequiredArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/scout")
@RequiredArgsConstructor
public class ScoutRestController {

    private final ScoutService scoutService;
    private final ScoutRepository scoutRepository;

    @GetMapping("/all")
    public List<Scout> all() {
        return scoutRepository.findAll();
    }

    @GetMapping("/profile")
    public ProfileDto getProfile(@RequestHeader("Authorization") String token) {
        final ScoutProfileProjection CURRENT_SCOUT = scoutService.getProfileByLogin(scoutService.getLoginCurrentScout(token));
        return scoutService.getProfile(CURRENT_SCOUT);
    }

    @PutMapping
    public void updateProfile(@RequestHeader("Authorization") String token,@Valid @RequestBody UpdateProfileDto dto) {
        scoutService.updateProfile(token,dto);
    }

    @GetMapping("/search")
    public List<SearchDto> search(@RequestParam String country, @RequestParam RoleFootball roleFootball, @RequestParam boolean agent,
                                  @RequestParam RolePeople rolePeople, @RequestParam LeadingLeg leadingLeg, @RequestParam LocalDate dateOfBirth,
                                  @RequestParam int page, @RequestParam int size) {
        return scoutService.search(country, roleFootball, agent, rolePeople, leadingLeg, dateOfBirth, page,size);
    }

    @PostMapping("/user/favorite")
    public void addUserToFavorite(@RequestHeader("Authorization") String token,@RequestParam long id) {
        final Scout CURRENT_SCOUT = scoutService.getCurrentScout(token);
        scoutService.addUserToFavorite(id,CURRENT_SCOUT);
    }
}



