package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.Section;
import in.org.cris.cmm.rms.repo.SectionRepository;
import in.org.cris.cmm.rms.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository repository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public Section saveOrUpdate(Section section) {

        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        if (section.getSectionId() == null) {
            // New record
            section.setOrgCode(orgCode);
            section.setCreatedBy(username);
            section.setCreatedAt(new Date());
            return repository.save(section);
        } else {
            // Update existing
            Optional<Section> existingOpt = repository.findById(section.getSectionId());
            if (existingOpt.isPresent()) {
                Section existing = existingOpt.get();

                if (section.getSectionCode() != null) existing.setSectionCode(section.getSectionCode());
                if (section.getSectionName() != null) existing.setSectionName(section.getSectionName());
//                if (section.getMuId() != null) existing.setMuId(section.getMuId());
                if (section.getDescription() != null) existing.setDescription(section.getDescription());
                if (section.getCommissioningDate() != null) existing.setCommissioningDate(section.getCommissioningDate());
                if (section.getRemarks() != null) existing.setRemarks(section.getRemarks());
                if (section.getValidFlag() != null) existing.setValidFlag(section.getValidFlag());
                if (section.getCreatedBy() != null) existing.setCreatedBy(section.getCreatedBy());
                if (section.getUpdatedBy() != null) existing.setUpdatedBy(section.getUpdatedBy());

//                existing.setOrgCode(section.getOrgCode());
                existing.setUpdatedBy(username);
                existing.setUpdatedAt(new Date());
                return repository.save(existing);
            } else {
                throw new RuntimeException("Section not found with ID: " + section.getSectionId());
            }
        }
    }

    @Override
    public ApiResponse<List<Section>> getByOrgCode(String orgCode) {
        List<Section> list = repository.findByOrgCodeAndValidFlagTrueOrderBySectionIdAsc(orgCode);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<Section>> getById(Long id) {
        List<Section> list = repository.findBySectionIdAndValidFlagTrue(id);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    @Transactional
    public String deleteById(Long id) {

        Optional<Section> optionalSection = repository.findById(id);
        if (!optionalSection.isPresent()) {
            return "Section not found for id: " + id;
        }

        String username = authenticationFacade.getLoggedInUser().getUser_name();

        Section section = optionalSection.get();
        section.setValidFlag(false);
        section.setUpdatedAt(new Date());
        section.setUpdatedBy(username);

        repository.save(section);

        return "Section deleted successfully (validFlag set to false)";
    }

}
