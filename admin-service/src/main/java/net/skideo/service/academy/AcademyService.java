package net.skideo.service.academy;

import net.skideo.dto.AdminAcademyInfoDto;
import net.skideo.dto.base.SkideoListDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AcademyService {

    SkideoListDto<AdminAcademyInfoDto> findAllAcademies(int page, int size);

}