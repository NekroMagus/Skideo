package net.skideo.service.academy;

import net.skideo.client.AuthServiceFeignClient;
import net.skideo.dto.*;
import net.skideo.dto.projections.AcademyProfileProjection;
import net.skideo.dto.projections.IdProjection;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.enums.ServiceRole;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final UserService userService;
    private final AcademyRepository academyRepository;
    private final AuthServiceFeignClient feignClient;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAcademy(Academy academy) {
        academy.getInfo().setPassword(passwordEncoder.encode(academy.getInfo().getPassword()));
        academy.getInfo().setServiceRole(ServiceRole.ACADEMY);
        academyRepository.save(academy);
    }

    @Override
    public Academy findByLogin(String login) {
        Academy academy = academyRepository.findByInfoLogin(login).orElseThrow(
                () -> new NotFoundException("Academy not found")
        );

        return academy;
    }

    @Override
    @Transactional
    public void addPlayer(long id) {
        User user = userService.getUserById(id);
        Academy currentAcademy = getCurrentAcademy();

        List<User> listPlayers = currentAcademy.getListPlayers();
        listPlayers.add(user);

        updateListPlayers(listPlayers);

        currentAcademy.setNumberPlayers(currentAcademy.getNumberPlayers()+1);
        save(currentAcademy);
    }

    @Override
    public void updateLoginAndPassword(String token,AcademyAuthDto authDto) {
        feignClient.updateLoginAndPassword(token,new AuthDto(authDto.getLogin(),authDto.getPassword()));

        Academy dbAcademy = getCurrentAcademy();

        if(StringUtils.isNotBlank(authDto.getLogin())) {
            dbAcademy.getInfo().setLogin(authDto.getLogin());
        }
        if(StringUtils.isNotBlank(authDto.getPassword())) {
            dbAcademy.getInfo().setPassword(passwordEncoder.encode(authDto.getPassword()));
        }

        save(dbAcademy);
    }

    @Override
    public void updateProfile(AcademyProfileDto academyProfileDto) {
        Academy dbAcademy = getCurrentAcademy();

        dbAcademy.getInfo().setCity(academyProfileDto.getCity());
        dbAcademy.getInfo().setCountry(academyProfileDto.getCountry());
        dbAcademy.getInfo().setName(academyProfileDto.getTitleClub());

        academyRepository.save(dbAcademy);
    }

    @Override
    public AcademyProfileDto getProfile() {
        return academyRepository.findProfileByInfoLogin(getLoginCurrentAcademy());
    }

    @Override
    public Page<UserShortInfoDto> getMyPlayers(Pageable pageable) {
        return academyRepository.findPlayersByInfoLogin(getLoginCurrentAcademy(),pageable);
    }

    @Override
    public IdProjection getIdCurrentAcademy() {
        return academyRepository.getAcademyIdByInfoLogin(getLoginCurrentAcademy());
    }

    @Override
    public Academy getCurrentAcademy() {
        return findByLogin(getLoginCurrentAcademy());
    }

    @Override
    public String getLoginCurrentAcademy() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private void updateListPlayers(List<User> listPlayers) {
        Academy dbAcademy = getCurrentAcademy();

        dbAcademy.setListPlayers(listPlayers);

        academyRepository.save(dbAcademy);
    }

    private void save(Academy academy) {
        academyRepository.save(academy);
    }


}
