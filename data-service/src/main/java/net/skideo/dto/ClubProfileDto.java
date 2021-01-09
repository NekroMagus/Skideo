package net.skideo.dto;

import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.model.Club;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubProfileDto {

    private String titleClub;
    private String logoLink;

    public ClubProfileDto(ClubProfileProjection club) {
        this.titleClub=club.getInfoName();
        this.logoLink=club.getLogoLink();
    }

    public ClubProfileDto(Club club) {
        this.titleClub=club.getTitleClub();
        this.logoLink=club.getLogoLink();
    }

}
