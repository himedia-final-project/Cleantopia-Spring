package aircleanprojectback.restapi.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;
    private String searchValue;

    public Criteria(){
        this(1, 10);  // 1페이지, 10개 게시글
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

}
