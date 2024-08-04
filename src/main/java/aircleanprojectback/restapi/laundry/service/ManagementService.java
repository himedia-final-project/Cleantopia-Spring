package aircleanprojectback.restapi.laundry.service;

import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;
import aircleanprojectback.restapi.laundry.entity.WaterTank;
import aircleanprojectback.restapi.laundry.repository.LandryRepository;
import aircleanprojectback.restapi.laundry.repository.LaundryRepository;
import aircleanprojectback.restapi.water.dao.WaterSupplyRepository;
import aircleanprojectback.restapi.water.dao.WaterTankRepository;
import aircleanprojectback.restapi.water.dto.WaterSupplyDTO;
import aircleanprojectback.restapi.water.entity.WaterSupply;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagementService {

    private final LandryRepository landryRepository;
    private final WaterTankRepository waterTankRepository;
    private final ModelMapper modelMapper;
    private final WaterSupplyRepository waterSupplyRepository;
    private final LaundryRepository laundryRepository;


    public ManagementService(LandryRepository landryRepository, WaterTankRepository waterTankRepository, ModelMapper modelMapper, WaterSupplyRepository waterSupplyRepository, LaundryRepository laundryRepository) {
        this.landryRepository = landryRepository;
        this.waterTankRepository = waterTankRepository;
        this.modelMapper = modelMapper;
        this.waterSupplyRepository = waterSupplyRepository;
        this.laundryRepository = laundryRepository;
    }

    public List<WaterTankDTO> waterTankList() {
        List<WaterTank> waterTankList = landryRepository.findAll();

//        System.out.println("여기");
//        System.out.println(waterTankList);
        return waterTankList.stream()
                .map(waterTank -> modelMapper.map(waterTank, WaterTankDTO.class))
                .collect(Collectors.toList());
    }

    public List<WaterSupplyDTO> SelectWaterSupply(String branchCode) {

        // 브런치 코드로 물탱크 번호 조회
        String waterTankNo = waterTankRepository.selectWaterTankNo(branchCode);

        System.out.println(waterTankNo);
        System.out.println("물탱크 번호" + waterTankNo);

        List<WaterSupply> waterSupplyList = waterSupplyRepository.findByWaterTankNo(waterTankNo);

        return waterSupplyList.stream()
                .map(waterSupply -> modelMapper.map(waterSupply, WaterSupplyDTO.class))
                .collect(Collectors.toList());
    }

    public List<LaundryDTO> selectLaundry(String branchCode) {

        List<Laundry> selectLaundry = laundryRepository.findByBranchCode(branchCode);

        return selectLaundry.stream()
                .map(laundry -> modelMapper.map(laundry, LaundryDTO.class))
                .collect(Collectors.toList());
    }


    public boolean updateLaundryStatus(int laundryCode, String statusType, String statusValue) {
        Optional<Laundry> optionalLaundry = laundryRepository.findById((long) laundryCode);
        if (optionalLaundry.isPresent()) {
            Laundry laundry = optionalLaundry.get();

            Laundry updatedLaundry = null;

            switch (statusType) {
                case "laundryCollectionStatus":
                    updatedLaundry = laundry.toBuilder()
                            .laundryCollectionStatus(statusValue)
                            .build();
                    break;
                case "laundryArriveStatus":
                    updatedLaundry = laundry.toBuilder()
                            .laundryArriveStatus(statusValue)
                            .build();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid status type: " + statusType);
            }

            laundryRepository.save(updatedLaundry);
            return true;
        } else {
            return false;
        }
    }

}
