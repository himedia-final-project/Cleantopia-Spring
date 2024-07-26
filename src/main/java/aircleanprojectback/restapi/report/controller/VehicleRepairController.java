package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.car.dto.CarDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.report.dto.CarMembersDTO;
import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.service.CarMembersService;
import aircleanprojectback.restapi.report.service.VehicleRepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
public class VehicleRepairController {
    // 본사 차량 수리 보고서

    private final VehicleRepairService vehicleRepairService;
    private final CarMembersService service;
    public VehicleRepairController(VehicleRepairService vehicleRepairService, CarMembersService service) {
        this.vehicleRepairService = vehicleRepairService;
        this.service = service;
    }

    // 본사 차량보고서 전체조회
    @GetMapping("/company/newReports")
    public ResponseEntity<ResponseDTO> selectAllVehicleRepairs() {

        List<VehicleRepairDTO> vehicleRepair = vehicleRepairService.getAllVehicleRepair();

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리보고서 전체 조회성공", vehicleRepair));
    }

    // 차량수리비 세부조회
    @GetMapping("/company/detailVehicleRepair/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> selectVehicleRepairByVehicleReportCode(@PathVariable int vehicleReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리보고서 상세조회", vehicleRepairService.detailVehicleRepair(vehicleReportCode)));
    }

    // 본사 차량보고서 등록
    @PostMapping("/company/newReports")
    public ResponseEntity<ResponseDTO> newVehicleReports(@ModelAttribute VehicleRepairDTO vehicleRepairDTO,
                                                         @RequestParam MultipartFile beforeImage,
                                                         @RequestParam MultipartFile afterImage)
    {
        System.out.println("vehicleRepairDTO = " + vehicleRepairDTO);

        vehicleRepairService.insertVehicleRepair(vehicleRepairDTO,beforeImage,afterImage);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리비보고서 등록완료",vehicleRepairDTO ));

    }

    // 차량번호, 차량기사 조회(모달창에서의)
    @GetMapping("driver/numbers")
    public ResponseEntity<ResponseDTO> selectAllDriverNumbers() {

        List<CarMembersDTO> carMembersList = service.findCarMembers();
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"차량기사 번호",carMembersList));
    }

    // 차량수리비보고서 상태 - 승인
    @PutMapping("/company/reports/vehicleRepairApprove/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> updateVehicleRepairApprove(@PathVariable int vehicleReportCode){
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리비보고서 상태수정 승인완료", vehicleRepairService.updateVehicleRepairStatus(vehicleReportCode,"승인")));
    }

    // 차량수리비보고서 상태 - 반려
    @PutMapping("/company/reports/vehicleRepairReject/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> updateVehicleRepairReject(@PathVariable int vehicleReportCode){
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리비보고서 상태수정 반려완료", vehicleRepairService.updateVehicleRepairStatus(vehicleReportCode,"반려")));
    }



}
