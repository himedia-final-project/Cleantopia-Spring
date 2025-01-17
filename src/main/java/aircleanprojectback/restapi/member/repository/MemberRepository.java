package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.Members;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Members, String> {
    Members findByMemberId(String memberId);

    List<Members> findByMemberNameAndMemberEmailAndMemberPhoneNumberAndMemberRole(String memberName, String memberEmail, String memberPhoneNumber, String memberRole);

    List<Members> findByMemberIdIn(List<String> deleteMember);

    @Query(value = "select member_id from tbl_members where member_role =?1 order by member_id desc limit 1",nativeQuery = true)
    String findLastMemberId(String memberRole);

    Page<Members> findAllByMemberRoleAndMemberStatus(String b, String n, Pageable pageable);

    List<Members> findAllByMemberRoleAndBranchOwnership(String b, String n);

    Members findByMemberIdAndMemberStatus(String memberId, String y);
}
