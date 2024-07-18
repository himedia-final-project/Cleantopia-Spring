package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_branch_stock_application")
@AllArgsConstructor
@Getter
@ToString
public class LocationCodeAndBranchStockApplication {

    @Id
    @Column(name = "b_application_code")
    private int bApplicationCode;

    @Column(name = "b_detergent")
    private int bDetergent;

    @Column(name = "b_softener")
    private int bSoftener;

    @Column(name = "b_bleach")
    private int bBleach;

    @Column(name = "b_remover")
    private int bRemover;

    @Column(name = "b_drum_cleaner")
    private int bDrumCleaner;

    @Column(name = "b_sheet")
    private int bSheet;

    @Column(name = "b_laundry_filter")
    private int bLaundryFilter;

    @Column(name = "b_dryer_filter")
    private int bDryerFilter;

    @Column(name = "b_dry_cleaner_filter")
    private int bDryCleanerFilter;

    @Column(name = "b_application_status")
    private String bApplicationStatus;

    @Column(name = "b_application_date")
    private LocalDate bApplicationDate;

    @Column(name = "b_approver_name")
    private String bApproverName;

    @Column(name = "b_approval_date")
    private LocalDate bApprovalDate;

    @ManyToOne
    @JoinColumn(name = "branch_code")
    private LocationCode locationCode;

    public LocationCodeAndBranchStockApplication() {}

    public LocationCodeAndBranchStockApplication bApplicationCode(int bApplicationCode) {
        this.bApplicationCode = bApplicationCode;
        return this;
    }

    public LocationCodeAndBranchStockApplication bDetergent(int bDetergent) {
        this.bDetergent = bDetergent;
        return this;
    }

    public LocationCodeAndBranchStockApplication bSoftener(int bSoftener) {
        this.bSoftener = bSoftener;
        return this;
    }

    public LocationCodeAndBranchStockApplication bBleach(int bBleach) {
        this.bBleach = bBleach;
        return this;
    }

    public LocationCodeAndBranchStockApplication bRemover(int bRemover) {
        this.bRemover = bRemover;
        return this;
    }

    public LocationCodeAndBranchStockApplication bDrumCleaner(int bDrumCleaner) {
        this.bDrumCleaner = bDrumCleaner;
        return this;
    }

    public LocationCodeAndBranchStockApplication bSheet(int bSheet) {
        this.bSheet = bSheet;
        return this;
    }

    public LocationCodeAndBranchStockApplication bLaundryFilter(int bLaundryFilter) {
        this.bLaundryFilter = bLaundryFilter;
        return this;
    }

    public LocationCodeAndBranchStockApplication bDryerFilter(int bDryerFilter) {
        this.bDryerFilter = bDryerFilter;
        return this;
    }

    public LocationCodeAndBranchStockApplication bDryCleanerFilter(int bDryCleanerFilter) {
        this.bDryCleanerFilter = bDryCleanerFilter;
        return this;
    }

    public LocationCodeAndBranchStockApplication bApplicationStatus(String bApplicationStatus) {
        this.bApplicationStatus = bApplicationStatus;
        return this;
    }

    public LocationCodeAndBranchStockApplication bApplicationDate(LocalDate bApplicationDate) {
        this.bApplicationDate = bApplicationDate;
        return this;
    }

    public LocationCodeAndBranchStockApplication bApproverName(String bApproverName) {
        this.bApproverName = bApproverName;
        return this;
    }

    public LocationCodeAndBranchStockApplication bApprovalDate(LocalDate bApprovalDate) {
        this.bApprovalDate = bApprovalDate;
        return this;
    }

    public LocationCodeAndBranchStockApplication locationCode(LocationCode locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    public LocationCodeAndBranchStockApplication build() {
        return new LocationCodeAndBranchStockApplication(bApplicationCode, bDetergent, bSoftener, bBleach,
                bRemover, bDrumCleaner, bSheet, bLaundryFilter, bDryerFilter, bDryCleanerFilter,
                bApplicationStatus, bApplicationDate, bApproverName, bApprovalDate, locationCode);
    }

}
