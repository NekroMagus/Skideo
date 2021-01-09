package net.skideo.service.user;

import net.skideo.dto.ProfileUserDto;
import net.skideo.model.User;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<ProfileUserDto> findUserProfilesAll();

    Page<User> findAllByCountryAndRoleFootballAndHasAgentAndRolePeopleAndLeadingLegAndBirthDate(String country, RoleFootball roleFootball, boolean agent,
                                                                                                RolePeople rolePeople, LeadingLeg leadingLeg, LocalDate dateOfBirth,
                                                                                                int page,int size);
    User findById(long id);
}
