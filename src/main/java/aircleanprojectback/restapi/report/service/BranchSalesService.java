package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.entity.BranchSales;
import aircleanprojectback.restapi.report.repository.BranchSalesRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BranchSalesService {

    private final BranchSalesRepository branchSalesRepository;
    private final ModelMapper modelMapper;

    public BranchSalesService(BranchSalesRepository branchSalesRepository, ModelMapper modelMapper) {
        this.branchSalesRepository = branchSalesRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String insertBranchSales(BranchSalesDTO branchSalesDTO) {

        BranchSales insertBranchSales = modelMapper.map(branchSalesDTO, BranchSales.class);
        branchSalesRepository.save(insertBranchSales);

        return "보고서 등록 성공";
    }


    @Transactional
    public String updateBranchSales(BranchSalesDTO branchSalesDTO, int branchReportCode) {

        BranchSales branchSales = branchSalesRepository.findById(branchReportCode)
                .orElseThrow(() -> new RuntimeException("보고서를 찾을수 없습니다. ID: " + branchReportCode));

        branchSales = branchSales
                .officeSales(branchSalesDTO.getOfficeSales())
                .detergent(branchSalesDTO.getDetergent())
                .fabricSoftener(branchSalesDTO.getFabricSoftener())
                .bleach(branchSalesDTO.getBleach())
                .stainRemover(branchSalesDTO.getStainRemover())
                .washerCleaner(branchSalesDTO.getWasherCleaner())
                .dryerSheet(branchSalesDTO.getDryerSheet());

        return "보고서 수정 성공";
    }

    public String deleteBranchSales(int branchReportCode) {

        branchSalesRepository.deleteById(branchReportCode);
        return "삭제 성공";
    }
}