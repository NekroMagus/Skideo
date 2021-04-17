package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.RegAcademyDto;
import net.skideo.exception.AlreadyExistsException;
import net.skideo.model.Academy;
import net.skideo.model.City;
import net.skideo.model.Country;
import net.skideo.model.User;
import net.skideo.model.enums.ServiceRole;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.city.CityService;
import net.skideo.service.country.CountryService;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AcademyService academyService;
    private final UserService userService;
    private final CityService cityService;
    private final AuthServiceFeignClient feignClient;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @PostMapping("/registration")
    public ResponseEntity<OAuth2AccessToken> registration(@Valid @RequestBody RegAcademyDto regAcademyDto) {

        if(userService.isExistsByLogin(regAcademyDto.getLogin())) {
            throw new AlreadyExistsException("Academy already exists");
        }

        City city = cityService.getCity(regAcademyDto.getCity(),regAcademyDto.getCountry());

        User user = new User(regAcademyDto.getLogin(),regAcademyDto.getPassword(),city,
                             regAcademyDto.getTitle(), ServiceRole.ACADEMY);

        academyService.createAcademy(new Academy(user,regAcademyDto.getNumberPlayers()));

        ResponseEntity<OAuth2AccessToken> response = feignClient.generateToken(regAcademyDto.getLogin(),regAcademyDto.getPassword(),clientId,
                                                                               clientSecret,"password");

        return response;
    }




}